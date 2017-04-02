package com.pbazaar.pbazaarforagent.helper;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by supto on 4/2/17.
 */

public class PreferenceHelper {

    private static final String TAG = PreferenceHelper.class.getSimpleName();


    public static final String NETWORK_STATUS_KEY = "network_state_key";
    public static final boolean NETWORK_STATUS_DEFAULT_VALUE = false;

    private static PreferenceHelper instance;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    private PreferenceHelper() {
        preferences = PreferenceManager.getDefaultSharedPreferences(AppController.getInstance());
        editor = preferences.edit();
    }

    public static PreferenceHelper getInstance() {
        if (instance == null) {
            instance = new PreferenceHelper();
        }

        return instance;
    }

    public void setNetworkStatus(boolean status) {
        editor.putBoolean(NETWORK_STATUS_KEY, status).apply();
    }

    public boolean getNetworkStatus() {
        return preferences.getBoolean(NETWORK_STATUS_KEY, NETWORK_STATUS_DEFAULT_VALUE);
    }
}
