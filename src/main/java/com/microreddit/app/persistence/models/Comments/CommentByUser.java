package com.microreddit.app.persistence.models.Comments;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author Josh Harkema
 */
@Table("comment_by_user")
public class CommentByUser {
    @PrimaryKey
    private final CommentByUserKey key;
    @Column
    private final String timestamp;
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

    public CommentByUser(CommentByUserKey key, String username, UUID parentID, String text, int karma) {
        this.key = key;
        this.timestamp = new Timestamp(System.currentTimeMillis()).toString();
        this.username = username;
        this.parentID = parentID;
        this.text = text;
        this.karma = karma;
    }

    public void upVote() {
        this.karma += 1;
    }

    public void downVote() {
        if (this.karma >= -20) {
            this.karma -= 1;
        }
    }

    public CommentByUserKey getKey() {
        return key;
    }

    public String getTimestamp() {
        return timestamp;
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
    public String toString() {
        return "CommentByUser{" +
                "key=" + key +
                ", timestamp='" + timestamp + '\'' +
                ", username='" + username + '\'' +
                ", parentID=" + parentID +
                ", text='" + text + '\'' +
                ", karma=" + karma +
                ", children=" + children +
                '}';
    }

}
