package com.pbazaar.pbazaarforagent.model;

/**
 * Created by supto on 4/6/17.
 */

public class PostProductModel {

    private int categoryId;
    private int subCategoryId;
    private String advertiserName;
    private String advertiserPhone;
    private String advertiserPhone2;
    private String advertiserPhone3;
    private String advertiserEmail;
    private String blockSector;
    private String houseNo;
    private String roadNo;
    private int thanaAreaId;
    private int pictureId;
    private int collectedId;
    private double lat;
    private double lng;
    private String address;

    public PostProductModel(int categoryId, int subCategoryId, String advertiserName, String advertiserPhone, String advertiserPhone2, String advertiserPhone3, String advertiserEmail, String blockSector, String houseNo, String roadNo, int thanaAreaId, int pictureId, int collectedId, double lat, double lng, String address) {
        this.categoryId = categoryId;
        this.subCategoryId = subCategoryId;
        this.advertiserName = advertiserName;
        this.advertiserPhone = advertiserPhone;
        this.advertiserPhone2 = advertiserPhone2;
        this.advertiserPhone3 = advertiserPhone3;
        this.advertiserEmail = advertiserEmail;
        this.blockSector = blockSector;
        this.houseNo = houseNo;
        this.roadNo = roadNo;
        this.thanaAreaId = thanaAreaId;
        this.pictureId = pictureId;
        this.collectedId = collectedId;
        this.lat = lat;
        this.lng = lng;
        this.address = address;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(int subCategoryId) {
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

    public String getBlockSector() {
        return blockSector;
    }

    public void setBlockSector(String blockSector) {
        this.blockSector = blockSector;
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

    public int getThanaAreaId() {
        return thanaAreaId;
    }

    public void setThanaAreaId(int thanaAreaId) {
        this.thanaAreaId = thanaAreaId;
    }

    public int getPictureId() {
        return pictureId;
    }

    public void setPictureId(int pictureId) {
        this.pictureId = pictureId;
    }

    public int getCollectedId() {
        return collectedId;
    }

    public void setCollectedId(int collectedId) {
        this.collectedId = collectedId;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
