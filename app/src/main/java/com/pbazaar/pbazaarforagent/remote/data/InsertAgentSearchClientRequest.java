package com.pbazaar.pbazaarforagent.remote.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by supto on 8/28/17.
 */

public class InsertAgentSearchClientRequest extends BaseRequest {

    @SerializedName("fullName")
    @Expose
    private String fullName;
    @SerializedName("mobileNumber")
    @Expose
    private String mobileNumber;
    @SerializedName("emailAddress")
    @Expose
    private String emailAddress;
    @SerializedName("agentId")
    @Expose
    private String agentId;

    public InsertAgentSearchClientRequest() {
    }

    public InsertAgentSearchClientRequest(String apiToken) {
        super(apiToken);
    }

    public InsertAgentSearchClientRequest(String fullName, String mobileNumber, String emailAddress, String agentId) {
        this.fullName = fullName;
        this.mobileNumber = mobileNumber;
        this.emailAddress = emailAddress;
        this.agentId = agentId;
    }

    public InsertAgentSearchClientRequest(String apiToken, String fullName, String mobileNumber, String emailAddress, String agentId) {
        super(apiToken);
        this.fullName = fullName;
        this.mobileNumber = mobileNumber;
        this.emailAddress = emailAddress;
        this.agentId = agentId;
    }

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

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }
}
