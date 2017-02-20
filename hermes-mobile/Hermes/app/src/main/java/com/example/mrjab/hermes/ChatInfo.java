package com.example.mrjab.hermes;


import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;


import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;


/**
 * Created by yatharth on 15/02/17.
 */

public class ChatInfo {
    int chatID;
    int userIDSender;
    int userIDReceiver;
    String keyValue;
    Date createDate;
    Context context;

    Bitmap profilepPic;

    public ChatInfo()
    {

    }
    public ChatInfo(Context context, int userIDReceiver){
        // Initialize variables on creation of a new chat
        this.context = context;
        this.userIDReceiver = userIDReceiver;

    }

    public ChatInfo(int sender,int receiver, int chatID){
        this.userIDSender = sender;
        this.userIDReceiver = receiver;
        this.chatID = chatID;
    }

    public int getChatID() {
        return chatID;
    }

    public Bitmap getProfilepPic() {
        return profilepPic;
    }

    public void setProfilepPic(Bitmap profilepPic) {
        this.profilepPic = profilepPic;
    }

    public void setChatID(int chatID) {
        this.chatID = chatID;
    }

    public int getUserIDSender() {
        return userIDSender;
    }

    public void setUserIDSender(int userIDSender) {
        this.userIDSender = userIDSender;
    }

    public int getUserIDReceiver() {
        return userIDReceiver;
    }

    public void setUserIDReceiver(int userIDReceiver) {
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
