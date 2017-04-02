package com.pbazaar.pbazaarforagent.mvp.login;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.pbazaar.pbazaarforagent.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = LoginActivity.class.getSimpleName();

    private static final String LOGIN_FRAGMENT_TAG = "login_fragment_tag";


    @BindView(R.id.fragment_login_container)
    FrameLayout fragmentContainer;

    private LoginFragment loginFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        loginFragment = (LoginFragment) getSupportFragmentManager().findFragmentByTag(LOGIN_FRAGMENT_TAG);

        if (loginFragment == null) {
            loginFragment = LoginFragment.newInstance();
        }

        getSupportFragmentManager().beginTransaction().replace(fragmentContainer.getId(), loginFragment, LOGIN_FRAGMENT_TAG).commit();
    }


}
