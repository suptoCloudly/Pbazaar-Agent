package com.pbazaar.pbazaarforagent.remote.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by supto on 5/5/17.
 */

public class PaymentHistoryResponse {

    @SerializedName("success")
    @Expose
    private Integer success;
    @SerializedName("data")
    @Expose
    private List<Data> data = null;
    @SerializedName("message")
    @Expose
    private String message;

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public class Data {

        @SerializedName("PayoutAmount")
        @Expose
        private String payoutAmount;
        @SerializedName("PaymentMethod")
        @Expose
        private String paymentMethod;
        @SerializedName("PayoutDate")
        @Expose
        private String payoutDate;
        @SerializedName("Id")
        @Expose
        private Integer id;


        public String getPayoutAmount() {
            return payoutAmount;
        }

        public void setPayoutAmount(String payoutAmount) {
            this.payoutAmount = payoutAmount;
        }

        public String getPaymentMethod() {
            return paymentMethod;
        }

        public void setPaymentMethod(String paymentMethod) {
            this.paymentMethod = paymentMethod;
        }

        public String getPayoutDate() {
            return payoutDate;
        }

        public void setPayoutDate(String payoutDate) {
            this.payoutDate = payoutDate;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

    }
}
