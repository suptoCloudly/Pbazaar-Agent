package com.pbazaar.pbazaarforagent.ui.main.product;

/**
 * Created by supto on 4/4/17.
 */

public class ProductPostingPresenter implements ProductPostingContract.Presenter {

    private static final String TAG = ProductPostingPresenter.class.getSimpleName();


    private ProductPostingContract.View view;


    public ProductPostingPresenter(ProductPostingContract.View view) {
        this.view = view;
        view.setPresenter(this);
    }


    @Override
    public void start() {
    }

    @Override
    public void end() {

    }
}
