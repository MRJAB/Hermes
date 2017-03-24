package com.example.mrjab.hermes;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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
import java.util.ArrayList;

public class SignUp extends AppCompatActivity {

    EditText fullName;
    EditText userName;
    EditText email;
    EditText confirm_email;
    EditText password;
    EditText confirmPassword;
    Button login;

    String[] parameters = new String[4];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        fullName = (EditText) findViewById(R.id.full_name);
        userName = (EditText) findViewById(R.id.username);
        email = (EditText) findViewById(R.id.email);
        confirm_email = (EditText) findViewById(R.id.confirm_email);
        password = (EditText) findViewById(R.id.password);
        confirmPassword = (EditText) findViewById(R.id.confirm_password);
        login = (Button) findViewById(R.id.btn_login);




        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(confirmPassword.getText().toString().equals(password.getText().toString()) && confirm_email.getText().toString().equals(email.getText().toString())) {

                    if(password.getText().toString().length()>=8 && password.getText().toString().length() <=20 && userName.getText().toString().length() >= 8 && userName.getText().toString().length() <= 20) {
                        parameters[0] = (fullName.getText().toString());
                        parameters[1] = (userName.getText().toString());
                        parameters[2] = (email.getText().toString());
                        parameters[3] = (password.getText().toString());
                        new createNewUser().execute(parameters);
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Password and Username should be between 8 to 20 characters.",Toast.LENGTH_LONG).show();

                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Confirm Password or Confirm Email wrong",Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent i=new Intent(this,MainActivity.class);
        startActivity(i);
        finish();
    }


    class createNewUser extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            final StringBuilder builder = new StringBuilder();
            //Do Your stuff here..

            final HttpClient httpclient = new DefaultHttpClient();
            final HttpPost httppost = new HttpPost("http://hermes.webutu.com/ChatSelect.php");

            final HttpGet httpget = new HttpGet("http://hermes.webutu.com/SP/UserCreate.php?"+"NameV='"+params[0]+"'&UsernameV='"+params[1]
            +"'&EmailV='" + params[2] + "'&PasswordV='" + params[3]+"'");
            String result;

            HttpResponse httpresponse = null;

            Looper.prepare();
            ArrayList<NameValuePair> postParameters;

            postParameters = new ArrayList<NameValuePair>();
            postParameters.add(
                    new BasicNameValuePair("NameV", params[0]));
            postParameters.add(
                    new BasicNameValuePair("UsernameV", params[1]));
            postParameters.add(
                    new BasicNameValuePair("EmailV", params[2]));

            postParameters.add(
                    new BasicNameValuePair("PasswordV", params[3]));

            try {
                httppost.setEntity(new UrlEncodedFormEntity(postParameters));
                // reset to null before making a new post if it's being reused
                httpresponse = null;
                httpresponse = httpclient.execute(httpget);
            } catch (IOException e1) {

                Toast.makeText(getApplicationContext(),"Error connecting to server",Toast.LENGTH_LONG).show();
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
            //Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();
            TextView tv = (TextView) findViewById(R.id.testing);
            tv.setText(result);

            if(result.contains("True"))
            {
                Toast.makeText(getApplicationContext(),"Sign Up Successful :)",Toast.LENGTH_LONG).show();

            }
            else if(result.contains("False"))
            {
                Toast.makeText(getApplicationContext(),"Sign Up Unsuccessful :(. \nTry a different username",Toast.LENGTH_LONG).show();

            }
            else
            {
                Toast.makeText(getApplicationContext(),"Connection Error :(",Toast.LENGTH_LONG).show();

            }


            Intent i =new Intent(SignUp.this, MainActivity.class);
            startActivity(i);

            finish();
        }

        }


    }
