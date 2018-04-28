package com.microreddit.app.persistence.models;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

/**
 * Model to store all the post data.
 *
 * @author Josh Harkema
 */
@Table("posts")
public class Post {
    @PrimaryKey
    private final PostKey key;

    @Column("post_title")
    private final String title;
    @Column("post_sub")
    private final String subName;
    @Column("post_user")
    private final String userName;
    @Column("post_text")
    private String text;
    @Column("post_karma")
    private int karma;

    /**
     * @param key      a unique PostKey.class object.
     * @param title    the post's title.
     * @param text     the post's body (text or URL).
     * @param subName  the name of the sub the post is subbed to.
     * @param userName the name of the user subbing the sub.
     */
    public Post(final PostKey key, final String title, final String text, final String subName, final String userName) {
        this.key = key;
        this.title = Jsoup.clean(title, Whitelist.simpleText());
        this.text = Jsoup.clean(text, Whitelist.simpleText());
        this.subName = subName;
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
     * Remove a karma point.
     */
    public void downVote() {
        this.karma -= 1;
    }

    public PostKey getKey() {
        return key;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSubName() {
        return subName;
    }

    public String getUserName() {
        return userName;
    }

    /**
     * @return a JSON formatted string of all the post data.
     */
    @Override
    public String toString() {
        return "Post{" +
                "key=" + key +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", subName='" + subName + '\'' +
                ", userName='" + userName + '\'' +
                ", karma=" + karma +
                '}';
    }
}