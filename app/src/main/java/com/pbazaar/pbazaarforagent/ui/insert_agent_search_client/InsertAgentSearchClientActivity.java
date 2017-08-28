package com.pbazaar.pbazaarforagent.ui.insert_agent_search_client;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.pbazaar.pbazaarforagent.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InsertAgentSearchClientActivity extends AppCompatActivity {


    public static final String ARG_CLIENT_PHONE = "arg_client_phn";
    private static final String TAG = InsertAgentSearchClientActivity.class.getSimpleName();


    @BindView(R.id.fragment_container_insert_agent_search_activity)
    FrameLayout fragmentContainer;

    private String phoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_agent_search_client);
        ButterKnife.bind(this);


        getSupportActionBar().setTitle(R.string.insert_agent_search_client);

        phoneNumber = getIntent().getExtras().getString(ARG_CLIENT_PHONE);

        showAgentSearchClientFragment(phoneNumber);
    }

    private void showAgentSearchClientFragment(@NonNull String phoneNumber) {
        InsertAgentSearchClientFragment insertAgentSearchClientFragment = (InsertAgentSearchClientFragment) getSupportFragmentManager().findFragmentByTag(InsertAgentSearchClientActivity.class.getSimpleName());


        if (insertAgentSearchClientFragment == null) {
            insertAgentSearchClientFragment = InsertAgentSearchClientFragment.newInstance(phoneNumber);
        }

        getSupportFragmentManager().beginTransaction()
                .replace(fragmentContainer.getId(), insertAgentSearchClientFragment, InsertAgentSearchClientFragment.class.getSimpleName())
                .commit();
    }
}
