package com.example.raund.model;

import com.google.gson.annotations.SerializedName;

public class HistoryItem{

    @SerializedName("no_receiver")
    private String noReceiver;

    @SerializedName("current_loc")
    private String currentLoc;

    @SerializedName("travel_id")
    private int travelId;

    @SerializedName("id_mobil")
    private String idMobil;

    @SerializedName("nama_receiver")
    private String namaReceiver;

    @SerializedName("destination")
    private String destination;

    @SerializedName("jam_arrive")
    private String jamArrive;

    @SerializedName("jenis")
    private int jenis;

    @SerializedName("created_at")
    private Object createdAt;

    @SerializedName("no_sender")
    private String noSender;

    @SerializedName("jam_departure")
    private String jamDeparture;

    @SerializedName("gambar")
    private String gambar;

    @SerializedName("total")
    private int total;

    @SerializedName("nim")
    private String nim;

    @SerializedName("nama_sender")
    private String namaSender;

    @SerializedName("updated_at")
    private Object updatedAt;

    @SerializedName("berat_paket")
    private String beratPaket;

    @SerializedName("id_travel_agent")
    private String idTravelAgent;

    @SerializedName("arrive")
    private String arrive;

    @SerializedName("nama_travel")
    private String namaTravel;

    @SerializedName("id")
    private int id;

    @SerializedName("jumlah_passanger")
    private Object jumlahPassanger;

    @SerializedName("departure")
    private String departure;

    @SerializedName("status")
    private int status;

    public String getNoReceiver(){
        return noReceiver;
    }

    public String getCurrentLoc(){
        return currentLoc;
    }

    public int getTravelId(){
        return travelId;
    }

    public int getJenis(){
        return jenis;
    }

    public String getIdMobil(){
        return idMobil;
    }

    public String getNamaReceiver(){
        return namaReceiver;
    }

    public String getDestination(){
        return destination;
    }

    public String getJamArrive(){
        return jamArrive;
    }

    public Object getCreatedAt(){
        return createdAt;
    }

    public String getNoSender(){
        return noSender;
    }

    public String getJamDeparture(){
        return jamDeparture;
    }

    public String getGambar(){
        return gambar;
    }

    public int getTotal(){
        return total;
    }

    public String getNim(){
        return nim;
    }

    public String getNamaSender(){
        return namaSender;
    }

    public Object getUpdatedAt(){
        return updatedAt;
    }

    public String getBeratPaket(){
        return beratPaket;
    }

    public String getIdTravelAgent(){
        return idTravelAgent;
    }

    public String getArrive(){
        return arrive;
    }

    public String getNamaTravel(){
        return namaTravel;
    }

    public int getId(){
        return id;
    }

    public Object getJumlahPassanger(){
        return jumlahPassanger;
    }

    public String getDeparture(){
        return departure;
    }

    public int getStatus(){
        return status;
    }
}