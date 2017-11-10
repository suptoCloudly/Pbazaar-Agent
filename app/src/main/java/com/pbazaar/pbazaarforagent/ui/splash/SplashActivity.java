package com.pbazaar.pbazaarforagent.ui.splash;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessaging;
import com.pbazaar.pbazaarforagent.R;
import com.pbazaar.pbazaarforagent.firebase.PushConstants;
import com.pbazaar.pbazaarforagent.helper.PreferenceHelper;
import com.pbazaar.pbazaarforagent.ui.dialogs.ForceUpdateDialog;
import com.pbazaar.pbazaarforagent.ui.login.LoginActivity;
import com.pbazaar.pbazaarforagent.ui.main.MainActivity;

public class SplashActivity extends AppCompatActivity implements ForceUpdateDialog.OnButtonClicked {

    public static final String DEVELOPER_STATUS_PATH = "developerStatus";
    public static final String DEVELOPER_HAPPY = "happy";
    private static final String TAG = SplashActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        FirebaseMessaging.getInstance().subscribeToTopic(PushConstants.TOPIC_NAME);


        if (!PreferenceHelper.getInstance().getNetworkStatus()) {
            Toast.makeText(this, getString(R.string.no_internet_error_message), Toast.LENGTH_SHORT).show();
        }


        int versionCode = getCurrentVersionCode();
        if (versionCode < PreferenceHelper.getInstance().getLatestVersionCode()) {

            ForceUpdateDialog forceUpdateDialog = ForceUpdateDialog.newInstance(SplashActivity.this);
            forceUpdateDialog.setCancelable(false);
            forceUpdateDialog.show(getSupportFragmentManager(), ForceUpdateDialog.class.getSimpleName());

        } else {


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
            }, 2000);


        }
    }


    @Override
    public void onConfirmButtonClicked() {

        String packageName = getApplication().getPackageName();
        Log.d(TAG, "Package name: " + packageName);
        try {
            Intent playStoreIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + packageName));
            playStoreIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(playStoreIntent);
        } catch (android.content.ActivityNotFoundException anfe) {
            Intent playStoreIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + packageName));
            playStoreIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(playStoreIntent);
        }

        finish();
    }

    @Override
    public void onCancelButtonClicked() {
        finish();
    }

    private int getCurrentVersionCode() {
        PackageManager manager = getPackageManager();
        PackageInfo info = null;
        try {
            info = manager.getPackageInfo(getPackageName(), 0);
            return info.versionCode;

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return 0;
    }
}
