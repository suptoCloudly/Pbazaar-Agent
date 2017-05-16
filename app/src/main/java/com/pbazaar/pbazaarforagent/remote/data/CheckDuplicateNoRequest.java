package com.pbazaar.pbazaarforagent.remote.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by supto on 5/16/17.
 */

public class CheckDuplicateNoRequest extends BaseRequest {

    @SerializedName("advertiserPhone")
    @Expose
    String advertiserPhone;

    @SerializedName("advertiserPhone2")
    @Expose
    String advertiserPhone2;

    @SerializedName("advertiserPhone3")
    @Expose
    String advertiserPhone3;


    public CheckDuplicateNoRequest(String apiToken, String advertiserPhone, String advertiserPhone2, String advertiserPhone3) {
        super(apiToken);
        this.advertiserPhone = advertiserPhone;
        this.advertiserPhone2 = advertiserPhone2;
        this.advertiserPhone3 = advertiserPhone3;
    }

    public String getAdvertiserPhone() {
        return advertiserPhone;
    }

    public void setAdvertiserPhone(String advertiserPhone) {
        this.advertiserPhone = advertiserPhone;
    }

    public String getAdvertiserPhone2() {
        return advertiserPhone2;
    }

    public void setAdvertiserPhone2(String advertiserPhone2) {
        this.advertiserPhone2 = advertiserPhone2;
    }

    public String getAdvertiserPhone3() {
        return advertiserPhone3;
    }

    public void setAdvertiserPhone3(String advertiserPhone3) {
        this.advertiserPhone3 = advertiserPhone3;
    }
}
