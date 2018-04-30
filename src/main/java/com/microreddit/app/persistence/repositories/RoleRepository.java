package com.microreddit.app.persistence.repositories;

import com.microreddit.app.persistence.models.Role;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Repo for getting custom role objects.
 *
 * @author Josh Harkema
 */
@Repository
public interface RoleRepository extends CassandraRepository<Role, UUID> {
    @Query(allowFiltering = true)
    Role findByName(final String role);

}
