package com.microreddit.app.persistence.repositories.posts.Sub;

import com.microreddit.app.persistence.models.Posts.Sub.PostBySubScore;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * @author Josh Harkema
 */
@Repository
public interface PostBySubScoreRepository extends CassandraRepository<PostBySubScore, UUID> {
    PostBySubScore findByKey_PostID(final UUID id);
}
