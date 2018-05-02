package com.microreddit.app.persistence.repositories.Posts;

import com.microreddit.app.persistence.models.Posts.PostBySub;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * @author Josh Harkema
 */
@Repository
public interface PostBySubRepository extends CassandraRepository<PostBySub, UUID> {
    List<PostBySub> findBySubID(UUID subID);

    List<PostBySub> findBySubIDOrderByScore(UUID subID);
}
