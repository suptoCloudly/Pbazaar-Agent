package com.pbazaar.pbazaarforagent.ui.login;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.pbazaar.pbazaarforagent.R;
import com.pbazaar.pbazaarforagent.helper.AppController;
import com.pbazaar.pbazaarforagent.helper.PreferenceHelper;
import com.pbazaar.pbazaarforagent.model.LoginCredentialModel;
import com.pbazaar.pbazaarforagent.model.UserDataModel;
import com.pbazaar.pbazaarforagent.remote.PbazaarApi;
import com.pbazaar.pbazaarforagent.remote.RemoteConstant;
import com.pbazaar.pbazaarforagent.remote.data.LoginRequest;
import com.pbazaar.pbazaarforagent.remote.data.LoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by supto on 4/2/17.
 */

public class LoginPresenter implements LoginContract.Presenter {

    public static final String TAG = LoginPresenter.class.getSimpleName();

    private LoginContract.View view;

    public LoginPresenter(LoginContract.View view) {
        this.view = view;
        this.view.setPresenter(this);
    }

    @Override
    public void start() {
        Log.d(TAG, "Login presenter started");
        // start all the mandatory work needed

        ConnectivityManager connectivityManager = (ConnectivityManager) AppController.getInstance()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager
                .getActiveNetworkInfo();
        // Check internet connection and set the current network state in sharedpreference
        if (networkInfo != null && networkInfo.isConnected()) {
            PreferenceHelper.getInstance().setNetworkStatus(true);
            Log.d(TAG, "TRue");
        } else {
            PreferenceHelper.getInstance().setNetworkStatus(false);
            Log.d(TAG, "False");
        }

    }

    @Override
    public void end() {

        // do all the resource disposal if needed
    }

    @Override
    public void onLoginButtonClicked(LoginCredentialModel loginCredentialModel) {


        // if there is any empty data then return and display an error message
        if (loginCredentialModel.getEmail().matches("") || loginCredentialModel.getPassword().matches("")) {
            view.showMessage("One or more input field is empty");
            return;
        }

        // if not connected to network then return and display error message
        if (!PreferenceHelper.getInstance().getNetworkStatus()) {
            view.showMessage(AppController.getInstance().getString(R.string.no_internet_error_message));
            return;
        }


        // show the loading indicator and start network request
        view.setLoadingIndicator(true);


        LoginRequest loginRequest = new LoginRequest(RemoteConstant.PUBLIC_API_TOKEN, loginCredentialModel.getEmail(), loginCredentialModel.getPassword());

        Call<LoginResponse> call = PbazaarApi.getInstance().getPbazaarApiServiceClient().logIn(loginRequest);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                // turn of the loading indicator
                view.setLoadingIndicator(false);


                if (response.isSuccessful()) {
                    // login successful

                    LoginResponse loginResponse = response.body();

                    if (loginResponse.getSuccess() == 1) {

                        UserDataModel userDataModel = new UserDataModel(loginResponse.getData().getEmail(),
                                loginResponse.getData().getFullName(), loginResponse.getData().getCustomerId(),
                                loginResponse.getData().getRentOk(), loginResponse.getData().getSellOk(),
                                loginResponse.getData().getRealEstateCompanyOk(), loginResponse.getData().getRealEstateCompanyId(),
                                loginResponse.getData().getGuestHouseOk(), loginResponse.getData().getGuestHouseId()
                        );

                        view.onLoginSuccess(userDataModel);

                    } else {
                        // login unsuccessful
                        view.showMessage(loginResponse.getMessage());
                    }


                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

                // turn of the loading indicator
                view.setLoadingIndicator(false);

                view.showMessage(AppController.getInstance().getString(R.string.internet_connection_error_message));

            }
        });
    }
}
