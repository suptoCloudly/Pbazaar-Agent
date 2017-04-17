package com.pbazaar.pbazaarforagent.ui.main.product;

import android.graphics.Bitmap;
import android.util.Log;

import com.pbazaar.pbazaarforagent.R;
import com.pbazaar.pbazaarforagent.helper.AppController;
import com.pbazaar.pbazaarforagent.helper.PreferenceHelper;
import com.pbazaar.pbazaarforagent.model.LocationSpinnerDataModel;
import com.pbazaar.pbazaarforagent.model.PostProductModel;
import com.pbazaar.pbazaarforagent.model.SubCategoryModel;
import com.pbazaar.pbazaarforagent.remote.PbazaarApi;
import com.pbazaar.pbazaarforagent.remote.RemoteConstant;
import com.pbazaar.pbazaarforagent.remote.data.GetDistrictByCountryIdRequest;
import com.pbazaar.pbazaarforagent.remote.data.GetDistrictByCountryIdResponse;
import com.pbazaar.pbazaarforagent.remote.data.GetSubcategoryRequest;
import com.pbazaar.pbazaarforagent.remote.data.GetSubcategoryResponse;
import com.pbazaar.pbazaarforagent.remote.data.GetThanaByDistrictIdRequest;
import com.pbazaar.pbazaarforagent.remote.data.GetThanaByDistrictIdResponse;
import com.pbazaar.pbazaarforagent.remote.data.InsertImageResponse;
import com.pbazaar.pbazaarforagent.remote.data.PostProductRequest;
import com.pbazaar.pbazaarforagent.remote.data.PostProductResponse;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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


    public void getSubCategories(int categoryId, final GetSubCategoryCompletionListener completionListener) {

        final ArrayList<SubCategoryModel> subCategoryModelsList = new ArrayList<>();

        if (categoryId == -1) {
            if (completionListener != null)
                completionListener.onLoadSuccess(subCategoryModelsList);
        }


        if (!PreferenceHelper.getInstance().getNetworkStatus()) {
            if (completionListener != null)
                completionListener.onLoadFailed(AppController.getInstance().getString(R.string.no_internet_error_message));
            return;
        }

        GetSubcategoryRequest request = new GetSubcategoryRequest(RemoteConstant.PUBLIC_API_TOKEN, categoryId);
        Call<GetSubcategoryResponse> call = PbazaarApi.getInstance().getPbazaarApiServiceClient().getSubcategory(request);
        call.enqueue(new Callback<GetSubcategoryResponse>() {
            @Override
            public void onResponse(Call<GetSubcategoryResponse> call, Response<GetSubcategoryResponse> response) {
                if (response.isSuccessful()) {
                    GetSubcategoryResponse getSubcategoryResponse = response.body();
                    if (getSubcategoryResponse.getSuccess() == 1) {
                        for (GetSubcategoryResponse.Data data : getSubcategoryResponse.getData()) {
                            subCategoryModelsList.add(new SubCategoryModel(data.getName(), data.getId()));
                        }
                        if (completionListener != null)
                            completionListener.onLoadSuccess(subCategoryModelsList);
                    } else {
                        if (completionListener != null)
                            completionListener.onLoadFailed(getSubcategoryResponse.getMessage());

                        Log.d(TAG, getSubcategoryResponse.getMessage());
                    }

                    Log.d(TAG, "Subcategory response: " + getSubcategoryResponse.getSuccess());
                } else {
                    if (completionListener != null)
                        completionListener.onLoadSuccess(subCategoryModelsList);
                }
            }

            @Override
            public void onFailure(Call<GetSubcategoryResponse> call, Throwable t) {
                if (completionListener != null)
                    completionListener.onLoadFailed(AppController.getInstance().getString(R.string.no_internet_error_message));
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


        final GetDistrictByCountryIdRequest getDistrictByCountryIdRequest = new GetDistrictByCountryIdRequest(RemoteConstant.PUBLIC_API_TOKEN, countryId);
        final ArrayList<LocationSpinnerDataModel> spinnerDataModelArrayList = new ArrayList<LocationSpinnerDataModel>();

        new Thread(new Runnable() {
            @Override
            public void run() {

                int retryCount = 0;
                while (true) {

                    try {
                        final Call<GetDistrictByCountryIdResponse> call = PbazaarApi.getInstance().getPbazaarApiServiceClient().getDistrictsByCountryId(getDistrictByCountryIdRequest);
                        Response<GetDistrictByCountryIdResponse> response = call.execute();
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
                            Log.d(TAG, "Response success");

                            break;
                        } else {

                            retryCount++;
                            Log.d(TAG, "Response unsuccess");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        Log.d(TAG, "Response fail");
                        retryCount++;
                    }

                    if (retryCount > RemoteConstant.RETRY_ATTEMPT) {
                        if (districtLoadCompletionListener != null)
                            districtLoadCompletionListener.onDistrictLoadFailed(AppController.getInstance().getString(R.string.no_internet_error_message));

                        break;
                    }

                }
            }
        }).start();


//        GetDistrictByCountryIdRequest getDistrictByCountryIdRequest = new GetDistrictByCountryIdRequest(RemoteConstant.PUBLIC_API_TOKEN, countryId);
//        final ArrayList<LocationSpinnerDataModel> spinnerDataModelArrayList = new ArrayList<LocationSpinnerDataModel>();
//
//        Call<GetDistrictByCountryIdResponse> call = PbazaarApi.getInstance().getPbazaarApiServiceClient().getDistrictsByCountryId(getDistrictByCountryIdRequest);
//        call.enqueue(new Callback<GetDistrictByCountryIdResponse>() {
//            @Override
//            public void onResponse(Call<GetDistrictByCountryIdResponse> call, Response<GetDistrictByCountryIdResponse> response) {
//                if (response.isSuccessful()) {
//
//                    GetDistrictByCountryIdResponse districtByCountryIdResponse = response.body();
//
//                    // if the request returns data then send it to view or show error message
//                    if (districtByCountryIdResponse.getSuccess() == 1) {
//                        for (GetDistrictByCountryIdResponse.Data data : districtByCountryIdResponse.getData()) {
//                            LocationSpinnerDataModel locationSpinnerDataModel = new LocationSpinnerDataModel(data.getName(), data.getId());
//                            spinnerDataModelArrayList.add(locationSpinnerDataModel);
//                        }
//
//                        if (districtLoadCompletionListener != null)
//                            districtLoadCompletionListener.onDistrictLoadSuccess(spinnerDataModelArrayList);
//                    } else {
//                        if (districtLoadCompletionListener != null)
//                            districtLoadCompletionListener.onDistrictLoadFailed(districtByCountryIdResponse.getMessage());
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<GetDistrictByCountryIdResponse> call, Throwable t) {
//                if (districtLoadCompletionListener != null)
//                    districtLoadCompletionListener.onDistrictLoadFailed(AppController.getInstance().getString(R.string.no_internet_error_message));
//            }
//        });
    }

    public void getThanaByDistrictId(final int districtId, final ProductPostingRemoteService.ThanaLoadCompletionListener thanaLoadCompletionListener) {

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


        new Thread(new Runnable() {
            @Override
            public void run() {

                int retryCount = 0;
                while (true) {

                    final GetThanaByDistrictIdRequest getThanaByDistrictIdRequest = new GetThanaByDistrictIdRequest(RemoteConstant.PUBLIC_API_TOKEN, districtId);
                    Call<GetThanaByDistrictIdResponse> call = PbazaarApi.getInstance().getPbazaarApiServiceClient().getThanaByDistrictId(getThanaByDistrictIdRequest);

                    try {
                        Response<GetThanaByDistrictIdResponse> response = call.execute();
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
                            break;
                        } else {
                            retryCount++;
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        retryCount++;
                    }

                    if (retryCount > RemoteConstant.RETRY_ATTEMPT) {
                        if (thanaLoadCompletionListener != null)
                            thanaLoadCompletionListener.onThanaloadFailed(AppController.getInstance().getString(R.string.no_internet_error_message));
                        break;
                    }

                }
            }
        }).start();


//        final GetThanaByDistrictIdRequest getThanaByDistrictIdRequest = new GetThanaByDistrictIdRequest(RemoteConstant.PUBLIC_API_TOKEN, districtId);
//        Call<GetThanaByDistrictIdResponse> call = PbazaarApi.getInstance().getPbazaarApiServiceClient().getThanaByDistrictId(getThanaByDistrictIdRequest);
//
//        call.enqueue(new Callback<GetThanaByDistrictIdResponse>() {
//            @Override
//            public void onResponse(Call<GetThanaByDistrictIdResponse> call, Response<GetThanaByDistrictIdResponse> response) {
//                if (response.isSuccessful()) {
//                    GetThanaByDistrictIdResponse getThanaByDistrictIdResponse = response.body();
//
//                    if (getThanaByDistrictIdResponse.getSuccess() == 1) {
//                        for (GetThanaByDistrictIdResponse.Data data : getThanaByDistrictIdResponse.getData()) {
//                            LocationSpinnerDataModel locationSpinnerDataModel = new LocationSpinnerDataModel(data.getName(), data.getId());
//                            spinnerDataModelArrayList.add(locationSpinnerDataModel);
//                        }
//
//                        if (thanaLoadCompletionListener != null)
//                            thanaLoadCompletionListener.onThanaLoadSuccess(spinnerDataModelArrayList);
//                    } else {
//                        if (thanaLoadCompletionListener != null)
//                            thanaLoadCompletionListener.onThanaloadFailed(getThanaByDistrictIdResponse.getMessage());
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<GetThanaByDistrictIdResponse> call, Throwable t) {
//                if (thanaLoadCompletionListener != null)
//                    thanaLoadCompletionListener.onThanaloadFailed(AppController.getInstance().getString(R.string.no_internet_error_message));
//            }
//        });

    }

    public void uploadProductImage(final Bitmap bitmap, final ProductImageUploadListener imageUploadListener) {


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

        Call<InsertImageResponse> call = PbazaarApi.getInstance().getPbazarApiServiceClientForMultipartUpload().uploadImage(body, description);
        call.enqueue(new Callback<InsertImageResponse>() {
            @Override
            public void onResponse(Call<InsertImageResponse> call, Response<InsertImageResponse> response) {
                if (response.isSuccessful()) {
                    InsertImageResponse insertImageResponse = response.body();
                    Log.d(TAG, "upload succes: " + insertImageResponse.getSuccess());
                    Log.d(TAG, "upload result: " + response.message());

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
                t.printStackTrace();
                if (imageUploadListener != null) {
                    imageUploadListener.onUploadFailed(AppController.getInstance().getString(R.string.image_upload_error_message));
                }
            }
        });
    }


    public void postProduct(PostProductModel model, final ProductPostCompletionListener completionListener) {

        if (!PreferenceHelper.getInstance().getNetworkStatus()) {
            if (completionListener != null)
                completionListener.onPostFailed(AppController.getInstance().getString(R.string.no_internet_error_message));
            return;
        }


        if (!checkEmptyString(model.getAdvertiserPhone(), model.getHouseNo(), model.getRoadNo())) {
            if (completionListener != null)
                completionListener.onPostFailed(AppController.getInstance().getString(R.string.empty_textbox_error_meassage));
            return;
        }


        if (model.getCategoryId() == -1 || model.getSubCategoryId() == -1 || model.getThanaAreaId() == -1 || model.getCollectedId() == PreferenceHelper.CUSTOMER_ID_DEFAULT_VALUE) {
            if (completionListener != null)
                completionListener.onPostFailed(AppController.getInstance().getString(R.string.empty_textbox_error_meassage));
            return;
        }


        PostProductRequest request = new PostProductRequest(RemoteConstant.PUBLIC_API_TOKEN, model.getCategoryId(), model.getSubCategoryId(), model.getAdvertiserName(), model.getAdvertiserPhone(), model.getAdvertiserEmail(), model.getBlockSector(), model.getHouseNo(), model.getRoadNo(), model.getThanaAreaId(), model.getPictureId(), model.getCollectedId());
        Call<PostProductResponse> call = PbazaarApi.getInstance().getPbazaarApiServiceClient().postProduct(request);
        call.enqueue(new Callback<PostProductResponse>() {
            @Override
            public void onResponse(Call<PostProductResponse> call, Response<PostProductResponse> response) {
                if (response.isSuccessful()) {

                    PostProductResponse postProductResponse = response.body();
                    if (postProductResponse.getSuccess() == 1) {
                        if (completionListener != null)
                            completionListener.onPostSuccess(postProductResponse.getData());
                    } else {
                        if (completionListener != null)
                            completionListener.onPostFailed(postProductResponse.getMessage());
                    }


                } else {
                    Log.d(TAG, "Image upload unsuccess");
                    if (completionListener != null)
                        completionListener.onPostFailed(AppController.getInstance().getString(R.string.no_internet_error_message));

                }
            }

            @Override
            public void onFailure(Call<PostProductResponse> call, Throwable t) {

                Log.d(TAG, "Image upload error: " + t.getMessage());
                if (completionListener != null)
                    completionListener.onPostFailed(AppController.getInstance().getString(R.string.no_internet_error_message));
            }
        });
    }


    private File getFileFromBitmap(Bitmap bitmap) {
        File mydir = AppController.getInstance().getCacheDir();
        File fileWithinMyDir = new File(mydir, AppController.getInstance().getString(R.string.app_name));

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

    private boolean checkEmptyString(String... strings) {
        for (String string : strings) {
            if (string.contentEquals("") || string.length() == 0) {
                return false;
            }
        }
        return true;
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

    public interface ProductPostCompletionListener {
        void onPostSuccess(String message);

        void onPostFailed(String message);
    }

}
