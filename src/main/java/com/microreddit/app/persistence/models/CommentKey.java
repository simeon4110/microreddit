package com.microreddit.app.persistence.models;

import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.UUID;

/**
 * Key class implements cassandra primary key columns for fast comment lookups.
 *
 * @author Josh Harkema
 */
@PrimaryKeyClass
public class CommentKey implements Serializable {
    @PrimaryKeyColumn(name = "comment_key", type = PrimaryKeyType.PARTITIONED)
    private final UUID id;
    @PrimaryKeyColumn(name = "post_id")
    private final UUID postID;
    @PrimaryKeyColumn(name = "user_id")
    private final UUID userID;
    @PrimaryKeyColumn(name = "comment_parent")
    private final UUID commentParent;
    @PrimaryKeyColumn(name = "comment_date", ordering = Ordering.DESCENDING)
    private final String timeStamp;

    /**
     * @param postID          the UUID of the post to add comment to.
     * @param userID          the UUID of the user posting the comment.
     * @param parentCommentID the parent comment's UUID (UUID(0, 0) if comment is TL).
     */
    public CommentKey(final UUID postID, final UUID userID, final UUID parentCommentID) {
        this.id = UUID.randomUUID();
        this.postID = postID;
        this.userID = userID;
        this.commentParent = parentCommentID;
        this.timeStamp = new Timestamp(System.currentTimeMillis()).toString();
    }

    public UUID getId() {
        return id;
    }

    public UUID getPostID() {
        return postID;
    }

    public UUID getUserID() {
        return userID;
    }

    public UUID getCommentParent() {
        return commentParent;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CommentKey that = (CommentKey) o;

        if (!id.equals(that.id)) return false;
        if (!postID.equals(that.postID)) return false;
        if (!userID.equals(that.userID)) return false;
        if (!commentParent.equals(that.commentParent)) return false;
        return timeStamp.equals(that.timeStamp);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + postID.hashCode();
        result = 31 * result + userID.hashCode();
        result = 31 * result + commentParent.hashCode();
        result = 31 * result + timeStamp.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "CommentKey{" +
                "id=" + id +
                ", postID=" + postID +
                ", userID=" + userID +
                ", commentParent=" + commentParent +
                ", timeStamp='" + timeStamp + '\'' +
                '}';
    }

}
