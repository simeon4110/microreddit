package com.microreddit.app.persistence.repositories;

import com.microreddit.app.persistence.models.PasswordResetToken;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;

import java.util.Date;
import java.util.UUID;
import java.util.stream.Stream;

public interface PasswordResetTokenRepository extends CassandraRepository<PasswordResetToken, UUID> {
    @Query
    PasswordResetToken findByToken(String token);

    @Query
    PasswordResetToken findByUser(UUID user);

    @Query
    Stream<PasswordResetToken> findAllByExpiryDateBefore(Date now);

    @Query
    void deleteByExpiryDateAfter(Date now);

    @Query
    void deleteAllByExpiryDateAfter(Date now);
}
