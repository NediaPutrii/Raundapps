package com.example.raund.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class SettingResponse{

    @SerializedName("success")
    private boolean success;

    @SerializedName("message")
    private String message;

    @SerializedName("setting")
    private List<SettingItem> setting;

    public boolean isSuccess(){
        return success;
    }

    public String getMessage(){
        return message;
    }

    public List<SettingItem> getSetting(){
        return setting;
    }
}