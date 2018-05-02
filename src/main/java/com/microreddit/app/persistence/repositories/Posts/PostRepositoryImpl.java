package com.microreddit.app.persistence.repositories.Posts;

import com.microreddit.app.persistence.models.Posts.Post;
import com.microreddit.app.persistence.models.Posts.PostBySub;
import org.springframework.data.cassandra.core.CassandraBatchOperations;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.data.cassandra.repository.query.CassandraEntityInformation;
import org.springframework.data.cassandra.repository.support.SimpleCassandraRepository;

import java.util.UUID;

/**
 * @author Josh Harkema
 */
public class PostRepositoryImpl extends SimpleCassandraRepository<Post, UUID> implements PostRepository {
    private final CassandraTemplate cassandraTemplate;
    private final PostBySubRepository postBySubRepository;

    public PostRepositoryImpl(final CassandraEntityInformation<Post, UUID> metadata,
                              final CassandraTemplate cassandraTemplate,
                              final PostBySubRepository postBySubRepository) {
        super(metadata, cassandraTemplate);

        this.cassandraTemplate = cassandraTemplate;
        this.postBySubRepository = postBySubRepository;
    }

    @Override
    public <S extends Post> S insert(final S post) {
        post.setId(UUID.randomUUID());
        final CassandraBatchOperations batchOperations = cassandraTemplate.batchOps();
        insertBySub(post, batchOperations);
        batchOperations.insert(post);
        batchOperations.execute();
        return post;

    }

    private void insertBySub(final Post post, final CassandraBatchOperations batchOperations) {
        batchOperations.insert(new PostBySub(post.getSubID(), post.getUserID(), post.getUsername(), post.getSubName(),
                post.getScore(), post.getKarma(), post.getTitle(), post.getText(), post.getText()));
        System.out.println("yay");
    }

}
