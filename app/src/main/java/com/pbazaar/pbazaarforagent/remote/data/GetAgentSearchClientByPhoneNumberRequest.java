package com.pbazaar.pbazaarforagent.remote.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by supto on 8/27/17.
 */

public class GetAgentSearchClientByPhoneNumberRequest extends BaseRequest {

    @SerializedName("phoneNumber")
    @Expose
    private String phoneNumber;


    public GetAgentSearchClientByPhoneNumberRequest(String apiToken, String phoneNumber) {
        super(apiToken);
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
