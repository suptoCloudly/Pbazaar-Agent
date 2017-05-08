package com.pbazaar.pbazaarforagent.ui.main.payment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pbazaar.pbazaarforagent.R;
import com.pbazaar.pbazaarforagent.helper.PreferenceHelper;
import com.pbazaar.pbazaarforagent.model.PaymentHistoryModel;
import com.pbazaar.pbazaarforagent.ui.dialogs.ShowMessageDialog;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class PayHistoryFragment extends Fragment implements PaymentHistoryContract.View {

    private static final String TAG = PayHistoryFragment.class.getSimpleName();
    public static final String ARG_PAYMENT_HISTORY_LIST = "arg_payment-history_list";

    @BindView(R.id.recycler_view_payment_history_fragment)
    RecyclerView recyclerView;

    @BindView(R.id.text_view_empty_list_payment_history_fragment)
    TextView emptyText;

    private PaymentHistoryContract.Presenter presenter;
    private ArrayList<PaymentHistoryModel> paymentHistoryModels = new ArrayList<>();
    private PaymentHistoryRcViewAdapter paymentHistoryRcViewAdapter;

    public PayHistoryFragment() {
    }

    public static PayHistoryFragment newInstance() {
        PayHistoryFragment payHistoryFragment = new PayHistoryFragment();
        return payHistoryFragment;
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
            presenter = PaymentHistoryPresenter.newInstance(this);
            presenter.start();
        }


        if (savedInstanceState == null) {
            presenter.loadPaymentHistory(PreferenceHelper.getInstance().getCustomerId());
//            presenter.loadPaymentHistory(8466846);

        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pay_history, container, false);
        ButterKnife.bind(this, view);

        if (savedInstanceState != null) {
            paymentHistoryModels = savedInstanceState.getParcelableArrayList(ARG_PAYMENT_HISTORY_LIST);
        }

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        paymentHistoryRcViewAdapter = new PaymentHistoryRcViewAdapter(getActivity(), paymentHistoryModels);
        recyclerView.setAdapter(paymentHistoryRcViewAdapter);


        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putParcelableArrayList(ARG_PAYMENT_HISTORY_LIST, paymentHistoryModels);
    }

    @Override
    public void setPresenter(PaymentHistoryContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onPaymentHistoryLoaded(ArrayList<PaymentHistoryModel> paymentHistoryModels) {

        if (paymentHistoryModels.size() == 0) {
            emptyText.setVisibility(View.VISIBLE);
        } else {
            emptyText.setVisibility(View.GONE);
        }


        this.paymentHistoryModels.clear();
        this.paymentHistoryModels.addAll(paymentHistoryModels);
        paymentHistoryRcViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void showMessage(String message) {
        ShowMessageDialog showMessageDialog = ShowMessageDialog.newInstance(message);
        showMessageDialog.show(getActivity().getSupportFragmentManager(), ShowMessageDialog.class.getSimpleName());

    }
}
