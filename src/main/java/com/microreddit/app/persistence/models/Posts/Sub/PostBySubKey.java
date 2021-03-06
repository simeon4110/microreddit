package com.microreddit.app.persistence.models.Posts.Sub;

import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

import java.io.Serializable;
import java.util.UUID;

/**
 * Compound key auto sorts by score.
 *
 * @author Josh Harkema
 */
@PrimaryKeyClass
public class PostBySubKey implements Serializable {
    @PrimaryKeyColumn(name = "sub_id", type = PrimaryKeyType.PARTITIONED)
    private final UUID subID;
    @PrimaryKeyColumn(name = "post_id", ordinal = 0, ordering = Ordering.DESCENDING)
    private final UUID postID;

    public PostBySubKey(UUID subID, UUID postID) {
        this.subID = subID;
        this.postID = postID;
    }

    public UUID getSubID() {
        return subID;
    }

    public UUID getPostID() {
        return postID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PostBySubKey key = (PostBySubKey) o;

        if (subID != null ? !subID.equals(key.subID) : key.subID != null) return false;
        return postID != null ? postID.equals(key.postID) : key.postID == null;
    }

    @Override
    public int hashCode() {
        int result = subID != null ? subID.hashCode() : 0;
        result = 31 * result + (postID != null ? postID.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PostBySubKey{" +
                "subID=" + subID +
                ", postID=" + postID +
                '}';
    }

}
