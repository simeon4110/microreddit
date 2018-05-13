package com.microreddit.app.persistence.repositories.posts;

import com.microreddit.app.persistence.models.Posts.Post;
import com.microreddit.app.persistence.models.Posts.PostByUser;
import com.microreddit.app.persistence.models.Posts.PostByUserKey;
import com.microreddit.app.persistence.models.Posts.Sub.*;
import com.microreddit.app.persistence.repositories.posts.Sub.PostBySubCommentsRepository;
import com.microreddit.app.persistence.repositories.posts.Sub.PostBySubKarmaRepository;
import com.microreddit.app.persistence.repositories.posts.Sub.PostBySubRepository;
import com.microreddit.app.persistence.repositories.posts.Sub.PostBySubScoreRepository;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.data.cassandra.repository.query.CassandraEntityInformation;
import org.springframework.data.cassandra.repository.support.SimpleCassandraRepository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author Josh Harkema
 */
public class PostRepositoryImpl extends SimpleCassandraRepository<Post, UUID> implements PostRepository {
    private final PostBySubRepository postBySubRepository;
    private final PostBySubCommentsRepository postBySubCommentsRepository;
    private final PostBySubKarmaRepository postBySubKarmaRepository;
    private final PostBySubScoreRepository postBySubScoreRepository;
    private final PostByUserRepository postByUserRepository;

    public PostRepositoryImpl(CassandraEntityInformation<Post, UUID> metadata, CassandraTemplate cassandraTemplate,
                              PostBySubRepository postBySubRepository,
                              PostBySubCommentsRepository postBySubCommentsRepository,
                              PostBySubKarmaRepository postBySubKarmaRepository,
                              PostBySubScoreRepository postBySubScoreRepository,
                              PostByUserRepository postByUserRepository) {
        super(metadata, cassandraTemplate);
        this.postBySubRepository = postBySubRepository;
        this.postBySubCommentsRepository = postBySubCommentsRepository;
        this.postBySubKarmaRepository = postBySubKarmaRepository;
        this.postBySubScoreRepository = postBySubScoreRepository;
        this.postByUserRepository = postByUserRepository;
    }

    @Override
    public <S extends Post> S insert(final S post) {
        insertBySub(post);
        insertBySubComments(post);
        insertBySubKarma(post);
        insertBySubScore(post);
        insertByUser(post);
        super.insert(post);
        return post;

    }

    @Override
    public Post findByPostIDEquals(final UUID id) {
        Optional<Post> post = super.findById(id);
        return post.orElse(null);
    }


    @Override
    public void delete(final Post post) {
        deleteBySub(post);
        deleteBySubComments(post);
        deleteBySubKarma(post);
        deleteBySubScore(post);
        deleteByUser(post);
        super.delete(post);
    }

    @Override
    public List<Post> findTodayPosts() {
        List<Post> posts = new ArrayList<>();
        for (Post p : findAll()) {
            if (Timestamp.valueOf(p.getTimestamp()).after(new Timestamp(System.currentTimeMillis() - 60 * 60 * 24 * 1000))) {
                posts.add(p);
                System.out.println("BOO");
            }
        }

        return posts;
    }

    @Override
    public void deleteAll(final Iterable<? extends Post> posts) {
        posts.forEach(this::delete);
    }

    @Override
    public void deleteAll() {
        super.findAll().forEach(this::delete);
    }

    @Override
    public <S extends Post> S save(final S post) {
        return insert(post);
    }

    @Override
    public <S extends Post> List<S> saveAll(final Iterable<S> posts) {
        return insert(posts);
    }

    private void insertBySub(Post post) {
        PostBySubKey key = new PostBySubKey(post.getSubID(), post.getPostID());
        postBySubRepository.insert(new PostBySub(
                key, post.getUsername(), post.getSubName(), post.getScore(), post.getKarma(), post.getTitle(),
                post.getText(), post.getText(), post.getNumComments()
        ));
    }

    private void insertBySubComments(Post post) {
        PostBySubCommentsKey key = new PostBySubCommentsKey(post.getSubID(), post.getPostID());
        key.setNumComments(post.getNumComments());
        postBySubCommentsRepository.insert(new PostBySubComments(
                key, post.getUsername(), post.getSubName(), post.getScore(), post.getKarma(), post.getTitle(),
                post.getText(), post.getType()
        ));
    }

    private void insertBySubKarma(Post post) {
        PostBySubKarmaKey key = new PostBySubKarmaKey(post.getSubID(), post.getPostID());
        key.setKarma(post.getKarma());
        postBySubKarmaRepository.insert(new PostBySubKarma(
                key, post.getUsername(), post.getSubName(), post.getScore(), post.getTitle(), post.getText(),
                post.getType(), post.getNumComments()
        ));
    }

    private void insertBySubScore(Post post) {
        PostBySubScoreKey key = new PostBySubScoreKey(post.getSubID(), post.getPostID());
        key.setScore(post.getScore());
        postBySubScoreRepository.insert(new PostBySubScore(
                key, post.getUsername(), post.getSubName(), post.getKarma(), post.getTitle(), post.getText(),
                post.getType(), post.getNumComments()
        ));
    }

    private void insertByUser(Post post) {
        PostByUserKey key = new PostByUserKey(post.getUserID(), post.getPostID());
        postByUserRepository.insert(new PostByUser(
                key, post.getUsername(), post.getSubName(), post.getTitle(), post.getType(), post.getScore(),
                post.getKarma(), post.getText(), post.getNumComments()
        ));
    }

    private void deleteBySub(final Post post) {
        postBySubRepository.delete(postBySubRepository.findByKey_PostID(post.getPostID()));
    }

    private void deleteBySubComments(final Post post) {
        postBySubCommentsRepository.delete(postBySubCommentsRepository.findByKey_PostID(post.getPostID()));
    }

    private void deleteBySubKarma(final Post post) {
        postBySubKarmaRepository.delete(postBySubKarmaRepository.findByKey_PostID(post.getPostID()));
    }

    private void deleteBySubScore(final Post post) {
        postBySubScoreRepository.delete(postBySubScoreRepository.findByKey_PostID(post.getPostID()));
    }

    private void deleteByUser(final Post post) {
        postByUserRepository.delete(postByUserRepository.findByKey_PostID(post.getPostID()));
    }

}
