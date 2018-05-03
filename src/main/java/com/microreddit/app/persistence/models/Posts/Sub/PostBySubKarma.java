package com.microreddit.app.persistence.models.Posts.Sub;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

/**
 * @author Josh Harkema
 */
@Table("post_by_sub_karma")
public class PostBySubKarma {
    @PrimaryKey
    private final PostBySubKarmaKey key;
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
    private String text;
    @Column
    private int numComments;

    public PostBySubKarma(PostBySubKarmaKey key, String username, String subName, int score, String title, String text, String type, int numComments) {
        this.key = key;
        this.username = username;
        this.subName = subName;
        this.score = score;
        this.title = title;
        this.text = text;
        this.type = type;
        this.numComments = numComments;
    }

    public PostBySubKarmaKey getKey() {
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
        return "PostBySubKarma{" +
                "key=" + key +
                ", username='" + username + '\'' +
                ", subName='" + subName + '\'' +
                ", score=" + score +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", type='" + type + '\'' +
                ", numComments='" + numComments + '\'' +
                '}';
    }

}
