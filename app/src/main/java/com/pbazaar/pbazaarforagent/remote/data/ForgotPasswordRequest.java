package com.pbazaar.pbazaarforagent.remote.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by supto on 5/8/17.
 */

public class ForgotPasswordRequest extends BaseRequest {

    @SerializedName("email")
    @Expose
    private String email;

    public ForgotPasswordRequest(String apiToken, String email) {
        super(apiToken);
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
