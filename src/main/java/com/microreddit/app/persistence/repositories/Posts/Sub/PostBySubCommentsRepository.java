package com.microreddit.app.persistence.repositories.Posts.Sub;

import com.microreddit.app.persistence.models.Posts.Sub.PostBySubComments;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * @author Josh Harkema
 */
@Repository
public interface PostBySubCommentsRepository extends CassandraRepository<PostBySubComments, UUID> {
    PostBySubComments findByKey_PostID(final UUID id);
}
