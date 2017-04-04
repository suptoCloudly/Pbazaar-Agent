package com.pbazaar.pbazaarforagent.ui.main.product;


import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.pbazaar.pbazaarforagent.R;
import com.pbazaar.pbazaarforagent.helper.ImagePicker;
import com.pbazaar.pbazaarforagent.remote.PbazaarApi;
import com.pbazaar.pbazaarforagent.remote.RemoteConstant;
import com.pbazaar.pbazaarforagent.remote.data.InsertImageResponse;

import java.io.File;
import java.io.FileOutputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductPostingFragment extends Fragment implements ProductPostingContract.View, View.OnClickListener {

    private static final String TAG = ProductPostingFragment.class.getSimpleName();

    private static final int PICK_IMAGE_ID = 100;


    @BindView(R.id.image_view_pick_image_product_posting)
    ImageView pickImageImageView;


    private ProductPostingContract.Presenter presenter;


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
                    pickImageImageView.setImageBitmap(bitmap);

                    File imageFile = getFileFromBitmap(bitmap);

                    Log.d(TAG, "File size: " + imageFile.length());

                    RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/png"), imageFile);

                    MultipartBody.Part body =
                            MultipartBody.Part.createFormData("uploaded_file", imageFile.getName(), requestFile);
                    RequestBody description = RequestBody.create(
                            MediaType.parse("multipart/form-data"), RemoteConstant.PUBLIC_API_TOKEN);


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
                                } else {
                                    Log.d(TAG, "Success: " + insertImageResponse.getMesaage());
                                }
                            } else {
                                Log.d(TAG, "upload error");
                            }
                        }

                        @Override
                        public void onFailure(Call<InsertImageResponse> call, Throwable t) {
                            Log.d(TAG, "upload fail: " + t.getMessage());
                        }
                    });
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

    private File getFileFromBitmap(Bitmap bitmap) {
        File mydir = getActivity().getCacheDir();
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
}
