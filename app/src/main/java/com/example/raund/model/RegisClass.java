package com.example.raund.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegisClass {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("regisdata")
    @Expose
    private RegisData regisdata;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public RegisData getData() {
        return regisdata;
    }

    public void setData(RegisData regisdata) {
        this.regisdata = regisdata;
    }

}