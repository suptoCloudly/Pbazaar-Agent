package com.pbazaar.pbazaarforagent.ui.main.payment;

import android.util.Log;

import com.pbazaar.pbazaarforagent.model.PaymentHistoryModel;
import com.pbazaar.pbazaarforagent.remote.PbazaarApi;
import com.pbazaar.pbazaarforagent.remote.RemoteConstant;
import com.pbazaar.pbazaarforagent.remote.data.PaymentHistoryRequest;
import com.pbazaar.pbazaarforagent.remote.data.PaymentHistoryResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by supto on 5/5/17.
 */

public class PaymentHistoryPresenter implements PaymentHistoryContract.Presenter {

    private static final String TAG = PaymentHistoryPresenter.class.getSimpleName();

    private PaymentHistoryContract.View view;

    private PaymentHistoryPresenter(PaymentHistoryContract.View view) {
        this.view = view;
    }

    public static PaymentHistoryPresenter newInstance(PaymentHistoryContract.View view) {
        PaymentHistoryPresenter paymentHistoryPresenter = new PaymentHistoryPresenter(view);
        return paymentHistoryPresenter;
    }


    @Override
    public void start() {

    }

    @Override
    public void end() {

    }

    @Override
    public void loadPaymentHistory(int agentId) {

        PaymentHistoryRequest paymentHistoryRequest = new PaymentHistoryRequest(RemoteConstant.PUBLIC_API_TOKEN, agentId, 0);

        Call<PaymentHistoryResponse> call = PbazaarApi.getInstance().getPbazaarApiServiceClient().getPaymentHistory(paymentHistoryRequest);
        call.enqueue(new Callback<PaymentHistoryResponse>() {
            @Override
            public void onResponse(Call<PaymentHistoryResponse> call, Response<PaymentHistoryResponse> response) {
                if (response.isSuccessful()) {


                    ArrayList<PaymentHistoryModel> paymentHistoryModels = new ArrayList<PaymentHistoryModel>();

                    PaymentHistoryResponse paymentHistoryResponse = response.body();
                    if (paymentHistoryResponse.getSuccess() == 1) {

                        for (PaymentHistoryResponse.Data data : paymentHistoryResponse.getData()) {
                            PaymentHistoryModel paymentHistoryModel = new PaymentHistoryModel(data.getPayoutDate(), data.getPayoutAmount(), data.getPaymentMethod());
                            paymentHistoryModels.add(paymentHistoryModel);

                            Log.d(TAG, data.getPayoutDate() + " " + data.getPayoutAmount() + " " + data.getPaymentMethod());
                        }

                        view.onPaymentHistoryLoaded(paymentHistoryModels);
                    } else {
                        view.showMessage(paymentHistoryResponse.getMessage());
                        Log.d(TAG, paymentHistoryResponse.getMessage());

                    }
                }
            }

            @Override
            public void onFailure(Call<PaymentHistoryResponse> call, Throwable t) {
                Log.d(TAG, "On failure calling");
            }
        });
    }
}
