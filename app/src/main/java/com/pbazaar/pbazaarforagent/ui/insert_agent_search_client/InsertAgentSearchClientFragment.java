package com.pbazaar.pbazaarforagent.ui.insert_agent_search_client;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.pbazaar.pbazaarforagent.R;
import com.pbazaar.pbazaarforagent.helper.PreferenceHelper;
import com.pbazaar.pbazaarforagent.helper.RouteHelper;
import com.pbazaar.pbazaarforagent.model.SearchClientModel;
import com.pbazaar.pbazaarforagent.ui.dialogs.ShowMessageDialog;
import com.pbazaar.pbazaarforagent.ui.insert_agent_search_client_requirement.InsertAgentSearchClientRequirementActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class InsertAgentSearchClientFragment extends Fragment implements InsertAgentSearchClientContract.View, View.OnClickListener {

    public static final String ARG_PHONE_NUMBER = "arg_phone_number";
    private static final String TAG = InsertAgentSearchClientFragment.class.getSimpleName();

    @BindView(R.id.et_full_name_insert_agent_search_fragment)
    TextInputEditText fullNameEt;

    @BindView(R.id.et_phone_insert_agent_search_fragment)
    TextInputEditText phoneEt;

    @BindView(R.id.et_email_insert_agent_search_fragment)
    TextInputEditText emailEt;

    @BindView(R.id.button_insert_agent_search_fragment)
    Button insertClientButton;

    private InsertAgentSearchClientContract.Presenter presenter;
    private String phoneNumber;
    private ProgressDialog progressDialog;

    public InsertAgentSearchClientFragment() {
        // Required empty public constructor
    }

    public static InsertAgentSearchClientFragment newInstance(@NonNull String phoneNumber) {

        Bundle bundle = new Bundle();
        bundle.putString(ARG_PHONE_NUMBER, phoneNumber);

        InsertAgentSearchClientFragment insertAgentSearchClientFragment = new InsertAgentSearchClientFragment();
        insertAgentSearchClientFragment.setArguments(bundle);
        return insertAgentSearchClientFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setRetainInstance(true);

        phoneNumber = getArguments().getString(ARG_PHONE_NUMBER);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (presenter == null) {
            presenter = new InsertAgentSearchClientPresenter(this);
            presenter.start();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_insert_agent_search_client, container, false);
        ButterKnife.bind(this, view);

        // set the phone number and make it non editable
        phoneEt.setText(phoneNumber);
        phoneEt.setKeyListener(null);

        insertClientButton.setOnClickListener(this);


        // TODO apply some styling for progressbar
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage(getString(R.string.network_operation_running_message));
        progressDialog.setCancelable(false);


        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.end();
    }

    @Override
    public void setPresenter(InsertAgentSearchClientContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onClick(View view) {
        if (view == insertClientButton) {
            insertClient();
        }
    }

    @Override
    public void showMessage(String message) {
        if (getActivity().getSupportFragmentManager() != null) {
            ShowMessageDialog showMessageDialog = ShowMessageDialog.newInstance(message);
            showMessageDialog.show(getActivity().getSupportFragmentManager(), ShowMessageDialog.class.getSimpleName());
        }
    }

    @Override
    public void showLoadingIndicator(boolean status) {

        if (status) {
            progressDialog.show();
        } else {
            progressDialog.dismiss();
        }
    }

    @Override
    public void onInsertClientSuccess(SearchClientModel searchClientModel) {
        Log.d(TAG, searchClientModel.toString());

        Bundle bundle = new Bundle();
        bundle.putParcelable(InsertAgentSearchClientRequirementActivity.ARG_CLIENT_MODEL, searchClientModel);

        RouteHelper.startActivityWithFinish(getActivity(), InsertAgentSearchClientRequirementActivity.class, bundle);
    }

    private void insertClient() {
        String fullName = fullNameEt.getText().toString();
        String email = emailEt.getText().toString();

        if (!validateForm()) {
            return;
        }

        presenter.onInsertClientButtonClicked(fullName, phoneNumber, email, String.valueOf(PreferenceHelper.getInstance().getCustomerId()));
    }

    private boolean validateForm() {

        if (fullNameEt.getText().toString().trim().length() == 0) {
            fullNameEt.setError("The name field is required");
            showMessage("The name field is required");
            return false;
        }

        if (!emailEt.getText().toString().matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")) {
            emailEt.setError("The email must be a valid email address");
            showMessage("The email must be a valid email address");
            return false;
        }
        return true;
    }
}
