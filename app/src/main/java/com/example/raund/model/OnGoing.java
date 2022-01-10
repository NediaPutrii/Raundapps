package com.example.raund.model;

import java.sql.Timestamp;

public class OnGoing {
    public String nama_travel_og;
    public String fromDelivery_og;
    public String destinationDelivery_og;
    public String tgl_departure_og;
    public String tgl_arrival_og;
    public String jam_pergi_og;
    public String jam_datang_og;
    public int harga_og;
    public int jenis;


    public OnGoing(String currentLoc, String destination, int total, String arrive, String namaTravel, String departure, String jam_arrive, String jam_departure, int jenis) {
            this.nama_travel_og = namaTravel;
            this.fromDelivery_og = currentLoc;
            this.destinationDelivery_og = destination;
            this.tgl_departure_og= departure;
            this.tgl_arrival_og = arrive;
            this.jam_pergi_og = jam_departure;
            this.jam_datang_og = jam_arrive;
            this.harga_og = total;
            this.jenis = jenis;
    }
}