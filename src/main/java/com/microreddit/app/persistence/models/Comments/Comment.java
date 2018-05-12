package com.microreddit.app.persistence.models.Comments;

import com.datastax.driver.core.utils.UUIDs;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Model stores comment info. All comments have posts, thus you must create a comment object with a postID.
 *
 * @author Josh Harkema
 */
@UserDefinedType
@Table("comments")
public class Comment implements Serializable {
    @PrimaryKey
    private final UUID id;
    @Column
    private final UUID postID;
    @Column
    private final String timestamp;
    @Column
    private UUID userID;
    @Column
    private String username;
    @Column
    private UUID parentID;
    @Column
    private String text;
    @Column
    private int karma;
    @Column
    private List<Comment> children;

    public Comment(UUID postID) {
        this.id = UUIDs.timeBased();
        this.postID = postID;
        this.timestamp = new Timestamp(System.currentTimeMillis()).toString();
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

    public UUID getId() {
        return id;
    }

    public UUID getPostID() {
        return postID;
    }

    public String getTimestamp() {
        return timestamp;
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

    public List<Comment> getChildren() {
        return children;
    }

    public void setChildren(List<Comment> children) {
        if (this.children == null) {
            this.children = new ArrayList<>();
        }

        this.children = children;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Comment comment = (Comment) o;

        if (karma != comment.karma) return false;
        if (id != null ? !id.equals(comment.id) : comment.id != null) return false;
        if (postID != null ? !postID.equals(comment.postID) : comment.postID != null) return false;
        if (timestamp != null ? !timestamp.equals(comment.timestamp) : comment.timestamp != null) return false;
        if (userID != null ? !userID.equals(comment.userID) : comment.userID != null) return false;
        if (username != null ? !username.equals(comment.username) : comment.username != null) return false;
        if (parentID != null ? !parentID.equals(comment.parentID) : comment.parentID != null) return false;
        if (text != null ? !text.equals(comment.text) : comment.text != null) return false;
        return children != null ? children.equals(comment.children) : comment.children == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (postID != null ? postID.hashCode() : 0);
        result = 31 * result + (timestamp != null ? timestamp.hashCode() : 0);
        result = 31 * result + (userID != null ? userID.hashCode() : 0);
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (parentID != null ? parentID.hashCode() : 0);
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + karma;
        result = 31 * result + (children != null ? children.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", postID=" + postID +
                ", timestamp='" + timestamp + '\'' +
                ", userID=" + userID +
                ", username='" + username + '\'' +
                ", parentID=" + parentID +
                ", text='" + text + '\'' +
                ", karma=" + karma +
                ", children=" + children +
                '}';
    }

}
