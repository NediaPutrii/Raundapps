package com.example.raund.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class HistoryResponse{

    @SerializedName("success")
    private boolean success;

    @SerializedName("history")
    private List<HistoryItem> history;

    @SerializedName("message")
    private String message;

    public boolean isSuccess(){
        return success;
    }

    public List<HistoryItem> getHistory(){
        return history;
    }

    public String getMessage(){
        return message;
    }
}