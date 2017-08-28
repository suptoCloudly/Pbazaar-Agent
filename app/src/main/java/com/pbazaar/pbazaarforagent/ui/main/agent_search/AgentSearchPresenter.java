package com.pbazaar.pbazaarforagent.ui.main.agent_search;

import android.support.annotation.NonNull;

import com.pbazaar.pbazaarforagent.R;
import com.pbazaar.pbazaarforagent.helper.AppController;
import com.pbazaar.pbazaarforagent.helper.ConnectivityUtils;
import com.pbazaar.pbazaarforagent.helper.RxAndroidUtils;
import com.pbazaar.pbazaarforagent.model.GetAgentSearchClientModel;
import com.pbazaar.pbazaarforagent.model.SearchClientModel;
import com.pbazaar.pbazaarforagent.remote.PbazaarApi;
import com.pbazaar.pbazaarforagent.remote.RemoteConstant;
import com.pbazaar.pbazaarforagent.remote.data.GetAgentSearchClientByPhoneNumberRequest;
import com.pbazaar.pbazaarforagent.remote.data.GetAgentSearchClientByPhoneNumberResponse;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by supto on 8/27/17.
 */

public class AgentSearchPresenter implements AgentSearchContract.Presenter {

    private static final String TAG = AgentSearchPresenter.class.getSimpleName();


    private AgentSearchContract.View view;

    private Disposable submitPhnDisposable;

    public AgentSearchPresenter(AgentSearchContract.View view) {
        this.view = view;
    }

    @Override
    public void start() {

    }

    @Override
    public void end() {
        RxAndroidUtils.disposeDisposable(submitPhnDisposable);
    }

    @Override
    public void onSubmitPhoneButtonClicked(@NonNull String phoneNumber) {


        if (!ConnectivityUtils.isNetworkAvailable()) {
            view.showMessage(AppController.getInstance().getString(R.string.no_internet_error_message));
            return;
        }

        RxAndroidUtils.disposeDisposable(submitPhnDisposable);

        final GetAgentSearchClientByPhoneNumberRequest request = new GetAgentSearchClientByPhoneNumberRequest(RemoteConstant.PUBLIC_API_TOKEN, phoneNumber);

        view.showLoadingIndicator(true);

        submitPhnDisposable = PbazaarApi.getInstance().getPbazaarApiServiceClient()
                .getAgentSearchClientByPhoneNumber(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<GetAgentSearchClientByPhoneNumberResponse>() {
                    @Override
                    public void accept(GetAgentSearchClientByPhoneNumberResponse response) throws Exception {

                        if (response.getSuccess() == 1) {
                            SearchClientModel searchClientModel = new SearchClientModel("Fake Name", response.getData().getMobileNumber(), response.getData().getEmailAddress(), response.getData().getAgentId(), response.getData().getId());
                            if (searchClientModel.getId() == 0) {
                                view.onClientNotFound(request.getPhoneNumber());
                            } else {
                                view.onClientFound(searchClientModel);
                            }
                        }

                        view.showLoadingIndicator(false);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        view.showMessage(AppController.getInstance().getString(R.string.no_internet_error_message));
                        view.showLoadingIndicator(false);

                    }
                });
    }
}
