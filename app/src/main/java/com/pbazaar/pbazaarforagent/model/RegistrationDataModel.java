package com.pbazaar.pbazaarforagent.model;

/**
 * Created by Supto on 4/4/2017.
 */

public class RegistrationDataModel {

    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private String gender;
    private boolean isIndividualForRent;
    private boolean isIndividualForSell;
    private boolean isRealEstateCompany;
    private boolean isHotelGuestHouse;
    private boolean isAgentForSell;
    private boolean isAgentForRent;
    private String company;
    private int countryId;
    private int districtId;
    private int thanaAreaId;
    private String streetAddress;
    private String streetAddress2;
    private String mobile;
    private String mobile2;
    private String phone;
    private boolean subscribeNewsletter;
    private String password;

    public RegistrationDataModel() {
    }

    public RegistrationDataModel(String firstName, String lastName, String userName, String email, String gender, boolean isIndividualForRent, boolean isIndividualForSell, boolean isRealEstateCompany, boolean isHotelGuestHouse, boolean isAgentForSell, boolean isAgentForRent, String company, int countryId, int districtId, int thanaAreaId, String streetAddress, String streetAddress2, String mobile, String mobile2, String phone, boolean subscribeNewsletter, String password) {
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
        this.subscribeNewsletter = subscribeNewsletter;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public boolean isIndividualForRent() {
        return isIndividualForRent;
    }

    public void setIndividualForRent(boolean individualForRent) {
        isIndividualForRent = individualForRent;
    }

    public boolean isIndividualForSell() {
        return isIndividualForSell;
    }

    public void setIndividualForSell(boolean individualForSell) {
        isIndividualForSell = individualForSell;
    }

    public boolean isRealEstateCompany() {
        return isRealEstateCompany;
    }

    public void setRealEstateCompany(boolean realEstateCompany) {
        isRealEstateCompany = realEstateCompany;
    }

    public boolean isHotelGuestHouse() {
        return isHotelGuestHouse;
    }

    public void setHotelGuestHouse(boolean hotelGuestHouse) {
        isHotelGuestHouse = hotelGuestHouse;
    }

    public boolean isAgentForSell() {
        return isAgentForSell;
    }

    public void setAgentForSell(boolean agentForSell) {
        isAgentForSell = agentForSell;
    }

    public boolean isAgentForRent() {
        return isAgentForRent;
    }

    public void setAgentForRent(boolean agentForRent) {
        isAgentForRent = agentForRent;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public int getDistrictId() {
        return districtId;
    }

    public void setDistrictId(int districtId) {
        this.districtId = districtId;
    }

    public int getThanaAreaId() {
        return thanaAreaId;
    }

    public void setThanaAreaId(int thanaAreaId) {
        this.thanaAreaId = thanaAreaId;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isSubscribeNewsletter() {
        return subscribeNewsletter;
    }

    public void setSubscribeNewsletter(boolean subscribeNewsletter) {
        this.subscribeNewsletter = subscribeNewsletter;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
