package com.example.raund.model;

import com.google.gson.annotations.SerializedName;

public class DeliveryResponse{

    @SerializedName("no_receiver")
    private String noReceiver;

    @SerializedName("current_loc")
    private String currentLoc;

    @SerializedName("current_lat")
    private String currentLat;

    @SerializedName("id_mobil")
    private String idMobil;

    @SerializedName("nama_receiver")
    private String namaReceiver;

    @SerializedName("destination")
    private String destination;

    @SerializedName("jam_arrive")
    private String jamArrive;

    @SerializedName("current_long")
    private String currentLong;

    @SerializedName("no_sender")
    private String noSender;

    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("jam_departure")
    private String jamDeparture;

    @SerializedName("total")
    private String total;

    @SerializedName("nim")
    private String nim;

    @SerializedName("nama_sender")
    private String namaSender;

    @SerializedName("berat_paket")
    private String beratPaket;

    @SerializedName("updated_at")
    private String updatedAt;

    @SerializedName("id_travel_agent")
    private String idTravelAgent;

    @SerializedName("arrive")
    private String arrive;

    @SerializedName("jenis")
    private int jenis;

    @SerializedName("destination_lat")
    private String destinationLat;

    @SerializedName("destination_long")
    private String destinationLong;

    @SerializedName("id")
    private int id;

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

    public String getCurrentLat(){
        return currentLat;
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

    public String getCurrentLong(){
        return currentLong;
    }

    public String getNoSender(){
        return noSender;
    }

    public String getCreatedAt(){
        return createdAt;
    }

    public String getJamDeparture(){
        return jamDeparture;
    }

    public String getTotal(){
        return total;
    }

    public String getNim(){
        return nim;
    }

    public String getNamaSender(){
        return namaSender;
    }

    public String getBeratPaket(){
        return beratPaket;
    }

    public String getUpdatedAt(){
        return updatedAt;
    }

    public String getIdTravelAgent(){
        return idTravelAgent;
    }

    public String getArrive(){
        return arrive;
    }

    public int getJenis(){
        return jenis;
    }

    public String getDestinationLat(){
        return destinationLat;
    }

    public String getDestinationLong(){
        return destinationLong;
    }

    public int getId(){
        return id;
    }

    public String getDeparture(){
        return departure;
    }

    public int getStatus(){
        return status;
    }
}