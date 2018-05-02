package com.microreddit.app.persistence.models.Posts;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.UUID;

/**
 * Model to store all the post data.
 *
 * @author Josh Harkema
 */
@Table("posts")
public class Post implements Serializable {
    @Column
    private final String timeStamp;
    @PrimaryKey
    private UUID id;
    @Column
    private UUID userID;
    @Column
    private String username;
    @Column
    private UUID subID;
    @Column
    private String subName;
    @Column
    private int score;
    @Column
    private int karma;
    @Column
    private String title;
    @Column
    private String text;
    @Column
    private String type;

    public Post() {
        this.timeStamp = new Timestamp(System.currentTimeMillis()).toString();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTimeStamp() {
        return timeStamp;
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

    public UUID getSubID() {
        return subID;
    }

    public void setSubID(UUID subID) {
        this.subID = subID;
    }

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Post post = (Post) o;

        if (score != post.score) return false;
        if (karma != post.karma) return false;
        if (id != null ? !id.equals(post.id) : post.id != null) return false;
        if (timeStamp != null ? !timeStamp.equals(post.timeStamp) : post.timeStamp != null) return false;
        if (userID != null ? !userID.equals(post.userID) : post.userID != null) return false;
        if (username != null ? !username.equals(post.username) : post.username != null) return false;
        if (subID != null ? !subID.equals(post.subID) : post.subID != null) return false;
        if (subName != null ? !subName.equals(post.subName) : post.subName != null) return false;
        if (title != null ? !title.equals(post.title) : post.title != null) return false;
        if (text != null ? !text.equals(post.text) : post.text != null) return false;
        return type != null ? type.equals(post.type) : post.type == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (timeStamp != null ? timeStamp.hashCode() : 0);
        result = 31 * result + (userID != null ? userID.hashCode() : 0);
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (subID != null ? subID.hashCode() : 0);
        result = 31 * result + (subName != null ? subName.hashCode() : 0);
        result = 31 * result + score;
        result = 31 * result + karma;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", timeStamp='" + timeStamp + '\'' +
                ", userID=" + userID +
                ", username='" + username + '\'' +
                ", subID=" + subID +
                ", subName='" + subName + '\'' +
                ", score=" + score +
                ", karma=" + karma +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

}