package com.pbazaar.pbazaarforagent.ui.main.payment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pbazaar.pbazaarforagent.R;
import com.pbazaar.pbazaarforagent.model.PaymentHistoryModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by supto on 5/5/17.
 */

public class PaymentHistoryRcViewAdapter extends RecyclerView.Adapter<PaymentHistoryRcViewAdapter.PaymentHistoryRcViewHolder> {

    private Context context;
    private ArrayList<PaymentHistoryModel> paymentHistoryModels;


    public PaymentHistoryRcViewAdapter(Context context, ArrayList<PaymentHistoryModel> paymentHistoryModels) {
        this.context = context;
        this.paymentHistoryModels = paymentHistoryModels;
    }

    @Override
    public PaymentHistoryRcViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_payment_hislory_list, parent, false);
        return new PaymentHistoryRcViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PaymentHistoryRcViewHolder holder, int position) {

        PaymentHistoryModel paymentHistoryModel = paymentHistoryModels.get(position);

        holder.date.setText(paymentHistoryModel.getDate());
        holder.amount.setText(paymentHistoryModel.getAmount());
        holder.method.setText(paymentHistoryModel.getMethod());
    }

    @Override
    public int getItemCount() {
        return paymentHistoryModels.size();
    }

    public static class PaymentHistoryRcViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.text_view_date_pay_history_item)
        TextView date;

        @BindView(R.id.text_view_amount_pay_history_item)
        TextView amount;

        @BindView(R.id.text_view_method_pay_history_item)
        TextView method;


        public PaymentHistoryRcViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
