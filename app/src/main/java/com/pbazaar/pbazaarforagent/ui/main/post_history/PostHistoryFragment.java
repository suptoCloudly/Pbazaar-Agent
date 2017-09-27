package com.pbazaar.pbazaarforagent.ui.main.post_history;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pbazaar.pbazaarforagent.R;
import com.pbazaar.pbazaarforagent.helper.PreferenceHelper;
import com.pbazaar.pbazaarforagent.model.PostHistoryModel;
import com.pbazaar.pbazaarforagent.ui.dialogs.ShowMessageDialog;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class PostHistoryFragment extends Fragment implements PostHistoryContract.View, PostHistoryConstants {

    public static final String TAG = PostHistoryFragment.class.getSimpleName();
    private static final String ARG_HISTORY_LIST = "arg_history_list";


    // ui fields for this week
    @BindView(R.id.tv_this_week_total_post_post_history)
    TextView thisWeekTotalPostTV;

    @BindView(R.id.tv_this_invalid_post_history)
    TextView thisWeekInvalidTv;

    @BindView(R.id.tv_this_week_already_exists_post_history)
    TextView thisWeekAlreadyExistsTv;

    @BindView(R.id.tv_this_week_rent_out_post_history)
    TextView thisWeekAlreadyRentOutTv;

    @BindView(R.id.tv_this_week_sold_out_post_history)
    TextView thisWeekAlreadySoldOutTv;

    @BindView(R.id.tv_this_week_successful_post_history)
    TextView thisWeekSuccessfulTv;

    @BindView(R.id.tv_this_week_check_in_progress_post_history)
    TextView thisWeekCheckInProgressTv;

    @BindView(R.id.tv_this_week_not_interested_post_history)
    TextView thisWeekNotInterestedProgressTv;


    // ui field for total
    @BindView(R.id.tv_total_total_post_post_history)
    TextView totalTotalPostTV;

    @BindView(R.id.tv_total_invalid_post_history)
    TextView totalInvalidTv;

    @BindView(R.id.tv_total_already_exists_post_history)
    TextView totalAlreadyExistsTv;

    @BindView(R.id.tv_total_rent_out_post_history)
    TextView totalAlreadyRentOutTv;

    @BindView(R.id.tv_total_sold_out_post_history)
    TextView totalAlreadySoldOutTv;

    @BindView(R.id.tv_total_successful_post_history)
    TextView totalSuccessfulTv;

    @BindView(R.id.tv_total_check_in_progress_post_history)
    TextView totalCheckInProgressTv;

    @BindView(R.id.tv_total_not_interested_post_history)
    TextView totalNotInterestedTv;


    private ArrayList<PostHistoryModel> postHistoryList = new ArrayList<>();
    private PostHistoryContract.Presenter presenter;
    private ProgressDialog progressDialog;

    public PostHistoryFragment() {
    }

    public static PostHistoryFragment newInstance() {
        PostHistoryFragment postHistoryFragment = new PostHistoryFragment();
        return postHistoryFragment;
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
            presenter = PostHistoryPresenter.newInstance(this);
        }
        presenter.start();


        if (savedInstanceState == null) {
            presenter.loadHistory(PreferenceHelper.getInstance().getCustomerId());
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_post_history, container, false);
        ButterKnife.bind(this, view);


        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setCancelable(false);
        progressDialog.setMessage(getString(R.string.network_operation_running_message));


        if (savedInstanceState != null) {
            Log.d(TAG, "History from saveinstancestate");
            postHistoryList = savedInstanceState.getParcelableArrayList(ARG_HISTORY_LIST);
            setUiWithHistoryData(postHistoryList);
        }

        return view;
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(ARG_HISTORY_LIST, postHistoryList);
    }

    @Override
    public void setPresenter(PostHistoryContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showMessage(String message) {
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
    public void showHistoryData(ArrayList<PostHistoryModel> postHistoryModels) {
        Log.d(TAG, "History from online");
        this.postHistoryList = postHistoryModels;
        setUiWithHistoryData(postHistoryModels);
    }

    private void setUiWithHistoryData(ArrayList<PostHistoryModel> postHistoryModels) {

        int totalThisWeek = 0;
        int invalidThisWeek = 0;
        int alreadyRentOutThisWeek = 0;
        int alreadySoldOutThisWeek = 0;
        int successfulThisWeek = 0;
        int checkInProgressThisWeek = 0;
        int alreadyExistsThisWeek = 0;
        int notInterestedThisWeek = 0;

        int totalTotal = 0;
        int invalidTotal = 0;
        int alreadyRentOutTotal = 0;
        int alreadySoldOutTotal = 0;
        int successfulTotal = 0;
        int checkInProgressTotal = 0;
        int alreadyExistsTotal = 0;
        int notInterestedTotal = 0;


        for (PostHistoryModel postHistoryModel : postHistoryModels) {
            switch (postHistoryModel.getProductInfoStatus()) {

                case NotCalledYet:

                    checkInProgressThisWeek = checkInProgressThisWeek + postHistoryModel.getCountThisWeek();
                    checkInProgressTotal = checkInProgressTotal + postHistoryModel.getCountAllTime();

                    break;

                case NoAnswer:

                    invalidThisWeek = invalidThisWeek + postHistoryModel.getCountThisWeek();
                    invalidTotal = invalidTotal + postHistoryModel.getCountAllTime();

                    break;

                case SwitchedOff:

                    invalidThisWeek = invalidThisWeek + postHistoryModel.getCountThisWeek();
                    invalidTotal = invalidTotal + postHistoryModel.getCountAllTime();

                    break;

                case Busy:


                    invalidThisWeek = invalidThisWeek + postHistoryModel.getCountThisWeek();
                    invalidTotal = invalidTotal + postHistoryModel.getCountAllTime();

                    break;

                case CallLater:

                    invalidThisWeek = invalidThisWeek + postHistoryModel.getCountThisWeek();
                    invalidTotal = invalidTotal + postHistoryModel.getCountAllTime();

                    break;

                case NotInterested:


//                    successfulThisWeek = successfulThisWeek + postHistoryModel.getCountThisWeek();
//                    successfulTotal = successfulTotal + postHistoryModel.getCountAllTime();

                    notInterestedThisWeek = notInterestedThisWeek + postHistoryModel.getCountThisWeek();
                    notInterestedTotal = notInterestedTotal + postHistoryModel.getCountAllTime();

                    break;

                case NotAwared:


                    successfulThisWeek = successfulThisWeek + postHistoryModel.getCountThisWeek();
                    successfulTotal = successfulTotal + postHistoryModel.getCountAllTime();

                    break;

                case Invalid:


                    invalidThisWeek = invalidThisWeek + postHistoryModel.getCountThisWeek();
                    invalidTotal = invalidTotal + postHistoryModel.getCountAllTime();

                    break;

                case RentOut:

                    alreadyRentOutThisWeek = alreadyRentOutThisWeek + postHistoryModel.getCountThisWeek();
                    alreadyRentOutTotal = alreadyRentOutTotal + postHistoryModel.getCountAllTime();

                    break;

                case SoldOut:


                    alreadySoldOutThisWeek = alreadySoldOutThisWeek + postHistoryModel.getCountThisWeek();
                    alreadySoldOutTotal = alreadySoldOutTotal + postHistoryModel.getCountAllTime();

                    break;

                case Finished:

                    successfulThisWeek = successfulThisWeek + postHistoryModel.getCountThisWeek();
                    successfulTotal = successfulTotal + postHistoryModel.getCountAllTime();

                    break;

                case AlreadyExists:


                    alreadyExistsThisWeek = alreadyExistsThisWeek + postHistoryModel.getCountThisWeek();
                    alreadyExistsTotal = alreadyExistsTotal + postHistoryModel.getCountAllTime();

                    break;
            }

            totalThisWeek = invalidThisWeek + alreadyRentOutThisWeek + alreadySoldOutThisWeek +
                    successfulThisWeek + checkInProgressThisWeek + alreadyExistsThisWeek;

            totalTotal = invalidTotal + alreadyRentOutTotal + alreadySoldOutTotal +
                    successfulTotal + checkInProgressTotal + alreadyExistsTotal;

            thisWeekTotalPostTV.setText(String.valueOf(totalThisWeek));
            thisWeekInvalidTv.setText(String.valueOf(invalidThisWeek));
            thisWeekAlreadyRentOutTv.setText(String.valueOf(alreadyRentOutThisWeek));
            thisWeekAlreadySoldOutTv.setText(String.valueOf(alreadySoldOutThisWeek));
            thisWeekSuccessfulTv.setText(String.valueOf(successfulThisWeek));
            thisWeekCheckInProgressTv.setText(String.valueOf(checkInProgressThisWeek));
            thisWeekAlreadyExistsTv.setText(String.valueOf(alreadyExistsThisWeek));
            thisWeekNotInterestedProgressTv.setText(String.valueOf(notInterestedThisWeek));


            totalTotalPostTV.setText(String.valueOf(totalTotal));
            totalInvalidTv.setText(String.valueOf(invalidTotal));
            totalAlreadyRentOutTv.setText(String.valueOf(alreadyRentOutTotal));
            totalAlreadySoldOutTv.setText(String.valueOf(alreadySoldOutTotal));
            totalSuccessfulTv.setText(String.valueOf(successfulTotal));
            totalCheckInProgressTv.setText(String.valueOf(checkInProgressTotal));
            totalAlreadyExistsTv.setText(String.valueOf(alreadyExistsTotal));
            totalNotInterestedTv.setText(String.valueOf(notInterestedTotal));
        }
    }
}
