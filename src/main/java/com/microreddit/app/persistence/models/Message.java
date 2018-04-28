package com.microreddit.app.persistence.models;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

/**
 * Defines message model.
 */
@Table("Messages")
public class Message {
    @PrimaryKey
    private final MessageKey key;

    @Column("message_sender_name")
    private final String messageSender;

    @Column("message_receiver_name")
    private final String messageReceiver;

    @Column("message_text")
    private final String text;

    /**
     * @param key             a unique MessageKey.class object.
     * @param messageSender   the sender's username as a String.
     * @param messageReceiver the receiver's username as a String.
     * @param text            the message text.
     */
    public Message(MessageKey key, String messageSender, String messageReceiver, String text) {
        this.key = key;
        this.messageSender = messageSender;
        this.messageReceiver = messageReceiver;
        this.text = Jsoup.clean(text, Whitelist.simpleText());
    }

    public MessageKey getKey() {
        return key;
    }

    public String getMessageSender() {
        return messageSender;
    }

    public String getMessageReceiver() {
        return messageReceiver;
    }

    public String getText() {
        return text;
    }

    /**
     * @return a JSON formatted string of all message data.
     */
    @Override
    public String toString() {
        return "Message{" +
                "key=" + key +
                ", messageSender='" + messageSender + '\'' +
                ", messageReceiver='" + messageReceiver + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
