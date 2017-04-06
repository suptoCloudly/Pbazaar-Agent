package com.pbazaar.pbazaarforagent.model;

/**
 * Created by supto on 4/6/17.
 */

public class PostProductModel {

    private int categoryId;
    private int subCategoryId;
    private String advertiserName;
    private String advertiserPhone;
    private String advertiserEmail;
    private String houseNo;
    private String roadNo;
    private int thanaAreaId;
    private int pictureId;
    private int collectedId;

    public PostProductModel(int categoryId, int subCategoryId, String advertiserName, String advertiserPhone, String advertiserEmail, String houseNo, String roadNo, int thanaAreaId, int pictureId, int collectedId) {
        this.categoryId = categoryId;
        this.subCategoryId = subCategoryId;
        this.advertiserName = advertiserName;
        this.advertiserPhone = advertiserPhone;
        this.advertiserEmail = advertiserEmail;
        this.houseNo = houseNo;
        this.roadNo = roadNo;
        this.thanaAreaId = thanaAreaId;
        this.pictureId = pictureId;
        this.collectedId = collectedId;
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

    public String getAdvertiserEmail() {
        return advertiserEmail;
    }

    public void setAdvertiserEmail(String advertiserEmail) {
        this.advertiserEmail = advertiserEmail;
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
}
