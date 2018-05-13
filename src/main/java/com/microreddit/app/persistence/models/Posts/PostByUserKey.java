package com.microreddit.app.persistence.models.Posts;

import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

import java.io.Serializable;
import java.util.UUID;

/**
 * @author Josh Harkema
 */
@PrimaryKeyClass
public class PostByUserKey implements Serializable {
    @PrimaryKeyColumn(name = "user_id", type = PrimaryKeyType.PARTITIONED)
    private final UUID userID;
    @PrimaryKeyColumn(name = "post_id", ordering = Ordering.DESCENDING)
    private final UUID postID;

    public PostByUserKey(UUID userID, UUID postID) {
        this.userID = userID;
        this.postID = postID;
    }

    public UUID getUserID() {
        return userID;
    }

    public UUID getPostID() {
        return postID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PostByUserKey that = (PostByUserKey) o;

        if (userID != null ? !userID.equals(that.userID) : that.userID != null) return false;
        return postID != null ? postID.equals(that.postID) : that.postID == null;
    }

    @Override
    public int hashCode() {
        int result = userID != null ? userID.hashCode() : 0;
        result = 31 * result + (postID != null ? postID.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PostByUserKey{" +
                "userID=" + userID +
                ", postID=" + postID +
                '}';
    }

}
