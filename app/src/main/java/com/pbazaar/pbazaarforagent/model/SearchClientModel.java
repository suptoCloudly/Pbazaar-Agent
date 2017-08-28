package com.pbazaar.pbazaarforagent.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by supto on 8/28/17.
 */

public class SearchClientModel implements Parcelable{

    private String fullName;
    private String mobileNumber;
    private String emailAddress;
    private int agentId;
    private int id;

    public SearchClientModel() {
    }

    public SearchClientModel(String fullName, String mobileNumber, String emailAddress, int agentId, int id) {
        this.fullName = fullName;
        this.mobileNumber = mobileNumber;
        this.emailAddress = emailAddress;
        this.agentId = agentId;
        this.id = id;
    }

    protected SearchClientModel(Parcel in) {
        fullName = in.readString();
        mobileNumber = in.readString();
        emailAddress = in.readString();
        agentId = in.readInt();
        id = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(fullName);
        dest.writeString(mobileNumber);
        dest.writeString(emailAddress);
        dest.writeInt(agentId);
        dest.writeInt(id);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<SearchClientModel> CREATOR = new Creator<SearchClientModel>() {
        @Override
        public SearchClientModel createFromParcel(Parcel in) {
            return new SearchClientModel(in);
        }

        @Override
        public SearchClientModel[] newArray(int size) {
            return new SearchClientModel[size];
        }
    };

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public int getAgentId() {
        return agentId;
    }

    public void setAgentId(int agentId) {
        this.agentId = agentId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "SearchClientModel{" +
                "fullName='" + fullName + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", agentId=" + agentId +
                ", id=" + id +
                '}';
    }
}
