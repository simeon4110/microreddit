package com.microreddit.app.persistence.repositories.Posts;

import com.microreddit.app.persistence.models.Posts.Post;
import com.microreddit.app.persistence.models.Posts.Sub.*;
import com.microreddit.app.persistence.repositories.Posts.Sub.*;
import org.springframework.data.cassandra.core.CassandraBatchOperations;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.data.cassandra.repository.query.CassandraEntityInformation;
import org.springframework.data.cassandra.repository.support.SimpleCassandraRepository;

import java.util.List;
import java.util.UUID;

/**
 * @author Josh Harkema
 */
public class PostRepositoryImpl extends SimpleCassandraRepository<Post, UUID> implements PostRepository {
    private final CassandraTemplate cassandraTemplate;
    private final PostBySubRepository postBySubRepository;
    private final PostBySubCommentsRepository postBySubCommentsRepository;
    private final PostBySubKarmaRepository postBySubKarmaRepository;
    private final PostBySubNewRepository postBySubNewRepository;
    private final PostBySubScoreRepository postBySubScoreRepository;

    public PostRepositoryImpl(CassandraEntityInformation<Post, UUID> metadata, CassandraTemplate cassandraTemplate,
                              PostBySubRepository postBySubRepository,
                              PostBySubCommentsRepository postBySubCommentsRepository,
                              PostBySubKarmaRepository postBySubKarmaRepository,
                              PostBySubNewRepository postBySubNewRepository,
                              PostBySubScoreRepository postBySubScoreRepository) {
        super(metadata, cassandraTemplate);
        this.cassandraTemplate = cassandraTemplate;
        this.postBySubRepository = postBySubRepository;
        this.postBySubCommentsRepository = postBySubCommentsRepository;
        this.postBySubKarmaRepository = postBySubKarmaRepository;
        this.postBySubNewRepository = postBySubNewRepository;
        this.postBySubScoreRepository = postBySubScoreRepository;
    }

    @Override
    public <S extends Post> S insert(final S post) {
        final CassandraBatchOperations batchOperations = cassandraTemplate.batchOps();

        insertBySub(post, batchOperations);
        insertBySubComments(post, batchOperations);
        insertBySubKarma(post, batchOperations);
        insertBySubNew(post, batchOperations);
        insertBySubScore(post, batchOperations);
        batchOperations.insert(post);
        batchOperations.execute();
        return post;

    }

    @Override
    public void delete(final Post post) {
        final CassandraBatchOperations batchOperations = cassandraTemplate.batchOps();
        deleteBySub(post, batchOperations);
        deleteBySubComments(post, batchOperations);
        deleteBySubKarma(post, batchOperations);
        deleteBySubNew(post, batchOperations);
        deleteBySubScore(post, batchOperations);
        batchOperations.delete(post);
        batchOperations.execute();
    }

    @Override
    public void deleteAll(final Iterable<? extends Post> posts) {
        posts.forEach(this::delete);
    }

    @Override
    public <S extends Post> S save(final S post) {
        return insert(post);
    }

    @Override
    public <S extends Post> List<S> saveAll(final Iterable<S> posts) {
        return insert(posts);
    }

    private void insertBySub(Post post, CassandraBatchOperations batchOperations) {
        PostBySubKey key = new PostBySubKey(post.getSubID(), post.getPostID());
        batchOperations.insert(new PostBySub(
                key, post.getUsername(), post.getSubName(), post.getScore(), post.getKarma(), post.getTitle(),
                post.getText(), post.getText(), post.getNumComments()
        ));
    }

    private void insertBySubComments(Post post, CassandraBatchOperations batchOperations) {
        PostBySubCommentsKey key = new PostBySubCommentsKey(post.getSubID(), post.getPostID());
        key.setNumComments(post.getNumComments());
        batchOperations.insert(new PostBySubComments(
                key, post.getUsername(), post.getSubName(), post.getScore(), post.getKarma(), post.getTitle(),
                post.getText(), post.getType()
        ));
    }

    private void insertBySubKarma(Post post, CassandraBatchOperations batchOperations) {
        PostBySubKarmaKey key = new PostBySubKarmaKey(post.getSubID(), post.getPostID());
        key.setKarma(post.getKarma());
        batchOperations.insert(new PostBySubKarma(
                key, post.getUsername(), post.getSubName(), post.getScore(), post.getTitle(), post.getText(),
                post.getType(), post.getNumComments()
        ));
    }

    private void insertBySubNew(Post post, CassandraBatchOperations batchOperations) {
        PostBySubNewKey key = new PostBySubNewKey(post.getSubID(), post.getPostID());
        batchOperations.insert(new PostBySubNew(
                key, post.getUsername(), post.getSubName(), post.getScore(), post.getKarma(), post.getTitle(),
                post.getText(), post.getType(), post.getNumComments()
        ));
    }

    private void insertBySubScore(Post post, CassandraBatchOperations batchOperations) {
        PostBySubScoreKey key = new PostBySubScoreKey(post.getSubID(), post.getPostID());
        key.setScore(post.getScore());
        batchOperations.insert(new PostBySubScore(
                key, post.getUsername(), post.getSubName(), post.getKarma(), post.getTitle(), post.getText(),
                post.getType(), post.getNumComments()
        ));
    }

    private void deleteBySub(final Post post, final CassandraBatchOperations batchOperations) {
        batchOperations.delete(postBySubRepository.findByKey_PostID(post.getPostID()));
    }

    private void deleteBySubComments(final Post post, final CassandraBatchOperations batchOperations) {
        batchOperations.delete(postBySubCommentsRepository.findByKey_PostID(post.getPostID()));
    }

    private void deleteBySubKarma(final Post post, final CassandraBatchOperations batchOperations) {
        batchOperations.delete(postBySubKarmaRepository.findByKey_PostID(post.getPostID()));
    }

    private void deleteBySubNew(final Post post, final CassandraBatchOperations batchOperations) {
        batchOperations.delete(postBySubNewRepository.findByKey_PostID(post.getPostID()));
    }

    private void deleteBySubScore(final Post post, final CassandraBatchOperations batchOperations) {
        batchOperations.delete(postBySubScoreRepository.findByKey_PostID(post.getPostID()));
    }

}
