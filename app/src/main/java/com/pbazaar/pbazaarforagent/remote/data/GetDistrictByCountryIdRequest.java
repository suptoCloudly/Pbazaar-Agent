package com.pbazaar.pbazaarforagent.remote.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by supto on 4/2/17.
 */

public class GetDistrictByCountryIdRequest extends BaseRequest {

    @SerializedName("countryId")
    @Expose
    private int countryId;

    public GetDistrictByCountryIdRequest() {
    }

    public GetDistrictByCountryIdRequest(String apiToken, int countryId) {
        super(apiToken);
        this.countryId = countryId;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }
}
