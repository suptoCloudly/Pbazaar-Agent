package com.pbazaar.pbazaarforagent.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.pbazaar.pbazaarforagent.R;
import com.pbazaar.pbazaarforagent.helper.PreferenceHelper;
import com.pbazaar.pbazaarforagent.ui.login.LoginActivity;
import com.pbazaar.pbazaarforagent.ui.main.MainActivity;

public class SplashActivity extends AppCompatActivity {

    private static final String TAG = SplashActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        final Intent intent;

        if (PreferenceHelper.getInstance().getCustomerId() == PreferenceHelper.CUSTOMER_ID_DEFAULT_VALUE) {
            Log.d(TAG, "Not Logged In");
            intent = new Intent(SplashActivity.this, LoginActivity.class);
        } else {
            Log.d(TAG, "Logged In");
            intent = new Intent(SplashActivity.this, MainActivity.class);
        }


        Log.d(TAG, "Id: " + PreferenceHelper.getInstance().getCustomerId());


        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
                startActivity(intent);
                overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
            }
        }, 1000);

    }
}
