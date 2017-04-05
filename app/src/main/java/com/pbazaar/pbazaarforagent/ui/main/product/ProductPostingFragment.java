package com.pbazaar.pbazaarforagent.ui.main.product;


import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatSpinner;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;

import com.pbazaar.pbazaarforagent.CustomAreaSpinnerAdapter;
import com.pbazaar.pbazaarforagent.CustomCategorySpinnerAdapter;
import com.pbazaar.pbazaarforagent.R;
import com.pbazaar.pbazaarforagent.helper.ImagePicker;
import com.pbazaar.pbazaarforagent.model.LocationSpinnerDataModel;
import com.pbazaar.pbazaarforagent.model.SubCategoryModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductPostingFragment extends Fragment implements ProductPostingContract.View, View.OnClickListener, AdapterView.OnItemSelectedListener {

    private static final String TAG = ProductPostingFragment.class.getSimpleName();

    private static final int PICK_IMAGE_ID = 100;


    @BindView(R.id.root_layout_product_posting_fragment)
    CoordinatorLayout rootLayout;

    @BindView(R.id.spinner_category_product_posting_fragment)
    AppCompatSpinner selectCategorySpinner;

    @BindView(R.id.spinner_subcategory_product_posting_fragment)
    AppCompatSpinner selectSubCategorySpinner;

    @BindView(R.id.spinner_select_district_product_posting_fragment)
    AppCompatSpinner selectDistrictSpinner;

    @BindView(R.id.spinner_select_area_product_posting_fragment)
    AppCompatSpinner selectAreaSpinner;

    @BindView(R.id.et_house_no_product_posting_fragment)
    TextInputEditText houseNoEt;

    @BindView(R.id.et_road_no_product_posting_fragment)
    TextInputEditText roadNoEt;

    @BindView(R.id.image_view_pick_image_product_posting_fagment)
    ImageView pickImageImageView;

    @BindView(R.id.et_advertiser_name_product_posting_fragment)
    TextInputEditText advertiserNameEt;

    @BindView(R.id.et_advertiser_phone_product_posting_fragment)
    TextInputEditText advertiserPhoneEt;

    @BindView(R.id.et_advertiser_email_product_posting_fragment)
    TextInputEditText advertiserEmailEt;

    @BindView(R.id.button_product_post_fragment)
    Button postProductButton;


    private ProductPostingContract.Presenter presenter;

    private ArrayList<SubCategoryModel> categories;
    private ArrayList<SubCategoryModel> subCategories;
    private ArrayList<LocationSpinnerDataModel> districtList;
    private ArrayList<LocationSpinnerDataModel> thanaList;

    private CustomCategorySpinnerAdapter categorySpinnerAdapter;
    private CustomCategorySpinnerAdapter subcategorySpinnerAdapter;
    private CustomAreaSpinnerAdapter districtListAdapter;
    private CustomAreaSpinnerAdapter thanaListAdapter;

    private ProgressDialog progressDialog;

    private int imageId = 0;

    public ProductPostingFragment() {
    }

    public static ProductPostingFragment getInstance() {
        ProductPostingFragment productPostingFragment = new ProductPostingFragment();
        return productPostingFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_posting, container, false);
        ButterKnife.bind(this, view);

        pickImageImageView.setOnClickListener(this);
        selectCategorySpinner.setOnItemSelectedListener(this);
        selectDistrictSpinner.setOnItemSelectedListener(this);

        categories = new ArrayList<>();
        subCategories = new ArrayList<>();
        districtList = new ArrayList<>();
        thanaList = new ArrayList<>();

        categories.add(new SubCategoryModel("Select Category", "Select Category", -1));
        subCategories.add(new SubCategoryModel("Select Subcategory", "Select Subcategory", -1));
        districtList.add(new LocationSpinnerDataModel("Select District", -1));
        thanaList.add(new LocationSpinnerDataModel("Select Area", -1));

        categorySpinnerAdapter = new CustomCategorySpinnerAdapter(getActivity(), categories);
        subcategorySpinnerAdapter = new CustomCategorySpinnerAdapter(getActivity(), subCategories);
        districtListAdapter = new CustomAreaSpinnerAdapter(getActivity(), districtList);
        thanaListAdapter = new CustomAreaSpinnerAdapter(getActivity(), thanaList);

        selectCategorySpinner.setAdapter(categorySpinnerAdapter);
        selectSubCategorySpinner.setAdapter(subcategorySpinnerAdapter);
        selectDistrictSpinner.setAdapter(districtListAdapter);
        selectAreaSpinner.setAdapter(thanaListAdapter);

        // TODO apply some styling for progressbar
        progressDialog = new ProgressDialog(getActivity());


        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (presenter == null) {
            presenter = new ProductPostingPresenter(this);
            presenter.start();
        }
    }

    @Override
    public void onClick(View view) {
        if (view == pickImageImageView) {
            Intent chooseImageIntent = ImagePicker.getPickImageIntent(getActivity());
            startActivityForResult(chooseImageIntent, PICK_IMAGE_ID);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (requestCode) {
            case PICK_IMAGE_ID:
                Bitmap bitmap = ImagePicker.getImageFromResult(getActivity(), resultCode, data);
                if (bitmap != null) {
                    presenter.uploadProductImage(bitmap);

//                    pickImageImageView.setImageBitmap(bitmap);
//
//                    File imageFile = getFileFromBitmap(bitmap);
//
//                    Log.d(TAG, "File size: " + imageFile.length());
//
//                    RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/png"), imageFile);
//
//                    MultipartBody.Part body =
//                            MultipartBody.Part.createFormData("uploaded_file", imageFile.getName(), requestFile);
//                    RequestBody description = RequestBody.create(
//                            MediaType.parse("multipart/form-data"), RemoteConstant.PUBLIC_API_TOKEN);
//
//
//                    Call<InsertImageResponse> call = PbazaarApi.getInstance().getPbazaarApiServiceClient().uploadImage(body, description);
//                    call.enqueue(new Callback<InsertImageResponse>() {
//                        @Override
//                        public void onResponse(Call<InsertImageResponse> call, Response<InsertImageResponse> response) {
//                            if (response.isSuccessful()) {
//                                InsertImageResponse insertImageResponse = response.body();
//                                Log.d(TAG, "upload succes: " + insertImageResponse.getSuccess());
//
//                                if (insertImageResponse.getSuccess() == 1) {
//                                    Log.d(TAG, "Success: " + insertImageResponse.getData().getImageUrl());
//                                    Log.d(TAG, "Success: " + insertImageResponse.getData().getId());
//                                } else {
//                                    Log.d(TAG, "Success: " + insertImageResponse.getMesaage());
//                                }
//                            } else {
//                                Log.d(TAG, "upload error");
//                            }
//                        }
//
//                        @Override
//                        public void onFailure(Call<InsertImageResponse> call, Throwable t) {
//                            Log.d(TAG, "upload fail: " + t.getMessage());
//                        }
//                    });
                }
                break;
            default:
                super.onActivityResult(requestCode, resultCode, data);
                break;
        }
    }

    @Override
    public void setPresenter(ProductPostingContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showMessage(String message) {
        Snackbar.make(rootLayout, message, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void showLoadingIndicator(boolean status, String message) {
        if (message.length() > 0)
            progressDialog.setMessage(message);
        else
            progressDialog.setMessage(getString(R.string.network_operation_running_message));


        if (status)
            progressDialog.show();
        else
            progressDialog.dismiss();
    }

    @Override
    public void setCategories(ArrayList<SubCategoryModel> categories) {
        this.categories.clear();
        this.categories.add(new SubCategoryModel("Select Category", "Select Category", -1));
        this.categories.addAll(categories);
        categorySpinnerAdapter.notifyDataSetChanged();

    }

    @Override
    public void setSubcategories(ArrayList<SubCategoryModel> subCategories) {
        this.subCategories.clear();
        this.subCategories.add(new SubCategoryModel("Select Subcategory", "Select Subcategory", -1));
        this.subCategories.addAll(subCategories);
        subcategorySpinnerAdapter.notifyDataSetChanged();
        selectSubCategorySpinner.setSelection(0);
    }

    @Override
    public void onDistrictLoaded(ArrayList<LocationSpinnerDataModel> districtList) {
        this.districtList.clear();
        this.districtList.add(new LocationSpinnerDataModel("Select District", -1));
        this.districtList.addAll(districtList);
        districtListAdapter = new CustomAreaSpinnerAdapter(getActivity(), this.districtList);
        selectDistrictSpinner.setAdapter(districtListAdapter);
    }

    @Override
    public void onThanaLoaded(ArrayList<LocationSpinnerDataModel> thanaList) {
        this.thanaList.clear();
        this.thanaList.add(new LocationSpinnerDataModel("Select Area", -1));
        this.thanaList.addAll(thanaList);
        thanaListAdapter = new CustomAreaSpinnerAdapter(getActivity(), this.thanaList);
        selectAreaSpinner.setAdapter(thanaListAdapter);
        selectAreaSpinner.setSelection(0);
    }

    @Override
    public void onProductImageUploaded(Bitmap bitmap, int imageId) {
        pickImageImageView.setImageBitmap(bitmap);
        this.imageId = imageId;
        Log.d(TAG, "Image Id: " + imageId);
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if (adapterView == selectCategorySpinner) {
            presenter.getSubcategories(categories.get(i).getId());
        } else if (adapterView == selectDistrictSpinner) {
            presenter.onDistrictSelected(districtList.get(i).getLocationId());
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
