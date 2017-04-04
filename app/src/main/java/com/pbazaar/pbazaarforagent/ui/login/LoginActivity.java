package com.pbazaar.pbazaarforagent.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.FrameLayout;

import com.pbazaar.pbazaarforagent.R;
import com.pbazaar.pbazaarforagent.helper.PreferenceHelper;
import com.pbazaar.pbazaarforagent.ui.main.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {

    public static final String ARG_EMAIL = "arg_email";
    public static final String ARG_PASSWORD = "arg_password";
    private static final String TAG = LoginActivity.class.getSimpleName();
    private static final String LOGIN_FRAGMENT_TAG = "login_fragment_tag";
    @BindView(R.id.fragment_login_container)
    FrameLayout fragmentContainer;

    private LoginFragment loginFragment;

    private String email = "";
    private String password = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // if a user is logged in then start main activity
        if (PreferenceHelper.getInstance().getCustomerId() != PreferenceHelper.CUSTOMER_ID_DEFAULT_VALUE) {
            finish();
            Intent mainActivityIntent = new Intent(this, MainActivity.class);
            startActivity(mainActivityIntent);
        }


        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        // if registration activity returns a email and password then pass it to fragment
        if (getIntent().getExtras() != null) {
            email = getIntent().getExtras().getString(ARG_EMAIL);
            password = getIntent().getExtras().getString(ARG_PASSWORD);
        }


        Log.d(TAG, "Email: " + email);
        Log.d(TAG, "Pass: " + password);

        loginFragment = (LoginFragment) getSupportFragmentManager().findFragmentByTag(LOGIN_FRAGMENT_TAG);

        if (loginFragment == null) {
            loginFragment = LoginFragment.newInstance(email, password);
            Log.d(TAG, "New Login fragment created");
        }

        getSupportFragmentManager().beginTransaction().replace(fragmentContainer.getId(), loginFragment, LOGIN_FRAGMENT_TAG).commit();

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();

        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }


}
