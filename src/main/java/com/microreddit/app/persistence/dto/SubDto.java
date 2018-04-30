package com.microreddit.app.persistence.dto;

import javax.validation.constraints.NotEmpty;
import java.util.UUID;

/**
 * Sub dto for catching form data.
 *
 * @author Josh Harkema
 */
public class SubDto {
    @NotEmpty
    private String name;
    @NotEmpty
    private String title;
    private String description;
    private UUID creatorID;
    private String sideBar;
    private String submissionText;
    private String language;
    private String type;
    private String contentOption;
    private String customCSS;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UUID getCreatorID() {
        return creatorID;
    }

    public void setCreatorID(UUID creatorID) {
        this.creatorID = creatorID;
    }

    public String getSideBar() {
        return sideBar;
    }

    public void setSideBar(String sideBar) {
        this.sideBar = sideBar;
    }

    public String getSubmissionText() {
        return submissionText;
    }

    public void setSubmissionText(String submissionText) {
        this.submissionText = submissionText;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContentOption() {
        return contentOption;
    }

    public void setContentOption(String contentOption) {
        this.contentOption = contentOption;
    }

    public String getCustomCSS() {
        return customCSS;
    }

    public void setCustomCSS(String customCSS) {
        this.customCSS = customCSS;
    }

    @Override
    public String toString() {
        return "SubDto{" +
                "name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", creatorID=" + creatorID +
                ", sideBar='" + sideBar + '\'' +
                ", submissionText='" + submissionText + '\'' +
                ", language='" + language + '\'' +
                ", type=" + type +
                ", contentOption=" + contentOption +
                ", customCSS='" + customCSS + '\'' +
                '}';
    }

}
