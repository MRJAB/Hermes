package com.example.mrjab.hermes;

/**
 * Created by yatharth on 08/02/17.
 */

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import github.ankushsachdeva.emojicon.EmojiconTextView;
import github.ankushsachdeva.emojicon.emoji.Emojicon;

class ChatArrayAdapter extends ArrayAdapter<ChatMessage> {

    private EmojiconTextView chatText;

    private  TextView dateText;

    private List<ChatMessage> chatMessageList = new ArrayList<ChatMessage>();
    private Context context;

    @Override
    public void add(ChatMessage object) {
        chatMessageList.add(object);
        super.add(object);
    }

    public ChatArrayAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
        this.context = context;
    }

    public int getCount() {
        return this.chatMessageList.size();
    }

    public ChatMessage getItem(int index) {
        return this.chatMessageList.get(index);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ChatMessage chatMessageObj = getItem(position);
        View row = convertView;
        LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (chatMessageObj.left == 0) {
            row = inflater.inflate(R.layout.right, parent, false);
        }else if (chatMessageObj.left == 1){
            row = inflater.inflate(R.layout.left, parent, false);
        }
        else
        {
            row = inflater.inflate(R.layout.center,parent,false);
        }
        chatText = (EmojiconTextView) row.findViewById(R.id.msgr);
        chatText.setText(chatMessageObj.message);

        if(chatMessageObj.received !=null) {
            dateText = (TextView) row.findViewById(R.id.date);
            dateText.setText(chatMessageObj.received.getHours() + ":" + chatMessageObj.received.getMinutes());
        }

        return row;
    }
}