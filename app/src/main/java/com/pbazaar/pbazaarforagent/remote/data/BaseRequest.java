package com.pbazaar.pbazaarforagent.remote.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.pbazaar.pbazaarforagent.remote.RemoteConstant;

/**
 * Created by supto on 4/2/17.
 */

public class BaseRequest {

    @SerializedName("apiToken")
    @Expose
    private String apiToken = RemoteConstant.PUBLIC_API_TOKEN;

    public BaseRequest() {
    }

    public BaseRequest(String apiToken) {
        this.apiToken = apiToken;
    }

    public String getApiToken() {
        return apiToken;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }
}
