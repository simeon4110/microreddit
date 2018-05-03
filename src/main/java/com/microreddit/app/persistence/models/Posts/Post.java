package com.microreddit.app.persistence.models.Posts;

import com.datastax.driver.core.DataType;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Model to store all the post data.
 *
 * @author Josh Harkema
 */
@Table("posts")
public class Post implements Serializable {
    @PrimaryKey
    private final UUID postID;
    @Column
    private final String timestamp;
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
    @CassandraType(type = DataType.Name.LIST, typeArguments = DataType.Name.UUID)
    private List<UUID> comments;

    public Post() {
        this.postID = UUID.randomUUID();
        this.timestamp = new Timestamp(System.currentTimeMillis()).toString();
    }

    public UUID getPostID() {
        return postID;
    }

    public String getTimestamp() {
        return timestamp;
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

    public List<UUID> getComments() {
        return comments;
    }

    public void setComments(List<UUID> comments) {
        this.comments = comments;
    }

    public int getNumComments() {
        if (this.comments == null) {
            this.comments = new ArrayList<>();
        }
        return this.comments.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Post post = (Post) o;

        if (score != post.score) return false;
        if (karma != post.karma) return false;
        if (postID != null ? !postID.equals(post.postID) : post.postID != null) return false;
        if (timestamp != null ? !timestamp.equals(post.timestamp) : post.timestamp != null) return false;
        if (userID != null ? !userID.equals(post.userID) : post.userID != null) return false;
        if (username != null ? !username.equals(post.username) : post.username != null) return false;
        if (subID != null ? !subID.equals(post.subID) : post.subID != null) return false;
        if (subName != null ? !subName.equals(post.subName) : post.subName != null) return false;
        if (title != null ? !title.equals(post.title) : post.title != null) return false;
        if (text != null ? !text.equals(post.text) : post.text != null) return false;
        if (type != null ? !type.equals(post.type) : post.type != null) return false;
        return comments != null ? comments.equals(post.comments) : post.comments == null;
    }

    @Override
    public int hashCode() {
        int result = postID != null ? postID.hashCode() : 0;
        result = 31 * result + (timestamp != null ? timestamp.hashCode() : 0);
        result = 31 * result + (userID != null ? userID.hashCode() : 0);
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (subID != null ? subID.hashCode() : 0);
        result = 31 * result + (subName != null ? subName.hashCode() : 0);
        result = 31 * result + score;
        result = 31 * result + karma;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (comments != null ? comments.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Post{" +
                "postID=" + postID +
                ", timestamp=" + timestamp +
                ", userID=" + userID +
                ", username='" + username + '\'' +
                ", subID=" + subID +
                ", subName='" + subName + '\'' +
                ", score=" + score +
                ", karma=" + karma +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", type='" + type + '\'' +
                ", comments=" + comments +
                '}';
    }

}