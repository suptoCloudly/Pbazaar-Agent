package com.pbazaar.pbazaarforagent.model;

/**
 * Created by supto on 4/9/17.
 */

public class RegistrationDataModel {

    private String firstName;
    private String lastName;
    private String mobileNumber;
    private String email;
    private String gender;
    private boolean isAgentForData;
    private boolean isAgentForSearch;
    private boolean isAgentForAgency;
    private int districtId;
    private int thanaId;
    private String streetAddress;
    private String streetAddressTwo;
    private String referral;
    private String password;

    public RegistrationDataModel(String firstName, String lastName, String mobileNumber, String email, String gender, boolean isAgentForData, boolean isAgentForSearch, boolean isAgentForAgency, int districtId, int thanaId, String streetAddress, String streetAddressTwo, String referral, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobileNumber = mobileNumber;
        this.email = email;
        this.gender = gender;
        this.isAgentForData = isAgentForData;
        this.isAgentForSearch = isAgentForSearch;
        this.isAgentForAgency = isAgentForAgency;
        this.districtId = districtId;
        this.thanaId = thanaId;
        this.streetAddress = streetAddress;
        this.streetAddressTwo = streetAddressTwo;
        this.referral = referral;
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public boolean isAgentForData() {
        return isAgentForData;
    }

    public void setAgentForData(boolean agentForData) {
        isAgentForData = agentForData;
    }

    public boolean isAgentForSearch() {
        return isAgentForSearch;
    }

    public void setAgentForSearch(boolean agentForSearch) {
        isAgentForSearch = agentForSearch;
    }

    public boolean isAgentForAgency() {
        return isAgentForAgency;
    }

    public void setAgentForAgency(boolean agentForAgency) {
        isAgentForAgency = agentForAgency;
    }

    public int getDistrictId() {
        return districtId;
    }

    public void setDistrictId(int districtId) {
        this.districtId = districtId;
    }

    public int getThanaId() {
        return thanaId;
    }

    public void setThanaId(int thanaId) {
        this.thanaId = thanaId;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getStreetAddressTwo() {
        return streetAddressTwo;
    }

    public void setStreetAddressTwo(String streetAddressTwo) {
        this.streetAddressTwo = streetAddressTwo;
    }

    public String getReferral() {
        return referral;
    }

    public void setReferral(String referral) {
        this.referral = referral;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}




