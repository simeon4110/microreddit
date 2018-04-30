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
 * Defines the User model. UserName is stored in UserKey for fast search lookups.
 *
 * @author Josh Harkema
 */
@Table("users")
public class User {
    @PrimaryKey
    private final UserKey key;

    @Column("email")
    private String email;

    @Column("comment_karma")
    private int commentKarma;

    @Column("post_karma")
    private int postKarma;

    @Column("password")
    private String password;

    @Column("enabled")
    private boolean isEnabled;

    @Column("roles")
    @CassandraType(type = DataType.Name.LIST, typeArguments = DataType.Name.UUID)
    private List<UUID> roles;

    @Column("subs")
    @CassandraType(type = DataType.Name.LIST, typeArguments = DataType.Name.UUID)
    private List<UUID> subs;

    @Column("blocked")
    @CassandraType(type = DataType.Name.LIST, typeArguments = DataType.Name.UUID)
    private List<UUID> blocked;

    @Column("friends")
    @CassandraType(type = DataType.Name.LIST, typeArguments = DataType.Name.UUID)
    private List<UUID> friends;

    /**
     * @param key the UserKey object associated to this user.
     */
    public User(final UserKey key) {
        this.key = key;
        this.commentKarma = 0;
        this.postKarma = 0;
        this.roles = new ArrayList<>();
        this.subs = new ArrayList<>();
        this.blocked = new ArrayList<>();
        this.friends = new ArrayList<>();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void addSub(UUID sub) {
        this.subs.add(sub);
    }

    public void removeSub(UUID sub) {
        this.subs.remove(sub);
    }

    public void addBlocked(UUID user) {
        this.blocked.add(user);
    }

    public void removeBlocked(UUID user) {
        this.blocked.remove(user);
    }

    public void addFriend(UUID user) {
        this.friends.add(user);
    }

    public void removeFriend(UUID user) {
        this.friends.remove(user);
    }

    public UserKey getKey() {
        return key;
    }

    public int getCommentKarma() {
        return commentKarma;
    }

    public void setCommentKarma(int commentKarma) {
        this.commentKarma = commentKarma;
    }

    public int getPostKarma() {
        return postKarma;
    }

    public void setPostKarma(int postKarma) {
        this.postKarma = postKarma;
    }

    public List<UUID> getSubs() {
        return subs;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        this.isEnabled = enabled;
    }

    public List<UUID> getRoles() {
        return roles;
    }

    public void setRoles(List<UUID> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "key=" + key +
                ", commentKarma=" + commentKarma +
                ", postKarma=" + postKarma +
                '}';
    }

}
