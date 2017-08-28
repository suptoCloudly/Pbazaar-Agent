package com.pbazaar.pbazaarforagent.ui.insert_agent_search_client_requirement;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;

import com.pbazaar.pbazaarforagent.CustomAreaSpinnerAdapter;
import com.pbazaar.pbazaarforagent.R;
import com.pbazaar.pbazaarforagent.model.LocationSpinnerDataModel;
import com.pbazaar.pbazaarforagent.model.SearchClientModel;
import com.pbazaar.pbazaarforagent.ui.dialogs.ShowMessageDialog;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class InsertAgentSearchClientRequirementFragment extends Fragment implements InsertAgentSearchClientRequirementContract.View {

    public static final String ARG_SEARCH_CLIENT_MODEL = "arg_search_client_model";
    public static final String ARG_DISTRICT_LIST = "arg_district_list";
    public static final String ARG_AREA_LIST = "arg_area_list";
    public static final String ARG_CATEGORY_LIST = "arg_category_list";
    private static final String TAG = InsertAgentSearchClientRequirementFragment.class.getSimpleName();

    @BindView(R.id.district_spinner_insert_client_requirement_fragment)
    Spinner districtSpinner;

    @BindView(R.id.area_spinner_insert_client_requirement_fragment)
    Spinner areaSpinner;

    @BindView(R.id.category_spinner_insert_client_requirement_fragment)
    Spinner categorySpinner;

    @BindView(R.id.min_budget_et_insert_client_requirement_fragment)
    EditText minBudgetEt;

    @BindView(R.id.max_budget_et_insert_client_requirement_fragment)
    EditText maxBudgetEt;

    private InsertAgentSearchClientRequirementContract.Presenter presenter;
    private SearchClientModel searchClientModel;
    private ProgressDialog progressDialog;

    private ArrayList<LocationSpinnerDataModel> districtList;
    private CustomAreaSpinnerAdapter districtListAdapter;

    private ArrayList<LocationSpinnerDataModel> thanaList;
    private CustomAreaSpinnerAdapter thanaListAdapter;

    public InsertAgentSearchClientRequirementFragment() {
        // Required empty public constructor
    }

    public static InsertAgentSearchClientRequirementFragment newInstance(SearchClientModel searchClientModel) {
        InsertAgentSearchClientRequirementFragment fragment = new InsertAgentSearchClientRequirementFragment();

        Bundle bundle = new Bundle();
        bundle.putParcelable(ARG_SEARCH_CLIENT_MODEL, searchClientModel);

        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setRetainInstance(true);

        searchClientModel = getArguments().getParcelable(ARG_SEARCH_CLIENT_MODEL);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_insert_agent_search_client_requirement, container, false);
        ButterKnife.bind(this, view);

        // TODO apply some styling for progressbar
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Connecting to pBazaar");
        progressDialog.setCancelable(false);


        districtList = new ArrayList<>();
        districtList.add(new LocationSpinnerDataModel("Select District", 0));
        districtListAdapter = new CustomAreaSpinnerAdapter(getActivity(), districtList);
        districtSpinner.setAdapter(districtListAdapter);

        thanaList = new ArrayList<>();
        thanaList.add(new LocationSpinnerDataModel("Select Area", 0));
        thanaListAdapter = new CustomAreaSpinnerAdapter(getActivity(), thanaList);
        areaSpinner.setAdapter(thanaListAdapter);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (presenter == null) {
            presenter = new InsertAgentSearchClientRequirementPresenter(this);
            presenter.start();
        }

        if (savedInstanceState == null) {
            presenter.initialDataLoad();
//            presenter.loadAreaList(78);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(ARG_DISTRICT_LIST, districtList);
        outState.putParcelableArrayList(ARG_AREA_LIST, thanaList);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);

        if (savedInstanceState != null) {

            ArrayList<LocationSpinnerDataModel> tempDistList = savedInstanceState.getParcelableArrayList(ARG_DISTRICT_LIST);
            districtList.clear();
            districtList.addAll(tempDistList);
            districtListAdapter.notifyDataSetChanged();

            ArrayList<LocationSpinnerDataModel> tempThaneList = savedInstanceState.getParcelableArrayList(ARG_AREA_LIST);
            thanaList.clear();
            thanaList.addAll(tempThaneList);
            thanaListAdapter.notifyDataSetChanged();

        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.end();
    }

    @Override
    public void setPresenter(InsertAgentSearchClientRequirementContract.Presenter presenter) {
        this.presenter = presenter;
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
        if (status)
            progressDialog.show();
        else
            progressDialog.dismiss();
    }

    @Override
    public void onDistrictListLoaded(ArrayList<LocationSpinnerDataModel> districtList) {
        this.districtList.clear();
        this.districtList.addAll(districtList);
        districtListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onThanaLoaded(ArrayList<LocationSpinnerDataModel> thanaList) {
        this.thanaList.clear();
        this.thanaList.addAll(thanaList);
        thanaListAdapter.notifyDataSetChanged();
    }
}
