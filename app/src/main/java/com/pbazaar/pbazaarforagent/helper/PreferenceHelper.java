package com.pbazaar.pbazaarforagent.helper;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by supto on 4/2/17.
 */

public class PreferenceHelper {

    public static final String NETWORK_STATUS_KEY = "network_state_key";
    public static final boolean NETWORK_STATUS_DEFAULT_VALUE = true;
    public static final String CUSTOMER_ID_KEY = "customer_id_key";
    public static final int CUSTOMER_ID_DEFAULT_VALUE = -1;
    public static final String LATEST_VERSION_CODE_KEY = "latest_version_code_key";
    public static final int LATEST_VERSION_CODE_DEFAULT_VALUE = 0;

    private static final String TAG = PreferenceHelper.class.getSimpleName();


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

    public boolean getNetworkStatus() {
        return preferences.getBoolean(NETWORK_STATUS_KEY, NETWORK_STATUS_DEFAULT_VALUE);
    }

    public void setNetworkStatus(boolean status) {
        editor.putBoolean(NETWORK_STATUS_KEY, status).apply();
    }

    public int getCustomerId() {
        return preferences.getInt(CUSTOMER_ID_KEY, CUSTOMER_ID_DEFAULT_VALUE);
    }

    public void setCustomerId(int customerId) {
        editor.putInt(CUSTOMER_ID_KEY, customerId).apply();
    }

    public int getLatestVersionCode() {
        return preferences.getInt(LATEST_VERSION_CODE_KEY, LATEST_VERSION_CODE_DEFAULT_VALUE);
    }

    public void setLatestVersionCode(int versionCode) {
        editor.putInt(LATEST_VERSION_CODE_KEY, versionCode).apply();
    }
}
