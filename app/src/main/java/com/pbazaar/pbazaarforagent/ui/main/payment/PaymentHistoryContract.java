package com.pbazaar.pbazaarforagent.ui.main.payment;

import com.pbazaar.pbazaarforagent.model.PaymentHistoryModel;
import com.pbazaar.pbazaarforagent.ui.BasePresenter;
import com.pbazaar.pbazaarforagent.ui.BaseView;

import java.util.ArrayList;

/**
 * Created by supto on 5/5/17.
 */

public interface PaymentHistoryContract {

    interface View extends BaseView<Presenter> {
        void onPaymentHistoryLoaded(ArrayList<PaymentHistoryModel> paymentHistoryModels);

        void showMessage(String message);
    }

    interface Presenter extends BasePresenter {
        void loadPaymentHistory(int agentId);
    }
}
