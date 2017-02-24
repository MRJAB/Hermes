package com.example.mrjab.hermes;

import android.os.AsyncTask;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by yatharth on 24/02/17.
 */

public class GetLastMessageFromChat {

    int userID;
    int chatID;

    ArrayList<MessageInfo> messages = new ArrayList<>();
    public GetMessage asyncMessages = new GetMessage();

    public GetLastMessageFromChat(int uid,int chatid) {
        this.userID=uid;
        this.chatID=chatid;
    }


    public class GetMessage extends AsyncTask<String, Void, String> {

        public AsyncResponseMessages delegate = null;
        @Override
        protected String doInBackground( String[] userID) {
            // TODO Auto-generated method stub

            final StringBuilder builder = new StringBuilder();
            //Do Your stuff here..

            final HttpClient httpclient = new DefaultHttpClient();
            final HttpPost httppost = new HttpPost("http://hermes.webutu.com/ChatSelect.php");

            final HttpGet httpget = new HttpGet("http://hermes.webutu.com/ChatMessageSelect.php?userID="+String.valueOf(userID[0]));
            String result;


            HttpResponse httpresponse = null;

            Looper.prepare();
            ArrayList<NameValuePair> postParameters;

            postParameters = new ArrayList<NameValuePair>();
            postParameters.add(
                    new BasicNameValuePair("userID", String.valueOf(userID[0])));

            try {
                httppost.setEntity(new UrlEncodedFormEntity(postParameters));
                // reset to null before making a new post if it's being reused
                httpresponse = null;
                httpresponse = httpclient.execute(httpget);
            } catch (IOException e1) {
                e1.printStackTrace();
            }

            if(httpresponse!=null){

                try {

                    // Get the data in the entity
                    BufferedReader reader = new BufferedReader(
                            new InputStreamReader(
                                    httpresponse.getEntity().getContent(), "UTF-8")
                    );


                    for (String line = null; (line = reader.readLine()) != null;) {
                        builder.append(line).append("\n");
                    }



                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return builder.toString();
        }
        @Override
        protected void onPostExecute(String result) {


            super.onPostExecute(result);


            JSONObject finaljson = null;

            try {
                JSONArray jArray = new JSONArray(result);
                //JSONTokener tokener = new JSONTokener(builder.toString());
                finaljson = jArray.getJSONObject(0);

                Log.v("JSON OUTPUT: ", finaljson.toString());

                for (int i = 0; i < jArray.length(); i++) {
                    try {
                        JSONObject oneObject = jArray.getJSONObject(i);
                        // Pulling items from the array
                        if (!oneObject.getString("RecieveDateTime").equals("null")) {
                            if (oneObject.getInt("ChatID") == chatID) {
                                Date date = new Date();
                                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");
                                try {

                                    date = format.parse(oneObject.getString("RecieveDateTime"));
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                                messages.add(new MessageInfo(oneObject.getInt("MessageID"), oneObject.getInt("ChatID"), oneObject.getInt("fk_SenderUserID"), oneObject.getString("Content"), date));
                            }
                        }
                    }catch (JSONException e) {
                        // Oops
                        e.printStackTrace();
                    }

                }
            }catch (JSONException e) {
                e.printStackTrace();
            }

            delegate.processfinishMessages(messages);
            //this method will be running on UI thread


        }
    }



}


