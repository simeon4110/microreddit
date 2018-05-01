package com.microreddit.app.persistence.models;

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
    private String title;
    @Column("post_text")
    private String text;
    @Column("type")
    private String type;
    @Column("post_karma")
    private int karma;
    @Column("sub_title")
    private String subTitle;
    @Column("post_username")
    private String username;

    public Post(final PostKey key) {
        this.key = key;
        this.karma = 0;
    }

    public void upVote() {
        this.karma += 1;
    }

    public void downVote() {
        this.karma -= 1;
    }

    public PostKey getKey() {
        return key;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getKarma() {
        return karma;
    }

    public void setKarma(int karma) {
        this.karma = karma;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "{title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", karma=" + karma +
                ", subTitle='" + subTitle + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}