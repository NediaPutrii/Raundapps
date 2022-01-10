package com.example.raund.model;

import com.google.gson.annotations.SerializedName;

import java.sql.Timestamp;

public class OngoingItem {

    @SerializedName("no_receiver")
    private Object noReceiver;

    @SerializedName("current_loc")
    private String currentLoc;

    @SerializedName("travel_id")
    private int travelId;

    @SerializedName("id_mobil")
    private String idMobil;

    @SerializedName("nama_receiver")
    private Object namaReceiver;

    @SerializedName("destination")
    private String destination;

    @SerializedName("created_at")
    private Object createdAt;

    @SerializedName("no_sender")
    private Object noSender;

    @SerializedName("gambar")
    private String gambar;

    @SerializedName("total")
    private int total;

    @SerializedName("nim")
    private String nim;

    @SerializedName("nama_sender")
    private Object namaSender;

    @SerializedName("updated_at")
    private Object updatedAt;

    @SerializedName("berat_paket")
    private Object beratPaket;

    @SerializedName("id_travel_agent")
    private String idTravelAgent;

    @SerializedName("arrive")
    private String arrive;

    @SerializedName("jam_arrive")
    private String jam_arrive;

    @SerializedName("nama_travel")
    private String namaTravel;

    @SerializedName("id")
    private int id;

    @SerializedName("jenis")
    private int jenis;

    @SerializedName("departure")
    private String departure;

    @SerializedName("jam_departure")
    private String jam_departure;

    @SerializedName("jumlah_passanger")
    private int jumlahPassanger;

    @SerializedName("status")
    private int status;

    public Object getNoReceiver(){
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

    public Object getNamaReceiver(){
        return namaReceiver;
    }

    public String getDestination(){
        return destination;
    }

    public Object getCreatedAt(){
        return createdAt;
    }

    public Object getNoSender(){
        return noSender;
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

    public Object getNamaSender(){
        return namaSender;
    }

    public Object getUpdatedAt(){
        return updatedAt;
    }

    public Object getBeratPaket(){
        return beratPaket;
    }

    public String getIdTravelAgent(){
        return idTravelAgent;
    }

    public String getArrive(){
        return arrive;
    }

    public String getJam_arrive(){
        return jam_arrive;
    }

    public String getNamaTravel(){
        return namaTravel;
    }

    public int getId(){
        return id;
    }

    public String getDeparture(){
        return departure;
    }

    public String getJam_departure(){
        return jam_departure;
    }

    public int getJumlahPassanger(){
        return jumlahPassanger;
    }

    public int getStatus(){
        return status;
    }
}