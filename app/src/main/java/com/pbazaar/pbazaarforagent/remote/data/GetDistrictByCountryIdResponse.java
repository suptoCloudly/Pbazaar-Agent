package com.pbazaar.pbazaarforagent.remote.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by supto on 4/2/17.
 */

public class GetDistrictByCountryIdResponse {

    @SerializedName("success")
    @Expose
    private Integer success;
    @SerializedName("data")
    @Expose
    private List<Data> data = null;

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


    public class Data {

        @SerializedName("Name")
        @Expose
        private String name;
        @SerializedName("Id")
        @Expose
        private Integer id;
        @SerializedName("CustomProperties")
        @Expose
        private CustomProperties customProperties;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
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
