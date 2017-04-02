package com.pbazaar.pbazaarforagent.model;

/**
 * Created by supto on 4/2/17.
 */

public class UserDataModel {

    private String email;
    private String fullName;
    private int customerId;
    private boolean rentOk;
    private boolean sellOk;
    private boolean realEstateCompanyOk;
    private int realEstateCompanyID;
    private boolean guestHouseOk;
    private int guestHouseId;


    public UserDataModel() {
    }

    public UserDataModel(String email, String fullName, int customerId, boolean rentOk, boolean sellOk, boolean realEstateCompanyOk, int realEstateCompanyID, boolean guestHouseOk, int guestHouseId) {
        this.email = email;
        this.fullName = fullName;
        this.customerId = customerId;
        this.rentOk = rentOk;
        this.sellOk = sellOk;
        this.realEstateCompanyOk = realEstateCompanyOk;
        this.realEstateCompanyID = realEstateCompanyID;
        this.guestHouseOk = guestHouseOk;
        this.guestHouseId = guestHouseId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public boolean isRentOk() {
        return rentOk;
    }

    public void setRentOk(boolean rentOk) {
        this.rentOk = rentOk;
    }

    public boolean isSellOk() {
        return sellOk;
    }

    public void setSellOk(boolean sellOk) {
        this.sellOk = sellOk;
    }

    public boolean isRealEstateCompanyOk() {
        return realEstateCompanyOk;
    }

    public void setRealEstateCompanyOk(boolean realEstateCompanyOk) {
        this.realEstateCompanyOk = realEstateCompanyOk;
    }

    public int getRealEstateCompanyID() {
        return realEstateCompanyID;
    }

    public void setRealEstateCompanyID(int realEstateCompanyID) {
        this.realEstateCompanyID = realEstateCompanyID;
    }

    public boolean isGuestHouseOk() {
        return guestHouseOk;
    }

    public void setGuestHouseOk(boolean guestHouseOk) {
        this.guestHouseOk = guestHouseOk;
    }

    public int getGuestHouseId() {
        return guestHouseId;
    }

    public void setGuestHouseId(int guestHouseId) {
        this.guestHouseId = guestHouseId;
    }
}
