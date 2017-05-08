package com.pbazaar.pbazaarforagent.remote.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by supto on 5/8/17.
 */

public class ForgotPasswordResponse {

    @SerializedName("success")
    @Expose
    private Integer success;

    @SerializedName("data")
    @Expose
    private String data;

    @SerializedName("message")
    @Expose
    private String message;


    public ForgotPasswordResponse(Integer success, String data, String message) {
        this.success = success;
        this.data = data;
        this.message = message;
    }

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
