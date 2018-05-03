package com.microreddit.app.persistence.models.Posts.Sub;

import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

import java.io.Serializable;
import java.util.UUID;

/**
 * Table ordered by karma.
 *
 * @author Josh Harkema
 */
@PrimaryKeyClass
public class PostBySubKarmaKey implements Serializable {
    @PrimaryKeyColumn(name = "sub_id", type = PrimaryKeyType.PARTITIONED)
    private final UUID subID;
    @PrimaryKeyColumn(name = "post_id", ordinal = 1, ordering = Ordering.DESCENDING)
    private final UUID postID;
    @PrimaryKeyColumn(name = "karma", ordinal = 0, ordering = Ordering.DESCENDING)
    private int karma;

    public PostBySubKarmaKey(UUID subID, UUID postID) {
        this.subID = subID;
        this.postID = postID;
    }

    public UUID getSubID() {
        return subID;
    }

    public int getKarma() {
        return karma;
    }

    public void setKarma(int karma) {
        this.karma = karma;
    }

    public UUID getPostID() {
        return postID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PostBySubKarmaKey that = (PostBySubKarmaKey) o;

        if (karma != that.karma) return false;
        if (subID != null ? !subID.equals(that.subID) : that.subID != null) return false;
        return postID != null ? postID.equals(that.postID) : that.postID == null;
    }

    @Override
    public int hashCode() {
        int result = subID != null ? subID.hashCode() : 0;
        result = 31 * result + karma;
        result = 31 * result + (postID != null ? postID.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PostBySubKarmaKey{" +
                "subID=" + subID +
                ", karma=" + karma +
                ", postID=" + postID +
                '}';
    }
}
