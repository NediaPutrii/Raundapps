package com.example.raund;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.raund.model.TravelResponse;
import com.example.raund.retrofit.PortalClient;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class confirmTravel extends AppCompatActivity {

    TextView namaTravel;
    TextView namaMobil;
    TextView pukulBerangkat;
    TextView uang_price;
    TextView uang_total;
    TextView tgl_Berangkat;
    EditText editTextPessanger;
    int harga, pessanger, total;
    String token,nim, currentLoc, currentLat,currentLong,destLoc,destLat,destLong,tglGo;
    TextView txtDeparture, txtArrive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_travel);
        //Button
//        BtnOrder = findViewById(R.id.BtnOrder);
//        BtnOrder.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                OnGoingActivity();
//            }
//        });


        Intent intent = getIntent();
        //nama mobil
        String snama_mobil =  intent.getStringExtra("nama_mobil");
        namaMobil = findViewById(R.id.namaMobil);
        namaMobil.setText(snama_mobil);

        //nama travel
        String snama_travel =  intent.getStringExtra("nama_travel");
        namaTravel = findViewById(R.id.namaTravel);
        namaTravel.setText(snama_travel);

        //jam departure
        String sjam_departure =  intent.getStringExtra("jam_departure");
        pukulBerangkat = findViewById(R.id.pukulBerangkat);
        pukulBerangkat.setText(sjam_departure);

        //pessanger
        editTextPessanger = findViewById(R.id.editTextPessanger);

        //harga
        int sharga =  intent.getIntExtra("harga_travel",-1);
        uang_price = findViewById(R.id.uang_price);
        uang_price.setText(String.valueOf(sharga));

        //total
        uang_total = findViewById(R.id.uang_total);

        data();


//        DateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");
//        DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
//        String tglBrngkt = dateFormat.format(df.parse(tgl_go));

//        Date date = new SimpleDateFormat("yyyy-MM-dd'").parse("tgl");
//        String formattedDate = new SimpleDateFormat("dd/MM/yyyy, Ka").format(date);

//        String pattern = "yyyy MMMMM dd";
//        SimpleDateFormat simpleDateFormat =
//                new SimpleDateFormat(pattern, new Locale("da", "DK"));
//
//        String date = simpleDateFormat.format(tglGo);
        tgl_Berangkat = findViewById(R.id.tglBerangkat);
        tgl_Berangkat.setText(String.valueOf(tglGo));

        //departure & arrive
        txtDeparture = findViewById(R.id.txtDeparture);
        txtDeparture.setText(currentLoc);

        txtArrive = findViewById(R.id.txtArrive);
        txtArrive.setText(destLoc);


    }

    public  void convert(){
        harga = Integer.parseInt(uang_price.getText().toString());
        pessanger = Integer.parseInt(editTextPessanger.getText().toString());
    }

    public void hitungtotalTravel(View view){
        convert();
        total = pessanger * harga;
        uang_total.setText(Integer.toString(total));
    }

    public void data(){
        SharedPreferences preferences = getSharedPreferences(
                "com.example.raund.PREFS", MODE_PRIVATE);
        token = preferences.getString( "ACCESS_TOKEN", "");
        nim = preferences.getString("NIM","");
        currentLoc = preferences.getString("CURRENT_LOC", "Tidak diketahui");
        currentLat = preferences.getString("CURRENT_LAT","Tidak diketahui");
        currentLong = preferences.getString("CURRENT_LONG", "Tidak diketahui");
        destLoc = preferences.getString("DEST_LOC", "Tidak diketahui");
        destLat = preferences.getString("DEST_LAT", "Tidak diketahui");
        destLong = preferences.getString("DEST_LONG", "Tidak diketahui");
        tglGo = preferences.getString("TGL_BRNGKT","Tidak diketahui");
    }


    public void Travel(View view){


        data();

        Intent intent = getIntent();

        int iid_mobil =  intent.getIntExtra("id_mobil", -1);
        String sid_mobil= String.valueOf(iid_mobil);

        String sid_travel_agent =  intent.getStringExtra("id_travel_agent");

        int istatus =  intent.getIntExtra("status", 1);

        //jam departure
        String sjam_departure =  intent.getStringExtra("jam_departure");
        pukulBerangkat = findViewById(R.id.pukulBerangkat);
        pukulBerangkat.setText(sjam_departure);

        String spassanger = Integer.toString(pessanger);

        String hargatotal = uang_total.getText().toString();


        //Buat Object Client Retrofit
        String API_BASE_URL = "https://raund.herokuapp.com/";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        PortalClient client = retrofit.create(PortalClient.class);

        Call<TravelResponse> call = client.travelResponse("Bearer"+ token, nim, sid_mobil, sid_travel_agent, sjam_departure
                ,istatus, currentLoc,currentLat,currentLong,destLoc,destLat,destLong,spassanger,tglGo, hargatotal);
        call.enqueue(new Callback<TravelResponse>() {
            @Override
            public void onResponse(Call<TravelResponse> call, Response<TravelResponse> response) {
                Toast.makeText(confirmTravel.this,"Berhasil",Toast.LENGTH_LONG).show();
                try {
                    if(response.body()!=null && response.isSuccessful())
                        Toast.makeText(confirmTravel.this,"Berhasil",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(confirmTravel.this, OngoingActivity.class);
                    startActivity(intent);
                    finish();
                    if(response.errorBody()!=null)
                        Toast.makeText(confirmTravel.this,"Gagal",Toast.LENGTH_LONG).show();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<TravelResponse> call, Throwable t) {

            }
        });

    }


    public void OnGoingActivity(){
        Intent intent = new Intent(this, OngoingActivity.class);
        startActivity(intent);
    }
}