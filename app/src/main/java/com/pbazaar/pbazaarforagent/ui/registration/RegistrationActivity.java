package com.pbazaar.pbazaarforagent.ui.registration;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.pbazaar.pbazaarforagent.R;
import com.pbazaar.pbazaarforagent.ui.login.LoginActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegistrationActivity extends AppCompatActivity {

    private static final String TAG = RegistrationActivity.class.getSimpleName();

    public static final String REGISTRATION_FRAGMENT_TAG = "registration_fragment_tag";

    @BindView(R.id.fragment_registration_container)
    FrameLayout fragmentContainer;

    private RegistrationFragment registrationFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        ButterKnife.bind(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Registration");
        getSupportActionBar().setElevation(0f);

        registrationFragment = (RegistrationFragment) getSupportFragmentManager().findFragmentByTag(REGISTRATION_FRAGMENT_TAG);

        if (registrationFragment == null) {
            registrationFragment = RegistrationFragment.newInstance();
        }

        getSupportFragmentManager().beginTransaction().replace(fragmentContainer.getId(), registrationFragment, REGISTRATION_FRAGMENT_TAG).commit();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        // start the login activity
        Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
        startActivity(intent);

        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }
}

