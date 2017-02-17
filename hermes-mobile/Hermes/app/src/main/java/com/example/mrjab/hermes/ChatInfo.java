package com.example.mrjab.hermes;

import java.math.BigInteger;
import java.util.Date;

/**
 * Created by yatharth on 15/02/17.
 */

public class ChatInfo {
    BigInteger chatID;
    BigInteger userIDSender;
    BigInteger userIDReceiver;
    String keyValue;
    Date createDate;

    public ChatInfo(){
        // Initialize variables on creation of a new chat
    }

    public BigInteger getChatID() {
        return chatID;
    }

    public void setChatID(BigInteger chatID) {
        this.chatID = chatID;
    }

    public BigInteger getUserIDSender() {
        return userIDSender;
    }

    public void setUserIDSender(BigInteger userIDSender) {
        this.userIDSender = userIDSender;
    }

    public BigInteger getUserIDReceiver() {
        return userIDReceiver;
    }

    public void setUserIDReceiver(BigInteger userIDReceiver) {
        this.userIDReceiver = userIDReceiver;
    }

    public String getKeyValue() {
        return keyValue;
    }

    public void setKeyValue(String keyValue) {
        this.keyValue = keyValue;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
