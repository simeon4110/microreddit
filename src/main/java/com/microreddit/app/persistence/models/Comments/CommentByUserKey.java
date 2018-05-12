package com.microreddit.app.persistence.models.Comments;

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
public class CommentByUserKey implements Serializable {
    @PrimaryKeyColumn(name = "user_id", type = PrimaryKeyType.PARTITIONED)
    private final UUID userID;
    @PrimaryKeyColumn(name = "comment_id", ordering = Ordering.DESCENDING)
    private final UUID commentID;

    public CommentByUserKey(UUID userID, UUID commentID) {
        this.userID = userID;
        this.commentID = commentID;
    }

    public UUID getUserID() {
        return userID;
    }

    public UUID getCommentID() {
        return commentID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CommentByUserKey that = (CommentByUserKey) o;

        if (userID != null ? !userID.equals(that.userID) : that.userID != null) return false;
        return commentID != null ? commentID.equals(that.commentID) : that.commentID == null;
    }

    @Override
    public int hashCode() {
        int result = userID != null ? userID.hashCode() : 0;
        result = 31 * result + (commentID != null ? commentID.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CommentByUserKey{" +
                "userID=" + userID +
                ", commentID=" + commentID +
                '}';
    }

}
