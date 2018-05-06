package com.microreddit.app.persistence.models.Comments;

import com.datastax.driver.core.utils.UUIDs;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;

import java.io.Serializable;
import java.util.UUID;

/**
 * Model stores comment data. Query managed in CommentKey.class.
 *
 * @author Josh Harkema
 */
@UserDefinedType
@Table("comments")
public class Comment implements Serializable {
    @PrimaryKey("id")
    private final UUID key;
    @Column("comment_user_id")
    private UUID userID;
    @Column("comment_user")
    private String username;
    @Column("comment_parent")
    private UUID parentID;
    @Column("comment_text")
    private String text;
    @Column("comment_karma")
    private int karma;

    public Comment() {
        this.key = UUIDs.timeBased();
        this.karma = 0;
    }

    public void upVote() {
        this.karma += 1;
    }

    public void downVote() {
        if (this.karma >= -20) {
            this.karma -= 1;
        }
    }

    public UUID getKey() {
        return key;
    }

    public UUID getUserID() {
        return userID;
    }

    public void setUserID(UUID userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UUID getParentID() {
        return parentID;
    }

    public void setParentID(UUID parentID) {
        this.parentID = parentID;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getKarma() {
        return karma;
    }

    public void setKarma(int karma) {
        this.karma = karma;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Comment comment = (Comment) o;

        if (karma != comment.karma) return false;
        if (key != null ? !key.equals(comment.key) : comment.key != null) return false;
        if (userID != null ? !userID.equals(comment.userID) : comment.userID != null) return false;
        if (username != null ? !username.equals(comment.username) : comment.username != null) return false;
        if (parentID != null ? !parentID.equals(comment.parentID) : comment.parentID != null) return false;
        return text != null ? text.equals(comment.text) : comment.text == null;
    }

    @Override
    public int hashCode() {
        int result = key != null ? key.hashCode() : 0;
        result = 31 * result + (userID != null ? userID.hashCode() : 0);
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (parentID != null ? parentID.hashCode() : 0);
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + karma;
        return result;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "key=" + key +
                ", userID=" + userID +
                ", username='" + username + '\'' +
                ", parentID=" + parentID +
                ", text='" + text + '\'' +
                ", karma=" + karma +
                '}';
    }

}
