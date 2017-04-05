package com.pbazaar.pbazaarforagent.ui.login;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.pbazaar.pbazaarforagent.R;
import com.pbazaar.pbazaarforagent.helper.PreferenceHelper;
import com.pbazaar.pbazaarforagent.model.LoginCredentialModel;
import com.pbazaar.pbazaarforagent.model.UserDataModel;
import com.pbazaar.pbazaarforagent.ui.main.MainActivity;
import com.pbazaar.pbazaarforagent.ui.registration.RegistrationActivity;

import butterknife.BindView;
import butterknife.ButterKnife;


public class LoginFragment extends Fragment implements LoginContract.View, View.OnClickListener {

    public static final String TAG = LoginFragment.class.getSimpleName();


    @BindView(R.id.root_layout_login_fragment)
    CoordinatorLayout rootCoordinatorLayout;

    @BindView(R.id.et_email_login_fragment)
    TextInputEditText emailEt;

    @BindView(R.id.et_password_login_fragment)
    TextInputEditText passwordEt;

    @BindView(R.id.button_login_login_fragment)
    Button logInButton;

    @BindView(R.id.tv_sign_up)
    TextView signUpTv;

    private ProgressDialog progressDialog;
    private LoginContract.Presenter presenter;

    private String email;
    private String password;

    public LoginFragment() {
    }


    public static LoginFragment newInstance(String email, String password) {
        LoginFragment loginFragment = new LoginFragment();

        Bundle bundle = new Bundle();
        bundle.putString(LoginActivity.ARG_EMAIL, email);
        bundle.putString(LoginActivity.ARG_PASSWORD, password);

        loginFragment.setArguments(bundle);
        return loginFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        ButterKnife.bind(this, view);


        logInButton.setOnClickListener(this);
        signUpTv.setOnClickListener(this);

        // TODO apply some styling for progressbar
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage(getString(R.string.network_operation_running_message));


        // get the email and password and set it in the test edit test fields
        email = getArguments().getString(LoginActivity.ARG_EMAIL);
        password = getArguments().getString(LoginActivity.ARG_PASSWORD);
        emailEt.setText(email);
        passwordEt.setText(password);

        Log.d(TAG, "Email: " + email);
        Log.d(TAG, "Pass: " + password);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (presenter == null) {
            presenter = new LoginPresenter(this);
            presenter.start();
        }

    }


    @Override
    public void onClick(View v) {
        if (v == logInButton) {

            emptyInputFieldValidation(emailEt, passwordEt);

            String email = emailEt.getText().toString();
            String password = passwordEt.getText().toString();
            LoginCredentialModel loginCredentialModel = new LoginCredentialModel(email, password);

            presenter.onLoginButtonClicked(loginCredentialModel);
        } else if (v == signUpTv) {
            // start registration activity

            getActivity().finish();


            Intent intent = new Intent(getActivity(), RegistrationActivity.class);
            startActivity(intent);
            getActivity().overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
        }

    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onLoginSuccess(UserDataModel userDataModel) {

        PreferenceHelper.getInstance().setCustomerId(userDataModel.getCustomerId());

        getActivity().finish();
        Intent productPostingIntent = new Intent(getActivity(), MainActivity.class);
        startActivity(productPostingIntent);
        getActivity().overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
    }

    @Override
    public void setLoadingIndicator(boolean status) {

        if (status) {
            progressDialog.show();
        } else {
            progressDialog.dismiss();
        }

    }


    @Override
    public void showMessage(String message) {
        Log.d(TAG, message);
        Snackbar.make(rootCoordinatorLayout, message, Snackbar.LENGTH_SHORT).show();
    }


    private void emptyInputFieldValidation(TextInputEditText... textInputEditTextGroups) {

        for (TextInputEditText textInputEditText : textInputEditTextGroups) {
            String inputText = textInputEditText.getText().toString();
            if (inputText.matches("")) {
                textInputEditText.setError(getString(R.string.empty_input_field_error_message));
            }
        }
    }
}
