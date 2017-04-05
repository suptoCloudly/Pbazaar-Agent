package com.pbazaar.pbazaarforagent.remote.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Supto on 4/5/2017.
 */

public class GetSubcategoryFromCategoryRequest extends BaseRequest {
    @SerializedName("rootCategoryId")
    @Expose
    private Integer rootCategoryId;
    @SerializedName("level")
    @Expose
    private Integer level;
    @SerializedName("levelsToLoad")
    @Expose
    private Integer levelsToLoad;

    public GetSubcategoryFromCategoryRequest(String apiToken, Integer rootCategoryId) {
        super(apiToken);
        this.rootCategoryId = rootCategoryId;
        this.level = 0;
        this.levelsToLoad = 0;
    }
}
