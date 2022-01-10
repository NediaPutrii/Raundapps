package com.example.raund.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ChooseTravelResponse{

    @SerializedName("travellist")
    private List<ChooseTravelItem> chooselist;

    @SerializedName("tanggal")
    private String tanggal;

    @SerializedName("jumlah_data")
    private int jumlahData;


    public String getTanggal(){
        return tanggal;
    }

    public int getJumlahData(){
        return jumlahData;
    }

    public List<ChooseTravelItem> getChooselist() {
        return  chooselist;
    }

}