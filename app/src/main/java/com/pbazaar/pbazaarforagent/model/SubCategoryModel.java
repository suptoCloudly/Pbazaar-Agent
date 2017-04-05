package com.pbazaar.pbazaarforagent.model;

/**
 * Created by Supto on 4/5/2017.
 */

public class SubCategoryModel {
    private String name;
    private String seName;
    private int id;

    public SubCategoryModel() {
    }

    public SubCategoryModel(String name, String seName, int id) {
        this.name = name;
        this.seName = seName;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSeName() {
        return seName;
    }

    public void setSeName(String seName) {
        this.seName = seName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
