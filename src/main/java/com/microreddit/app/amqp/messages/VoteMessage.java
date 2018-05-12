package com.microreddit.app.amqp.messages;

import java.io.Serializable;

/**
 * @author Josh Harkema
 */
public final class VoteMessage implements Serializable {
    private String itemID;
    private String userID;
    private String command;
    private String type;

    public VoteMessage() {
    }

    public VoteMessage(String itemID, String userID, String command, String type) {
        this.itemID = itemID;
        this.userID = userID;
        this.command = command;
        this.type = type;
    }

    public String getItemID() {
        return itemID;
    }

    public String getUserID() {
        return userID;
    }

    public String getCommand() {
        return command;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "VoteMessage{" +
                "itemID='" + itemID + '\'' +
                ", userID='" + userID + '\'' +
                ", command='" + command + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

}
