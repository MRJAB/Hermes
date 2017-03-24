package com.example.mrjab.hermes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenuListView;

import java.util.ArrayList;

public class SearchResults extends AppCompatActivity {


    ArrayList<String> unames1 = new ArrayList<>();
    ArrayList<String> mess1 = new ArrayList<>();
    ArrayList<String> times1 = new ArrayList<>();
    ArrayList<ChatInfo> allcha = new ArrayList<ChatInfo>();
    int userID;
    SwipeMenuListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        Intent in = getIntent();
        unames1 = (ArrayList<String>) in.getSerializableExtra("unames");
        mess1 = (ArrayList<String>) in.getSerializableExtra("mess");
        times1 = (ArrayList<String>) in.getSerializableExtra("times1");
        allcha = (ArrayList<ChatInfo>) in.getSerializableExtra("allchats");
        userID = in.getIntExtra("userID",0);
        int proimages[] = {};

        listView= (SwipeMenuListView) findViewById(R.id.listView);
        listView.setAdapter(new CustomAdapter(this,unames1,proimages,mess1,times1));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // adapter.notifyDataSetChanged();
                Toast.makeText(getApplicationContext(), unames1.get(i) + " Clicked", Toast.LENGTH_SHORT).show();
                Intent in =new Intent(SearchResults.this,ChatDetails.class);
                in.putExtra("Messages", allcha.get(i).messages);
                in.putExtra("uname",unames1.get(i));
                in.putExtra("userID",userID);
                in.putExtra("chatID",allcha.get(i).chatID);
                startActivity(in);
                finish();
            }
        });

    }
}
