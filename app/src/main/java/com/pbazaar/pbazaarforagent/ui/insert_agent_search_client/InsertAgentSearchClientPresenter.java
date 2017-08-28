package com.pbazaar.pbazaarforagent.ui.insert_agent_search_client;

import com.pbazaar.pbazaarforagent.R;
import com.pbazaar.pbazaarforagent.helper.AppController;
import com.pbazaar.pbazaarforagent.helper.ConnectivityUtils;
import com.pbazaar.pbazaarforagent.helper.RxAndroidUtils;
import com.pbazaar.pbazaarforagent.model.SearchClientModel;
import com.pbazaar.pbazaarforagent.remote.PbazaarApi;
import com.pbazaar.pbazaarforagent.remote.RemoteConstant;
import com.pbazaar.pbazaarforagent.remote.data.InsertAgentSearchClientRequest;
import com.pbazaar.pbazaarforagent.remote.data.InsertAgentSearchClientResponse;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by supto on 8/27/17.
 */

public class InsertAgentSearchClientPresenter implements InsertAgentSearchClientContract.Presenter {

    public static final String TAG = InsertAgentSearchClientPresenter.class.getSimpleName();

    private InsertAgentSearchClientContract.View view;
    private Disposable insertClientDisposable;

    public InsertAgentSearchClientPresenter(InsertAgentSearchClientContract.View view) {
        this.view = view;
    }

    @Override
    public void start() {

    }

    @Override
    public void end() {
        RxAndroidUtils.disposeDisposable(insertClientDisposable);
    }

    @Override
    public void onInsertClientButtonClicked(String name, String phone, String email, String agentId) {
        RxAndroidUtils.disposeDisposable(insertClientDisposable);

        // if not connected to network then return and display error message
        if (!ConnectivityUtils.isNetworkAvailable()) {
            view.showMessage(AppController.getInstance().getString(R.string.no_internet_error_message));
            return;
        }


        view.showLoadingIndicator(true);

        InsertAgentSearchClientRequest request = new InsertAgentSearchClientRequest(RemoteConstant.PUBLIC_API_TOKEN, name, phone, email, agentId);

        insertClientDisposable = PbazaarApi.getInstance().getPbazaarApiServiceClient()
                .insertAgentSearchClient(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<InsertAgentSearchClientResponse>() {

                    @Override
                    public void accept(InsertAgentSearchClientResponse response) throws Exception {

                        if (response.getSuccess() == 1) {
                            // Todo problem in api, name returning null
                            SearchClientModel searchClientModel = new SearchClientModel("Fake name", response.getData().getMobileNumber(), response.getData().getEmailAddress(), response.getData().getAgentId(), response.getData().getId());
                            view.onInsertClientSuccess(searchClientModel);
                        } else {
                            view.showMessage("Insert Agent Information Failed");
                        }

                        view.showLoadingIndicator(false);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                        view.showLoadingIndicator(false);
//                        view.showMessage(AppController.getInstance().getString(R.string.no_internet_error_message));
                        view.showMessage(throwable.getMessage());
                    }
                });


    }
}
