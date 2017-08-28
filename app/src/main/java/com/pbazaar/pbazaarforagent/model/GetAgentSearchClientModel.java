package com.pbazaar.pbazaarforagent.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by supto on 8/27/17.
 */

public class GetAgentSearchClientModel implements Parcelable{

    private Object fullName;
    private String mobileNumber;
    private String emailAddress;
    private int agentId;
    private int id;


    public GetAgentSearchClientModel() {
    }

    public GetAgentSearchClientModel(Object fullName, String mobileNumber, String emailAddress, Integer agentId, Integer id) {
        this.fullName = fullName;
        this.mobileNumber = mobileNumber;
        this.emailAddress = emailAddress;
        this.agentId = agentId;
        this.id = id;
    }

    protected GetAgentSearchClientModel(Parcel in) {
        mobileNumber = in.readString();
        emailAddress = in.readString();
        agentId = in.readInt();
        id = in.readInt();
    }

    public static final Creator<GetAgentSearchClientModel> CREATOR = new Creator<GetAgentSearchClientModel>() {
        @Override
        public GetAgentSearchClientModel createFromParcel(Parcel in) {
            return new GetAgentSearchClientModel(in);
        }

        @Override
        public GetAgentSearchClientModel[] newArray(int size) {
            return new GetAgentSearchClientModel[size];
        }
    };

    public Object getFullName() {
        return fullName;
    }

    public void setFullName(Object fullName) {
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
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mobileNumber);
        parcel.writeString(emailAddress);
        parcel.writeInt(agentId);
        parcel.writeInt(id);
    }
}
