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
 */
@Table("subs")
public class Sub {
    @Column("sub_creator_id")
    private final UUID creatorID; // the initial sub creator and first mod.
    @PrimaryKey
    private SubKey key;
    @Column("sub_description")
    private String description;
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
        this.moderators = new ArrayList<>();
        this.moderators.add(creatorID);
        this.banned = new ArrayList<>();
    }

    /**
     * @param userID UUID of mod to add.
     */
    public void addModerator(UUID userID) {
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

    /**
     * @return a JSON formatted list of all sub data.
     */
    @Override
    public String toString() {
        return "Sub{" +
                "key=" + key +
                ", description='" + description + '\'' +
                ", customCSS='" + customCSS + '\'' +
                ", creatorID=" + creatorID +
                ", moderators=" + moderators +
                '}';
    }

}
