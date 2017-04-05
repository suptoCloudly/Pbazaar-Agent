package com.pbazaar.pbazaarforagent.ui.main.product;

import android.graphics.Bitmap;
import android.util.Log;

import com.pbazaar.pbazaarforagent.R;
import com.pbazaar.pbazaarforagent.helper.AppController;
import com.pbazaar.pbazaarforagent.helper.PreferenceHelper;
import com.pbazaar.pbazaarforagent.model.LocationSpinnerDataModel;
import com.pbazaar.pbazaarforagent.model.SubCategoryModel;
import com.pbazaar.pbazaarforagent.remote.PbazaarApi;
import com.pbazaar.pbazaarforagent.remote.RemoteConstant;
import com.pbazaar.pbazaarforagent.remote.data.GetDistrictByCountryIdRequest;
import com.pbazaar.pbazaarforagent.remote.data.GetDistrictByCountryIdResponse;
import com.pbazaar.pbazaarforagent.remote.data.GetSubcategoryFromCategoryRequest;
import com.pbazaar.pbazaarforagent.remote.data.GetSubcategoryFromCategoryResponse;
import com.pbazaar.pbazaarforagent.remote.data.GetThanaByDistrictIdRequest;
import com.pbazaar.pbazaarforagent.remote.data.GetThanaByDistrictIdResponse;
import com.pbazaar.pbazaarforagent.remote.data.InsertImageResponse;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Supto on 4/5/2017.
 */

public class ProductPostingRemoteService {

    private static final String TAG = ProductPostingRemoteService.class.getSimpleName();

    private ProductPostingRemoteService() {

    }

    public static ProductPostingRemoteService newInstance() {
        return new ProductPostingRemoteService();
    }


    public void getRootCategories(final GetRootCategoryCompletionListener completionListener) {

        final ArrayList<SubCategoryModel> subCategoryModelsList = new ArrayList<>();

        // TODO network check

        GetSubcategoryFromCategoryRequest request = new GetSubcategoryFromCategoryRequest(RemoteConstant.PUBLIC_API_TOKEN, 0);
        Call<GetSubcategoryFromCategoryResponse> call = PbazaarApi.getInstance().getPbazaarApiServiceClient().getSubcategoryFromCategory(request);
        call.enqueue(new Callback<GetSubcategoryFromCategoryResponse>() {
            @Override
            public void onResponse(Call<GetSubcategoryFromCategoryResponse> call, Response<GetSubcategoryFromCategoryResponse> response) {

                if (response.isSuccessful()) {

                    GetSubcategoryFromCategoryResponse getSubcategoryFromCategoryResponse = response.body();
                    if (getSubcategoryFromCategoryResponse.getSuccess() == 1) {

                        for (GetSubcategoryFromCategoryResponse.Data data : getSubcategoryFromCategoryResponse.getData()) {
                            SubCategoryModel subCategoryModel = new SubCategoryModel(data.getName(), data.getSeName(), data.getId());
                            subCategoryModelsList.add(subCategoryModel);
                        }


                        if (completionListener != null)
                            completionListener.onLoadSuccess(subCategoryModelsList);
                    } else {
                        if (completionListener != null)
                            completionListener.onLoadFailed(getSubcategoryFromCategoryResponse.getMessage());

                        Log.d(TAG, getSubcategoryFromCategoryResponse.getMessage());
                    }

                } else {
                    if (completionListener != null)
                        completionListener.onLoadSuccess(subCategoryModelsList);
                }
            }

            @Override
            public void onFailure(Call<GetSubcategoryFromCategoryResponse> call, Throwable t) {
                if (completionListener != null)
                    completionListener.onLoadFailed("response is failed");
            }
        });
    }

    public void getSubCategories(int categoryId, final GetSubCategoryCompletionListener completionListener) {

        final ArrayList<SubCategoryModel> subCategoryModelsList = new ArrayList<>();

        if (categoryId == -1) {
            if (completionListener != null)
                completionListener.onLoadSuccess(subCategoryModelsList);
        }

        // TODO network check

        GetSubcategoryFromCategoryRequest request = new GetSubcategoryFromCategoryRequest(RemoteConstant.PUBLIC_API_TOKEN, categoryId);
        Call<GetSubcategoryFromCategoryResponse> call = PbazaarApi.getInstance().getPbazaarApiServiceClient().getSubcategoryFromCategory(request);
        call.enqueue(new Callback<GetSubcategoryFromCategoryResponse>() {
            @Override
            public void onResponse(Call<GetSubcategoryFromCategoryResponse> call, Response<GetSubcategoryFromCategoryResponse> response) {

                if (response.isSuccessful()) {

                    GetSubcategoryFromCategoryResponse getSubcategoryFromCategoryResponse = response.body();
                    if (getSubcategoryFromCategoryResponse.getSuccess() == 1) {

                        for (GetSubcategoryFromCategoryResponse.Data data : getSubcategoryFromCategoryResponse.getData()) {
                            SubCategoryModel subCategoryModel = new SubCategoryModel(data.getName(), data.getSeName(), data.getId());
                            subCategoryModelsList.add(subCategoryModel);
                        }


                        if (completionListener != null)
                            completionListener.onLoadSuccess(subCategoryModelsList);
                    } else {
                        if (completionListener != null)
                            completionListener.onLoadFailed(getSubcategoryFromCategoryResponse.getMessage());

                        Log.d(TAG, getSubcategoryFromCategoryResponse.getMessage());
                    }

                } else {
                    if (completionListener != null)
                        completionListener.onLoadSuccess(subCategoryModelsList);
                }
            }

            @Override
            public void onFailure(Call<GetSubcategoryFromCategoryResponse> call, Throwable t) {
                if (completionListener != null)
                    completionListener.onLoadFailed("response is failed");
            }
        });
    }

    public void getDistrictByCountryId(int countryId, final ProductPostingRemoteService.DistrictLoadCompletionListener districtLoadCompletionListener) {
        // check internet connection and return if not available
        if (!PreferenceHelper.getInstance().getNetworkStatus()) {
            if (districtLoadCompletionListener != null)
                districtLoadCompletionListener.onDistrictLoadFailed(AppController.getInstance().getString(R.string.no_internet_error_message));

            return;
        }

        GetDistrictByCountryIdRequest getDistrictByCountryIdRequest = new GetDistrictByCountryIdRequest(RemoteConstant.PUBLIC_API_TOKEN, countryId);
        final ArrayList<LocationSpinnerDataModel> spinnerDataModelArrayList = new ArrayList<LocationSpinnerDataModel>();

        Call<GetDistrictByCountryIdResponse> call = PbazaarApi.getInstance().getPbazaarApiServiceClient().getDistrictsByCountryId(getDistrictByCountryIdRequest);
        call.enqueue(new Callback<GetDistrictByCountryIdResponse>() {
            @Override
            public void onResponse(Call<GetDistrictByCountryIdResponse> call, Response<GetDistrictByCountryIdResponse> response) {
                if (response.isSuccessful()) {

                    GetDistrictByCountryIdResponse districtByCountryIdResponse = response.body();

                    // if the request returns data then send it to view or show error message
                    if (districtByCountryIdResponse.getSuccess() == 1) {
                        for (GetDistrictByCountryIdResponse.Data data : districtByCountryIdResponse.getData()) {
                            LocationSpinnerDataModel locationSpinnerDataModel = new LocationSpinnerDataModel(data.getName(), data.getId());
                            spinnerDataModelArrayList.add(locationSpinnerDataModel);
                        }

                        if (districtLoadCompletionListener != null)
                            districtLoadCompletionListener.onDistrictLoadSuccess(spinnerDataModelArrayList);
                    } else {
                        if (districtLoadCompletionListener != null)
                            districtLoadCompletionListener.onDistrictLoadFailed(districtByCountryIdResponse.getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<GetDistrictByCountryIdResponse> call, Throwable t) {
                if (districtLoadCompletionListener != null)
                    districtLoadCompletionListener.onDistrictLoadFailed(AppController.getInstance().getString(R.string.no_internet_error_message));
            }
        });
    }

    public void getThanaByDistrictId(int districtId, final ProductPostingRemoteService.ThanaLoadCompletionListener thanaLoadCompletionListener) {

        final ArrayList<LocationSpinnerDataModel> spinnerDataModelArrayList = new ArrayList<>();

        if (districtId == -1) {
            if (thanaLoadCompletionListener != null)
                thanaLoadCompletionListener.onThanaLoadSuccess(spinnerDataModelArrayList);
            return;
        }

        // check internet connection and return if not available
        if (!PreferenceHelper.getInstance().getNetworkStatus()) {
            if (thanaLoadCompletionListener != null) {
                thanaLoadCompletionListener.onThanaloadFailed(AppController.getInstance().getString(R.string.no_internet_error_message));
            }
            return;
        }


        final GetThanaByDistrictIdRequest getThanaByDistrictIdRequest = new GetThanaByDistrictIdRequest(RemoteConstant.PUBLIC_API_TOKEN, districtId);
        Call<GetThanaByDistrictIdResponse> call = PbazaarApi.getInstance().getPbazaarApiServiceClient().getThanaByDistrictId(getThanaByDistrictIdRequest);

        call.enqueue(new Callback<GetThanaByDistrictIdResponse>() {
            @Override
            public void onResponse(Call<GetThanaByDistrictIdResponse> call, Response<GetThanaByDistrictIdResponse> response) {
                if (response.isSuccessful()) {
                    GetThanaByDistrictIdResponse getThanaByDistrictIdResponse = response.body();

                    if (getThanaByDistrictIdResponse.getSuccess() == 1) {
                        for (GetThanaByDistrictIdResponse.Data data : getThanaByDistrictIdResponse.getData()) {
                            LocationSpinnerDataModel locationSpinnerDataModel = new LocationSpinnerDataModel(data.getName(), data.getId());
                            spinnerDataModelArrayList.add(locationSpinnerDataModel);
                        }

                        if (thanaLoadCompletionListener != null)
                            thanaLoadCompletionListener.onThanaLoadSuccess(spinnerDataModelArrayList);
                    } else {
                        if (thanaLoadCompletionListener != null)
                            thanaLoadCompletionListener.onThanaloadFailed(getThanaByDistrictIdResponse.getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<GetThanaByDistrictIdResponse> call, Throwable t) {
                if (thanaLoadCompletionListener != null)
                    thanaLoadCompletionListener.onThanaloadFailed(AppController.getInstance().getString(R.string.no_internet_error_message));
            }
        });

    }

    public void uploadProductImage(final Bitmap bitmap, final ProductImageUploadListener imageUploadListener) {
//        if (imageUploadListener != null) {
//            imageUploadListener.onUploadSuccess(bitmap);
//        }

        if (!PreferenceHelper.getInstance().getNetworkStatus()) {
            if (imageUploadListener != null) {
                imageUploadListener.onUploadFailed(AppController.getInstance().getString(R.string.image_upload_error_message));
            }
        }

        File imageFile = getFileFromBitmap(bitmap);
        Log.d(TAG, "File size: " + imageFile.length());

        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/png"), imageFile);
        MultipartBody.Part body = MultipartBody.Part.createFormData("uploaded_file", imageFile.getName(), requestFile);
        RequestBody description = RequestBody.create(MediaType.parse("multipart/form-data"), RemoteConstant.PUBLIC_API_TOKEN);

        Call<InsertImageResponse> call = PbazaarApi.getInstance().getPbazaarApiServiceClient().uploadImage(body, description);
        call.enqueue(new Callback<InsertImageResponse>() {
            @Override
            public void onResponse(Call<InsertImageResponse> call, Response<InsertImageResponse> response) {
                if (response.isSuccessful()) {
                    InsertImageResponse insertImageResponse = response.body();
                    Log.d(TAG, "upload succes: " + insertImageResponse.getSuccess());

                    if (insertImageResponse.getSuccess() == 1) {

                        Log.d(TAG, "Success: " + insertImageResponse.getData().getImageUrl());
                        Log.d(TAG, "Success: " + insertImageResponse.getData().getId());

                        if (imageUploadListener != null) {
                            imageUploadListener.onUploadSuccess(bitmap, insertImageResponse.getData().getId());
                        }
                    } else {
                        Log.d(TAG, "Success: " + insertImageResponse.getMesaage());
                        if (imageUploadListener != null) {
                            imageUploadListener.onUploadFailed(insertImageResponse.getMesaage());
                        }
                    }
                } else {
                    Log.d(TAG, "upload error");
                    if (imageUploadListener != null) {
                        imageUploadListener.onUploadFailed(AppController.getInstance().getString(R.string.image_upload_error_message));
                    }
                }
            }

            @Override
            public void onFailure(Call<InsertImageResponse> call, Throwable t) {
                Log.d(TAG, "upload fail: " + t.getMessage());
                if (imageUploadListener != null) {
                    imageUploadListener.onUploadFailed(AppController.getInstance().getString(R.string.image_upload_error_message));
                }
            }
        });
    }

    private File getFileFromBitmap(Bitmap bitmap) {
        File mydir = AppController.getInstance().getCacheDir();
        File fileWithinMyDir = new File(mydir, "Pbazar");
//        String rootPath = Environment.getExternalStorageDirectory() + "/Eskarjay";
//        File file = new File(fileWithinMyDir, fileWithinMyDir);

        if (fileWithinMyDir.exists()) {
            fileWithinMyDir.delete();
        }

        try {
            FileOutputStream out = new FileOutputStream(fileWithinMyDir);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
            out.flush();
            out.close();
            Log.d(TAG, fileWithinMyDir.length() + " bytes");
            return fileWithinMyDir;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public interface GetRootCategoryCompletionListener {
        void onLoadSuccess(ArrayList<SubCategoryModel> subCategoryModelArrayList);

        void onLoadFailed(String message);
    }

    public interface GetSubCategoryCompletionListener {
        void onLoadSuccess(ArrayList<SubCategoryModel> subCategoryModelArrayList);

        void onLoadFailed(String message);
    }

    public interface DistrictLoadCompletionListener {
        void onDistrictLoadSuccess(ArrayList<LocationSpinnerDataModel> spinnerDataModelArrayList);

        void onDistrictLoadFailed(String message);
    }

    public interface ThanaLoadCompletionListener {
        void onThanaLoadSuccess(ArrayList<LocationSpinnerDataModel> spinnerDataModelArrayList);

        void onThanaloadFailed(String message);
    }

    public interface ProductImageUploadListener {
        void onUploadSuccess(Bitmap bitmap, int imageId);

        void onUploadFailed(String message);
    }

}
