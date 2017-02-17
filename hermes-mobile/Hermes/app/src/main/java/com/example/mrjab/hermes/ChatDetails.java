package com.example.mrjab.hermes;

import android.content.Intent;
import android.database.DataSetObserver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.text.DateFormatSymbols;
import java.util.Date;
import java.util.List;

public class ChatDetails extends AppCompatActivity {
    ListView listView;
    Button buttonSend;
    EditText chatText;
    ChatArrayAdapter chatArrayAdapter;
    String sentMessages[];
    String receivedMessages[];
    int sentTimes[];
    int receivedTimes[];
    Date[] sentDates;
    Date[] receivedDates;
    private boolean side = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_details);

        Intent i = getIntent();
        String uname = i.getStringExtra("uname");
        sentMessages = new String[]{"Hi", "Whats up", "I am just going to chill at home."};
        receivedMessages = new String[]{"Hello", "Going to a chelsea game tonight", "What about you?", "Okay great"};
        sentTimes = new int[]{1, 3, 6};
        receivedTimes = new int[]{2, 4, 5, 7};

        sentDates = new Date[]{new Date(2017, 2, 14, 14, 32, 0), new Date(2017, 2, 14, 14, 33, 10), new Date(2017, 2, 14, 14, 35, 0)};
        receivedDates = new Date[]{new Date(2017, 2, 14, 14, 32, 10), new Date(2017, 2, 14, 14, 34, 0), new Date(2017, 2, 14, 14, 34, 30), new Date(2017, 2, 14, 14, 36, 0)};


        this.setTitle(uname);

        buttonSend = (Button) findViewById(R.id.send);

        listView = (ListView) findViewById(R.id.msgview);

        chatArrayAdapter = new ChatArrayAdapter(getApplicationContext(), R.layout.right);
        listView.setAdapter(chatArrayAdapter);

        chatText = (EditText) findViewById(R.id.msg);
        chatText.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    return sendChatMessage();
                }
                return false;
            }
        });
        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                sendChatMessage();
            }
        });

        listView.setTranscriptMode(AbsListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
        listView.setAdapter(chatArrayAdapter);

        //to scroll the list view to bottom on data change
        chatArrayAdapter.registerDataSetObserver(new DataSetObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                listView.setSelection(chatArrayAdapter.getCount() - 1);
            }
        });

        int k = 1;
        int m = 0;
        int n = 0;
        chatArrayAdapter.add(new ChatMessage(2, sentDates[0].getDay() + " " + getMonthForInt(sentDates[0].getMonth()-1) + " " + sentDates[0].getYear(),null));
        while (k <= sentTimes.length + receivedTimes.length && m < sentTimes.length && n < receivedTimes.length) {
            if (sentDates[m].compareTo(receivedDates[n]) < 0) {
                chatArrayAdapter.add(new ChatMessage(0, sentMessages[m], sentDates[m])); // true  for right side
                m++;
            } else if (sentDates[m].compareTo(receivedDates[n]) > 0) {
                chatArrayAdapter.add(new ChatMessage(1, receivedMessages[n],receivedDates[n])); // false for left side
                n++;
            }
            k++;
        }
    }

    private boolean sendChatMessage() {
        chatArrayAdapter.add(new ChatMessage(0, chatText.getText().toString(),new Date()));
        chatText.setText("");
        side = !side;
        return true;
    }

    String getMonthForInt(int num) {
        String month = "wrong";
        DateFormatSymbols dfs = new DateFormatSymbols();
        String[] months = dfs.getMonths();
        if (num >= 0 && num <= 11) {
            month = months[num];
        }
        return month;
    }
}