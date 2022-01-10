package com.example.raund.model;

public class ChooseTravel {
    public String id_mobil;
    public String id_travel_agent;
    public String nama_travel;
    public String nama_mobil;
    public String jenisMobil;
    public String jam_departure;
    public int kapasitas;
    public int harga;
    public String gambar;

    public ChooseTravel(String id_mobil, String id_travel_agent,String nama_travel, String nama_mobil,String jenisMobil, String jam_departure, int kapasitas ,int harga, String gambar) {
        this.id_mobil = id_mobil;
        this.id_travel_agent = id_travel_agent;
        this.nama_travel = nama_travel;
        this.nama_mobil = nama_mobil;
        this.jenisMobil = jenisMobil;
        this.jam_departure = jam_departure;
        this.kapasitas = kapasitas;
        this.harga = harga;
        this.gambar = gambar;
    }
}