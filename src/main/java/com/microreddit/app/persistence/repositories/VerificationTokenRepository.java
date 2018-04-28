package com.microreddit.app.persistence.repositories;

import com.microreddit.app.persistence.models.VerificationToken;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;

import java.util.Date;
import java.util.UUID;
import java.util.stream.Stream;

public interface VerificationTokenRepository extends CassandraRepository<VerificationToken, UUID> {
    @Query(allowFiltering = true)
    VerificationToken findByToken(final String token);

    @Query(allowFiltering = true)
    VerificationToken findByUserID(final UUID userID);

    @Query(allowFiltering = true)
    Stream<VerificationToken> findAllByExpiryDateAfter(final Date now);

    void deleteByExpiryDateBefore(Date now);

    @Query("delete from VerificationToken t where t.expiryDate <= ?1")
    void deleteAllByExpiryDateBefore(Date now);
}
