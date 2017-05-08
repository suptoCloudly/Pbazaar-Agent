package com.pbazaar.pbazaarforagent.ui.forgot_password;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.pbazaar.pbazaarforagent.R;
import com.pbazaar.pbazaarforagent.ui.dialogs.ShowMessageDialog;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ForgotPasswordFragment extends Fragment implements ForgotPasswordContract.View, View.OnClickListener {

    private static final String TAG = ForgotPasswordFragment.class.getSimpleName();

    @BindView(R.id.et_email_forgot_password_fragment)
    TextInputEditText emailEt;

    @BindView(R.id.button_request_password_forgot_password_fragment)
    Button requestPasswordButton;

    private ProgressDialog progressDialog;
    private String email;

    private ForgotPasswordContract.Presenter presenter;

    public ForgotPasswordFragment() {
    }

    public static ForgotPasswordFragment newInstance() {
        ForgotPasswordFragment forgotPasswordFragment = new ForgotPasswordFragment();
        return forgotPasswordFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setRetainInstance(true);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (presenter == null) {
            presenter = ForgotPasswordPresenter.newInstance(this);
            presenter.start();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_forgot_password, container, false);
        ButterKnife.bind(this, view);

        requestPasswordButton.setOnClickListener(this);

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage(getString(R.string.network_operation_running_message));
        progressDialog.setCancelable(false);

        return view;
    }

    @Override
    public void setPresenter(ForgotPasswordContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showMessage(String message) {
        ShowMessageDialog showMessageDialog = ShowMessageDialog.newInstance(message);
        showMessageDialog.show(getActivity().getSupportFragmentManager(), ShowMessageDialog.class.getSimpleName());
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
    public void onClick(View v) {
        if (v == requestPasswordButton) {
            email = emailEt.getText().toString();

            emptyInputFieldValidation(emailEt);

            presenter.onRequestPasswordClicked(email);
        }
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
