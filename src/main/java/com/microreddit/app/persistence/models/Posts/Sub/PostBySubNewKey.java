package com.microreddit.app.persistence.models.Posts.Sub;

import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.UUID;

/**
 * Table ordered by new.
 *
 * @author Josh Harkema
 */
@PrimaryKeyClass
public class PostBySubNewKey implements Serializable {
    @PrimaryKeyColumn(name = "sub_id", type = PrimaryKeyType.PARTITIONED)
    private final UUID subID;
    @PrimaryKeyColumn(name = "timestamp", ordinal = 0, ordering = Ordering.DESCENDING)
    private final String time;
    @PrimaryKeyColumn(name = "post_id", ordinal = 1, ordering = Ordering.DESCENDING)
    private final UUID postID;

    public PostBySubNewKey(UUID subID, UUID postID) {
        this.subID = subID;
        this.time = new Timestamp(System.currentTimeMillis()).toString();
        this.postID = postID;
    }

    public UUID getSubID() {
        return subID;
    }

    public String getTime() {
        return time;
    }

    public UUID getPostID() {
        return postID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PostBySubNewKey that = (PostBySubNewKey) o;

        if (subID != null ? !subID.equals(that.subID) : that.subID != null) return false;
        if (time != null ? !time.equals(that.time) : that.time != null) return false;
        return postID != null ? postID.equals(that.postID) : that.postID == null;
    }

    @Override
    public int hashCode() {
        int result = subID != null ? subID.hashCode() : 0;
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (postID != null ? postID.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PostBySubNewKey{" +
                "subID=" + subID +
                ", time=" + time +
                ", postID=" + postID +
                '}';
    }

}

