package com.pbazaar.pbazaarforagent.remote.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Supto on 4/4/2017.
 */

public class InsertImageResponse {
    @SerializedName("success")
    @Expose
    private Integer success;
    @SerializedName("data")
    @Expose
    private Data data;
    @SerializedName("message")
    @Expose
    private String mesaage;

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

    public String getMesaage() {
        return mesaage;
    }

    public void setMesaage(String mesaage) {
        this.mesaage = mesaage;
    }

    public class Data {

        @SerializedName("success")
        @Expose
        private Integer success;
        @SerializedName("Message")
        @Expose
        private String message;
        @SerializedName("ImageUrl")
        @Expose
        private String imageUrl;
        @SerializedName("Id")
        @Expose
        private Integer id;
        @SerializedName("CustomProperties")
        @Expose
        private CustomProperties customProperties;

        public Integer getSuccess() {
            return success;
        }

        public void setSuccess(Integer success) {
            this.success = success;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
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


    }

    public class CustomProperties {


    }
}
