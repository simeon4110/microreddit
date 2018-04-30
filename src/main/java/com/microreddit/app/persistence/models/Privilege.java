package com.microreddit.app.persistence.models;

import com.datastax.driver.core.DataType.Name;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.List;
import java.util.UUID;

/**
 * Model to handle custom user privileges.
 *
 * @author Josh Harkema
 */
@Table("privilege")
public class Privilege {
    @PrimaryKey
    private final UUID id;
    @Column
    private String name;

    @CassandraType(type = Name.LIST, typeArguments = Name.UUID)
    @Column
    private List<UUID> roles;

    public Privilege(String name) {
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

    public List<UUID> getRoles() {
        return roles;
    }

    public void setRoles(List<UUID> roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Privilege privilege = (Privilege) o;

        if (!id.equals(privilege.id)) return false;
        if (!name.equals(privilege.name)) return false;
        return roles != null ? roles.equals(privilege.roles) : privilege.roles == null;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + (roles != null ? roles.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Privilege{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", roles=" + roles +
                '}';
    }

}
