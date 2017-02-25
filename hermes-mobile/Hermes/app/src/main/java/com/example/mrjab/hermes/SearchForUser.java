package com.example.mrjab.hermes;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class SearchForUser extends AppCompatActivity {
    EditText et;
    ImageButton search;
    ListView ls;

    ArrayList<String> userNames = new ArrayList<>();
    ArrayList<String> names = new ArrayList<>();
    ArrayList<String> emailID = new ArrayList<>();
    int[] profImages;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_for_user);

        et = (EditText) findViewById(R.id.search_user);
        search = (ImageButton) findViewById(R.id.button_search_user);
        ls = (ListView) findViewById(R.id.list_view_search);


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setTitle("Search For a User");
        setSupportActionBar(toolbar);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getUserSearchData();
                populateListView();
            }
        });
    }
    public void getUserSearchData(){

    }

    public void populateListView(){


        userNames.add("DeathEater");
        names.add("Yatharth");
        emailID.add("shit@kcl.ac.uk");


        userNames.add("Razor");
        names.add("Nobody");
        emailID.add("fuck@kcl.ac.uk");

        ls.setAdapter(new CustomAdapterSearchUser(this,userNames,profImages,names,emailID));

        ls.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(),userNames.get(i)+" Selected",Toast.LENGTH_LONG).show();
            }
        });
    }
}
