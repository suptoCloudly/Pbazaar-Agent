package com.pbazaar.pbazaarforagent.ui.main.post_history;

import com.pbazaar.pbazaarforagent.model.PostHistoryModel;
import com.pbazaar.pbazaarforagent.ui.BasePresenter;
import com.pbazaar.pbazaarforagent.ui.BaseView;

import java.util.ArrayList;

/**
 * Created by Supto on 5/11/2017.
 */

public interface PostHistoryContract {

    interface View extends BaseView<Presenter> {
        void showMessage(String message);


        void showLoadingIndicator(boolean status);

        void showHistoryData(ArrayList<PostHistoryModel> postHistoryModels);

    }

    interface Presenter extends BasePresenter {
        void loadHistory(int collectedById);
    }
}
