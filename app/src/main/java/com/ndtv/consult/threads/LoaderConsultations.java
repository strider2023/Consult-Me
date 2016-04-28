package com.ndtv.consult.threads;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import com.ndtv.consult.dao.ConsultListData;
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
public class LoaderConsultations extends AsyncTaskLoader<List<ConsultListData>> {

    private final String json = "{ \"response\":[\n" +
            "\t{\"name\":\"Maitri Nath\",\"id\":1,\"city\": \"Bangalore\", \"profile\": \"http://thejakartaglobe.beritasatu.com/img/523512-640x360.jpg\", \"date\": 1461781800000, \"lastChat\" : \"Need assistance with heart attack\", \"isNew\" : true },\n" +
            "\t{\"name\":\"Arindam Nath\", \"id\": \"2\", \"city\": \"Bangalore\", \"profile\": \"http://thejakartaglobe.beritasatu.com/img/523512-640x360.jpg\", \"date\": 1461695400000, \"lastChat\" : \"SOS\", \"isNew\" : true },\n" +
            "\t{\"name\":\"Aroon Nath\", \"id\": \"2\", \"city\": \"Bangalore\", \"profile\": \"http://thejakartaglobe.beritasatu.com/img/523512-640x360.jpg\", \"date\": 1460572200000, \"lastChat\" : \"Thanks for your advice :)\", \"isNew\" : false }\n" +
            "]}";

    private final String json_doc = "{ \"response\":[\n" +
            "\t{\"name\":\"Dr. Maitri Nath\",\"id\":1,\"city\": \"Bangalore\", \"profile\": \"http://thejakartaglobe.beritasatu.com/img/523512-640x360.jpg\", \"date\": 1461781800000, \"lastChat\" : \"Need assistance with heart attack\", \"isNew\" : true },\n" +
            "\t{\"name\":\"Dr. Arindam Nath\", \"id\": \"2\", \"city\": \"Bangalore\", \"profile\": \"http://thejakartaglobe.beritasatu.com/img/523512-640x360.jpg\", \"date\": 1461695400000, \"lastChat\" : \"SOS\", \"isNew\" : true },\n" +
            "\t{\"name\":\"Dr. Aroon Nath\", \"id\": \"2\", \"city\": \"Bangalore\", \"profile\": \"http://thejakartaglobe.beritasatu.com/img/523512-640x360.jpg\", \"date\": 1460572200000, \"lastChat\" : \"Thanks for your advice :)\", \"isNew\" : false }\n" +
            "]}";

    private NetworkUtils networkUtils;
    private AppPreferences appPreferences;
    private JSONParser parser;
    private Bundle params = new Bundle();
    private List<ConsultListData> consultListDatas = new ArrayList<>();

    public LoaderConsultations(Context context, Bundle params) {
        super(context);
        this.parser = new JSONParser();
        this.appPreferences = new AppPreferences(context);
        this.networkUtils = new NetworkUtils(context);
        this.params = params;
    }

    @Override
    public List<ConsultListData> loadInBackground() {
        if (networkUtils.isNetworkAvailable()) {
            try {
                //Parse the incoming response
                JSONObject response = (JSONObject) parser.parse((appPreferences.isDoctorLoggedIn()) ? json_doc : json);
                this.consultListDatas.clear();
                JSONArray responseObj = (JSONArray) response.get("response");
                if (responseObj.size() > 0) {
                    for (int i = 0; i < responseObj.size(); i++) {
                        ConsultListData consultListData = new ConsultListData(getContext());
                        consultListData.parse(parser, (JSONObject) responseObj.get(i));
                        consultListDatas.add(consultListData);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(AppConstants.APP_TAG, e.toString());
            }
            return consultListDatas;
        } else {
            return null;
        }
    }
}