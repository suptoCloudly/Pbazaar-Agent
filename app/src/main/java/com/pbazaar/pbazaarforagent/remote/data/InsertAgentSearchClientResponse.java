package com.pbazaar.pbazaarforagent.remote.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by supto on 8/28/17.
 */

public class InsertAgentSearchClientResponse {

    @SerializedName("success")
    @Expose
    private Integer success;
    @SerializedName("data")
    @Expose
    private Data data;

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


    public class Data {

        @SerializedName("FullName")
        @Expose
        private Object fullName;
        @SerializedName("MobileNumber")
        @Expose
        private String mobileNumber;
        @SerializedName("EmailAddress")
        @Expose
        private String emailAddress;
        @SerializedName("AgentId")
        @Expose
        private Integer agentId;
        @SerializedName("Id")
        @Expose
        private Integer id;
        @SerializedName("CustomProperties")
        @Expose
        private CustomProperties customProperties;

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

        public Integer getAgentId() {
            return agentId;
        }

        public void setAgentId(Integer agentId) {
            this.agentId = agentId;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public CustomProperties getCustomProperties() {
            return customProperties;
        }

        public void setCustomProperties(CustomProperties customProperties) {
            this.customProperties = customProperties;
        }

        public class CustomProperties {


        }
    }


}
