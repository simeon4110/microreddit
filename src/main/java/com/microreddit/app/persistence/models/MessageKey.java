package com.microreddit.app.persistence.models;

import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.UUID;

/**
 * Key class implements cassandra primary key columns for fast amqp lookups.
 *
 * @author Josh Harkema
 */
@PrimaryKeyClass
public class MessageKey implements Serializable {
    @PrimaryKeyColumn(name = "message_key", type = PrimaryKeyType.PARTITIONED)
    private final UUID id;
    @PrimaryKeyColumn(name = "message_sender")
    private final UUID senderID;
    @PrimaryKeyColumn(name = "message_receiver")
    private final UUID receiverID;
    @PrimaryKeyColumn(name = "message_date", ordering = Ordering.DESCENDING)
    private final String timeStamp;

    /**
     * @param senderID   UUID of amqp sender.
     * @param receiverID UUID of amqp receiver.
     */
    public MessageKey(final UUID id, final UUID senderID, final UUID receiverID) {
        this.id = UUID.randomUUID();
        this.senderID = senderID;
        this.receiverID = receiverID;
        this.timeStamp = new Timestamp(System.currentTimeMillis()).toString();
    }

    public UUID getId() {
        return id;
    }

    public UUID getSenderID() {
        return senderID;
    }

    public UUID getReceiverID() {
        return receiverID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MessageKey that = (MessageKey) o;

        if (!id.equals(that.id)) return false;
        if (!senderID.equals(that.senderID)) return false;
        return receiverID.equals(that.receiverID);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + senderID.hashCode();
        result = 31 * result + receiverID.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "MessageKey{" +
                "id=" + id +
                ", senderID=" + senderID +
                ", receiverID=" + receiverID +
                '}';
    }

}
