package com.example.mrjab.hermes;

import android.app.usage.UsageEvents;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.EventListener;

public class MainActivity extends AppCompatActivity {

    EditText username;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences prefs = getSharedPreferences("login", MODE_PRIVATE);
        int id = prefs.getInt("userID", 0); //0 is the default value.

        if(id > 0)
        {
            Intent intent =new Intent(this,Chats.class);
            intent.putExtra("userID", id);
            startActivity(intent);
            finish();
        }

        setContentView(R.layout.sign_in);

        username = (EditText) findViewById(R.id.username_textField);
        password = (EditText) findViewById(R.id.password_textField);


        Log.v("Encryption",bin2hex(getHash("yatharth")).toLowerCase());

    }

    public void signupPage_click(View e) {
        Intent i = new Intent(MainActivity.this, SignUp.class);
        startActivity(i);
        finish();
    }


    public byte[] getHash(String password) {
        MessageDigest digest=null;
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        digest.reset();
        return digest.digest(password.getBytes());
    }

    static String bin2hex(byte[] data) {
        return String.format("%0" + (data.length*2) + "X", new BigInteger(1, data));
    }

    public void chatListPage_click(View e) {

        Authenticate authenticate = new Authenticate();
        String params[] = {username.getText().toString(),password.getText().toString()};
        authenticate.execute(params);
    }

    class Authenticate extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String[] user) {

            final StringBuilder builder = new StringBuilder();
            //Do Your stuff here..

            final HttpClient httpclient = new DefaultHttpClient();
            final HttpPost httppost = new HttpPost("http://hermes.webutu.com/ChatSelect.php");

            final HttpGet httpget = new HttpGet("http://hermes.webutu.com/SP/UserAuthentication.php?user_username="+"'"+String.valueOf(user[0])+"'&"+"user_password="+"'"+String.valueOf(user[1])+"'");
            String result;


            HttpResponse httpresponse = null;

            Looper.prepare();
            ArrayList<NameValuePair> postParameters;

            postParameters = new ArrayList<NameValuePair>();
            postParameters.add(
                    new BasicNameValuePair("userID", String.valueOf(user[0])));

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

            //Toast.makeText(getApplicationContext(),""+result,Toast.LENGTH_LONG).show();
            int userID = 0;
            if(isJSONValid(result))
            {
                //Toast.makeText(getApplicationContext(),"Sign In Successful :)",Toast.LENGTH_LONG).show();
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
                            userID = oneObject.getInt("UserID");
                        } catch (JSONException e) {
                            // Oops
                            e.printStackTrace();
                        }
                    }
                }catch (JSONException e) {
                    e.printStackTrace();
                }

                if(userID == 0)
                {
                    Toast.makeText(getApplicationContext(),"Sign In Unsuccessful :(. \nTry a different username or password",Toast.LENGTH_LONG).show();
                }
                else
                {

                    SharedPreferences.Editor editor = getSharedPreferences("login", MODE_PRIVATE).edit();
                    editor.putInt("userID", userID);
                    editor.commit();

                    Intent i = new Intent(MainActivity.this, Chats.class);
                    i.putExtra("userID", userID);
                    startActivity(i);
                    finish();
                }
            }
            else
            {
                Toast.makeText(getApplicationContext(),"Connection Error :(",Toast.LENGTH_LONG).show();
            }

        }

        public boolean isJSONValid(String test) {
            try {
                new JSONObject(test);
            } catch (JSONException ex) {
                // edited, to include @Arthur's comment
                // e.g. in case JSONArray is valid as well...
                try {
                    new JSONArray(test);
                } catch (JSONException ex1) {
                    return false;
                }
            }
            return true;
        }


    }
}
