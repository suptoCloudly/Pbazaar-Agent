package com.pbazaar.pbazaarforagent.ui.forgot_password;

import com.pbazaar.pbazaarforagent.ui.BasePresenter;
import com.pbazaar.pbazaarforagent.ui.BaseView;

/**
 * Created by supto on 5/8/17.
 */

public interface ForgotPasswordContract {

    interface View extends BaseView<Presenter> {

        void showMessage(String message);

        void showLoadingIndicator(boolean status);
    }

    interface Presenter extends BasePresenter {
        void onRequestPasswordClicked(String email);
    }
}
