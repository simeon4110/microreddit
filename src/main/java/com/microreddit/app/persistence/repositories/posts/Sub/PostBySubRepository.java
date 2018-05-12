package com.microreddit.app.persistence.repositories.posts.Sub;

import com.microreddit.app.persistence.models.Posts.Sub.PostBySub;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * Queries the Post by sub table.
 *
 * @author Josh Harkema
 */
@Repository
public interface PostBySubRepository extends CassandraRepository<PostBySub, UUID> {
    PostBySub findByKey_PostID(final UUID id);

    List<PostBySub> findByKey_SubID(final UUID subID);

    Slice<PostBySub> findByKey_SubID(final UUID uuid, Pageable pageable);
}
