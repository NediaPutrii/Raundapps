package com.example.raund;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.raund.model.DeliveryResponse;
import com.example.raund.retrofit.PortalClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class KonfirmDeliveryActivity extends AppCompatActivity {
    TextView namaTravelDelivery;
    TextView namaMobil;
    TextView pukulBerangkat;
    TextView uang_price;
    TextView uang_total, tgl_Berangkat, txtDeparture, txtArrive;
    EditText edtTxtBeratpaket;
    int harga, beratpaket, total;
    String token,nim,locSender,latSender,longSender,namasender,nosender,locReceiver,latReceiver,longReceiver,namaReceiver,noReceiver,tglBerangkat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmdelivery);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);


        Intent intent = getIntent();
        //nama mobil
        String snama_mobil =  intent.getStringExtra("nama_mobil");
        namaMobil = findViewById(R.id.namaMobil);
        namaMobil.setText(snama_mobil);
        //nama travel
        String snama_travel =  intent.getStringExtra("nama_travel");
        namaTravelDelivery = findViewById(R.id.namaTravelDelivery);
        namaTravelDelivery.setText(snama_travel);
        //jam departure
        String sjam_departure =  intent.getStringExtra("jam_departure");
        pukulBerangkat = findViewById(R.id.pukulBerangkat);
        pukulBerangkat.setText(sjam_departure);
        //berat paket
        edtTxtBeratpaket = findViewById(R.id.edtTxtBeratpaket);
        //harga
        int sharga =  intent.getIntExtra("harga_delivery",-1);
        uang_price = findViewById(R.id.uang_price);
        uang_price.setText(String.valueOf(sharga));
        uang_total = findViewById(R.id.uang_total);

        //kapasitas
        int kapasitas =  intent.getIntExtra("kapasitas", 8);
        String skapasitas= String.valueOf(kapasitas);

        data();

        tgl_Berangkat = findViewById(R.id.tglBerangkat);
        tgl_Berangkat.setText(String.valueOf(tglBerangkat));

        //departure & arrive
        txtDeparture = findViewById(R.id.txtDeparture);
        txtDeparture.setText(locSender);

        txtArrive = findViewById(R.id.txtArrive);
        txtArrive.setText(locReceiver);

    }

    public  void convert(){
        harga = Integer.parseInt(uang_price.getText().toString());
        beratpaket = Integer.parseInt(edtTxtBeratpaket.getText().toString());
    }

    public void hitungtotal(View view){
        convert();
        total = beratpaket * harga;
        uang_total.setText(Integer.toString(total));
    }

    public void data(){
        SharedPreferences preferences = getSharedPreferences(
                "com.example.raund.PREFS", MODE_PRIVATE);
        token = preferences.getString( "ACCESS_TOKEN", "");
        nim = preferences.getString("NIM","");
        locSender = preferences.getString("LOC_SENDER", "Tidak diketahui");
        latSender = preferences.getString("LAT_SENDER","Tidak diketahui");
        longSender = preferences.getString("LONG_SENDER", "Tidak diketahui");
        namasender = preferences.getString("NAMA_SENDER", "Tidak diketahui");
        nosender = preferences.getString("NO_SENDER", "Tidak diketahui");
        locReceiver = preferences.getString("LOC_RECEIVER", "Tidak diketahui");
        latReceiver = preferences.getString("LAT_RECEIVER","Tidak diketahui");
        longReceiver = preferences.getString("LONG_RECEIVER", "Tidak diketahui");
        namaReceiver = preferences.getString("NAMA_RECEIVER", "Tidak diketahui");
        noReceiver = preferences.getString("NO_RECEIVER", "Tidak diketahui");
        tglBerangkat = preferences.getString("DATE_PERGI", "Tidak diketahui");
    }

    public void Delivery(View view){
        data();

        Intent intent = getIntent();
        int iid_mobil =  intent.getIntExtra("id_mobil", -1);
        String sid_mobil= String.valueOf(iid_mobil);
        String sid_travel_agent =  intent.getStringExtra("id_travel_agent");
//        int ijml_pessanger =  intent.getIntExtra("jml_pessanger", -1);
        int istatus =  intent.getIntExtra("status", 1);
        //jam departure
        String sjam_departure =  intent.getStringExtra("jam_departure");
        pukulBerangkat = findViewById(R.id.pukulBerangkat);
        pukulBerangkat.setText(sjam_departure);
        String berat_paket = edtTxtBeratpaket.getText().toString();
        String hargatotal = uang_total.getText().toString();


//        //Buat Object Client Retrofit
        String API_BASE_URL = "https://raund.herokuapp.com/";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        PortalClient client = retrofit.create(PortalClient.class);

        Call<DeliveryResponse> call = client.deliveryResponse("Bearer"+ token, nim, sid_mobil, sid_travel_agent, sjam_departure
                ,istatus, locSender,latSender,longSender, namasender, nosender, locReceiver, latReceiver, longReceiver,
                namaReceiver, noReceiver, berat_paket, tglBerangkat,hargatotal);
        call.enqueue(new Callback<DeliveryResponse>() {
            @Override
            public void onResponse(Call<DeliveryResponse> call, Response<DeliveryResponse> response) {

                try {
                    if(response.body()!=null && response.isSuccessful())
                        Toast.makeText(KonfirmDeliveryActivity.this,"Berhasil",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(KonfirmDeliveryActivity.this, OngoingActivity.class);
                    startActivity(intent);
                    finish();
                    if(response.errorBody()!=null)
                        Toast.makeText(KonfirmDeliveryActivity.this,response.errorBody().string(),Toast.LENGTH_LONG).show();
                }catch (Exception e){
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<DeliveryResponse> call, Throwable t) {

            }
        });

    }


    @Override
    public boolean onSupportNavigateUp(){
        onBackPressed();
        return true;
    }
    @Override
    public void onBackPressed(){
        super.onBackPressed();
    }
}