package com.pbazaar.pbazaarforagent.ui.main.product;

import android.graphics.Bitmap;
import android.util.Log;

import com.pbazaar.pbazaarforagent.R;
import com.pbazaar.pbazaarforagent.helper.AppController;
import com.pbazaar.pbazaarforagent.model.LocationSpinnerDataModel;
import com.pbazaar.pbazaarforagent.model.PostProductModel;
import com.pbazaar.pbazaarforagent.model.SubCategoryModel;

import java.util.ArrayList;

/**
 * Created by supto on 4/4/17.
 */

public class ProductPostingPresenter implements ProductPostingContract.Presenter {

    private static final String TAG = ProductPostingPresenter.class.getSimpleName();


    private ProductPostingContract.View view;
    private ProductPostingRemoteService remoteService;


    public ProductPostingPresenter(ProductPostingContract.View view) {
        this.view = view;
        view.setPresenter(this);

        remoteService = ProductPostingRemoteService.newInstance();
    }


    @Override
    public void start() {
        onCountrySelected();
        getSubcategories(1);
        Log.d(TAG, "Presenter start");
    }

    @Override
    public void end() {

    }


    @Override
    public void getSubcategories(int categoryId) {
        view.showLoadingIndicator(true, AppController.getInstance().getString(R.string.network_operation_running_message));
        remoteService.getSubCategories(categoryId, new ProductPostingRemoteService.GetSubCategoryCompletionListener() {
            @Override
            public void onLoadSuccess(ArrayList<SubCategoryModel> subCategoryModelArrayList) {
                view.setSubcategories(subCategoryModelArrayList);
                view.showLoadingIndicator(false, "");
            }

            @Override
            public void onLoadFailed(String message) {
                view.showMessage(message);
                view.showLoadingIndicator(false, "");
            }
        });
    }

    @Override
    public void onCountrySelected() {
        view.showLoadingIndicator(true, AppController.getInstance().getString(R.string.network_operation_running_message));
        ProductPostingRemoteService.newInstance().getDistrictByCountryId(3, new ProductPostingRemoteService.DistrictLoadCompletionListener() {
            @Override
            public void onDistrictLoadSuccess(ArrayList<LocationSpinnerDataModel> spinnerDataModelArrayList) {
                view.onDistrictLoaded(spinnerDataModelArrayList);
                view.showLoadingIndicator(false, "");
            }

            @Override
            public void onDistrictLoadFailed(String message) {
                //  view.showMessage(message);
                Log.d(TAG, "Error: " + message);
                view.showLoadingIndicator(false, "");
            }
        });
    }

    @Override
    public void onDistrictSelected(int districtId) {
        Log.d(TAG, "District id: " + districtId);
        view.showLoadingIndicator(true, AppController.getInstance().getString(R.string.network_operation_running_message));
        ProductPostingRemoteService.newInstance().getThanaByDistrictId(districtId, new ProductPostingRemoteService.ThanaLoadCompletionListener() {
            @Override
            public void onThanaLoadSuccess(ArrayList<LocationSpinnerDataModel> spinnerDataModelArrayList) {
                view.onThanaLoaded(spinnerDataModelArrayList);
                view.showLoadingIndicator(false, "");
            }

            @Override
            public void onThanaloadFailed(String message) {
                //  view.showMessage(message);
                Log.d(TAG, "Error: " + message);
                view.showLoadingIndicator(false, "");
            }
        });
    }

    @Override
    public void uploadProductImage(Bitmap bitmap) {
        view.showLoadingIndicator(true, AppController.getInstance().getString(R.string.image_uploading_message));
        ProductPostingRemoteService.newInstance().uploadProductImage(bitmap, new ProductPostingRemoteService.ProductImageUploadListener() {
            @Override
            public void onUploadSuccess(Bitmap bitmap, int imageId) {
                view.onProductImageUploaded(bitmap, imageId);
                view.showLoadingIndicator(false, "");
            }

            @Override
            public void onUploadFailed(String message) {
                view.showMessage(message);
                view.showLoadingIndicator(false, "");
            }
        });
    }

    @Override
    public void onPostProductClicked(PostProductModel postProductModel) {
        view.showLoadingIndicator(true, AppController.getInstance().getString(R.string.network_operation_running_message));
        ProductPostingRemoteService.newInstance().postProduct(postProductModel, new ProductPostingRemoteService.ProductPostCompletionListener() {
            @Override
            public void onPostSuccess(String message) {
                view.showLoadingIndicator(false, "");
                view.clearAllFields();
                view.onPostSuccess();
            }

            @Override
            public void onPostFailed(String message) {
                view.showMessage(message);
                view.showLoadingIndicator(false, "");
            }
        });
    }
}
