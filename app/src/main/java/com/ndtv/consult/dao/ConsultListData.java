package com.ndtv.consult.dao;

import android.content.Context;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.util.Date;

/**
 * Created by arindamnath on 26/04/16.
 */
public class ConsultListData extends BaseDAO {

    private String name;
    private String lastChatText;
    private boolean isNew;
    private Long date;

    public ConsultListData(Context context) {
        super(context);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastChatText() {
        return lastChatText;
    }

    public void setLastChatText(String lastChatText) {
        this.lastChatText = lastChatText;
    }

    public boolean isNew() {
        return isNew;
    }

    public void setIsNew(boolean isNew) {
        this.isNew = isNew;
    }

    public String getDate() {
        return getDateFormat().format(new Date(date));
    }

    public void setDate(Long date) {
        this.date = date;
    }

    @Override
    public void parse(JSONParser jsonParser, JSONObject jsonObject) {
        setName(jsonObject.get("name").toString());
        setDate((Long) jsonObject.get("date"));
        setLastChatText(jsonObject.get("lastChat").toString());
        setIsNew((Boolean) jsonObject.get("isNew"));
    }
}
