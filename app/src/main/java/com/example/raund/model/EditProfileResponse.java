package com.example.raund.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EditProfileResponse{

    @SerializedName("success")
    private boolean success;

    @SerializedName("message")
    private String message;

    @SerializedName("editprofile")
    private Editprofile editprofile;

    public boolean isSuccess(){
        return success;
    }

    public String getMessage(){
        return message;
    }


    public List<Editprofile> getEditprofile(){
        return (List<Editprofile>) editprofile;
    }
}