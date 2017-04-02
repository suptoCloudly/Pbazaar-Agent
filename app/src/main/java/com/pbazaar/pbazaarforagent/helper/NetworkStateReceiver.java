package com.pbazaar.pbazaarforagent.helper;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkStateReceiver extends BroadcastReceiver {

    public static final String TAG = "NetworkStateReceiver";

    public NetworkStateReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager
                .getActiveNetworkInfo();

        // Check internet connection and set the current network state in sharedpreference
        if (networkInfo != null && networkInfo.isConnected()) {
            PreferenceHelper.getInstance().setNetworkStatus(true);
        } else {
            PreferenceHelper.getInstance().setNetworkStatus(false);
        }

    }

}
