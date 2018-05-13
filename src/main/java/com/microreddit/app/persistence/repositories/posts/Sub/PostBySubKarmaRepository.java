package com.microreddit.app.persistence.repositories.posts.Sub;

import com.microreddit.app.persistence.models.Posts.Sub.PostBySubKarma;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * @author Josh Harkema
 */
@Repository
public interface PostBySubKarmaRepository extends CassandraRepository<PostBySubKarma, UUID> {
    @AllowFiltering
    PostBySubKarma findByKey_PostID(final UUID id);

    List<PostBySubKarma> findByKey_SubID(final UUID id);
}
