package com.ndtv.consult.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by arindamnath on 26/04/16.
 */
public class AppPreferences {

    private SharedPreferences mAppPrefs;

    public AppPreferences(Context context) {
        mAppPrefs = context.getSharedPreferences("AppPreferences", 0);
    }

    public void setDoctorLoggedIn() {
        SharedPreferences.Editor edit = mAppPrefs.edit();
        edit.putBoolean("doctorLoggedIn", true);
        edit.commit();
    }

    public void setLoggedIn() {
        SharedPreferences.Editor edit = mAppPrefs.edit();
        edit.putBoolean("appLoggedIn", true);
        edit.commit();
    }

    public void setLoggedOut() {
        SharedPreferences.Editor edit = mAppPrefs.edit();
        edit.clear();
        edit.commit();
    }

    public boolean isLoggedIn() {
        return mAppPrefs.getBoolean("appLoggedIn", false);
    }

    public boolean isDoctorLoggedIn() {
        return mAppPrefs.getBoolean("doctorLoggedIn", false);
    }
}
