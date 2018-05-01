package com.microreddit.app.persistence.models;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

/**
 * Model stores comment data. Query managed in CommentKey.class.
 *
 * @author Josh Harkema
 */
@Table("comments")
public class Comment {
    @Column("comment_user")
    private String userName;
    @PrimaryKey
    private CommentKey key;
    @Column("comment_text")
    private String text;
    @Column("comment_karma")
    private int karma;

    /**
     * @param key      unique CommentKey.class object.
     */
    public Comment(final CommentKey key) {
        this.key = key;
        this.karma = 0;
    }

    /**
     * Add a karma point.
     */
    public void upVote() {
        this.karma += 1;
    }

    /**
     * Remove a karma point if karma is greater than -20.
     */
    public void downVote() {
        if (this.karma >= -20) {
            this.karma -= 1;
        }
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public CommentKey getKey() {
        return key;
    }

    public void setKey(CommentKey key) {
        this.key = key;
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
    public String toString() {
        return "Comment{" +
                "userName='" + userName + '\'' +
                ", key=" + key +
                ", text='" + text + '\'' +
                ", karma=" + karma +
                '}';
    }

}
