package com.pbazaar.pbazaarforagent.ui.insert_agent_search_client;

import com.pbazaar.pbazaarforagent.model.SearchClientModel;
import com.pbazaar.pbazaarforagent.ui.BasePresenter;
import com.pbazaar.pbazaarforagent.ui.BaseView;

/**
 * Created by supto on 8/27/17.
 */

public interface InsertAgentSearchClientContract {

    interface View extends BaseView<Presenter> {
        void showMessage(String message);

        void showLoadingIndicator(boolean status);

        void onInsertClientSuccess(SearchClientModel searchClientModel);
    }

    interface Presenter extends BasePresenter {
        void onInsertClientButtonClicked(String name, String phone, String email, String agentId);
    }
}
