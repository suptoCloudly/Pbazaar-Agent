package com.pbazaar.pbazaarforagent.ui.forgot_password;

import android.util.Log;

import com.pbazaar.pbazaarforagent.R;
import com.pbazaar.pbazaarforagent.helper.AppController;
import com.pbazaar.pbazaarforagent.remote.PbazaarApi;
import com.pbazaar.pbazaarforagent.remote.RemoteConstant;
import com.pbazaar.pbazaarforagent.remote.data.ForgotPasswordRequest;
import com.pbazaar.pbazaarforagent.remote.data.ForgotPasswordResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by supto on 5/8/17.
 */

public class ForgotPasswordPresenter implements ForgotPasswordContract.Presenter {

    private static final String TAG = ForgotPasswordPresenter.class.getSimpleName();


    private ForgotPasswordContract.View view;

    private ForgotPasswordPresenter(ForgotPasswordContract.View view) {
        this.view = view;
    }


    public static ForgotPasswordPresenter newInstance(ForgotPasswordContract.View view) {
        ForgotPasswordPresenter forgotPasswordPresenter = new ForgotPasswordPresenter(view);
        return forgotPasswordPresenter;
    }

    @Override
    public void start() {

    }

    @Override
    public void end() {

    }

    @Override
    public void onRequestPasswordClicked(String email) {
        if (email.length() == 0 || email.contentEquals("")) {
            view.showMessage(AppController.getInstance().getString(R.string.no_email_error_text));
            return;
        }

        view.showLoadingIndicator(true);

        ForgotPasswordRequest forgotPasswordRequest = new ForgotPasswordRequest(RemoteConstant.PUBLIC_API_TOKEN, email);
        Call<ForgotPasswordResponse> call = PbazaarApi.getInstance().getPbazaarApiServiceClient().forgotPassword(forgotPasswordRequest);
        call.enqueue(new Callback<ForgotPasswordResponse>() {

            @Override
            public void onResponse(Call<ForgotPasswordResponse> call, Response<ForgotPasswordResponse> response) {
                Log.d(TAG, "Executing");

                if (response.isSuccessful()) {

                    ForgotPasswordResponse forgotPasswordResponse = response.body();

                    if (forgotPasswordResponse.getSuccess() == 1) {
                        view.showMessage(forgotPasswordResponse.getData());
                        Log.d(TAG, forgotPasswordResponse.getData());
                    } else {
                        view.showMessage(forgotPasswordResponse.getMessage());
                        Log.d(TAG, forgotPasswordResponse.getMessage());
                    }
                    view.showLoadingIndicator(false);

                }
            }

            @Override
            public void onFailure(Call<ForgotPasswordResponse> call, Throwable t) {
                view.showMessage(AppController.getInstance().getString(R.string.internet_connection_error_message));

                view.showLoadingIndicator(false);

            }
        });

    }
}
