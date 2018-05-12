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
public class CommentByPostKey implements Serializable {
    @PrimaryKeyColumn(name = "post_id", type = PrimaryKeyType.PARTITIONED)
    private final UUID postID;
    @PrimaryKeyColumn(name = "comment_id", ordering = Ordering.DESCENDING)
    private final UUID commentID;

    public CommentByPostKey(UUID postID, UUID commentID) {
        this.postID = postID;
        this.commentID = commentID;
    }

    public UUID getPostID() {
        return postID;
    }

    public UUID getCommentID() {
        return commentID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CommentByPostKey that = (CommentByPostKey) o;

        if (postID != null ? !postID.equals(that.postID) : that.postID != null) return false;
        return commentID != null ? commentID.equals(that.commentID) : that.commentID == null;
    }

    @Override
    public int hashCode() {
        int result = postID != null ? postID.hashCode() : 0;
        result = 31 * result + (commentID != null ? commentID.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CommentByPostKey{" +
                "postID=" + postID +
                ", commentID=" + commentID +
                '}';
    }

}
