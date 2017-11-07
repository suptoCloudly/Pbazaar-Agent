package com.pbazaar.pbazaarforagent.ui.main.new_product;

import com.pbazaar.pbazaarforagent.ui.BasePresenter;
import com.pbazaar.pbazaarforagent.ui.BaseView;

/**
 * Created by supto on 11/7/17.
 */

public interface NewProductPostingContract {

    interface View extends BaseView<Presenter> {
        void showLoadingIndicator(boolean status);
    }

    interface Presenter extends BasePresenter {

    }
}
