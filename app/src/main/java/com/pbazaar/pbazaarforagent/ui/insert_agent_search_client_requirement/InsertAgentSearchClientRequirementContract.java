package com.pbazaar.pbazaarforagent.ui.insert_agent_search_client_requirement;

import com.pbazaar.pbazaarforagent.model.LocationSpinnerDataModel;
import com.pbazaar.pbazaarforagent.ui.BasePresenter;
import com.pbazaar.pbazaarforagent.ui.BaseView;

import java.util.ArrayList;

/**
 * Created by supto on 8/28/17.
 */

public interface InsertAgentSearchClientRequirementContract {

    interface View extends BaseView<Presenter> {

        void showMessage(String message);

        void showLoadingIndicator(boolean status);

        void onDistrictListLoaded(ArrayList<LocationSpinnerDataModel> districtList);

        void onThanaLoaded(ArrayList<LocationSpinnerDataModel> thanaList);
    }

    interface Presenter extends BasePresenter {

        void initialDataLoad();

        void loadDistrictList(int countryId);

        void loadAreaList(int districtId);
    }
}
