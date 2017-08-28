package com.pbazaar.pbazaarforagent.ui.insert_agent_search_client_requirement;

import com.pbazaar.pbazaarforagent.R;
import com.pbazaar.pbazaarforagent.helper.AppController;
import com.pbazaar.pbazaarforagent.helper.ConnectivityUtils;
import com.pbazaar.pbazaarforagent.helper.RxAndroidUtils;
import com.pbazaar.pbazaarforagent.model.LocationSpinnerDataModel;
import com.pbazaar.pbazaarforagent.remote.PbazaarApi;
import com.pbazaar.pbazaarforagent.remote.RemoteConstant;
import com.pbazaar.pbazaarforagent.remote.data.GetDistrictByCountryIdRequest;
import com.pbazaar.pbazaarforagent.remote.data.GetDistrictByCountryIdResponse;
import com.pbazaar.pbazaarforagent.remote.data.GetThanaByDistrictIdRequest;
import com.pbazaar.pbazaarforagent.remote.data.GetThanaByDistrictIdResponse;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by supto on 8/28/17.
 */

public class InsertAgentSearchClientRequirementPresenter implements InsertAgentSearchClientRequirementContract.Presenter {

    public static final String TAG = InsertAgentSearchClientRequirementPresenter.class.getSimpleName();

    private InsertAgentSearchClientRequirementContract.View view;

    private Disposable initialLoadDisposable;
    private Disposable loadDistrictDisposable;
    private Disposable loadAreaDisposable;


    public InsertAgentSearchClientRequirementPresenter(InsertAgentSearchClientRequirementContract.View view) {
        this.view = view;
    }

    @Override
    public void start() {

    }

    @Override
    public void end() {
        RxAndroidUtils.disposeDisposable(initialLoadDisposable, loadDistrictDisposable);
    }


    @Override
    public void initialDataLoad() {

        RxAndroidUtils.disposeDisposable(initialLoadDisposable);

        // if not connected to network then return and display error message
        if (!ConnectivityUtils.isNetworkAvailable()) {
            view.showMessage(AppController.getInstance().getString(R.string.no_internet_error_message));
            return;
        }


        view.showLoadingIndicator(true);

        GetDistrictByCountryIdRequest request = new GetDistrictByCountryIdRequest(RemoteConstant.PUBLIC_API_TOKEN, 3);
        GetThanaByDistrictIdRequest thanaByDistrictIdRequest = new GetThanaByDistrictIdRequest(RemoteConstant.PUBLIC_API_TOKEN, 78);

        Observable<GetDistrictByCountryIdResponse> getDistrictByCountryIdObservable = PbazaarApi.getInstance().getPbazaarApiServiceClient()
                .getDistrictsByCountryIdObservable(request);

        Observable<GetThanaByDistrictIdResponse> getThanaByDistrictIdObservable = PbazaarApi.getInstance().getPbazaarApiServiceClient()
                .getThanaByDistrictIdObservable(thanaByDistrictIdRequest);



//        initialLoadDisposable = Observable
//                .zip(getDistrictByCountryIdObservable, getThanaByDistrictIdObservable, new BiFunction<GetDistrictByCountryIdResponse, GetThanaByDistrictIdResponse, Boolean>() {
//                    @Override
//                    public Boolean apply(@NonNull GetDistrictByCountryIdResponse response, @NonNull GetThanaByDistrictIdResponse getThanaByDistrictIdResponse) throws Exception {
//
//
//                        Log.d(TAG, "District: " + response.getSuccess());
//                        Log.d(TAG, "thana: " + getThanaByDistrictIdResponse.getSuccess());
//
//                        if (response.getSuccess() == 1 && getThanaByDistrictIdResponse.getSuccess() == 1) {
//
//
//                            Log.d(TAG, "both: " + response.getSuccess() + getThanaByDistrictIdResponse.getSuccess());
//
//
//                            ArrayList<LocationSpinnerDataModel> districtList = new ArrayList<>();
//
//                            for (GetDistrictByCountryIdResponse.Data data : response.getData()) {
//                                LocationSpinnerDataModel locationSpinnerDataModel = new LocationSpinnerDataModel(data.getName(), data.getId());
//                                districtList.add(locationSpinnerDataModel);
//                                Log.d(TAG, "District Add: " + locationSpinnerDataModel.getLocationName());
//                            }
//
//
//                            ArrayList<LocationSpinnerDataModel> spinnerDataModelArrayList = new ArrayList<>();
//                            for (GetThanaByDistrictIdResponse.Data data : getThanaByDistrictIdResponse.getData()) {
//                                LocationSpinnerDataModel locationSpinnerDataModel = new LocationSpinnerDataModel(data.getName(), data.getId());
//                                spinnerDataModelArrayList.add(locationSpinnerDataModel);
//                                Log.d(TAG, "Thana Add: " + locationSpinnerDataModel.getLocationName());
//                            }
//
//
//                            view.onDistrictListLoaded(districtList);
//                            view.onThanaLoaded(spinnerDataModelArrayList);
//
//                            return true;
//                        } else {
//                            return false;
//                        }
//                    }
//                })
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<Boolean>() {
//                    @Override
//                    public void accept(Boolean aBoolean) throws Exception {
//                        view.showLoadingIndicator(false);
//
//                        Log.d(TAG, "Result data: " + aBoolean);
//                        if (!aBoolean)
//                            view.showMessage(AppController.getInstance().getString(R.string.no_internet_error_message));
//                    }
//                }, new Consumer<Throwable>() {
//                    @Override
//                    public void accept(Throwable throwable) throws Exception {
//                        Log.d(TAG, "Error: " + throwable.getMessage());
//                        view.showLoadingIndicator(false);
//                        view.showMessage(AppController.getInstance().getString(R.string.no_internet_error_message));
//                    }
//                });

    }

    @Override
    public void loadDistrictList(int countryId) {
        RxAndroidUtils.disposeDisposable(loadDistrictDisposable);

        // if not connected to network then return and display error message
        if (!ConnectivityUtils.isNetworkAvailable()) {
            view.showMessage(AppController.getInstance().getString(R.string.no_internet_error_message));
            return;
        }

        view.showLoadingIndicator(true);

        GetDistrictByCountryIdRequest request = new GetDistrictByCountryIdRequest(RemoteConstant.PUBLIC_API_TOKEN, countryId);

        loadDistrictDisposable = PbazaarApi.getInstance().getPbazaarApiServiceClient()
                .getDistrictsByCountryIdObservable(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<GetDistrictByCountryIdResponse>() {
                    @Override
                    public void accept(GetDistrictByCountryIdResponse response) throws Exception {

                        if (response.getSuccess() == 1) {
                            ArrayList<LocationSpinnerDataModel> districtList = new ArrayList<>();

                            for (GetDistrictByCountryIdResponse.Data data : response.getData()) {
                                LocationSpinnerDataModel locationSpinnerDataModel = new LocationSpinnerDataModel(data.getName(), data.getId());
                                districtList.add(locationSpinnerDataModel);
                            }

                            view.onDistrictListLoaded(districtList);
                            view.showLoadingIndicator(false);
                        } else {
                            view.showMessage(AppController.getInstance().getString(R.string.no_internet_error_message));
                            view.showLoadingIndicator(false);

                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        view.showMessage(AppController.getInstance().getString(R.string.no_internet_error_message));
                    }
                });

    }

    @Override
    public void loadAreaList(int districtId) {
//        loadAreaDisposable
        RxAndroidUtils.disposeDisposable(loadAreaDisposable);

        // if not connected to network then return and display error message
        if (!ConnectivityUtils.isNetworkAvailable()) {
            view.showMessage(AppController.getInstance().getString(R.string.no_internet_error_message));
            return;
        }

        view.showLoadingIndicator(true);
        GetThanaByDistrictIdRequest request = new GetThanaByDistrictIdRequest(RemoteConstant.PUBLIC_API_TOKEN, districtId);

        loadAreaDisposable = PbazaarApi.getInstance().getPbazaarApiServiceClient()
                .getThanaByDistrictIdObservable(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<GetThanaByDistrictIdResponse>() {
                    @Override
                    public void accept(GetThanaByDistrictIdResponse getThanaByDistrictIdResponse) throws Exception {

                        if (getThanaByDistrictIdResponse.getSuccess() == 1) {
                            ArrayList<LocationSpinnerDataModel> spinnerDataModelArrayList = new ArrayList<>();
                            for (GetThanaByDistrictIdResponse.Data data : getThanaByDistrictIdResponse.getData()) {
                                LocationSpinnerDataModel locationSpinnerDataModel = new LocationSpinnerDataModel(data.getName(), data.getId());
                                spinnerDataModelArrayList.add(locationSpinnerDataModel);
                            }
                            view.onThanaLoaded(spinnerDataModelArrayList);

                        } else {
                            view.showMessage(AppController.getInstance().getString(R.string.no_internet_error_message));
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
