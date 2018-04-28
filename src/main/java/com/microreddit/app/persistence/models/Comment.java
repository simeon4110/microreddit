package com.microreddit.app.persistence.models;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
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
    private final String userName;
    @PrimaryKey
    private CommentKey key;
    @Column("comment_text")
    private String text;
    @Column("comment_karma")
    private int karma;

    /**
     * @param key      unique CommentKey.class object.
     * @param text     the comment text.
     * @param userName the poster's username.
     */
    public Comment(final CommentKey key, final String text, final String userName) {
        this.key = key;
        this.text = Jsoup.clean(text, Whitelist.simpleText());
        this.userName = userName;
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

    public CommentKey getKey() {
        return key;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUserName() {
        return userName;
    }

    public int getKarma() {
        return karma;
    }

    /**
     * @return a lovely JSON styled string of all comment data.
     */
    @Override
    public String toString() {
        return "Comment{" +
                "key=" + key +
                ", text='" + text + '\'' +
                ", userName='" + userName + '\'' +
                ", karma=" + karma +
                '}';
    }

}
