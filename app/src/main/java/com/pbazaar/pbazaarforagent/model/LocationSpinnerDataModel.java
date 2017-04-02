package com.pbazaar.pbazaarforagent.model;

/**
 * Created by supto on 4/2/17.
 */

public class LocationSpinnerDataModel {

    private String locationName;
    private int locationId;


    public LocationSpinnerDataModel() {
    }

    public LocationSpinnerDataModel(String locationName, int locationId) {
        this.locationName = locationName;
        this.locationId = locationId;
    }

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
