package com.pbazaar.pbazaarforagent.ui.main;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.pbazaar.pbazaarforagent.R;
import com.pbazaar.pbazaarforagent.helper.PreferenceHelper;
import com.pbazaar.pbazaarforagent.ui.dialogs.ShareReferralDialog;
import com.pbazaar.pbazaarforagent.ui.login.LoginActivity;
import com.pbazaar.pbazaarforagent.ui.main.agent_search.AgentSearchFragment;
import com.pbazaar.pbazaarforagent.ui.main.payment.PayHistoryFragment;
import com.pbazaar.pbazaarforagent.ui.main.post_history.PostHistoryFragment;
import com.pbazaar.pbazaarforagent.ui.main.product.ProductPostingFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, ShareReferralDialog.OnShareButtonClickListener {

    public static final String ACTION = "no_permission_receiver";
    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int MY_PERMISSIONS_REQUEST_CODE = 420;


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


    @BindView(R.id.drawer_layout_main_activity)
    DrawerLayout drawerLayout;

    @BindView(R.id.navigation_view_main_activity)
    NavigationView navigationView;

    @BindView(R.id.toolbar_main_activity)
    Toolbar toolbar;

    @BindView(R.id.fragment_container_main_activity)
    FrameLayout fragmentContainer;


    private ProductPostingFragment productPostingFragment;
    private AgentSearchFragment agentSearchFragment;
    private PayHistoryFragment payHistoryFragment;
    private PostHistoryFragment postHistoryFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        showPostProductFragment();


        // check for the permission
        LocalBroadcastManager.getInstance(this).registerReceiver(mNotificationReceiver, new IntentFilter(ACTION));
        requestPermission();


        // initialize the navigation drawer
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        navigationView.getMenu().getItem(0).setChecked(true);
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

        if (id == R.id.share_referral_code_menu_button) {
            shareReferralCode();
        } else if (id == R.id.log_out_menu_button) {
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
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        drawerLayout.closeDrawers();

        navigationView.getMenu().getItem(0).setChecked(false);
        navigationView.getMenu().getItem(1).setChecked(false);
        navigationView.getMenu().getItem(2).setChecked(false);

        switch (item.getItemId()) {
            case R.id.product_posting_nav_menu_item:

                navigationView.getMenu().getItem(0).setChecked(true);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        showPostProductFragment();
                    }
                }, 200);

                return true;
//            case R.id.agent_search_nav_menu_item:
//                navigationView.getMenu().getItem(0).setChecked(false);
//                navigationView.getMenu().getItem(2).setChecked(false);
//                navigationView.getMenu().getItem(3).setChecked(false);
//                navigationView.getMenu().getItem(1).setChecked(true);
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        showAgentSearchFragment();
//                    }
//                }, 200);
//
//                return true;
            case R.id.post_history_nav_menu_item:

                navigationView.getMenu().getItem(1).setChecked(true);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        showPostHistoryFragment();
                    }
                }, 200);

                return true;
            case R.id.payment_nav_menu_item:

                navigationView.getMenu().getItem(2).setChecked(true);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        showPaymentHistoryFragment();
                    }
                }, 200);

                return true;
            case R.id.share_referral_nav_menu_item:
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        ShareReferralDialog shareReferralDialog = ShareReferralDialog.newInstance(MainActivity.this);
                        shareReferralDialog.show(getSupportFragmentManager(), ShareReferralDialog.class.getSimpleName());
                    }
                }, 200);

                return true;
            default:
                return true;
        }
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
                    // crazy task you need to do.

                    Intent intent = new Intent(ACTION);
                    LocalBroadcastManager.getInstance(MainActivity.this).sendBroadcast(intent);

                }
                return;
            }
        }
    }


    private void showPostProductFragment() {
        productPostingFragment = (ProductPostingFragment) getSupportFragmentManager().findFragmentByTag("AAAA");
        if (productPostingFragment == null) {
            productPostingFragment = ProductPostingFragment.getInstance();
            Log.d(TAG, "Fragment created");
        }
        getSupportFragmentManager().beginTransaction().replace(fragmentContainer.getId(), productPostingFragment, "AAAA").commit();


        getSupportActionBar().setTitle(getString(R.string.post_product_nav_menu_item));

    }

    private void showAgentSearchFragment() {

        agentSearchFragment = (AgentSearchFragment) getSupportFragmentManager().findFragmentByTag(AgentSearchFragment.class.getSimpleName());
        if (agentSearchFragment == null) {
            agentSearchFragment = AgentSearchFragment.newInstance();
        }

        getSupportFragmentManager().beginTransaction()
                .replace(fragmentContainer.getId(), agentSearchFragment, AgentSearchFragment.class.getSimpleName())
                .commit();
        getSupportActionBar().setTitle(getString(R.string.agent_search_nav_menu_item));
    }


    private void showPaymentHistoryFragment() {
        payHistoryFragment = (PayHistoryFragment) getSupportFragmentManager().findFragmentByTag(PayHistoryFragment.class.getSimpleName());

        if (payHistoryFragment == null) {
            payHistoryFragment = PayHistoryFragment.newInstance();
            Log.d(TAG, "Payment Fragment created");
        }

        getSupportFragmentManager().beginTransaction().replace(fragmentContainer.getId(), payHistoryFragment, PayHistoryFragment.class.getSimpleName()).commit();

        getSupportActionBar().setTitle(getString(R.string.payment_history_nav_menu_item));

    }

    private void showPostHistoryFragment() {
        postHistoryFragment = (PostHistoryFragment) getSupportFragmentManager().findFragmentByTag(PostHistoryFragment.class.getSimpleName());

        if (postHistoryFragment == null) {
            postHistoryFragment = PostHistoryFragment.newInstance();
            Log.d(TAG, "Payment Fragment created");
        }

        getSupportFragmentManager().beginTransaction().replace(fragmentContainer.getId(), postHistoryFragment, PostHistoryFragment.class.getSimpleName()).commit();

        getSupportActionBar().setTitle(getString(R.string.post_history_nav_menu_item));
    }

    private void requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED ||
                    checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                            != PackageManager.PERMISSION_GRANTED ||
                    checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                            != PackageManager.PERMISSION_GRANTED ||
                    checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION)
                            != PackageManager.PERMISSION_GRANTED

                    ) {

                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE
                                , Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.ACCESS_FINE_LOCATION,
                                Manifest.permission.ACCESS_COARSE_LOCATION}
                        , MY_PERMISSIONS_REQUEST_CODE);
            }
        }
    }


    private void shareReferralCode() {
        String shareBody = getResources().getString(R.string.share_referral_code_message) + " " + PreferenceHelper.getInstance().getCustomerId();

        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, getString(R.string.share_referral_intent_subject));
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        startActivity(Intent.createChooser(sharingIntent, getResources().getString(R.string.share_using)));

    }

    private void logout() {
        // clear the customerId saved in preference and start login activity

        PreferenceHelper.getInstance().setCustomerId(PreferenceHelper.CUSTOMER_ID_DEFAULT_VALUE);

        finish();
        Intent logInActivityIntent = new Intent(this, LoginActivity.class);
        startActivity(logInActivityIntent);
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    @Override
    public void onShareButtonClicked() {
        shareReferralCode();
    }
}
