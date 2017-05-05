package com.pbazaar.pbazaarforagent.remote.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by supto on 5/5/17.
 */

public class PaymentHistoryRequest extends BaseRequest {

    @SerializedName("customerId")
    @Expose
    private int customerId;

    @SerializedName("recordsToReturn")
    @Expose
    private int recordsToReturn;


    public PaymentHistoryRequest(String apiToken, int customerId, int recordsToReturn) {
        super(apiToken);
        this.customerId = customerId;
        this.recordsToReturn = recordsToReturn;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getRecordsToReturn() {
        return recordsToReturn;
    }

    public void setRecordsToReturn(int recordsToReturn) {
        this.recordsToReturn = recordsToReturn;
    }
}
