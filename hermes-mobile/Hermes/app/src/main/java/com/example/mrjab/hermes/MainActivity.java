package com.example.mrjab.hermes;

import android.app.usage.UsageEvents;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.EventListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in);
    }

    public void signupPage_click(View e)
    {
        setContentView(R.layout.sign_up);
    }

    public void chatListPage_click(View e)
    {
        Intent i=new Intent(MainActivity.this,Chats.class);
        startActivity(i);
    }

    public void InitHomePage()
    {
    }
}
