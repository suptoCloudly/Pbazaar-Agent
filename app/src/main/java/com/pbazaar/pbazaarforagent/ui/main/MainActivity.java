package com.pbazaar.pbazaarforagent.ui.main;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.pbazaar.pbazaarforagent.R;
import com.pbazaar.pbazaarforagent.helper.PreferenceHelper;
import com.pbazaar.pbazaarforagent.ui.login.LoginActivity;
import com.pbazaar.pbazaarforagent.ui.main.product.ProductPostingFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private static final int MY_PERMISSIONS_REQUEST_CODE = 420;
    public static final String ACTION = "no_permission_receiver";

    @BindView(R.id.fragment_container_main_activity)
    FrameLayout fragmentContainer;


    private ProductPostingFragment productPostingFragment;

    private final BroadcastReceiver mNotificationReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(TAG, "ON RECEIVE");

            NoPermissionDialog noPermissionDialog = new NoPermissionDialog();
            noPermissionDialog.setOnConfirmButtonmClickListener(new NoPermissionDialog.OnConfirmButtonmClickListener() {
                @Override
                public void onConfirmButtonClicked() {

                    requestPermission();
                }
            });
            noPermissionDialog.show(MainActivity.this.getSupportFragmentManager(), NoPermissionDialog.class.getSimpleName());
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        getSupportActionBar().setElevation(0);
        getSupportActionBar().setTitle("Post Product");

        productPostingFragment = (ProductPostingFragment) getSupportFragmentManager().findFragmentByTag("AAAA");

        if (productPostingFragment == null) {
            productPostingFragment = ProductPostingFragment.getInstance();
            Log.d(TAG, "Fragment created");
        }

        getSupportFragmentManager().beginTransaction().replace(fragmentContainer.getId(), productPostingFragment, "AAAA").commit();


        // check for the permission
        LocalBroadcastManager.getInstance(this).registerReceiver(mNotificationReceiver, new IntentFilter(ACTION));
        requestPermission();


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        LocalBroadcastManager.getInstance(this).unregisterReceiver(mNotificationReceiver);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.log_out_menu_button) {
            logout();
        }

        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_CODE: {

                boolean status = true;
                Log.d(TAG, "statussize : " + grantResults.length);
                for (int reult : grantResults) {

                    Log.d(TAG, "status: " + reult);

                    if (reult != PackageManager.PERMISSION_GRANTED) {
                        status = false;
                        break;
                    }
                }

                if (!status) {

                    // permission was granted, yay! do the
                    // calendar task you need to do.

                    Intent intent = new Intent(ACTION);
                    LocalBroadcastManager.getInstance(MainActivity.this).sendBroadcast(intent);

                }
                return;
            }
        }
    }

    private void requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED ||
                    checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                            != PackageManager.PERMISSION_GRANTED) {

                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE
                                , Manifest.permission.WRITE_EXTERNAL_STORAGE}
                        , MY_PERMISSIONS_REQUEST_CODE);
                return;
            }
        }
    }


    private void logout() {
        // clear the customerId saved in preference and start login activity

        PreferenceHelper.getInstance().setCustomerId(PreferenceHelper.CUSTOMER_ID_DEFAULT_VALUE);

        finish();
        Intent logInActivityIntent = new Intent(this, LoginActivity.class);
        startActivity(logInActivityIntent);
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }
}
