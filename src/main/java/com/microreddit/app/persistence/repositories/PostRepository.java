package com.microreddit.app.persistence.repositories;

import com.microreddit.app.persistence.models.Post;
import com.microreddit.app.persistence.models.PostKey;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * Repo for all post data. Allows query by compound key.
 *
 * @author Josh Harkema
 */
@Repository
public interface PostRepository extends CassandraRepository<Post, PostKey> {
    @Query(allowFiltering = true)
    Post findByKey_Id(final UUID id);

    @Query(allowFiltering = true)
    List<Post> findByKey_UserID(final UUID userID);

    @Query(allowFiltering = true)
    List<Post> findByKey_SubID(final UUID subID);

    @Query(allowFiltering = true)
    Post findByKey_TimeStamp(final String timeStamp);

}
