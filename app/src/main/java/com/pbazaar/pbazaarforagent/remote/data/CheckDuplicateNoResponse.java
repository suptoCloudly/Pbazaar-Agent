package com.pbazaar.pbazaarforagent.remote.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by supto on 5/16/17.
 */

public class CheckDuplicateNoResponse {

    @SerializedName("success")
    @Expose
    private Integer success;

    @SerializedName("data")
    @Expose
    private Data data;

    @SerializedName("message")
    @Expose
    private String message;

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public class Data {

        @SerializedName("AdvertiserPhone1Duplicate")
        @Expose
        private Boolean advertiserPhone1Duplicate;
        @SerializedName("AdvertiserPhone2Duplicate")
        @Expose
        private Boolean advertiserPhone2Duplicate;
        @SerializedName("AdvertiserPhone3Duplicate")
        @Expose
        private Boolean advertiserPhone3Duplicate;

        public Boolean getAdvertiserPhone1Duplicate() {
            return advertiserPhone1Duplicate;
        }

        public void setAdvertiserPhone1Duplicate(Boolean advertiserPhone1Duplicate) {
            this.advertiserPhone1Duplicate = advertiserPhone1Duplicate;
        }

        public Boolean getAdvertiserPhone2Duplicate() {
            return advertiserPhone2Duplicate;
        }

        public void setAdvertiserPhone2Duplicate(Boolean advertiserPhone2Duplicate) {
            this.advertiserPhone2Duplicate = advertiserPhone2Duplicate;
        }

        public Boolean getAdvertiserPhone3Duplicate() {
            return advertiserPhone3Duplicate;
        }

        public void setAdvertiserPhone3Duplicate(Boolean advertiserPhone3Duplicate) {
            this.advertiserPhone3Duplicate = advertiserPhone3Duplicate;
        }

    }
}
