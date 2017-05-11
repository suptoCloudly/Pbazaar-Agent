package com.pbazaar.pbazaarforagent.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Supto on 5/11/2017.
 */

public class PostHistoryModel implements Parcelable{

    private int productInfoStatus;
    private int countThisWeek;
    private int countAllTime;

    public PostHistoryModel() {
    }

    public PostHistoryModel(int productInfoStatus, int countThisWeek, int countAllTime) {
        this.productInfoStatus = productInfoStatus;
        this.countThisWeek = countThisWeek;
        this.countAllTime = countAllTime;
    }


    protected PostHistoryModel(Parcel in) {
        productInfoStatus = in.readInt();
        countThisWeek = in.readInt();
        countAllTime = in.readInt();
    }

    public static final Creator<PostHistoryModel> CREATOR = new Creator<PostHistoryModel>() {
        @Override
        public PostHistoryModel createFromParcel(Parcel in) {
            return new PostHistoryModel(in);
        }

        @Override
        public PostHistoryModel[] newArray(int size) {
            return new PostHistoryModel[size];
        }
    };

    public int getProductInfoStatus() {
        return productInfoStatus;
    }

    public void setProductInfoStatus(int productInfoStatus) {
        this.productInfoStatus = productInfoStatus;
    }

    public int getCountThisWeek() {
        return countThisWeek;
    }

    public void setCountThisWeek(int countThisWeek) {
        this.countThisWeek = countThisWeek;
    }

    public int getCountAllTime() {
        return countAllTime;
    }

    public void setCountAllTime(int countAllTime) {
        this.countAllTime = countAllTime;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(productInfoStatus);
        parcel.writeInt(countThisWeek);
        parcel.writeInt(countAllTime);
    }
}
