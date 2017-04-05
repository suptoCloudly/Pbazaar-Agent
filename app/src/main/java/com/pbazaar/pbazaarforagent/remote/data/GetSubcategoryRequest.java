package com.pbazaar.pbazaarforagent.remote.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by supto on 4/5/17.
 */

public class GetSubcategoryRequest extends BaseRequest {

    @SerializedName("typeId")
    @Expose
    private Integer typeId;


    public GetSubcategoryRequest(String apiToken, Integer typeId) {
        super(apiToken);
        this.typeId = typeId;
    }
}
