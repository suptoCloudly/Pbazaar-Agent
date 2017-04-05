package com.pbazaar.pbazaarforagent.ui.main.product;

import android.graphics.Bitmap;

import com.pbazaar.pbazaarforagent.model.LocationSpinnerDataModel;
import com.pbazaar.pbazaarforagent.model.SubCategoryModel;
import com.pbazaar.pbazaarforagent.ui.BasePresenter;
import com.pbazaar.pbazaarforagent.ui.BaseView;

import java.util.ArrayList;

/**
 * Created by supto on 4/4/17.
 */

public interface ProductPostingContract {
    interface View extends BaseView<Presenter> {
        void showMessage(String message);

        void showLoadingIndicator(boolean status,String message);

        void setCategories(ArrayList<SubCategoryModel> categories);

        void setSubcategories(ArrayList<SubCategoryModel> subCategories);

        void onDistrictLoaded(ArrayList<LocationSpinnerDataModel> districtList);

        void onThanaLoaded(ArrayList<LocationSpinnerDataModel> thanaList);

        void onProductImageUploaded(Bitmap bitmap, int imageId);
    }

    interface Presenter extends BasePresenter {
        void getRootCategories();

        void getSubcategories(int categoryId);

        void onCountrySelected();

        void onDistrictSelected(int districtId);

        void uploadProductImage(Bitmap bitmap);
    }
}
