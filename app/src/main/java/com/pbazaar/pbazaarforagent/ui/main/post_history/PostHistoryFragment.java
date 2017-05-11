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
    @BindView(R.id.tv_this_week_not_called_yet_post_history)
    TextView thisWeekNotCalledYet;

    @BindView(R.id.tv_this_week_no_answer_post_history)
    TextView thisWeekNoAnswerTv;

    @BindView(R.id.tv_this_switched_off_post_history)
    TextView thisWeekSwitchedOffTv;

    @BindView(R.id.tv_this_week_busy_post_history)
    TextView thisWeekBusyTv;

    @BindView(R.id.tv_this_week_call_later_post_history)
    TextView thisWeekCallLaterTv;

    @BindView(R.id.tv_this_week_not_interested_post_history)
    TextView thisWeekNotinterestedTv;

    @BindView(R.id.tv_this_week_not_awarded_post_history)
    TextView thisWeekNotAwardedTv;

    @BindView(R.id.tv_this_week_invalid_post_history)
    TextView thisWeekInvalidPostTv;

    @BindView(R.id.tv_this_week_rent_out_post_history)
    TextView thisWeekRentOutTv;

    @BindView(R.id.tv_this_week_sold_out_post_history)
    TextView thisWeekSoldOutTv;

    @BindView(R.id.tv_this_week_finished_post_history)
    TextView thisWeekFinishedPostTv;

    @BindView(R.id.tv_this_week_already_exists_post_history)
    TextView thisWeekAlreadyExistTv;


    // ui field for total
    @BindView(R.id.tv_total_not_called_yet_post_history)
    TextView totalNotCalledYet;

    @BindView(R.id.tv_total_no_answer_post_history)
    TextView totalNoAnswerTv;

    @BindView(R.id.tv_total_switched_off_post_history)
    TextView totalSwitchedOffTv;

    @BindView(R.id.tv_total_busy_post_history)
    TextView totalBusyTv;

    @BindView(R.id.tv_total_call_later_post_history)
    TextView totalCallLaterTv;

    @BindView(R.id.tv_total_not_interested_post_history)
    TextView totalNotinterestedTv;

    @BindView(R.id.tv_total_not_awarded_post_history)
    TextView totalNotAwardedTv;

    @BindView(R.id.tv_total_invalid_post_history)
    TextView totalInvalidPostTv;

    @BindView(R.id.tv_total_rent_out_post_history)
    TextView totalRentOutTv;

    @BindView(R.id.tv_total_sold_out_post_history)
    TextView totalSoldOutTv;

    @BindView(R.id.tv_total_finished_post_history)
    TextView totalFinishedPostTv;

    @BindView(R.id.tv_total_already_exists_post_history)
    TextView totalAlreadyExistTv;

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

        for (PostHistoryModel postHistoryModel : postHistoryModels) {
            switch (postHistoryModel.getProductInfoStatus()) {

                case NotCalledYet:
                    thisWeekNotCalledYet.setText(""+postHistoryModel.getCountThisWeek());
                    totalNotCalledYet.setText(""+postHistoryModel.getCountAllTime());
                    break;

                case NoAnswer:
                    thisWeekNoAnswerTv.setText(""+postHistoryModel.getCountThisWeek());
                    totalNoAnswerTv.setText(""+postHistoryModel.getCountAllTime());
                    break;

                case SwitchedOff:
                    thisWeekSwitchedOffTv.setText(""+postHistoryModel.getCountThisWeek());
                    totalSwitchedOffTv.setText(""+postHistoryModel.getCountAllTime());
                    break;

                case Busy:
                    thisWeekBusyTv.setText(""+postHistoryModel.getCountThisWeek());
                    totalBusyTv.setText(""+postHistoryModel.getCountAllTime());
                    break;

                case CallLater:
                    thisWeekCallLaterTv.setText(""+postHistoryModel.getCountThisWeek());
                    totalCallLaterTv.setText(""+postHistoryModel.getCountAllTime());
                    break;

                case NotInterested:
                    thisWeekNotinterestedTv.setText(""+postHistoryModel.getCountThisWeek());
                    totalNotinterestedTv.setText(""+postHistoryModel.getCountAllTime());
                    break;

                case NotAwared:
                    thisWeekNotAwardedTv.setText(""+postHistoryModel.getCountThisWeek());
                    totalNotAwardedTv.setText(""+postHistoryModel.getCountAllTime());
                    break;

                case Invalid:
                    thisWeekInvalidPostTv.setText(""+postHistoryModel.getCountThisWeek());
                    totalInvalidPostTv.setText(""+postHistoryModel.getCountAllTime());
                    break;

                case RentOut:
                    thisWeekRentOutTv.setText(""+postHistoryModel.getCountThisWeek());
                    totalRentOutTv.setText(""+postHistoryModel.getCountAllTime());
                    break;

                case SoldOut:
                    thisWeekSoldOutTv.setText(""+postHistoryModel.getCountThisWeek());
                    totalSoldOutTv.setText(""+postHistoryModel.getCountAllTime());
                    break;

                case Finished:
                    thisWeekFinishedPostTv.setText(""+postHistoryModel.getCountThisWeek());
                    totalFinishedPostTv.setText(""+postHistoryModel.getCountAllTime());
                    break;

                case AlreadyExists:
                    thisWeekAlreadyExistTv.setText(""+postHistoryModel.getCountThisWeek());
                    totalAlreadyExistTv.setText(""+postHistoryModel.getCountAllTime());
                    break;
            }
        }
    }
}
