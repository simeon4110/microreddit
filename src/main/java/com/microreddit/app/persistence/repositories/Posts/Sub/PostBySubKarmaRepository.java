package com.microreddit.app.persistence.repositories.Posts.Sub;

import com.microreddit.app.persistence.models.Posts.Sub.PostBySubKarma;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * @author Josh Harkema
 */
@Repository
public interface PostBySubKarmaRepository extends CassandraRepository<PostBySubKarma, UUID> {
    PostBySubKarma findByKey_PostID(final UUID id);
}
