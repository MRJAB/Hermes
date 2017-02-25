package com.example.mrjab.hermes;

import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.text.format.DateUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;


import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import java.util.List;

import github.ankushsachdeva.emojicon.EmojiconEditText;
import github.ankushsachdeva.emojicon.EmojiconGridView;
import github.ankushsachdeva.emojicon.EmojiconsPopup;
import github.ankushsachdeva.emojicon.emoji.Emojicon;

public class ChatDetails extends AppCompatActivity {
    ListView listView;
    ImageButton buttonSend;
    EditText chatText;
    ChatArrayAdapter chatArrayAdapter;

    ArrayList<String> sentMessages=new ArrayList<>();
    ArrayList<String> receivedMessages=new ArrayList<>();
    int sentTimes[];
    int receivedTimes[];
    ArrayList<Date> sentDates=new ArrayList<>();
    ArrayList<Date> receivedDates=new ArrayList<>();

    int userIDSelf = 1;
    private boolean side = false;
    ArrayList<MessageInfo> myList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_details);


        Intent i = getIntent();
        String uname = i.getStringExtra("uname");
        myList = (ArrayList<MessageInfo>) i.getSerializableExtra("Messages");



        //sentMessages = new String[]{"Hi", "Whats up", "I am just going to chill at home."};
        //receivedMessages = new String[]{"Hello", "Going to a chelsea game tonight", "What about you?", "Okay great"};
        sentTimes = new int[]{1, 3, 6};
        receivedTimes = new int[]{2, 4, 5, 7};

        //sentDates = new Date[]{new Date(2017, 2, 14, 14, 32, 0), new Date(2017, 2, 14, 14, 33, 10), new Date(2017, 2, 14, 14, 35, 0)};
        //receivedDates = new Date[]{new Date(2017, 2, 14, 14, 32, 10), new Date(2017, 2, 14, 14, 34, 0), new Date(2017, 2, 14, 14, 34, 30), new Date(2017, 2, 14, 14, 36, 0)};


        this.setTitle(uname);

        setupEmoji();

        buttonSend = (ImageButton) findViewById(R.id.send);

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

        updateMessageAndDates();

    }


    public void updateMessageAndDates()
    {

        chatArrayAdapter=new ChatArrayAdapter(getApplicationContext(), R.layout.right);
        sentMessages = new ArrayList<>();
        sentDates = new ArrayList<>();

        receivedDates = new ArrayList<>();
        receivedMessages = new ArrayList<>();
        for (MessageInfo m:myList
                ) {
            if(m.getUserIDSender() == userIDSelf)
            {
                sentMessages.add(m.getContent());
                sentDates.add(m.getReceived());
            }
            else
            {
                receivedMessages.add(m.getContent());
                receivedDates.add(m.getReceived());
            }
        }
        int k = 1;
        int m = 0;
        int n = 0;
        //chatArrayAdapter.add(new ChatMessage(2, sentDates.get(0).getDay() + " " + getMonthForInt(sentDates.get(0).getMonth()-1) + " " + sentDates.get(0).getYear(),null));
        Date first = myList.get(0).getReceived();
        if(DateUtils.isToday(first.getTime()))
        {
            chatArrayAdapter.add(new ChatMessage(2,"Today",null));

        }
        else{
            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            String newDate = df.format(first);
            chatArrayAdapter.add(new ChatMessage(2, newDate ,null));
        }
        while (k <= myList.size()) {
            if(myList.get(k-1).receivedDate.getDay() == first.getDay() && myList.get(k-1).receivedDate.getMonth() == first.getMonth()
                    && myList.get(k-1).receivedDate.getYear() == first.getYear()){

            }
            else
            {
                first = myList.get(k-1).getReceived();
                if(DateUtils.isToday(first.getTime()))
                {
                    chatArrayAdapter.add(new ChatMessage(2,"Today",null));

                }
                else{
                    SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                    String newDate = df.format(first);
                    chatArrayAdapter.add(new ChatMessage(2, newDate ,null));

                }

            }

            if(n>=receivedDates.size())
            {
                chatArrayAdapter.add(new ChatMessage(0, sentMessages.get(m), sentDates.get(m))); // true  for right side
                m++;
            }
            else if(m>=sentDates.size())
            {
                chatArrayAdapter.add(new ChatMessage(1, receivedMessages.get(n), receivedDates.get(n))); // false for left side
                n++;
            }
            else {
                if (sentDates.get(m).compareTo(receivedDates.get(n)) < 0) {
                    chatArrayAdapter.add(new ChatMessage(0, sentMessages.get(m), sentDates.get(m))); // true  for right side
                    m++;
                } else if (sentDates.get(m).compareTo(receivedDates.get(n)) > 0) {
                    chatArrayAdapter.add(new ChatMessage(1, receivedMessages.get(n), receivedDates.get(n))); // false for left side
                    n++;
                }
            }
            k++;
        }

        listView.setAdapter(chatArrayAdapter);

        //to scroll the list view to bottom on data change
        chatArrayAdapter.registerDataSetObserver(new DataSetObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                listView.setSelection(chatArrayAdapter.getCount() - 1);
            }
        });

    }


    public  void setupEmoji(){

        final ImageView emojiButton = (ImageView) findViewById(R.id.btn_emoji);

        final EmojiconEditText emojiconEditText = (EmojiconEditText) findViewById(R.id.msg);
        final View rootView = findViewById(R.id.root_view);

        // Give the topmost view of your activity layout hierarchy. This will be used to measure soft keyboard height
        final EmojiconsPopup popup = new EmojiconsPopup(rootView, this);

        //Will automatically set size according to the soft keyboard size
        popup.setSizeForSoftKeyboard();

        //If the emoji popup is dismissed, change emojiButton to smiley icon
        popup.setOnDismissListener(new PopupWindow.OnDismissListener() {

            @Override
            public void onDismiss() {
                changeEmojiKeyboardIcon(emojiButton, R.drawable.ic_action_emoji2);
            }
        });

        //If the text keyboard closes, also dismiss the emoji popup
        popup.setOnSoftKeyboardOpenCloseListener(new EmojiconsPopup.OnSoftKeyboardOpenCloseListener() {

            @Override
            public void onKeyboardOpen(int keyBoardHeight) {

            }

            @Override
            public void onKeyboardClose() {
                if(popup.isShowing())
                    popup.dismiss();
            }
        });

        //On emoji clicked, add it to edittext
        popup.setOnEmojiconClickedListener(new EmojiconGridView.OnEmojiconClickedListener() {

            @Override
            public void onEmojiconClicked(Emojicon emojicon) {
                if (emojiconEditText == null || emojicon == null) {
                    return;
                }

                int start = emojiconEditText.getSelectionStart();
                int end = emojiconEditText.getSelectionEnd();
                if (start < 0) {
                    emojiconEditText.append(emojicon.getEmoji());
                } else {
                    emojiconEditText.getText().replace(Math.min(start, end),
                            Math.max(start, end), emojicon.getEmoji(), 0,
                            emojicon.getEmoji().length());
                }
            }
        });

        //On backspace clicked, emulate the KEYCODE_DEL key event
        popup.setOnEmojiconBackspaceClickedListener(new EmojiconsPopup.OnEmojiconBackspaceClickedListener() {

            @Override
            public void onEmojiconBackspaceClicked(View v) {
                KeyEvent event = new KeyEvent(
                        0, 0, 0, KeyEvent.KEYCODE_DEL, 0, 0, 0, 0, KeyEvent.KEYCODE_ENDCALL);
                emojiconEditText.dispatchKeyEvent(event);
            }
        });

        // To toggle between text keyboard and emoji keyboard keyboard(Popup)
        emojiButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                //If popup is not showing => emoji keyboard is not visible, we need to show it
                if(!popup.isShowing()){

                    //If keyboard is visible, simply show the emoji popup
                    if(popup.isKeyBoardOpen()){
                        popup.showAtBottom();
                        changeEmojiKeyboardIcon(emojiButton, R.drawable.ic_action_keyboard);
                    }

                    //else, open the text keyboard first and immediately after that show the emoji popup
                    else{
                        emojiconEditText.setFocusableInTouchMode(true);
                        emojiconEditText.requestFocus();
                        popup.showAtBottomPending();
                        final InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        inputMethodManager.showSoftInput(emojiconEditText, InputMethodManager.SHOW_IMPLICIT);
                        changeEmojiKeyboardIcon(emojiButton, R.drawable.ic_action_keyboard);
                    }
                }

                //If popup is showing, simply dismiss it to show the undelying text keyboard
                else{
                    popup.dismiss();
                }
            }
        });
    }


    private void changeEmojiKeyboardIcon(ImageView iconToBeChanged, int drawableResourceId){
        iconToBeChanged.setImageResource(drawableResourceId);
    }


    private boolean sendChatMessage() {
        //chatArrayAdapter.add(new ChatMessage(0, chatText.getText().toString(),new Date()));
        MessageInfo msg = new MessageInfo((int)(Math.random()*100), myList.get(0).getChatID(),userIDSelf,chatText.getText().toString(),new Date());
        myList.add(msg);
        updateMessageAndDates();
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

