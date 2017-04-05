package com.pbazaar.pbazaarforagent.helper;

import android.app.Application;
import android.content.Context;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

/**
 * Created by supto on 4/2/17.
 */

public class AppController extends Application {

    private static final String TAG = AppController.class.getSimpleName();

    private static AppController instance;
    private RefWatcher refWatcher;

    public static RefWatcher getRefWatcher() {
        return instance.refWatcher;
    }

    public static AppController getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            return;
        }
        refWatcher = LeakCanary.install(this);
    }
}
