package com.ndtv.consult.threads;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import com.ndtv.consult.dao.PatientData;
import com.ndtv.consult.dao.constants.AppConstants;
import com.ndtv.consult.util.AppPreferences;
import com.ndtv.consult.util.NetworkUtils;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * Created by arindamnath on 28/04/16.
 */
public class LoaderUser extends AsyncTaskLoader<PatientData> {

    private final String json = "{ \"name\": \"Nagaraj M W\", \"id\": \"1\", \"age\": \"26\", \"gender\": \"Male\", \"bloodGroup\": \"A+\", \"city\": \"Bangalore\", \"address\": \"#24, 1st Main, Basaveshwar Nagar Bangalore\", \"emailId\": \"abc@gmail.com\", \"profile\": \"http://thejakartaglobe.beritasatu.com/img/523512-640x360.jpg\", \"mobileNo\": \"9922334457\", \"date\": \"2016-04-16T04:20:30+01:00\" }";

    private NetworkUtils networkUtils;
    private AppPreferences appPreferences;
    private JSONParser parser;
    private PatientData patientData;
    private Bundle params = new Bundle();

    public LoaderUser(Context context, Bundle params) {
        super(context);
        this.parser = new JSONParser();
        this.appPreferences = new AppPreferences(context);
        this.networkUtils = new NetworkUtils(context);
        this.params = params;
    }

    @Override
    public PatientData loadInBackground() {
        if (networkUtils.isNetworkAvailable()) {
            try {
                //Parse the incoming response
                JSONObject response = (JSONObject) parser.parse(json);
                if (response != null) {
                    patientData = new PatientData(getContext());
                    patientData.parse(parser, response);
                }
            } catch (Exception e) {
                Log.e(AppConstants.APP_TAG, e.toString());
            }
            return patientData;
        } else {
            return null;
        }
    }
}
