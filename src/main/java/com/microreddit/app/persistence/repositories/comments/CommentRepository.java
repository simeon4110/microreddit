package com.microreddit.app.persistence.repositories.comments;

import com.microreddit.app.persistence.models.Comments.Comment;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.UUID;

/**
 * @author Josh Harkema
 */
@NoRepositoryBean
public interface CommentRepository extends CassandraRepository<Comment, UUID> {
}
