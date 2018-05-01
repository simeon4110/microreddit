package com.microreddit.app.persistence.dto;

import javax.validation.constraints.NotEmpty;
import java.util.UUID;

/**
 * Post dto for catching form data.
 *
 * @author Josh Harkema
 */
public class PostDto {
    @NotEmpty
    private String title;
    @NotEmpty
    private String text;
    private String type;
    private UUID userID;
    private String subName;

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

    public UUID getUserID() {
        return userID;
    }

    public void setUserID(UUID userID) {
        this.userID = userID;
    }

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }

    @Override
    public String toString() {
        return "PostDto{" +
                "title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", type='" + type + '\'' +
                ", userID=" + userID +
                ", subName='" + subName + '\'' +
                '}';
    }
}
