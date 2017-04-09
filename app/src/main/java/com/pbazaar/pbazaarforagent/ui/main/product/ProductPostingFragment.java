package com.pbazaar.pbazaarforagent.ui.main.product;


import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatRadioButton;
import android.support.v7.widget.AppCompatSpinner;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.ScrollView;

import com.pbazaar.pbazaarforagent.CustomAreaSpinnerAdapter;
import com.pbazaar.pbazaarforagent.CustomCategorySpinnerAdapter;
import com.pbazaar.pbazaarforagent.R;
import com.pbazaar.pbazaarforagent.helper.ImagePicker;
import com.pbazaar.pbazaarforagent.helper.PreferenceHelper;
import com.pbazaar.pbazaarforagent.model.LocationSpinnerDataModel;
import com.pbazaar.pbazaarforagent.model.PostProductModel;
import com.pbazaar.pbazaarforagent.model.SubCategoryModel;
import com.pbazaar.pbazaarforagent.ui.main.PostSuccessDialog;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.content.Context.INPUT_METHOD_SERVICE;

public class ProductPostingFragment extends Fragment implements ProductPostingContract.View, View.OnClickListener, AdapterView.OnItemSelectedListener, RadioGroup.OnCheckedChangeListener {

    private static final String TAG = ProductPostingFragment.class.getSimpleName();

    private static final int PICK_IMAGE_ID = 100;

    private static final int CATEGORY_SELL = 4;
    private static final int CATEGORY_RENT = 1;


    @BindView(R.id.root_layout_product_posting_fragment)
    CoordinatorLayout rootLayout;

    @BindView(R.id.scroll_view_product_posting)
    ScrollView scrollView;

    @BindView(R.id.radio_group_select_category_product_posting)
    RadioGroup selectCategoryRadioGroup;

    @BindView(R.id.radio_button_rent_category_product_post)
    AppCompatRadioButton rentRadioButton;

    @BindView(R.id.radio_button_sell_category_product_post)
    AppCompatRadioButton sellRadioButton;

    @BindView(R.id.spinner_subcategory_product_posting_fragment)
    AppCompatSpinner selectSubCategorySpinner;

    @BindView(R.id.spinner_select_district_product_posting_fragment)
    AppCompatSpinner selectDistrictSpinner;

    @BindView(R.id.spinner_select_area_product_posting_fragment)
    AppCompatSpinner selectAreaSpinner;

    @BindView(R.id.et_sector_block_product_posting_fragment)
    TextInputEditText blockSectorEt;

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

    private ArrayList<SubCategoryModel> subCategories;
    private ArrayList<LocationSpinnerDataModel> districtList;
    private ArrayList<LocationSpinnerDataModel> thanaList;

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
        postProductButton.setOnClickListener(this);
        selectDistrictSpinner.setOnItemSelectedListener(this);
        selectCategoryRadioGroup.setOnCheckedChangeListener(this);


        subCategories = new ArrayList<>();
        districtList = new ArrayList<>();
        thanaList = new ArrayList<>();

        subCategories.add(new SubCategoryModel("Select Subcategory", -1));
        districtList.add(new LocationSpinnerDataModel("Select District", -1));
        thanaList.add(new LocationSpinnerDataModel("Select Area", -1));

        subcategorySpinnerAdapter = new CustomCategorySpinnerAdapter(getActivity(), subCategories);
        districtListAdapter = new CustomAreaSpinnerAdapter(getActivity(), districtList);
        thanaListAdapter = new CustomAreaSpinnerAdapter(getActivity(), thanaList);

        selectSubCategorySpinner.setAdapter(subcategorySpinnerAdapter);
        selectDistrictSpinner.setAdapter(districtListAdapter);
        selectAreaSpinner.setAdapter(thanaListAdapter);

        // TODO apply some styling for progressbar
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setCancelable(false);


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
        } else if (view == postProductButton) {

            // hide the soft keyboard
            if (getActivity().getCurrentFocus() != null) {
                InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
            }

            postProduct();
        }
    }

    private void postProduct() {
        // provide validation
        emptyInputFieldValidation(houseNoEt, roadNoEt, advertiserPhoneEt);


        int categoryId = (rentRadioButton.isChecked() ? CATEGORY_RENT : CATEGORY_SELL);
        int subCategoryId = subCategories.get(selectSubCategorySpinner.getSelectedItemPosition()).getId();
        int areaId = thanaList.get(selectAreaSpinner.getSelectedItemPosition()).getLocationId();
        int collectedById = PreferenceHelper.getInstance().getCustomerId();
        int imageId = this.imageId;

        String blockSector = blockSectorEt.getText().toString();
        String houseNo = houseNoEt.getText().toString();
        String roadNo = roadNoEt.getText().toString();
        String advertiserName = advertiserNameEt.getText().toString();
        String advertiserPhone = advertiserPhoneEt.getText().toString();
        String advertiserEmail = advertiserEmailEt.getText().toString();


        PostProductModel postProductModel = new PostProductModel(categoryId, subCategoryId, advertiserName, advertiserPhone, advertiserEmail, blockSector, houseNo, roadNo, areaId, imageId, collectedById);
        presenter.onPostProductClicked(postProductModel);

        Log.d(TAG, " Area: " + areaId + " cat: " + categoryId + " subc: " + subCategoryId + " coll: " + collectedById);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (requestCode) {
            case PICK_IMAGE_ID:
                Bitmap bitmap = ImagePicker.getImageFromResult(getActivity(), resultCode, data);
                if (bitmap != null) {
                    presenter.uploadProductImage(bitmap);
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
    public void setSubcategories(ArrayList<SubCategoryModel> subCategories) {
        this.subCategories.clear();
        this.subCategories.add(new SubCategoryModel("Select Subcategory", -1));
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
    public void clearAllFields() {
        selectSubCategorySpinner.setSelection(0);
        selectDistrictSpinner.setSelection(0);
        selectAreaSpinner.setSelection(0);
        houseNoEt.setText("");
        roadNoEt.setText("");
        advertiserNameEt.setText("");
        advertiserPhoneEt.setText("");
        advertiserEmailEt.setText("");
        pickImageImageView.setImageResource(R.drawable.plus);
    }

    @Override
    public void onPostSuccess() {
        PostSuccessDialog postSuccessDialog = PostSuccessDialog.getInstance(new PostSuccessDialog.OnButtonClicked() {
            @Override
            public void onPositiveButtonClicked() {
                scrollView.smoothScrollTo(0, 0);
            }

            @Override
            public void onNegativeButtonClicked() {
                getActivity().onBackPressed();
            }
        });

        postSuccessDialog.show(getActivity().getSupportFragmentManager(), PostSuccessDialog.class.getSimpleName());
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if (adapterView == selectDistrictSpinner) {
            presenter.onDistrictSelected(districtList.get(i).getLocationId());
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        if (checkedId == R.id.radio_button_rent_category_product_post) {
            presenter.getSubcategories(CATEGORY_RENT);
        } else if (checkedId == R.id.radio_button_sell_category_product_post) {
            presenter.getSubcategories(CATEGORY_SELL);
        }
    }

    private void emptyInputFieldValidation(TextInputEditText... textInputEditTextGroups) {

        for (TextInputEditText textInputEditText : textInputEditTextGroups) {
            String inputText = textInputEditText.getText().toString();
            if (inputText.matches("")) {
                textInputEditText.setError(getString(R.string.empty_input_field_error_message));
            }
        }
    }
}
