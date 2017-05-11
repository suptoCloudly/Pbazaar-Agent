package com.pbazaar.pbazaarforagent.remote.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Supto on 5/11/2017.
 */

public class PostHistoryResponse {
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

        @SerializedName("ProductInfoStatus")
        @Expose
        private Integer productInfoStatus;
        @SerializedName("CountThisWeekPosts")
        @Expose
        private Integer countThisWeekPosts;
        @SerializedName("CountAllTimePosts")
        @Expose
        private Integer countAllTimePosts;

        public Integer getProductInfoStatus() {
            return productInfoStatus;
        }

        public void setProductInfoStatus(Integer productInfoStatus) {
            this.productInfoStatus = productInfoStatus;
        }

        public Integer getCountThisWeekPosts() {
            return countThisWeekPosts;
        }

        public void setCountThisWeekPosts(Integer countThisWeekPosts) {
            this.countThisWeekPosts = countThisWeekPosts;
        }

        public Integer getCountAllTimePosts() {
            return countAllTimePosts;
        }

        public void setCountAllTimePosts(Integer countAllTimePosts) {
            this.countAllTimePosts = countAllTimePosts;
        }

    }
}
