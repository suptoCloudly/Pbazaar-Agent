package com.pbazaar.pbazaarforagent.remote.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by supto on 4/3/17.
 */

public class RegistrationRequest extends BaseRequest {

    @SerializedName("firstName")
    @Expose
    private String firstName;
    @SerializedName("lastName")
    @Expose
    private String lastName;
    @SerializedName("userName")
    @Expose
    private String userName;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("gender")
    @Expose
    private String gender;

    @SerializedName("isIndividualForRent")
    @Expose
    private boolean isIndividualForRent;
    @SerializedName("isIndividualForSell")
    @Expose
    private boolean isIndividualForSell;
    @SerializedName("isRealEstateCompany")
    @Expose
    private boolean isRealEstateCompany;
    @SerializedName("isHotelGuestHouse")
    @Expose
    private boolean isHotelGuestHouse;
    @SerializedName("isAgentForSell")
    @Expose
    private boolean isAgentForSell;
    @SerializedName("isAgentForRent")
    @Expose
    private boolean isAgentForRent;

    @SerializedName("company")
    @Expose
    private String company;

    @SerializedName("countryId")
    @Expose
    private int countryId;
    @SerializedName("districtId")
    @Expose
    private int districtId;
    @SerializedName("thanaAreaId")
    @Expose
    private int thanaAreaId;
    @SerializedName("streetAddress")
    @Expose
    private String streetAddress;
    @SerializedName("streetAddress2")
    @Expose
    private String streetAddress2;

    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("mobile2")
    @Expose
    private String mobile2;
    @SerializedName("phone")
    @Expose
    private String phone;

    @SerializedName("subscribeNewsletter")
    @Expose
    private boolean subscribeNewsletter;

    @SerializedName("password")
    @Expose
    private String password;

    public RegistrationRequest() {
    }

    public RegistrationRequest(String apiToken, String firstName, String lastName, String userName, String email, String gender, boolean isIndividualForRent, boolean isIndividualForSell, boolean isRealEstateCompany, boolean isHotelGuestHouse, boolean isAgentForSell, boolean isAgentForRent, String company, int countryId, int districtId, int thanaAreaId, String streetAddress, String streetAddress2, String mobile, String mobile2, String phone) {
        super(apiToken);
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.email = email;
        this.gender = gender;
        this.isIndividualForRent = isIndividualForRent;
        this.isIndividualForSell = isIndividualForSell;
        this.isRealEstateCompany = isRealEstateCompany;
        this.isHotelGuestHouse = isHotelGuestHouse;
        this.isAgentForSell = isAgentForSell;
        this.isAgentForRent = isAgentForRent;
        this.company = company;
        this.countryId = countryId;
        this.districtId = districtId;
        this.thanaAreaId = thanaAreaId;
        this.streetAddress = streetAddress;
        this.streetAddress2 = streetAddress2;
        this.mobile = mobile;
        this.mobile2 = mobile2;
        this.phone = phone;
    }
}
