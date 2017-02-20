package com.example.mrjab.hermes;

import android.os.AsyncTask;
import android.os.Debug;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by ARSALAN on 20-Feb-17.
 */

public class ChatList {


    ArrayList<ChatInfo> chats= new ArrayList<>();


    public ChatList(int userID){
        getJsonChat(userID);
    }

    public ArrayList<ChatInfo> getChats() {
        return this.chats;
    }

    public void setChats(ArrayList<ChatInfo> chats) {
        this.chats = chats;
    }

    public void getJsonChat(final int userID){
        final HttpClient httpclient = new DefaultHttpClient();
        final HttpPost httppost = new HttpPost("http://hermesdb.netai.net/ChatSelect.php");

        final HttpGet httpget = new HttpGet("http://hermesdb.netai.net/ChatSelect.php?userID="+String.valueOf(userID));
        String result;
        final Thread t = new Thread() {
            HttpResponse httpresponse = null;
            JSONObject finaljson = null;
            public void run() {
                Looper.prepare();
                ArrayList<NameValuePair> postParameters;

                postParameters = new ArrayList<NameValuePair>();
                postParameters.add(
                        new BasicNameValuePair("userID", String.valueOf(userID)));

                try {
                    httppost.setEntity(new UrlEncodedFormEntity(postParameters));
                    // reset to null before making a new post if it's being reused
                    httpresponse = null;
                    httpresponse = httpclient.execute(httpget);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // Do something after 5s = 5000ms
                        if(httpresponse!=null){

                            try {

                                // Get the data in the entity
                                BufferedReader reader = new BufferedReader(
                                        new InputStreamReader(
                                                httpresponse.getEntity().getContent(), "UTF-8")
                                );

                                final StringBuilder builder = new StringBuilder();
                                for (String line = null; (line = reader.readLine()) != null;) {
                                    builder.append(line).append("\n");
                                }

                                JSONArray jArray = new JSONArray(builder.toString());
                                //JSONTokener tokener = new JSONTokener(builder.toString());
                                finaljson = jArray.getJSONObject(0);

                                Log.v("JSON OUTPUT: ", finaljson.toString());

                                for (int i=0; i < jArray.length(); i++)
                                {
                                    try {
                                        JSONObject oneObject = jArray.getJSONObject(i);
                                        // Pulling items from the array
                                        chats.add(new ChatInfo(oneObject.getInt("fk_InitUserID_by"),oneObject.getInt("fk_InitUserID_with"),oneObject.getInt("ChatID")));
                                    } catch (JSONException e) {
                                        // Oops
                                        e.printStackTrace();
                                    }
                                }

                            } catch (IOException e) {
                                e.printStackTrace();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    }
                }, 10000);
                // Checking response


            }
        };

        t.start();

    }


}
