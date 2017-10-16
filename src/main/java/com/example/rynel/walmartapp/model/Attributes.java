package com.example.rynel.walmartapp.model;

/**
 * Created by rynel on 10/16/2017.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Attributes {

    @SerializedName("actualColor")
    @Expose
    private String actualColor;

    public String getActualColor() {
        return actualColor;
    }

    public void setActualColor(String actualColor) {
        this.actualColor = actualColor;
    }

}