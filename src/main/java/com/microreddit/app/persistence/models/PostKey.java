package com.microreddit.app.persistence.models;

import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.UUID;

/**
 * Key class implements cassandra primary key columns for fast post lookups.
 *
 * @author Josh Harkema
 */
@PrimaryKeyClass
public class PostKey implements Serializable {
    @PrimaryKeyColumn(name = "post_id", type = PrimaryKeyType.PARTITIONED)
    private final UUID id;
    @PrimaryKeyColumn(name = "user_id")
    private final UUID userID;
    @PrimaryKeyColumn(name = "sub_id")
    private final UUID subID;
    @PrimaryKeyColumn(name = "post_date", ordering = Ordering.DESCENDING)
    private final String timeStamp;

    /**
     * @param userID the UUID of the user submitting the post.
     * @param subID  the UUID of the sub the post is submitted to.
     */
    public PostKey(final UUID userID, final UUID subID) {
        this.id = UUID.randomUUID();
        this.userID = userID;
        this.subID = subID;
        this.timeStamp = new Timestamp(System.currentTimeMillis()).toString();
    }

    public UUID getId() {
        return this.id;
    }

    public UUID getUserID() {
        return this.userID;
    }

    public UUID getSubID() {
        return this.subID;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PostKey key = (PostKey) o;

        if (!id.equals(key.id)) return false;
        if (!userID.equals(key.userID)) return false;
        if (!subID.equals(key.subID)) return false;
        return timeStamp.equals(key.timeStamp);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + userID.hashCode();
        result = 31 * result + subID.hashCode();
        result = 31 * result + timeStamp.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "PostKey{" +
                "id=" + id +
                ", userID=" + userID +
                ", subID=" + subID +
                ", timeStamp='" + timeStamp + '\'' +
                '}';
    }

}
