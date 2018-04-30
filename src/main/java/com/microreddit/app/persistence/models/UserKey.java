package com.microreddit.app.persistence.models;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

import java.io.Serializable;
import java.util.UUID;

/**
 * Key class implements cassandra primary key columns for fast user lookups.
 *
 * @author Josh Harkema
 */
@PrimaryKeyClass
public class UserKey implements Serializable {
    @PrimaryKeyColumn(name = "user_key", type = PrimaryKeyType.PARTITIONED)
    private final UUID id;

    @PrimaryKeyColumn(name = "username")
    private final String userName;

    /**
     * @param id       a unique UUID.
     * @param userName a unique username. :TODO: verify username's are unique.
     */
    public UserKey(final UUID id, final String userName) {
        this.id = id;
        this.userName = userName;
    }

    public UUID getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserKey userKey = (UserKey) o;

        if (!id.equals(userKey.id)) return false;
        return userName.equals(userKey.userName);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + userName.hashCode();
        return result;
    }

    /**
     * @return JSON formatted string of all user data.
     */
    @Override
    public String toString() {
        return "UserKey{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                '}';
    }
}
