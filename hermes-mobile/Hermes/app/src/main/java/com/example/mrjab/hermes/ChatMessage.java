package com.example.mrjab.hermes;

import java.util.Date;

/**
 * Created by yatharth on 08/02/17.
 */

public class ChatMessage {
    public int left;
    public String message;
    public Date received;

    public ChatMessage(int left, String message, Date received) {
        super();
        this.left = left;
        this.message = message;
        this.received = received;
    }
}