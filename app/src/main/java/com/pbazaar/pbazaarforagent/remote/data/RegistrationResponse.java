package com.pbazaar.pbazaarforagent.remote.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Supto on 4/4/2017.
 */

public class RegistrationResponse {

    @SerializedName("success")
    @Expose
    private Integer success;

    @SerializedName("data")
    @Expose
    private String data;

    @SerializedName("Message")
    @Expose
    private List<String> message = null;

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

    public List<String> getMessage() {
        return message;
    }

    public void setMessage(List<String> message) {
        this.message = message;
    }
}
