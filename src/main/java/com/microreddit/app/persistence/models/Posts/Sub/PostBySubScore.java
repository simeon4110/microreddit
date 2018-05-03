package com.microreddit.app.persistence.models.Posts.Sub;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

/**
 * @author Josh Harkema
 */
@Table("post_by_sub_score")
public class PostBySubScore {
    @PrimaryKey
    private final PostBySubScoreKey key;
    @Column
    private final String username;
    @Column
    private final String subName;
    @Column
    private final String title;
    @Column
    private final String type;
    @Column
    private int karma;
    @Column
    private String text;
    @Column
    private int numComments;

    public PostBySubScore(PostBySubScoreKey key, String username, String subName, int karma, String title, String text,
                          String type, int numComments) {
        this.key = key;
        this.username = username;
        this.subName = subName;
        this.karma = karma;
        this.title = title;
        this.text = text;
        this.type = type;
        this.numComments = numComments;
    }

    public PostBySubScoreKey getKey() {
        return key;
    }

    public String getUsername() {
        return username;
    }

    public String getSubName() {
        return subName;
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

    public int getNumComments() {
        return numComments;
    }

    public void setNumComments(int numComments) {
        this.numComments = numComments;
    }

    @Override
    public String toString() {
        return "PostBySubScore{" +
                "key=" + key +
                ", username='" + username + '\'' +
                ", subName='" + subName + '\'' +
                ", karma=" + karma +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", type='" + type + '\'' +
                ", numComments='" + numComments + '\'' +
                '}';
    }

}
