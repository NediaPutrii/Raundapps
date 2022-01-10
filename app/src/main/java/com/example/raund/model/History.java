package com.example.raund.model;

public class History {
    public String nama_travelh;
    public String fromDelivery;
    public String destinationDelivery;
    public String tgl_departure2;
    public String tgl_arrival2;
    public String jam_pergi2;
    public String jam_datang2;
    public int harga2;
    public int jenis;


public History(String namaTravel, String fromDelivery, String destinationDelivery, String tgl_departure2, String tgl_arrival2, String jam_pergi2, String jam_datang2, int harga2, int jenis){
    this.nama_travelh = namaTravel;
    this.fromDelivery= fromDelivery;
    this.destinationDelivery= destinationDelivery;
    this.tgl_departure2= tgl_departure2;
    this.tgl_arrival2= tgl_arrival2;
    this.jam_pergi2= jam_pergi2;
    this.jam_datang2= jam_datang2;
    this.harga2=harga2;
    this.jenis = jenis;
}


}
