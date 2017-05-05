package com.pbazaar.pbazaarforagent.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Supto on 4/5/2017.
 */

public class SubCategoryModel implements Parcelable{
    private String name;
    private int id;

    public SubCategoryModel() {
    }

    public SubCategoryModel(String name, int id) {
        this.name = name;
        this.id = id;
    }

    protected SubCategoryModel(Parcel in) {
        name = in.readString();
        id = in.readInt();
    }

    public static final Creator<SubCategoryModel> CREATOR = new Creator<SubCategoryModel>() {
        @Override
        public SubCategoryModel createFromParcel(Parcel in) {
            return new SubCategoryModel(in);
        }

        @Override
        public SubCategoryModel[] newArray(int size) {
            return new SubCategoryModel[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(id);
    }
}
