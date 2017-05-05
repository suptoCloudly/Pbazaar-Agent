package com.pbazaar.pbazaarforagent.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by supto on 4/2/17.
 */

public class LocationSpinnerDataModel implements Parcelable{

    private String locationName;
    private int locationId;


    public LocationSpinnerDataModel() {
    }

    public LocationSpinnerDataModel(String locationName, int locationId) {
        this.locationName = locationName;
        this.locationId = locationId;
    }

    protected LocationSpinnerDataModel(Parcel in) {
        locationName = in.readString();
        locationId = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(locationName);
        dest.writeInt(locationId);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<LocationSpinnerDataModel> CREATOR = new Creator<LocationSpinnerDataModel>() {
        @Override
        public LocationSpinnerDataModel createFromParcel(Parcel in) {
            return new LocationSpinnerDataModel(in);
        }

        @Override
        public LocationSpinnerDataModel[] newArray(int size) {
            return new LocationSpinnerDataModel[size];
        }
    };

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }
}
