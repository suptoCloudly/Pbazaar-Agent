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
    @SerializedName("advertiserPhone2")
    @Expose
    private String advertiserPhone2;
    @SerializedName("advertiserPhone3")
    @Expose
    private String advertiserPhone3;
    @SerializedName("advertiserEmail")
    @Expose
    private String advertiserEmail;
    @SerializedName("sectorBlock")
    @Expose
    private String sectorBlock;
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

    @SerializedName("Latitude")
    @Expose
    private double latitude;
    @SerializedName("longitude")
    @Expose
    private double longitude;
    @SerializedName("GMapStringResponse")
    @Expose
    private String address;

    public PostProductRequest(String apiToken, Integer categoryId, Integer subCategoryId, String advertiserName, String advertiserPhone, String advertiserPhone2, String advertiserPhone3, String advertiserEmail, String sectorBlock, String houseNo, String roadNo, Integer thanaAreaId, Integer pictureId, Integer collectedById, double latitude, double longitude, String address) {
        super(apiToken);
        this.categoryId = categoryId;
        this.subCategoryId = subCategoryId;
        this.advertiserName = advertiserName;
        this.advertiserPhone = advertiserPhone;
        this.advertiserPhone2 = advertiserPhone2;
        this.advertiserPhone3 = advertiserPhone3;
        this.advertiserEmail = advertiserEmail;
        this.sectorBlock = sectorBlock;
        this.houseNo = houseNo;
        this.roadNo = roadNo;
        this.thanaAreaId = thanaAreaId;
        this.pictureId = pictureId;
        this.collectedById = collectedById;
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(Integer subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

    public String getAdvertiserName() {
        return advertiserName;
    }

    public void setAdvertiserName(String advertiserName) {
        this.advertiserName = advertiserName;
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

    public String getAdvertiserEmail() {
        return advertiserEmail;
    }

    public void setAdvertiserEmail(String advertiserEmail) {
        this.advertiserEmail = advertiserEmail;
    }

    public String getSectorBlock() {
        return sectorBlock;
    }

    public void setSectorBlock(String sectorBlock) {
        this.sectorBlock = sectorBlock;
    }

    public String getHouseNo() {
        return houseNo;
    }

    public void setHouseNo(String houseNo) {
        this.houseNo = houseNo;
    }

    public String getRoadNo() {
        return roadNo;
    }

    public void setRoadNo(String roadNo) {
        this.roadNo = roadNo;
    }

    public Integer getThanaAreaId() {
        return thanaAreaId;
    }

    public void setThanaAreaId(Integer thanaAreaId) {
        this.thanaAreaId = thanaAreaId;
    }

    public Integer getPictureId() {
        return pictureId;
    }

    public void setPictureId(Integer pictureId) {
        this.pictureId = pictureId;
    }

    public Integer getCollectedById() {
        return collectedById;
    }

    public void setCollectedById(Integer collectedById) {
        this.collectedById = collectedById;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
