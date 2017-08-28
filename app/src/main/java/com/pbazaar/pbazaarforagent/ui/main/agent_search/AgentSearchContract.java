package com.pbazaar.pbazaarforagent.ui.main.agent_search;

import android.support.annotation.NonNull;

import com.pbazaar.pbazaarforagent.model.GetAgentSearchClientModel;
import com.pbazaar.pbazaarforagent.model.SearchClientModel;
import com.pbazaar.pbazaarforagent.ui.BasePresenter;
import com.pbazaar.pbazaarforagent.ui.BaseView;

/**
 * Created by supto on 8/27/17.
 */

public interface AgentSearchContract {

    interface View extends BaseView<Presenter> {
        void showMessage(@NonNull String message);

        void showLoadingIndicator(boolean status);

        void onClientNotFound(@NonNull String phoneNumber);

        void onClientFound(SearchClientModel searchClientModel);
    }

    interface Presenter extends BasePresenter {
        void onSubmitPhoneButtonClicked(@NonNull String phoneNumber);
    }
}
