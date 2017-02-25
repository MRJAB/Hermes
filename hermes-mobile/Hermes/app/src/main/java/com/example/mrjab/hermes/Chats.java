package com.example.mrjab.hermes;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;

import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.format.DateFormat;
import android.text.format.DateUtils;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;

import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;


public class Chats extends AppCompatActivity implements AsyncResponse,AsyncResponseMessages{

    private Toolbar toolbar;
    ChatList chatList = new ChatList();
    ArrayList<ChatInfo> allChats= new ArrayList<>();

    int [] profileImages={};
    ArrayList<String> times=new ArrayList<>();
    ArrayList<String> messages = new ArrayList<>();
    ArrayList<String> username = new ArrayList<>();
    String currentMessage = "Message";
    Date currentDate = new Date();
    static int i=0;

    HermesDbHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chats);

        SwipeMenuListView listView= (SwipeMenuListView) findViewById(R.id.listView);
        System.out.println("started");
        Toast.makeText(Chats.this,"started",Toast.LENGTH_LONG).show();


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);

        dbHelper = new HermesDbHelper(getApplicationContext());

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        if(dbHelper.f==0)
        {
            Toast.makeText(getApplicationContext(),"On Create working",Toast.LENGTH_LONG).show();
        }

        ContentValues values = new ContentValues();
        values.put("UserID",1);
        values.put("Username", "Yatharth");
        values.put("Email","blah@shit.com");
        values.put("Password","Yath");
        values.put("CreateDate",new Date().toString());

        long newRowId = db.insert("tbl_user", null, values);


        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                // create "open" item
                SwipeMenuItem openItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                openItem.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9,
                        0xCE)));
                // set item width
                openItem.setWidth((int)(dipToPixels(Chats.this,90)));
                // set item title
                openItem.setTitle("Clear Cache");
                // set item title fontsize
                openItem.setTitleSize(18);
                // set item title font color
                openItem.setTitleColor(Color.WHITE);
                // add to menu
                menu.addMenuItem(openItem);

                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
                // set item width
                deleteItem.setWidth((int)dipToPixels(Chats.this,90));
                // set a icon
                deleteItem.setIcon(R.mipmap.ic_delete);
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };

        listView.setMenuCreator(creator);

        listView.setOnSwipeListener(new SwipeMenuListView.OnSwipeListener() {

            @Override
            public void onSwipeStart(int position) {
                // swipe start
            }

            @Override
            public void onSwipeEnd(int position) {
                // swipe end

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), username.get(i) + " Clicked", Toast.LENGTH_SHORT).show();
                Intent in =new Intent(Chats.this,ChatDetails.class);
                in.putExtra("Messages", allChats.get(i).messages);
                in.putExtra("uname",username.get(i));
                startActivity(in);
            }
        });


        listView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        // clear cache
                        Toast.makeText(getApplicationContext(), "Clear Cache", Toast.LENGTH_SHORT).show();

                        break;
                    case 1:
                        // delete
                        Toast.makeText(getApplicationContext(), "Delete chat", Toast.LENGTH_SHORT).show();

                        break;
                }
                // false : close the menu; true : not close the menu
                return false;
            }
        });

        //listView.setSwipeDirection(SwipeMenuListView.DIRECTION_LEFT);

        chatList.asyncChats.delegate = this;

        String [] userid = {"1"};

        chatList.asyncChats.execute(userid);

        EditText search = (EditText) findViewById(R.id.search_chat);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    public static float dipToPixels(Context context, float dipValue) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dipValue, metrics);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent dbmanager = new Intent(Chats.this,AndroidDatabaseManager.class);
            startActivity(dbmanager);
            return true;
        }
        if (id == R.id.action_exit) {
            System.exit(0);
            return true;
        }
        if (id == R.id.action_add) {
            // Add a new Chat
            Toast.makeText(getApplicationContext(),"Add new Chat",Toast.LENGTH_LONG).show();

            Intent i = new Intent(Chats.this, SearchForUser.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void processfinish(ArrayList<ChatInfo> output) {


        int i =0;
        for (ChatInfo ch  : output
             ) {
            //search.setText(search.getText()+"\n"+ch.getChatID()+" "+ch.getUserIDSender()+" "+ch.getUserIDReceiver());
            username.add("User ID : " + ch.getUserIDReceiver());
            GetLastMessageFromChat getMessages = new GetLastMessageFromChat(1,ch.getChatID());
            GetLastMessageFromChat newmess = new GetLastMessageFromChat(1,1);
            String [] userid = {"1"};
            getMessages.asyncMessages.delegate=this;
            getMessages.asyncMessages.execute(userid);
        }

        allChats=output;
    }

    @Override
    public void processfinishMessages(ArrayList<MessageInfo> allmessages) {
        allChats.get(i).setMessages(allmessages);
        i++;
        if(allmessages.size() > 0) {
            currentMessage = allmessages.get(allmessages.size() - 1).getContent();
            currentDate = allmessages.get(allmessages.size() - 1).getReceived();
        }
        else {
            currentMessage = "No Messages yet";
        }

        messages.add(currentMessage);


        if( DateUtils.isToday(currentDate.getDate()))
        times.add(currentDate.getHours()+":"+currentDate.getMinutes());
        else{
            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            String newDate = df.format(currentDate);
            times.add(newDate);
        }
        SwipeMenuListView listView= (SwipeMenuListView) findViewById(R.id.listView);

        if(username.size()==messages.size()) {
            listView.setAdapter(new CustomAdapter(this, username, profileImages, messages, times));
        }

    }
}

