package com.microreddit.app.persistence.models;

import com.datastax.driver.core.DataType.Name;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.List;
import java.util.UUID;

/**
 * Model to handle custom user roles.
 *
 * @author Josh Harkema
 */
@Table("role")
public class Role {
    @PrimaryKey
    private final UUID id;
    @Column
    private String name;
    @Column
    private UUID userID;

    @CassandraType(type = Name.LIST, typeArguments = Name.UUID)
    @Column
    private List<UUID> privileges;

    public Role(String name) {
        super();
        this.id = UUID.randomUUID();
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getUserID() {
        return userID;
    }

    public void setUserID(UUID userID) {
        this.userID = userID;
    }

    public List<UUID> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(List<UUID> privileges) {
        this.privileges = privileges;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Role role = (Role) o;

        if (!id.equals(role.id)) return false;
        if (!name.equals(role.name)) return false;
        if (userID != null ? !userID.equals(role.userID) : role.userID != null) return false;
        return privileges != null ? privileges.equals(role.privileges) : role.privileges == null;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + (userID != null ? userID.hashCode() : 0);
        result = 31 * result + (privileges != null ? privileges.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", userID=" + userID +
                '}';
    }

}
