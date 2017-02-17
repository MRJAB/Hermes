package com.example.mrjab.hermes;

import java.math.BigInteger;
import java.util.Date;

/**
 * Created by yatharth on 15/02/17.
 */

public class MessageInfo {
    BigInteger messageID;
    BigInteger userIDSender;
    BigInteger chatID;
    String content;
    Date received;

    public MessageInfo(){
        // Initialize variables on creation of a new message
    }

    public BigInteger getMessageID() {
        return messageID;
    }

    public void setMessageID(BigInteger messageID) {
        this.messageID = messageID;
    }

    public BigInteger getUserIDSender() {
        return userIDSender;
    }

    public void setUserIDSender(BigInteger userIDSender) {
        this.userIDSender = userIDSender;
    }

    public BigInteger getChatID() {
        return chatID;
    }

    public void setChatID(BigInteger chatID) {
        this.chatID = chatID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getReceived() {
        return received;
    }

    public void setReceived(Date received) {
        this.received = received;
    }
}
