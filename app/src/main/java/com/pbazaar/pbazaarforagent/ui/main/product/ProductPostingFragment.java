package com.pbazaar.pbazaarforagent.ui.main.product;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
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
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;
import com.pbazaar.pbazaarforagent.CustomAreaSpinnerAdapter;
import com.pbazaar.pbazaarforagent.CustomCategorySpinnerAdapter;
import com.pbazaar.pbazaarforagent.R;
import com.pbazaar.pbazaarforagent.helper.ImagePicker;
import com.pbazaar.pbazaarforagent.helper.PreferenceHelper;
import com.pbazaar.pbazaarforagent.model.LocationSpinnerDataModel;
import com.pbazaar.pbazaarforagent.model.PostProductModel;
import com.pbazaar.pbazaarforagent.model.SubCategoryModel;
import com.pbazaar.pbazaarforagent.ui.dialogs.SelectLocationDialog;
import com.pbazaar.pbazaarforagent.ui.dialogs.ShowMessageDialog;
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

    @BindView(R.id.select_property_location_tv)
    TextView selectPropertyLocationTv;

    @BindView(R.id.image_view_pick_image_product_posting_fagment)
    ImageView pickImageImageView;

    @BindView(R.id.et_advertiser_name_product_posting_fragment)
    TextInputEditText advertiserNameEt;

    @BindView(R.id.et_advertiser_phone_product_posting_fragment)
    TextInputEditText advertiserPhoneEt;

    @BindView(R.id.et_advertiser_phone_second_product_posting_fragment)
    TextInputEditText advertiserPhone2Et;

    @BindView(R.id.et_advertiser_phone_third_product_posting_fragment)
    TextInputEditText advertiserPhone3Et;

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
    private double lat = 0;
    private double lng = 0;
    private String propertyAddress = "";

    public ProductPostingFragment() {
    }

    public static ProductPostingFragment getInstance() {
        ProductPostingFragment productPostingFragment = new ProductPostingFragment();
        return productPostingFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_posting, container, false);
        ButterKnife.bind(this, view);

        pickImageImageView.setOnClickListener(this);
        postProductButton.setOnClickListener(this);
        selectPropertyLocationTv.setOnClickListener(this);
        selectDistrictSpinner.setOnItemSelectedListener(this);
        selectCategoryRadioGroup.setOnCheckedChangeListener(this);


        subCategories = new ArrayList<>();
        districtList = new ArrayList<>();
        thanaList = new ArrayList<>();

        subCategories.add(new SubCategoryModel("Select Subcategory", -1));
        districtList.add(new LocationSpinnerDataModel("Select Division", -1));
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
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.d(TAG, "OnSaveInstance");

        savedInstanceState.putParcelableArrayList("subCategories", subCategories);
        savedInstanceState.putParcelableArrayList("districtList", districtList);
        savedInstanceState.putParcelableArrayList("thanaList", thanaList);
        savedInstanceState.putInt("subCategoriesSelected", selectSubCategorySpinner.getSelectedItemPosition());
        savedInstanceState.putInt("districtListSelected", selectDistrictSpinner.getSelectedItemPosition());
        savedInstanceState.putInt("thanaListSelected", selectAreaSpinner.getSelectedItemPosition());

        savedInstanceState.putInt("categoryId", (rentRadioButton.isChecked() ? CATEGORY_RENT : CATEGORY_SELL));
        savedInstanceState.putInt("imageId", imageId);
        savedInstanceState.putString("blockSector", blockSectorEt.getText().toString());
        savedInstanceState.putString("houseNo", houseNoEt.getText().toString());
        savedInstanceState.putString("roadNo", roadNoEt.getText().toString());
        savedInstanceState.putString("advertiserName", advertiserNameEt.getText().toString());
        savedInstanceState.putString("advertiserPhone", advertiserPhoneEt.getText().toString());
        savedInstanceState.putString("advertiserPhone2", advertiserPhone2Et.getText().toString());
        savedInstanceState.putString("advertiserPhone3", advertiserPhone3Et.getText().toString());
        savedInstanceState.putString("advertiserEmail", advertiserEmailEt.getText().toString());

        savedInstanceState.putDouble("latitude", lat);
        savedInstanceState.putDouble("longitude", lng);
        savedInstanceState.putString("propertyAddress", propertyAddress);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        Log.d(TAG, "On Activity created");
        if (presenter == null) {
            Log.d(TAG, "Presenter is null");
            presenter = new ProductPostingPresenter(this);
        }

        if (savedInstanceState == null) {
            presenter.start();
        } else {
            subCategories = savedInstanceState.getParcelableArrayList("subCategories");
            districtList = savedInstanceState.getParcelableArrayList("districtList");
            thanaList = savedInstanceState.getParcelableArrayList("thanaList");


            subcategorySpinnerAdapter = new CustomCategorySpinnerAdapter(getActivity(), subCategories);
            districtListAdapter = new CustomAreaSpinnerAdapter(getActivity(), districtList);
            thanaListAdapter = new CustomAreaSpinnerAdapter(getActivity(), thanaList);

            selectSubCategorySpinner.setAdapter(subcategorySpinnerAdapter);
            selectDistrictSpinner.setAdapter(districtListAdapter);
            selectAreaSpinner.setAdapter(thanaListAdapter);

            selectSubCategorySpinner.setSelection(savedInstanceState.getInt("subCategoriesSelected"));
            selectDistrictSpinner.setSelection(savedInstanceState.getInt("districtListSelected"));
            selectAreaSpinner.setSelection(savedInstanceState.getInt("thanaListSelected"));


            if (savedInstanceState.getInt("categoryId") == CATEGORY_RENT) {
                rentRadioButton.setChecked(true);
            } else {
                sellRadioButton.setChecked(true);
            }

            this.imageId = savedInstanceState.getInt("imageId");
            blockSectorEt.setText(savedInstanceState.getString("blockSector"));
            houseNoEt.setText(savedInstanceState.getString("houseNo"));
            roadNoEt.setText(savedInstanceState.getString("roadNo"));
            advertiserNameEt.setText(savedInstanceState.getString("advertiserName"));
            advertiserPhoneEt.setText(savedInstanceState.getString("advertiserPhone"));
            advertiserPhone2Et.setText(savedInstanceState.getString("advertiserPhone2"));
            advertiserPhone3Et.setText(savedInstanceState.getString("advertiserPhone3"));
            advertiserEmailEt.setText(savedInstanceState.getString("advertiserEmail"));

            this.lat = savedInstanceState.getDouble("latitude");
            this.lng = savedInstanceState.getDouble("longitude");
            this.propertyAddress = savedInstanceState.getString("propertyAddress");
            selectPropertyLocationTv.setText(propertyAddress);
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
        } else if (view == selectPropertyLocationTv) {
            SelectLocationDialog dialog = (SelectLocationDialog) getActivity().getSupportFragmentManager().findFragmentByTag(SelectLocationDialog.class.getSimpleName());

            if (dialog == null) {
                dialog = SelectLocationDialog.newInstance(new SelectLocationDialog.AddressSelectionListener() {
                    @Override
                    public void onAddressSelected(LatLng latLng, String address) {
                        lat = latLng.latitude;
                        lng = latLng.longitude;
                        propertyAddress = address;
                        selectPropertyLocationTv.setText(propertyAddress);
                    }
                });
            }
            dialog.show(getActivity().getSupportFragmentManager(), SelectLocationDialog.class.getSimpleName());
        }
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

        if (getActivity().getSupportFragmentManager() != null) {
            ShowMessageDialog showMessageDialog = ShowMessageDialog.newInstance(message);
            showMessageDialog.show(getActivity().getSupportFragmentManager(), ShowMessageDialog.class.getSimpleName());
        }
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
    public void showImageLoadingIndicator(boolean status, String message) {
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

        subCategories.add(0, new SubCategoryModel("Select Subcategory", -1));
        if (isTwoSubCategoryModelArrayListsWithSameValues(subCategories, this.subCategories))
            return;

        this.subCategories.clear();
        this.subCategories.addAll(subCategories);
        subcategorySpinnerAdapter.notifyDataSetChanged();
        selectSubCategorySpinner.setSelection(0);
    }


    @Override
    public void onDistrictLoaded(ArrayList<LocationSpinnerDataModel> districtList) {
        districtList.add(0, new LocationSpinnerDataModel("Select Division", -1));

        if (isTwoArrayListsWithSameValues(districtList, this.districtList))
            return;

        this.districtList.clear();
        this.districtList.addAll(districtList);
        districtListAdapter = new CustomAreaSpinnerAdapter(getActivity(), this.districtList);
        selectDistrictSpinner.setAdapter(districtListAdapter);
    }

    @Override
    public void onThanaLoaded(ArrayList<LocationSpinnerDataModel> thanaList) {

        thanaList.add(0, new LocationSpinnerDataModel("Select Area", -1));
        if (isTwoArrayListsWithSameValues(thanaList, this.thanaList))
            return;

        this.thanaList.clear();
        this.thanaList.addAll(thanaList);
        thanaListAdapter = new CustomAreaSpinnerAdapter(getActivity(), this.thanaList);
        selectAreaSpinner.setAdapter(thanaListAdapter);
        selectAreaSpinner.setSelection(0);
    }

    @Override
    public void onProductImageUploaded(Bitmap bitmap, int imageId) {
        pickImageImageView.setImageBitmap(bitmap);
        this.imageId = imageId;

        Log.d(TAG, "should Scroll");

        // hide the keyboard and scroll to bottom
        scrollView.post(new Runnable() {
            @Override
            public void run() {
                scrollView.fullScroll(ScrollView.FOCUS_DOWN);
            }
        });

        if (getActivity().getCurrentFocus() != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
        }
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
        advertiserPhone2Et.setText("");
        advertiserPhone3Et.setText("");
        advertiserEmailEt.setText("");
        imageId = 0;
        pickImageImageView.setImageResource(R.drawable.ic_add_a_photo);
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

    private void postProduct() {
        // provide validation
        emptyInputFieldValidation(advertiserPhoneEt);


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
        String advertiserPhone2 = advertiserPhone2Et.getText().toString();
        String advertiserPhone3 = advertiserPhone3Et.getText().toString();
        String advertiserEmail = advertiserEmailEt.getText().toString();


        PostProductModel postProductModel = new PostProductModel(categoryId, subCategoryId, advertiserName, advertiserPhone, advertiserPhone2, advertiserPhone3, advertiserEmail, blockSector, houseNo, roadNo, areaId, imageId, collectedById, lat, lng, propertyAddress);
        presenter.onPostProductClicked(postProductModel);
    }

    private void emptyInputFieldValidation(TextInputEditText... textInputEditTextGroups) {

        for (TextInputEditText textInputEditText : textInputEditTextGroups) {
            String inputText = textInputEditText.getText().toString();
            if (inputText.matches("")) {
                textInputEditText.setError(getString(R.string.empty_input_field_error_message));
            }
        }
    }

    public boolean isTwoArrayListsWithSameValues(ArrayList<LocationSpinnerDataModel> list1, ArrayList<LocationSpinnerDataModel> list2) {
        //null checking
        if (list1.size() != list2.size()) {
            return false;
        }

        for (int i = 0; i < list1.size(); i++) {
            if (list1.get(i).getLocationId() != list2.get(i).getLocationId()) {
                return false;
            }
        }
        return true;
    }

    public boolean isTwoSubCategoryModelArrayListsWithSameValues(ArrayList<SubCategoryModel> list1, ArrayList<SubCategoryModel> list2) {
        //null checking
        if (list1.size() != list2.size()) {
            return false;
        }

        for (int i = 0; i < list1.size(); i++) {
            if (list1.get(i).getId() != list2.get(i).getId()) {
                return false;
            }
        }
        return true;
    }
}
