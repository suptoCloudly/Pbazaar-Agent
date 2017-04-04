package com.pbazaar.pbazaarforagent.ui.login;

import com.pbazaar.pbazaarforagent.model.LoginCredentialModel;
import com.pbazaar.pbazaarforagent.model.UserDataModel;
import com.pbazaar.pbazaarforagent.ui.BasePresenter;
import com.pbazaar.pbazaarforagent.ui.BaseView;

/**
 * Created by supto on 4/2/17.
 */

public interface LoginContract {
    interface View extends BaseView<Presenter> {

        void onLoginSuccess(UserDataModel userDataModel);

        void setLoadingIndicator(boolean status);

        void showMessage(String message);
    }

    interface Presenter extends BasePresenter {
        void onLoginButtonClicked(LoginCredentialModel loginCredentialModel);
    }
}
