package com.microreddit.app.persistence.models.Posts;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

/**
 * @author Josh Harkema
 */
@Table("post_by_user")
public class PostByUser {
    @PrimaryKey
    private final PostByUserKey key;
    @Column
    private final String username;
    @Column
    private final String subName;
    @Column
    private final String title;
    @Column
    private final String type;
    @Column
    private int score;
    @Column
    private int karma;
    @Column
    private String text;
    @Column
    private int numComments;

    public PostByUser(PostByUserKey key, String username, String subName, String title, String type, int score,
                      int karma, String text, int numComments) {
        this.key = key;
        this.username = username;
        this.subName = subName;
        this.title = title;
        this.type = type;
        this.score = score;
        this.karma = karma;
        this.text = text;
        this.numComments = numComments;
    }

    public PostByUserKey getKey() {
        return key;
    }

    public String getUsername() {
        return username;
    }

    public String getSubName() {
        return subName;
    }

    public String getTitle() {
        return title;
    }

    public String getType() {
        return type;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getKarma() {
        return karma;
    }

    public void setKarma(int karma) {
        this.karma = karma;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getNumComments() {
        return numComments;
    }

    public void setNumComments(int numComments) {
        this.numComments = numComments;
    }

    @Override
    public String toString() {
        return "PostByUser{" +
                "key=" + key +
                ", username='" + username + '\'' +
                ", subName='" + subName + '\'' +
                ", title='" + title + '\'' +
                ", type='" + type + '\'' +
                ", score=" + score +
                ", karma=" + karma +
                ", text='" + text + '\'' +
                ", numComments=" + numComments +
                '}';
    }
}
