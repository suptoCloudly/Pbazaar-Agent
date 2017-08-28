package com.pbazaar.pbazaarforagent.ui.insert_agent_search_client_requirement;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.pbazaar.pbazaarforagent.R;
import com.pbazaar.pbazaarforagent.model.SearchClientModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InsertAgentSearchClientRequirementActivity extends AppCompatActivity {

    public static final String ARG_CLIENT_MODEL = "arg_client_model";
    private static final String TAG = InsertAgentSearchClientRequirementActivity.class.getSimpleName();


    @BindView(R.id.fragment_container_insert_agent_search_client_activity)
    FrameLayout fragmentContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_agent_search_client_requirement);
        ButterKnife.bind(this);

        getSupportActionBar().setTitle(R.string.insert_client_requirements);

        if (getIntent().getExtras() != null) {
            SearchClientModel searchClientModel = getIntent().getExtras().getParcelable(ARG_CLIENT_MODEL);
            showInsertAgentSearchClientRequirementFragment(searchClientModel);
        } else {
            onBackPressed();
        }
    }

    private void showInsertAgentSearchClientRequirementFragment(SearchClientModel searchClientModel) {

        InsertAgentSearchClientRequirementFragment fragment = (InsertAgentSearchClientRequirementFragment) getSupportFragmentManager().findFragmentByTag(InsertAgentSearchClientRequirementFragment.class.getSimpleName());

        if (fragment == null) {
            fragment = InsertAgentSearchClientRequirementFragment.newInstance(searchClientModel);
        }

        getSupportFragmentManager().beginTransaction()
                .replace(fragmentContainer.getId(), fragment, InsertAgentSearchClientRequirementFragment.class.getSimpleName())
                .commit();
    }

}
