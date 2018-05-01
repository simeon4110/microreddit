package com.microreddit.app.persistence.models;

import com.datastax.driver.core.DataType;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Model stores sub data. Query managed in SubKey.class.
 *
 * @author Josh Harkema
 */
@Table("subs")
public class Sub {
    @PrimaryKey
    private final SubKey key;
    @Column("sub_creator_id")
    private final UUID creatorID; // the initial sub creator and first mod.
    @Column("sub_description")
    private String description;
    @Column("sub_title")
    private String title;
    @Column("sidebar")
    private String sidebar;
    @Column("submission_text")
    private String submissionText;
    @Column("language")
    private String language;
    @Column("sub_type")
    private String subType;
    @Column("content_option")
    private String subContentOption;
    @Column("sub_css")
    private String customCSS;
    @Column("sub_moderators")
    @CassandraType(type = DataType.Name.LIST, typeArguments = DataType.Name.UUID)
    private List<UUID> moderators;
    @Column("sub_banned")
    @CassandraType(type = DataType.Name.LIST, typeArguments = DataType.Name.UUID)
    private List<UUID> banned;

    /**
     * @param key         a unique SubKey.class object.
     * @param description the sub's description.
     * @param creatorID   the UUID of the sub's creator (user).
     */
    public Sub(final SubKey key, final String description, final UUID creatorID) {
        this.key = key;
        this.description = description;
        this.creatorID = creatorID;
        this.moderators.add(creatorID);
    }

    /**
     * @param userID UUID of mod to add.
     */
    public void addModerator(UUID userID) {
        if (this.moderators == null) {
            this.moderators = new ArrayList<>();
        }

        this.moderators.add(userID);
    }

    /**
     * @param userID UUID of mod to remove.
     */
    public void removeModerator(UUID userID) {
        this.moderators.remove(userID);
    }

    /**
     * @param userID UUID of user to ban.
     */
    public void addBan(UUID userID) {
        if (this.banned == null) {
            this.banned = new ArrayList<>();
        }

        this.banned.add(userID);
    }

    /**
     * @param userID UUID of user to unban.
     */
    public void removeBan(UUID userID) {
        this.banned.remove(userID);
    }

    public SubKey getKey() {
        return key;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCustomCSS() {
        return customCSS;
    }

    public void setCustomCSS(String customCSS) {
        this.customCSS = customCSS;
    }

    public UUID getCreatorID() {
        return this.creatorID;
    }

    public List<UUID> getModerators() {
        return moderators;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSidebar() {
        return sidebar;
    }

    public void setSidebar(String sidebar) {
        this.sidebar = sidebar;
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

    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }

    public String getSubContentOption() {
        return subContentOption;
    }

    public void setSubContentOption(String subContentOption) {
        this.subContentOption = subContentOption;
    }

    public void setModerators(List<UUID> moderators) {
        this.moderators = moderators;
    }

    public List<UUID> getBanned() {
        return banned;
    }

    public void setBanned(List<UUID> banned) {
        this.banned = banned;
    }

    @Override
    public String toString() {
        return "Sub{" +
                "creatorID=" + creatorID +
                ", key=" + key +
                ", description='" + description + '\'' +
                ", title='" + title + '\'' +
                ", language='" + language + '\'' +
                ", subType=" + subType +
                '}';
    }

}
