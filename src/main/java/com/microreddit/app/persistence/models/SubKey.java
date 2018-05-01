package com.microreddit.app.persistence.models;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

import java.io.Serializable;
import java.util.UUID;

/**
 * Key class implements cassandra primary key columns for fast sub lookups.
 *
 * @author Josh Harkema
 */
@PrimaryKeyClass
public class SubKey implements Serializable {
    @PrimaryKeyColumn(name = "sub_id", type = PrimaryKeyType.PARTITIONED)
    private final UUID id;
    @PrimaryKeyColumn(name = "sub_name")
    private final String subName;

    public SubKey(final String subName) {
        this.id = UUID.randomUUID();
        this.subName = subName;
    }

    public UUID getId() {
        return id;
    }

    public String getSubName() {
        return subName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SubKey subKey = (SubKey) o;

        if (!id.equals(subKey.id)) return false;
        return subName.equals(subKey.subName);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + subName.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "SubKey{" +
                "id=" + id +
                ", subName='" + subName + '\'' +
                '}';
    }

}
