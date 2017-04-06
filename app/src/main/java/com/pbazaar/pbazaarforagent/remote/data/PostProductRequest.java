package com.pbazaar.pbazaarforagent.remote.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by supto on 4/6/17.
 */

public class PostProductRequest extends BaseRequest {

    @SerializedName("categoryId")
    @Expose
    private Integer categoryId;
    @SerializedName("subCategoryId")
    @Expose
    private Integer subCategoryId;
    @SerializedName("advertiserName")
    @Expose
    private String advertiserName;
    @SerializedName("advertiserPhone")
    @Expose
    private String advertiserPhone;
    @SerializedName("advertiserEmail")
    @Expose
    private String advertiserEmail;
    @SerializedName("houseNo")
    @Expose
    private String houseNo;
    @SerializedName("roadNo")
    @Expose
    private String roadNo;
    @SerializedName("thanaAreaId")
    @Expose
    private Integer thanaAreaId;
    @SerializedName("pictureId")
    @Expose
    private Integer pictureId;
    @SerializedName("collectedById")
    @Expose
    private Integer collectedById;


    public PostProductRequest(String apiToken, Integer categoryId, Integer subCategoryId, String advertiserName, String advertiserPhone, String advertiserEmail, String houseNo, String roadNo, Integer thanaAreaId, Integer pictureId, Integer collectedById) {
        super(apiToken);
        this.categoryId = categoryId;
        this.subCategoryId = subCategoryId;
        this.advertiserName = advertiserName;
        this.advertiserPhone = advertiserPhone;
        this.advertiserEmail = advertiserEmail;
        this.houseNo = houseNo;
        this.roadNo = roadNo;
        this.thanaAreaId = thanaAreaId;
        this.pictureId = pictureId;
        this.collectedById = collectedById;
    }
}
