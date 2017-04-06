package com.pbazaar.pbazaarforagent.model;

/**
 * Created by Supto on 4/5/2017.
 */

public class SubCategoryModel {
    private String name;
    private int id;

    public SubCategoryModel() {
    }

    public SubCategoryModel(String name, int id) {
        this.name = name;
        this.id = id;
    }

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
}
