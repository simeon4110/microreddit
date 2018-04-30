package com.microreddit.app.persistence.repositories;

import com.microreddit.app.persistence.models.Sub;
import com.microreddit.app.persistence.models.SubKey;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Repo for all sub data. Queries by compound key.
 *
 * @author Josh Harkema
 */
@Repository
public interface SubRepository extends CassandraRepository<Sub, SubKey> {
    @Query
    Sub findByKey_Id(final UUID id);

    @Query(allowFiltering = true)
    Sub findByKey_SubName(final String subName);
}
