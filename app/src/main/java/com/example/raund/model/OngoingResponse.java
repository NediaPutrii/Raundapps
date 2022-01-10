package com.example.raund.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class OngoingResponse{

    @SerializedName("data")
    private List<OngoingItem> data;

    @SerializedName("success")
    private boolean success;

    @SerializedName("message")
    private String message;

    public List<OngoingItem> getOngoing(){
        return data;
    }

    public boolean isSuccess(){
        return success;
    }

    public String getMessage(){
        return message;
    }
}