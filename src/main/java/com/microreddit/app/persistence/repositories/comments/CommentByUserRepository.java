package com.microreddit.app.persistence.repositories.comments;

import com.microreddit.app.persistence.models.Comments.CommentByUser;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.UUID;

/**
 * @author Josh Harkema
 */
@ResponseBody
public interface CommentByUserRepository extends CassandraRepository<CommentByUser, UUID> {
}
