package com.microreddit.app.persistence.repositories.comments;

import com.microreddit.app.persistence.models.Comments.Comment;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.data.cassandra.repository.query.CassandraEntityInformation;
import org.springframework.data.cassandra.repository.support.SimpleCassandraRepository;

import java.util.UUID;

/**
 * @author Josh Harkema
 */
public class CommentRepositoryImpl extends SimpleCassandraRepository<Comment, UUID> implements CommentRepository {
    private final CassandraTemplate cassandraTemplate;
    private final CommentByPostRepository commentByPostRepository;
    private final CommentByUserRepository commentByUserRepository;

    public CommentRepositoryImpl(CassandraEntityInformation<Comment, UUID> metadata,
                                 CassandraTemplate cassandraTemplate,
                                 CommentByPostRepository commentByPostRepository,
                                 CommentByUserRepository commentByUserRepository) {
        super(metadata, cassandraTemplate);
        this.cassandraTemplate = cassandraTemplate;
        this.commentByPostRepository = commentByPostRepository;
        this.commentByUserRepository = commentByUserRepository;
    }

}
