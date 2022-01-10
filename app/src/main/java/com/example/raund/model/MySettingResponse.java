package com.example.raund.model;

import com.google.gson.annotations.SerializedName;

public class MySettingResponse{

    @SerializedName("nim")
    private String nim;

    @SerializedName("nama")
    private String nama;

    @SerializedName("updated_at")
    private String updatedAt;

    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("id")
    private int id;

    @SerializedName("email")
    private String email;

    @SerializedName("no_telepon")
    private String noTelepon;

    @SerializedName("token")
    private String token;

    public String getNim(){
        return nim;
    }

    public String getNama(){
        return nama;
    }

    public String getUpdatedAt(){
        return updatedAt;
    }

    public String getCreatedAt(){
        return createdAt;
    }

    public int getId(){
        return id;
    }

    public String getEmail(){
        return email;
    }

    public String getNoTelepon(){
        return noTelepon;
    }

    public String getToken(){
        return token;
    }
}