package com.microreddit.app.persistence.models.Posts.Sub;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

/**
 * @author Josh Harkema
 */
@Table("post_by_sub_comments")
public class PostBySubComments {
    @PrimaryKey
    private final PostBySubCommentsKey key;
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

    public PostBySubComments(PostBySubCommentsKey key, String username, String subName, int score, int karma,
                             String title, String text, String type) {
        this.key = key;
        this.username = username;
        this.subName = subName;
        this.score = score;
        this.karma = karma;
        this.title = title;
        this.text = text;
        this.type = type;
    }

    public PostBySubCommentsKey getKey() {
        return key;
    }

    public String getUsername() {
        return username;
    }

    public String getSubName() {
        return subName;
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

    public String getTitle() {
        return title;
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

    @Override
    public String toString() {
        return "PostBySubComments{" +
                "key=" + key +
                ", username='" + username + '\'' +
                ", subName='" + subName + '\'' +
                ", score=" + score +
                ", karma=" + karma +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

}

