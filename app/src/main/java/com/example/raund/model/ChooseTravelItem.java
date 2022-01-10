package com.example.raund.model;

import com.google.gson.annotations.SerializedName;

public class ChooseTravelItem{

    @SerializedName("nama_mobil")
    private String namaMobil;

    @SerializedName("id_mobil")
    private int idMobil;

    @SerializedName("created_at")
    private Object createdAt;

    @SerializedName("jam_departure")
    private String jamDeparture;

    @SerializedName("travel_fee")
    private int travelFee;

    @SerializedName("gambar")
    private String gambar;

    @SerializedName("delivery_fee")
    private int deliveryFee;

    @SerializedName("updated_at")
    private Object updatedAt;

    @SerializedName("id_travel_agent")
    private String idTravelAgent;

    @SerializedName("nama_travel")
    private String namaTravel;

    @SerializedName("id")
    private int id;

    @SerializedName("kapasitas")
    private int kapasitas;

    @SerializedName("status")
    private String status;

    public String getNamaMobil(){
        return namaMobil;
    }

    public int getIdMobil(){
        return idMobil;
    }

    public Object getCreatedAt(){
        return createdAt;
    }

    public String getJamDeparture(){
        return jamDeparture;
    }

    public int getTravelFee(){
        return travelFee;
    }

    public String getGambar(){
        return gambar;
    }

    public int getDeliveryFee(){
        return deliveryFee;
    }

    public Object getUpdatedAt(){
        return updatedAt;
    }

    public String getIdTravelAgent(){
        return idTravelAgent;
    }

    public String getNamaTravel(){
        return namaTravel;
    }

    public int getId(){
        return id;
    }

    public int getKapasitas(){
        return kapasitas;
    }

    public String getStatus(){
        return status;
    }
}