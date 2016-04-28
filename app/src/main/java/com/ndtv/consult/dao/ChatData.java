package com.ndtv.consult.dao;

import android.content.Context;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.util.Date;

/**
 * Created by arindamnath on 28/04/16.
 */
public class ChatData extends BaseDAO {

    private String chatText;
    private Long date;
    private boolean isSameUser;

    public ChatData(Context context) {
        super(context);
    }

    @Override
    public void parse(JSONParser jsonParser, JSONObject jsonObject) {
        setChatText(jsonObject.get("text").toString());
        setDate((Long) jsonObject.get("date"));
        setIsSameUser((Boolean) jsonObject.get("sameUser"));
    }

    public String getChatText() {
        return chatText;
    }

    public void setChatText(String chatText) {
        this.chatText = chatText;
    }

    public String getDate() {
        return getDateFormat().format(new Date(date));
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public boolean isSameUser() {
        return isSameUser;
    }

    public void setIsSameUser(boolean isSameUser) {
        this.isSameUser = isSameUser;
    }
}
