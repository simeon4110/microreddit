package com.microreddit.app.persistence.dto;

import javax.validation.constraints.NotEmpty;
import java.util.UUID;

/**
 * @author Josh Harkema
 */
public class CommentDto {
    @NotEmpty
    private String text;
    private UUID userID;
    private UUID parentID;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public UUID getUserID() {
        return userID;
    }

    public void setUserID(UUID userID) {
        this.userID = userID;
    }

    public UUID getParentID() {
        return parentID;
    }

    public void setParentID(UUID parentID) {
        this.parentID = parentID;
    }

    @Override
    public String toString() {
        return "CommentDto{" +
                "text='" + text + '\'' +
                ", userID='" + userID + '\'' +
                ", parentID=" + parentID +
                '}';
    }

}
