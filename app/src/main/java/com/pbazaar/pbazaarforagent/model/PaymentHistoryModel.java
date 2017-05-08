package com.pbazaar.pbazaarforagent.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by supto on 5/5/17.
 */

public class PaymentHistoryModel implements Parcelable{

    private String date;
    private String amount;
    private String method;

    public PaymentHistoryModel(String date, String amount, String method) {
        this.date = date;
        this.amount = amount;
        this.method = method;
    }


    protected PaymentHistoryModel(Parcel in) {
        date = in.readString();
        amount = in.readString();
        method = in.readString();
    }

    public static final Creator<PaymentHistoryModel> CREATOR = new Creator<PaymentHistoryModel>() {
        @Override
        public PaymentHistoryModel createFromParcel(Parcel in) {
            return new PaymentHistoryModel(in);
        }

        @Override
        public PaymentHistoryModel[] newArray(int size) {
            return new PaymentHistoryModel[size];
        }
    };

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(date);
        dest.writeString(amount);
        dest.writeString(method);
    }
}
