package com.ndtv.consult.threads;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import com.ndtv.consult.dao.ChatData;
import com.ndtv.consult.dao.constants.AppConstants;
import com.ndtv.consult.util.AppPreferences;
import com.ndtv.consult.util.NetworkUtils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arindamnath on 28/04/16.
 */
public class LoaderConversation extends AsyncTaskLoader<List<ChatData>>
{
    private final String json = "{ \"response\":[\n" +
            "\t{\"text\" : \"Hi\", \"date\" : 1461781800000, \"sameUser\" : true},\n" +
            "\t{\"text\" : \"Hi\", \"date\" : 1461781800000, \"sameUser\" : false},\n" +
            "\t{\"text\" : \"Sir I need help with back pain.\", \"date\" : 1461781800000, \"sameUser\" : true},\n" +
            "\t{\"text\" : \"Can you provide me details?\", \"date\" : 1461781800000, \"sameUser\" : false},\n" +
            "\t{\"text\" : \"Sure\", \"date\" : 1461781800000, \"sameUser\" : true},\n" +
            "\t{\"text\" : \"It started back at the gym.\", \"date\" : 1461781800000, \"sameUser\" : true}\n" +
            "]}";

    private NetworkUtils networkUtils;
    private AppPreferences appPreferences;
    private JSONParser parser;
    private Bundle params = new Bundle();
    private List<ChatData> chatDatas = new ArrayList<>();

    public LoaderConversation(Context context, Bundle params) {
        super(context);
        this.parser = new JSONParser();
        this.appPreferences = new AppPreferences(context);
        this.networkUtils = new NetworkUtils(context);
        this.params = params;
    }

    @Override
    public List<ChatData> loadInBackground() {
        if (networkUtils.isNetworkAvailable()) {
            try {
                //Parse the incoming response
                JSONObject response = (JSONObject) parser.parse(json);
                this.chatDatas.clear();
                JSONArray responseObj = (JSONArray) response.get("response");
                if (responseObj.size() > 0) {
                    for (int i = 0; i < responseObj.size(); i++) {
                        ChatData chatData = new ChatData(getContext());
                        chatData.parse(parser, (JSONObject) responseObj.get(i));
                        chatDatas.add(chatData);
                    }
                }
            } catch (Exception e) {
                Log.e(AppConstants.APP_TAG, e.toString());
            }
            return chatDatas;
        } else {
            return null;
        }
    }
}