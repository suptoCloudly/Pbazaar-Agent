package com.pbazaar.pbazaarforagent.mvp.registration;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatSpinner;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.pbazaar.pbazaarforagent.CustomSpinnerAdapter;
import com.pbazaar.pbazaarforagent.R;
import com.pbazaar.pbazaarforagent.model.LocationSpinnerDataModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class RegistrationFragment extends Fragment implements RegistrationContract.View, View.OnClickListener {


    private static final String TAG = RegistrationFragment.class.getSimpleName();


    @BindView(R.id.root_layout_registration_fragment)
    CoordinatorLayout rootCoordinatorLayout;

    @BindView(R.id.spinner_select_district_registration_fragment)
    AppCompatSpinner selectDistrictSpinner;

    @BindView(R.id.spinner_select_thana_registration_fragment)
    AppCompatSpinner selectThanaSpinner;

    @BindView(R.id.button_sign_up_fragment)
    Button signUpButton;


    private RegistrationContract.Presenter presenter;
    private ProgressDialog progressDialog;

    private ArrayList<LocationSpinnerDataModel> districtList;
    private ArrayList<LocationSpinnerDataModel> thanaList;

    private CustomSpinnerAdapter districtListAdapter;
    private CustomSpinnerAdapter thanaListAdapter;

    public static RegistrationFragment newInstance() {
        RegistrationFragment registrationFragment = new RegistrationFragment();
        return registrationFragment;
    }

    public RegistrationFragment() {
        // Required empty public constructor
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


        // initialize district and thana list spinner
        districtList = new ArrayList<>();
        thanaList = new ArrayList<>();

        districtList.add(new LocationSpinnerDataModel("Select District", -1));
        thanaList.add(new LocationSpinnerDataModel("Select Thana", -1));

        districtListAdapter = new CustomSpinnerAdapter(getActivity(), districtList);
        thanaListAdapter = new CustomSpinnerAdapter(getActivity(), thanaList);

        selectDistrictSpinner.setAdapter(districtListAdapter);
        selectThanaSpinner.setAdapter(thanaListAdapter);

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
            presenter.onRegistrationButtonClicked();
        }
    }

    @Override
    public void setPresenter(RegistrationContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onRegistrationSuccess() {

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
        districtListAdapter = new CustomSpinnerAdapter(getActivity(), this.districtList);
        selectDistrictSpinner.setAdapter(districtListAdapter);
    }

    @Override
    public void onThanaLoaded(ArrayList<String> thanaList) {

    }

}
