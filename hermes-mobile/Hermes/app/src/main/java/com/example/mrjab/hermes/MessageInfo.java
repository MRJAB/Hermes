package com.example.mrjab.hermes;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

/**
 * Created by yatharth on 15/02/17.
 */

public class MessageInfo implements Serializable {
    int messageID;
    int userIDSender;
    int chatID;
    String content;
    Date receivedDate;

    public MessageInfo(int messageID, int chatID, int userIDSender, String content, Date receivedDate){
        // Initialize variables on creation of a new message
        this.userIDSender=userIDSender;
        this.messageID = messageID;
        this.chatID=chatID;
        this.content = content;
        this.receivedDate = receivedDate;
    }

    public int getMessageID() {
        return messageID;
    }

    public void setMessageID(int messageID) {
        this.messageID = messageID;
    }

    public int getUserIDSender() {
        return userIDSender;
    }

    public void setUserIDSender(int userIDSender) {
        this.userIDSender = userIDSender;
    }

    public int getChatID() {
        return chatID;
    }

    public void setChatID(int chatID) {
        this.chatID = chatID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getReceived() {
        return receivedDate;
    }

    public void setReceived(Date received) {
        this.receivedDate = received;
    }
}
