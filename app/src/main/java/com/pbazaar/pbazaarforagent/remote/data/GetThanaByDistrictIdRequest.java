package com.pbazaar.pbazaarforagent.remote.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by supto on 4/3/17.
 */

public class GetThanaByDistrictIdRequest extends BaseRequest {

    @SerializedName("districtId")
    @Expose
    private int districtId;

    public GetThanaByDistrictIdRequest() {
    }

    public GetThanaByDistrictIdRequest(int districtId) {
        this.districtId = districtId;
    }

    public GetThanaByDistrictIdRequest(String apiToken, int districtId) {
        super(apiToken);
        this.districtId = districtId;
    }

    public int getDistrictId() {
        return districtId;
    }

    public void setDistrictId(int districtId) {
        this.districtId = districtId;
    }
}
