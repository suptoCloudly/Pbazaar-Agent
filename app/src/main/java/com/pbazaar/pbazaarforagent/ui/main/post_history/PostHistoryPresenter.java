package com.pbazaar.pbazaarforagent.ui.main.post_history;

import com.pbazaar.pbazaarforagent.model.PostHistoryModel;

import java.util.ArrayList;

/**
 * Created by Supto on 5/11/2017.
 */

public class PostHistoryPresenter implements PostHistoryContract.Presenter {

    public static final String TAG = PostHistoryPresenter.class.getSimpleName();

    private PostHistoryContract.View view;

    private PostHistoryPresenter(PostHistoryContract.View view) {
        this.view = view;
    }

    public static PostHistoryPresenter newInstance(PostHistoryContract.View view) {
        PostHistoryPresenter postHistoryPresenter = new PostHistoryPresenter(view);
        return postHistoryPresenter;
    }

    @Override
    public void start() {

    }

    @Override
    public void end() {

    }

    @Override
    public void loadHistory(int collectedById) {
        view.showLoadingIndicator(true);

        PostHistoryRemoteService.newInstance().loadPostHistory(collectedById, new PostHistoryRemoteService.LoadPostHistoryListener() {
            @Override
            public void onLoadSuccess(ArrayList<PostHistoryModel> postHistoryModels) {
                view.showHistoryData(postHistoryModels);
                view.showLoadingIndicator(false);
            }

            @Override
            public void onLoadFailed(String message) {
                view.showMessage(message);
                view.showLoadingIndicator(false);
            }
        });
    }
}
