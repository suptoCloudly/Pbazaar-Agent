package com.pbazaar.pbazaarforagent.remote.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by supto on 4/9/17.
 */

public class RegistrationRequest extends BaseRequest {
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("firstName")
    @Expose
    private String firstName;
    @SerializedName("lastName")
    @Expose
    private String lastName;
    @SerializedName("company")
    @Expose
    private String company;
    @SerializedName("streetAddress")
    @Expose
    private String streetAddress;
    @SerializedName("streetAddress2")
    @Expose
    private String streetAddress2;
    @SerializedName("countryId")
    @Expose
    private Integer countryId;
    @SerializedName("districtId")
    @Expose
    private Integer districtId;
    @SerializedName("thanaAreaId")
    @Expose
    private Integer thanaAreaId;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("mobile2")
    @Expose
    private String mobile2;
    @SerializedName("subscribeNewsletter")
    @Expose
    private Boolean subscribeNewsletter;
    @SerializedName("isAgentData")
    @Expose
    private Boolean isAgentData;
    @SerializedName("isAgentSearch")
    @Expose
    private Boolean isAgentSearch;
    @SerializedName("isAgentAgency")
    @Expose
    private Boolean isAgentAgency;
    @SerializedName("referralCode")
    @Expose
    private String referralCode;

    public RegistrationRequest(String apiToken, String email, String username, String password, String gender, String firstName, String lastName, String company, String streetAddress, String streetAddress2, Integer countryId, Integer districtId, Integer thanaAreaId, String phone, String mobile, String mobile2, Boolean subscribeNewsletter, Boolean isAgentData, Boolean isAgentSearch, Boolean isAgentAgency, String referralCode) {
        super(apiToken);
        this.email = email;
        this.username = username;
        this.password = password;
        this.gender = gender;
        this.firstName = firstName;
        this.lastName = lastName;
        this.company = company;
        this.streetAddress = streetAddress;
        this.streetAddress2 = streetAddress2;
        this.countryId = countryId;
        this.districtId = districtId;
        this.thanaAreaId = thanaAreaId;
        this.phone = phone;
        this.mobile = mobile;
        this.mobile2 = mobile2;
        this.subscribeNewsletter = subscribeNewsletter;
        this.isAgentData = isAgentData;
        this.isAgentSearch = isAgentSearch;
        this.isAgentAgency = isAgentAgency;
        this.referralCode = referralCode;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getStreetAddress2() {
        return streetAddress2;
    }

    public void setStreetAddress2(String streetAddress2) {
        this.streetAddress2 = streetAddress2;
    }

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public Integer getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Integer districtId) {
        this.districtId = districtId;
    }

    public Integer getThanaAreaId() {
        return thanaAreaId;
    }

    public void setThanaAreaId(Integer thanaAreaId) {
        this.thanaAreaId = thanaAreaId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMobile2() {
        return mobile2;
    }

    public void setMobile2(String mobile2) {
        this.mobile2 = mobile2;
    }

    public Boolean getSubscribeNewsletter() {
        return subscribeNewsletter;
    }

    public void setSubscribeNewsletter(Boolean subscribeNewsletter) {
        this.subscribeNewsletter = subscribeNewsletter;
    }

    public Boolean getAgentData() {
        return isAgentData;
    }

    public void setAgentData(Boolean agentData) {
        isAgentData = agentData;
    }

    public Boolean getAgentSearch() {
        return isAgentSearch;
    }

    public void setAgentSearch(Boolean agentSearch) {
        isAgentSearch = agentSearch;
    }

    public Boolean getAgentAgency() {
        return isAgentAgency;
    }

    public void setAgentAgency(Boolean agentAgency) {
        isAgentAgency = agentAgency;
    }

    public String getReferralCode() {
        return referralCode;
    }

    public void setReferralCode(String referralCode) {
        this.referralCode = referralCode;
    }
}
