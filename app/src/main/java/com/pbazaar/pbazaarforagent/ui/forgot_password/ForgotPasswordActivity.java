package com.pbazaar.pbazaarforagent.ui.forgot_password;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.pbazaar.pbazaarforagent.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ForgotPasswordActivity extends AppCompatActivity {

    private static final String TAG = ForgotPasswordActivity.class.getSimpleName();

    @BindView(R.id.fragment_container_forgot_password_activity)
    FrameLayout fragmentContainer;


    private ForgotPasswordFragment forgotPasswordFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        ButterKnife.bind(this);

        getSupportActionBar().setElevation(0f);
        getSupportActionBar().setTitle("Password Recovery");

        forgotPasswordFragment = (ForgotPasswordFragment) getSupportFragmentManager().findFragmentByTag(ForgotPasswordFragment.class.getSimpleName());

        if (forgotPasswordFragment == null) {
            forgotPasswordFragment = ForgotPasswordFragment.newInstance();
        }

        getSupportFragmentManager().beginTransaction().replace(fragmentContainer.getId(), forgotPasswordFragment, ForgotPasswordFragment.class.getSimpleName()).commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }
}
