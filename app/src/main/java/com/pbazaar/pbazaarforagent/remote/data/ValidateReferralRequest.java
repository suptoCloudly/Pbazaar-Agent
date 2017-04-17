package com.pbazaar.pbazaarforagent.remote.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by supto on 4/13/17.
 */

public class ValidateReferralRequest extends BaseRequest {

    @SerializedName("code")
    @Expose
    private String code;

    public ValidateReferralRequest(String apiToken, String code) {
        super(apiToken);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
