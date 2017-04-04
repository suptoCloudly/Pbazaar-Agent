package com.pbazaar.pbazaarforagent.ui.registration;

import com.pbazaar.pbazaarforagent.model.LocationSpinnerDataModel;
import com.pbazaar.pbazaarforagent.model.RegistrationDataModel;
import com.pbazaar.pbazaarforagent.ui.BasePresenter;
import com.pbazaar.pbazaarforagent.ui.BaseView;

import java.util.ArrayList;

/**
 * Created by supto on 4/2/17.
 */

public interface RegistrationContract {
    interface View extends BaseView<Presenter> {

        void onRegistrationSuccess(String email, String password);

        void setLoadingIndicator(boolean status);

        void showMessage(String message);

        void onDistrictLoaded(ArrayList<LocationSpinnerDataModel> districtList);

        void onThanaLoaded(ArrayList<LocationSpinnerDataModel> thanaList);

    }

    interface Presenter extends BasePresenter {

        void onRegistrationButtonClicked(RegistrationDataModel registrationDataModel);

        void onCountrySelected(int countryId);

        void onDistrictSelected(int districtId);


    }
}
