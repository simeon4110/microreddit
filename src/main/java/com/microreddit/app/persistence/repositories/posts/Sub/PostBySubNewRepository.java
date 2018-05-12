package com.microreddit.app.persistence.repositories.posts.Sub;

import com.microreddit.app.persistence.models.Posts.Sub.PostBySubNew;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * @author Josh Harkema
 */
@Repository
public interface PostBySubNewRepository extends CassandraRepository<PostBySubNew, UUID> {
    PostBySubNew findByKey_PostID(final UUID id);
}
