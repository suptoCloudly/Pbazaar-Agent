package com.pbazaar.pbazaarforagent.helper;

import android.app.Application;


/**
 * Created by supto on 4/2/17.
 */

public class AppController extends Application {

    private static final String TAG = AppController.class.getSimpleName();

    private static AppController instance;


    public static AppController getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

}
