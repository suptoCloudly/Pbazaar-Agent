package com.pbazaar.pbazaarforagent.remote.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Supto on 5/11/2017.
 */

public class PostHistoryRequest extends BaseRequest {

    @SerializedName("collectedById")
    @Expose
    private int collectedById;

    public PostHistoryRequest(String apiToken, int collectedById) {
        super(apiToken);
        this.collectedById = collectedById;
    }


    public int getCollectedById() {
        return collectedById;
    }

    public void setCollectedById(int collectedById) {
        this.collectedById = collectedById;
    }
}
