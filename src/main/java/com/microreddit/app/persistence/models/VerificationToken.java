package com.microreddit.app.persistence.models;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

/**
 * Model stores tokens to verify user email addresses.
 *
 * @author Josh Harkema
 */
@Table("verification_tokens")
public class VerificationToken {
    private static final int EXPIRATION = 60 * 24;

    @PrimaryKeyColumn(name = "id", type = PrimaryKeyType.PARTITIONED)
    private UUID id;

    @Column("token")
    private String token;

    @Column("user_id")
    private UUID userID;

    @Column("expiry_date")
    private Date expiryDate;

    public VerificationToken() {
        super();
        this.id = UUID.randomUUID();
        this.expiryDate = calculateExpiryDate(EXPIRATION);
    }

    public VerificationToken(final String token, final User user) {
        super();
        this.id = UUID.randomUUID();
        this.token = token;
        this.userID = user.getKey().getId();
        this.expiryDate = calculateExpiryDate(EXPIRATION);
    }

    private Date calculateExpiryDate(final int expiryTimeInMinutes) {
        final Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(new Date().getTime());
        cal.add(Calendar.MINUTE, expiryTimeInMinutes);
        return new Date(cal.getTime().getTime());
    }

    public void updateToken(final String token) {
        this.token = token;
        this.expiryDate = calculateExpiryDate(EXPIRATION);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UUID getUserID() {
        return userID;
    }

    public void setUserID(UUID userID) {
        this.userID = userID;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VerificationToken that = (VerificationToken) o;

        if (!id.equals(that.id)) return false;
        if (token != null ? !token.equals(that.token) : that.token != null) return false;
        if (userID != null ? !userID.equals(that.userID) : that.userID != null) return false;
        return expiryDate != null ? expiryDate.equals(that.expiryDate) : that.expiryDate == null;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (token != null ? token.hashCode() : 0);
        result = 31 * result + (userID != null ? userID.hashCode() : 0);
        result = 31 * result + (expiryDate != null ? expiryDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "VerificationToken{" +
                "id=" + id +
                ", token='" + token + '\'' +
                ", userID=" + userID +
                ", expiryDate=" + expiryDate +
                '}';
    }
}
