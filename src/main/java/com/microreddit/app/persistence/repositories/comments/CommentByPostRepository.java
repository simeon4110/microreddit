package com.microreddit.app.persistence.repositories.comments;

import com.microreddit.app.persistence.models.Comments.CommentByPost;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * @author Josh Harkema
 */
@Repository
public interface CommentByPostRepository extends CassandraRepository<CommentByPost, UUID> {
}
