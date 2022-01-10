package com.example.raund.model;

public class TravelList {
    public  int idMobil;
    public String idTravelAgent;
    public String nama_travel;
    public String nama_mobil;
    public String jam_departure;
    public int available;
    public int harga_travel;
    public String gambar;
    public String status;
    public int harga_delivery;


    public TravelList(int idMobil, String idTravelAgent, String namaTravel, String namaMobil, String jamDeparture, int kapasitas, int travelFee, String gambar, String status, int deliveryFee)
    {
        this.idMobil = idMobil;
        this.idTravelAgent = idTravelAgent;
        this.nama_travel = namaTravel;
        this.nama_mobil = namaMobil;
        this.jam_departure = jamDeparture;
        this.available = kapasitas;
        this.harga_travel = travelFee;
        this.gambar=gambar;
        this.status=status;
        this.harga_delivery = deliveryFee;

    }

//    public TravelList(String nama_travel, String nama_mobil, String tanggal_travel, int available, int harga_travel) {
//        this.nama_travel = nama_travel;
//        this.nama_mobil = nama_mobil;
//        this.tanggal_travel = tanggal_travel;
//        this.available = available;
//        this.harga_travel = harga_travel;
//    }
}

