package com.pbazaar.pbazaarforagent.ui.registration;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatRadioButton;
import android.support.v7.widget.AppCompatSpinner;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Toast;

import com.pbazaar.pbazaarforagent.CustomAreaSpinnerAdapter;
import com.pbazaar.pbazaarforagent.R;
import com.pbazaar.pbazaarforagent.model.LocationSpinnerDataModel;
import com.pbazaar.pbazaarforagent.model.RegistrationDataModel;
import com.pbazaar.pbazaarforagent.ui.login.LoginActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class RegistrationFragment extends Fragment implements RegistrationContract.View, View.OnClickListener {


    private static final String TAG = RegistrationFragment.class.getSimpleName();


    @BindView(R.id.root_layout_registration_fragment)
    CoordinatorLayout rootCoordinatorLayout;

    @BindView(R.id.et_first_name_registration_fragment)
    TextInputEditText firstNameET;

    @BindView(R.id.et_last_name_registration_fragment)
    TextInputEditText lastNameET;

    @BindView(R.id.et_mobile_number_registration_fragment)
    TextInputEditText mobileNumberEt;

    @BindView(R.id.et_email_registration_fragment)
    TextInputEditText emailET;

    @BindView(R.id.radio_button_male_registration_fragment)
    AppCompatRadioButton maleRadioButton;

    @BindView(R.id.radio_button_female_registration_fragment)
    AppCompatRadioButton femaleRadioButton;


    @BindView(R.id.radio_button_is_agent_for_data_registration_fragment)
    AppCompatRadioButton isAgentForDataRadioButton;


    @BindView(R.id.radio_button_is_agent_for_search_registration_fragment)
    AppCompatRadioButton isAgentForSearchRadioButton;


    @BindView(R.id.radio_button_is_agent_for_agency_registration_fragment)
    AppCompatRadioButton isAgentForAgencyRadioButton;


    @BindView(R.id.et_street_address_registration_fragment)
    TextInputEditText streetAddressEt;

    @BindView(R.id.et_street_address_two_registration_fragment)
    TextInputEditText streetAddressSecondEt;

    @BindView(R.id.spinner_select_district_registration_fragment)
    AppCompatSpinner selectDistrictSpinner;

    @BindView(R.id.spinner_select_thana_registration_fragment)
    AppCompatSpinner selectThanaSpinner;

    @BindView(R.id.et_referral_registration_fragment)
    TextInputEditText referralEt;

    @BindView(R.id.et_password_registration_fragment)
    TextInputEditText passwordEt;

    @BindView(R.id.et_confirm_password_registration_fragment)
    TextInputEditText confirmPasswordEt;

    @BindView(R.id.button_sign_up_fragment)
    Button signUpButton;


    private RegistrationContract.Presenter presenter;
    private ProgressDialog progressDialog;

    private ArrayList<LocationSpinnerDataModel> districtList;
    private ArrayList<LocationSpinnerDataModel> thanaList;

    private CustomAreaSpinnerAdapter districtListAdapter;
    private CustomAreaSpinnerAdapter thanaListAdapter;

    public RegistrationFragment() {
        // Required empty public constructor

    }

    public static RegistrationFragment newInstance() {
        RegistrationFragment registrationFragment = new RegistrationFragment();
        return registrationFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_registration, container, false);

        ButterKnife.bind(this, view);

        signUpButton.setOnClickListener(this);

        // TODO apply some styling for progressbar
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage(getString(R.string.network_operation_running_message));
        progressDialog.setCancelable(false);


        // initialize district and thana list spinner
        districtList = new ArrayList<>();
        thanaList = new ArrayList<>();

        districtList.add(new LocationSpinnerDataModel("Select District", -1));
        thanaList.add(new LocationSpinnerDataModel("Select Thana", -1));

        districtListAdapter = new CustomAreaSpinnerAdapter(getActivity(), districtList);
        thanaListAdapter = new CustomAreaSpinnerAdapter(getActivity(), thanaList);

        selectDistrictSpinner.setAdapter(districtListAdapter);
        selectThanaSpinner.setAdapter(thanaListAdapter);


        selectDistrictSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG, "Selected: " + districtList.get(position).getLocationId());
                presenter.onDistrictSelected(districtList.get(position).getLocationId());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (presenter == null) {
            presenter = RegistrationPresenter.getInstance(this);
            presenter.start();
        }
    }


    @Override
    public void onClick(View view) {
        if (view == signUpButton) {
            Log.d(TAG, "Click click");


            // Extract all data
            String firstName = firstNameET.getText().toString();
            String lastName = lastNameET.getText().toString();
            String mobile = mobileNumberEt.getText().toString();
            String email = emailET.getText().toString();
            String gender = (maleRadioButton.isChecked() ? "M" : "F");

            boolean isAgentForData = isAgentForDataRadioButton.isChecked();
            boolean isAgentForSearch = isAgentForSearchRadioButton.isChecked();
            boolean isAgentForAgency = isAgentForAgencyRadioButton.isChecked();

            int districtId = districtList.get(selectDistrictSpinner.getSelectedItemPosition()).getLocationId();
            int thanaAreaId = thanaList.get(selectThanaSpinner.getSelectedItemPosition()).getLocationId();
            String streetAddress = streetAddressEt.getText().toString();
            String streetAddress2 = streetAddressSecondEt.getText().toString();

            String referral = referralEt.getText().toString();

            String password = passwordEt.getText().toString();
            String confirmPassword = confirmPasswordEt.getText().toString();


            Log.d(TAG, "District: " + districtId + " thana: " + thanaAreaId);


            // apply form validations
            if (!emptyInputFieldValidation(firstNameET, lastNameET, mobileNumberEt, emailET, streetAddressEt, passwordEt, confirmPasswordEt)) {
                return;
            }

            if (!password.contentEquals(confirmPassword)) {
                showMessage(getString(R.string.passeword_mismatch_error_text));
                return;
            }

            if (districtId == -1 || thanaAreaId == -1) {
                showMessage(getString(R.string.invalid_address_error_text));
                return;
            }


            // create a registration model data and pass it to presenter

            RegistrationDataModel registrationDataModel = new RegistrationDataModel(firstName, lastName, mobile, email, gender, isAgentForData, isAgentForSearch, isAgentForAgency, districtId, thanaAreaId, streetAddress, streetAddress2, referral, password);
            presenter.onRegistrationButtonClicked(registrationDataModel);
        }
    }

    @Override
    public void setPresenter(RegistrationContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onRegistrationSuccess(String email, String password) {

        // show a toast message ant start the login activity and pass login credential to it
        Toast.makeText(getActivity(), R.string.registration_successfull_note, Toast.LENGTH_SHORT).show();

        Bundle bundle = new Bundle();
        bundle.putString(LoginActivity.ARG_EMAIL, email);
        bundle.putString(LoginActivity.ARG_PASSWORD, password);

        Intent loginActivityIntent = new Intent(getActivity(), LoginActivity.class);
        loginActivityIntent.putExtras(bundle);
        startActivity(loginActivityIntent);
        getActivity().finish();
        getActivity().overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
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
        Snackbar.make(rootCoordinatorLayout, message, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onDistrictLoaded(ArrayList<LocationSpinnerDataModel> districtList) {
        Log.d(TAG, "Distric list size: " + districtList.size());
        this.districtList.clear();
        this.districtList.add(new LocationSpinnerDataModel("Select District", -1));
        this.districtList.addAll(districtList);
        districtListAdapter = new CustomAreaSpinnerAdapter(getActivity(), this.districtList);
        selectDistrictSpinner.setAdapter(districtListAdapter);
    }

    @Override
    public void onThanaLoaded(ArrayList<LocationSpinnerDataModel> thanaList) {
        this.thanaList.clear();
        this.thanaList.add(new LocationSpinnerDataModel("Select Thana", -1));
        this.thanaList.addAll(thanaList);
        thanaListAdapter = new CustomAreaSpinnerAdapter(getActivity(), this.thanaList);
        selectThanaSpinner.setAdapter(thanaListAdapter);
    }

    @Override
    public void onInvalidReferralCodeGiven() {
        referralEt.setError(getString(R.string.invalid_referral_code_error_message));
    }


    private boolean emptyInputFieldValidation(TextInputEditText... textInputEditTextGroups) {

        boolean validation = true;

        for (TextInputEditText textInputEditText : textInputEditTextGroups) {
            String inputText = textInputEditText.getText().toString();
            if (inputText.matches("")) {
                textInputEditText.setError(getString(R.string.empty_input_field_error_message));
                validation = false;
            }
        }

        return validation;
    }

}
