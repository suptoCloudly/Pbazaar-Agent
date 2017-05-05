package com.pbazaar.pbazaarforagent.firebase;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.pbazaar.pbazaarforagent.helper.PreferenceHelper;

/**
 * Created by supto on 1/20/17.
 */

public class PushNotificationService extends FirebaseMessagingService implements PushConstants {

    private static final String TAG = PushNotificationService.class.getSimpleName();


    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        Log.d(TAG, "Message: " + remoteMessage.getData());

        if (!remoteMessage.getData().isEmpty()) {

            if (remoteMessage.getData().containsKey(VERSION_CODE_KEY)) {

                Log.d(TAG, "Version code: " + PreferenceHelper.getInstance().getLatestVersionCode());
                PreferenceHelper.getInstance().setLatestVersionCode(Integer.valueOf(remoteMessage.getData().get(VERSION_CODE_KEY)));
                Log.d(TAG, "Version code: " + PreferenceHelper.getInstance().getLatestVersionCode());

            }


        }

    }
}