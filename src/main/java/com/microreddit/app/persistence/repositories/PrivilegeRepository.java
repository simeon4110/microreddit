package com.microreddit.app.persistence.repositories;

import com.microreddit.app.persistence.models.Privilege;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Repo for getting privilege objects.
 *
 * @author Josh Harkema
 */
@Repository
public interface PrivilegeRepository extends CassandraRepository<Privilege, UUID> {
    @Query(allowFiltering = true)
    Privilege findByName(final String name);

}
