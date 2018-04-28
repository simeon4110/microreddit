package com.microreddit.app.persistence.repositories;

import com.microreddit.app.persistence.models.User;
import com.microreddit.app.persistence.models.UserKey;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Repo for grabbing user objects.
 *
 * @author Josh Harkema
 */
@Repository
public interface UserRepository extends CassandraRepository<User, UserKey> {
    @Query(allowFiltering = true)
    User findByKey_UserName(final String Username);

    @Query(allowFiltering = true)
    User findByKey_Id(final UUID userID);

    @Query(allowFiltering = true)
    User findByEmail(String email);

}
