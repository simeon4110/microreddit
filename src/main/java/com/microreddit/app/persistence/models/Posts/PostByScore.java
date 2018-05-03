package com.microreddit.app.persistence.models.Posts;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.UUID;

/**
 * @author Josh Harkema
 */
@Table("post_by_score")
public class PostByScore implements Serializable {
    @Column
    private final UUID subID;
    @Column
    private final String timeStamp;
    @PrimaryKey
    private int score;
    @Column
    private UUID userID;
    @Column
    private String username;
    @Column
    private String subName;
    @Column
    private int karma;
    @Column
    private String title;
    @Column
    private String text;
    @Column
    private String type;

    public PostByScore(int score, UUID subID, UUID userID, String username, String subName, int karma, String title,
                       String text, String type) {
        this.score = score;
        this.subID = subID;
        this.timeStamp = new Timestamp(System.currentTimeMillis()).toString();
        this.userID = userID;
        this.username = username;
        this.subName = subName;

        this.karma = karma;
        this.title = title;
        this.text = text;
        this.type = type;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public UUID getSubID() {
        return subID;
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

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
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

        PostByScore that = (PostByScore) o;

        if (score != that.score) return false;
        if (karma != that.karma) return false;
        if (subID != null ? !subID.equals(that.subID) : that.subID != null) return false;
        if (timeStamp != null ? !timeStamp.equals(that.timeStamp) : that.timeStamp != null) return false;
        if (userID != null ? !userID.equals(that.userID) : that.userID != null) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        if (subName != null ? !subName.equals(that.subName) : that.subName != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (text != null ? !text.equals(that.text) : that.text != null) return false;
        return type != null ? type.equals(that.type) : that.type == null;
    }

    @Override
    public int hashCode() {
        int result = score;
        result = 31 * result + (subID != null ? subID.hashCode() : 0);
        result = 31 * result + (timeStamp != null ? timeStamp.hashCode() : 0);
        result = 31 * result + (userID != null ? userID.hashCode() : 0);
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (subName != null ? subName.hashCode() : 0);
        result = 31 * result + karma;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PostByScore{" +
                "score=" + score +
                ", subID=" + subID +
                ", timeStamp='" + timeStamp + '\'' +
                ", userID=" + userID +
                ", username='" + username + '\'' +
                ", subName='" + subName + '\'' +
                ", karma=" + karma +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

}
