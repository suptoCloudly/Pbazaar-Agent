package com.pbazaar.pbazaarforagent.ui.main.post_history;

import android.support.annotation.NonNull;
import android.util.Log;

import com.pbazaar.pbazaarforagent.R;
import com.pbazaar.pbazaarforagent.helper.AppController;
import com.pbazaar.pbazaarforagent.helper.PreferenceHelper;
import com.pbazaar.pbazaarforagent.model.PostHistoryModel;
import com.pbazaar.pbazaarforagent.remote.PbazaarApi;
import com.pbazaar.pbazaarforagent.remote.RemoteConstant;
import com.pbazaar.pbazaarforagent.remote.data.PostHistoryRequest;
import com.pbazaar.pbazaarforagent.remote.data.PostHistoryResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Supto on 5/11/2017.
 */

public class PostHistoryRemoteService {

    public static final String TAG = PostHistoryRemoteService.class.getSimpleName();


    private PostHistoryRemoteService() {

    }


    public static PostHistoryRemoteService newInstance() {
        PostHistoryRemoteService postHistoryRemoteService = new PostHistoryRemoteService();
        return postHistoryRemoteService;
    }


    public void loadPostHistory(@NonNull int collectedById, @NonNull final LoadPostHistoryListener loadPostHistoryListener) {
        if (!PreferenceHelper.getInstance().getNetworkStatus()) {
            return;
        }


        PostHistoryRequest request = new PostHistoryRequest(RemoteConstant.PUBLIC_API_TOKEN, collectedById);

        Call<PostHistoryResponse> call = PbazaarApi.getInstance().getPbazaarApiServiceClient().getPostHistory(request);
        call.enqueue(new Callback<PostHistoryResponse>() {
            @Override
            public void onResponse(Call<PostHistoryResponse> call, Response<PostHistoryResponse> response) {

                if (response.isSuccessful()) {

                    PostHistoryResponse postHistoryResponse = response.body();

                    if (postHistoryResponse.getSuccess() == 1) {

                        ArrayList<PostHistoryModel> postHistoryModels = new ArrayList<PostHistoryModel>();
                        for (PostHistoryResponse.Data data : postHistoryResponse.getData()) {
                            postHistoryModels.add(new PostHistoryModel(data.getProductInfoStatus(), data.getCountThisWeekPosts(), data.getCountAllTimePosts()));
                        }


                        loadPostHistoryListener.onLoadSuccess(postHistoryModels);
                    } else {
                        loadPostHistoryListener.onLoadFailed(postHistoryResponse.getMessage());
                    }


                } else {
                    loadPostHistoryListener.onLoadFailed(AppController.getInstance().getString(R.string.internet_connection_error_message));
                }


            }

            @Override
            public void onFailure(Call<PostHistoryResponse> call, Throwable t) {
                loadPostHistoryListener.onLoadFailed(AppController.getInstance().getString(R.string.internet_connection_error_message));
            }
        });
    }


    public interface LoadPostHistoryListener {
        void onLoadSuccess(ArrayList<PostHistoryModel> postHistoryModels);

        void onLoadFailed(String message);
    }
}
