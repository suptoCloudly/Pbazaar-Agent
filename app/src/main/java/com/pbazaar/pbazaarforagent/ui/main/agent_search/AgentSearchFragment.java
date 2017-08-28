package com.pbazaar.pbazaarforagent.ui.main.agent_search;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.pbazaar.pbazaarforagent.R;
import com.pbazaar.pbazaarforagent.helper.RouteHelper;
import com.pbazaar.pbazaarforagent.model.SearchClientModel;
import com.pbazaar.pbazaarforagent.ui.dialogs.ShowMessageDialog;
import com.pbazaar.pbazaarforagent.ui.insert_agent_search_client.InsertAgentSearchClientActivity;
import com.pbazaar.pbazaarforagent.ui.insert_agent_search_client_requirement.InsertAgentSearchClientRequirementActivity;

import butterknife.BindView;
import butterknife.ButterKnife;


public class AgentSearchFragment extends Fragment implements AgentSearchContract.View, View.OnClickListener {

    private static final String TAG = AgentSearchFragment.class.getSimpleName();

    @BindView(R.id.et_agent_phone_agent_search_fragment)
    TextInputEditText phoneNoEt;

    @BindView(R.id.button_submit_client_phn_agent_search_fragment)
    Button submitButton;

    private AgentSearchContract.Presenter presenter;
    private ProgressDialog progressDialog;

    public AgentSearchFragment() {
        // Required empty public constructor
    }


    public static AgentSearchFragment newInstance() {
        AgentSearchFragment fragment = new AgentSearchFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setRetainInstance(true);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (presenter == null) {
            presenter = new AgentSearchPresenter(this);
            presenter.start();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_agent_search, container, false);
        ButterKnife.bind(this, view);

        submitButton.setOnClickListener(this);

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setCancelable(false);
        progressDialog.setMessage(getString(R.string.network_operation_running_message));

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.end();
    }

    @Override
    public void onClick(View view) {

        if (view == submitButton) {
            submitPhoneNo();
        }
    }

    @Override
    public void setPresenter(AgentSearchContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showMessage(@NonNull String message) {
        ShowMessageDialog showMessageDialog = ShowMessageDialog.newInstance(message);
        showMessageDialog.show(getActivity().getSupportFragmentManager(), ShowMessageDialog.class.getSimpleName());
    }

    @Override
    public void showLoadingIndicator(boolean status) {
        if (status)
            progressDialog.show();
        else
            progressDialog.dismiss();
    }

    @Override
    public void onClientNotFound(@NonNull String phoneNumber) {

        Bundle bundle = new Bundle();
        bundle.putString(InsertAgentSearchClientActivity.ARG_CLIENT_PHONE, phoneNumber);

        RouteHelper.startActivity(getActivity(), InsertAgentSearchClientActivity.class, bundle);
    }

    @Override
    public void onClientFound(SearchClientModel searchClientModel) {


        Bundle bundle = new Bundle();
        bundle.putParcelable(InsertAgentSearchClientRequirementActivity.ARG_CLIENT_MODEL, searchClientModel);

        RouteHelper.startActivity(getActivity(), InsertAgentSearchClientRequirementActivity.class, bundle);


    }

    private void submitPhoneNo() {
        String phoneNumber = phoneNoEt.getText().toString();

        if (phoneNumber.trim().length() == 0) {
            phoneNoEt.setError(getString(R.string.insert_client_phon_no));
            showMessage(getString(R.string.insert_client_phon_no));

            return;
        }

        phoneNoEt.setText("");
        presenter.onSubmitPhoneButtonClicked(phoneNumber);

    }
}
