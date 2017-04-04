package com.pbazaar.pbazaarforagent.ui.main;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

public class ProductPostingFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = ProductPostingFragment.class.getSimpleName();

    private static final int PICK_IMAGE_ID = 100;


    @BindView(R.id.image_view_product_posting_fragment)
    ImageView productimage;

    @BindView(R.id.button_select_image_product_posting_fragment)
    Button selectImageButton;

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

        selectImageButton.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        if (view == selectImageButton) {
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
                    productimage.setImageBitmap(bitmap);

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

    private File getFileFromBitmap(Bitmap bitmap) {
        File mydir = getActivity().getDir("Pbazar", Context.MODE_PRIVATE);
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
