package com.pbazaar.pbazaarforagent.remote.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by supto on 4/2/17.
 */

public class LoginResponse {

    @SerializedName("success")
    @Expose
    private Integer success;


    @SerializedName("data")
    @Expose
    private Data data;


    @SerializedName("message")
    @Expose
    private String message;

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public class Data {

        @SerializedName("Email")
        @Expose
        private String email;
        @SerializedName("FullName")
        @Expose
        private String fullName;
        @SerializedName("CustomerId")
        @Expose
        private Integer customerId;
        @SerializedName("RentOk")
        @Expose
        private Boolean rentOk;
        @SerializedName("SellOk")
        @Expose
        private Boolean sellOk;
        @SerializedName("RealEstateCompanyOk")
        @Expose
        private Boolean realEstateCompanyOk;
        @SerializedName("RealEstateCompanyId")
        @Expose
        private Integer realEstateCompanyId;
        @SerializedName("GuestHouseOk")
        @Expose
        private Boolean guestHouseOk;
        @SerializedName("GuestHouseId")
        @Expose
        private Integer guestHouseId;

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

        public Integer getCustomerId() {
            return customerId;
        }

        public void setCustomerId(Integer customerId) {
            this.customerId = customerId;
        }

        public Boolean getRentOk() {
            return rentOk;
        }

        public void setRentOk(Boolean rentOk) {
            this.rentOk = rentOk;
        }

        public Boolean getSellOk() {
            return sellOk;
        }

        public void setSellOk(Boolean sellOk) {
            this.sellOk = sellOk;
        }

        public Boolean getRealEstateCompanyOk() {
            return realEstateCompanyOk;
        }

        public void setRealEstateCompanyOk(Boolean realEstateCompanyOk) {
            this.realEstateCompanyOk = realEstateCompanyOk;
        }

        public Integer getRealEstateCompanyId() {
            return realEstateCompanyId;
        }

        public void setRealEstateCompanyId(Integer realEstateCompanyId) {
            this.realEstateCompanyId = realEstateCompanyId;
        }

        public Boolean getGuestHouseOk() {
            return guestHouseOk;
        }

        public void setGuestHouseOk(Boolean guestHouseOk) {
            this.guestHouseOk = guestHouseOk;
        }

        public Integer getGuestHouseId() {
            return guestHouseId;
        }

        public void setGuestHouseId(Integer guestHouseId) {
            this.guestHouseId = guestHouseId;
        }

    }
}
