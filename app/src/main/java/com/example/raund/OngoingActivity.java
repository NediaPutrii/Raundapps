package com.example.raund;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.raund.adapter.OnGoingAdapterRecyclerView;
import com.example.raund.model.OngoingItem;
import com.example.raund.model.OnGoing;
import com.example.raund.model.OngoingResponse;
import com.example.raund.retrofit.PortalClient;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OngoingActivity extends AppCompatActivity implements OnGoingAdapterRecyclerView.OnGoingClickListener {

    RecyclerView rvOnGoing ;
    Button BtnHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ongoing);

        final OnGoingAdapterRecyclerView adapter = new OnGoingAdapterRecyclerView();
//        adapter.setListdata(getListtravel());
        adapter.setListener(this);

        rvOnGoing = findViewById(R.id.rvOnGoing);
        rvOnGoing.setAdapter(adapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvOnGoing.setLayoutManager(layoutManager);

        //initialize and assign variable
        BottomNavigationView bottomNavigationView= findViewById(R.id.bottomNavigation);

        //Set Home Selected
        bottomNavigationView.setSelectedItemId(R.id.history);

        //perform ItemSelectedListener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.history:
                        startActivity(new Intent(getApplicationContext()
                                , HistoryActivity.class));

                        overridePendingTransition(0,0);
                        return true;

                    case R.id.home:
                        startActivity(new Intent(getApplicationContext()
                                ,HomeActivity.class));

                        overridePendingTransition(0,0);
                        return true;

                    case R.id.setting:
                        startActivity(new Intent(getApplicationContext()
                                ,SettingActivity.class));

                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        SharedPreferences preferences = getApplicationContext().getSharedPreferences(
                "com.example.raund.PREFS",Context.MODE_PRIVATE
        );
        String accessToken = preferences.getString("ACCESS_TOKEN","");

//        Toast.makeText(getApplicationContext(),accessToken,Toast.LENGTH_SHORT).show();

        //Minta data travel ongoing
        //Buat Object Client Retrofit
        String API_BASE_URL = "https://raund.herokuapp.com/";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PortalClient client = retrofit.create(PortalClient.class);

        Call<OngoingResponse> call = client.getMyOngoing("Bearer" + accessToken);
        call.enqueue(new Callback<OngoingResponse>() {


            @Override
            public void onResponse(Call<OngoingResponse> call, Response<OngoingResponse> response) {
                OngoingResponse ongoingResponse = response.body();
                ArrayList<OnGoing> listdata = new ArrayList<OnGoing>();

                if(ongoingResponse != null && response.isSuccessful()){
                    List<OngoingItem> ListOngoingItem = ongoingResponse.getOngoing();

                    for(OngoingItem ong : ListOngoingItem){
                        OnGoing going = new OnGoing(
                                ong.getCurrentLoc(),
                                ong.getDestination(),
                                ong.getTotal(),
                                ong.getArrive(),
                                ong.getNamaTravel(),
                                ong.getDeparture(),
                                ong.getJam_arrive(),
                                ong.getJam_departure(),
                                ong.getJenis()
                        );
                        listdata.add(going);




                    }

                } else{
                    Toast.makeText(getApplicationContext(),"GAGAL dapat data", Toast.LENGTH_SHORT).show();

                }
                adapter.setListdata(listdata);
            }

            @Override
            public void onFailure(Call<OngoingResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"GAGAL MBENG", Toast.LENGTH_SHORT).show();
            }
        });


        //Button
        BtnHistory = findViewById(R.id.BtnHistory);
        BtnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HistoryActivity();
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

    public void HistoryActivity() {
        Intent intent = new Intent(this, HistoryActivity.class);
        startActivity(intent);
    }

//    public ArrayList<OnGoing> getOnGoing(){
//        ArrayList<OnGoing> listdata = new ArrayList<OnGoing>();
//        listdata.add(new OnGoing(
//                "ERTE TRAVEL",
//                "Padang",
//                "Bukittinggi",
//                "Thu, 17 Aug 2021",
//                "Thu, 17 Aug 2021",
//                "08.00 WIB",
//                "11.00 WIB",
//                "Rp. 30.000"
//
//        ));
//
//        listdata.add(new OnGoing(
//                "AHYA TRAVEL",
//                "Padang",
//                "Pekanbaru",
//                "Thu, 17 Aug 2021",
//                "Thu, 17 Aug 2021",
//                "08.00 WIB",
//                "11.00 WIB",
//                "Rp. 50.000"
//        ));
//
//        listdata.add(new OnGoing(
//                "AHYA TRAVEL",
//                "Padang",
//                "Pekanbaru",
//                "Thu, 17 Aug 2021",
//                "Thu, 17 Aug 2021",
//                "08.00 WIB",
//                "11.00 WIB",
//                "Rp. 50.000"
//        ));
//
//        listdata.add(new OnGoing(
//                "AHYA TRAVEL",
//                "Padang",
//                "Pekanbaru",
//                "Thu, 17 Aug 2021",
//                "Thu, 17 Aug 2021",
//                "08.00 WIB",
//                "11.00 WIB",
//                "Rp. 50.000"
//        ));
//
//        listdata.add(new OnGoing(
//                "AHYA TRAVEL",
//                "Padang",
//                "Pekanbaru",
//                "Thu, 17 Aug 2021",
//                "Thu, 17 Aug 2021",
//                "08.00 WIB",
//                "11.00 WIB",
//                "Rp. 50.000"
//        ));
//
//        listdata.add(new OnGoing(
//                "AHYA TRAVEL",
//                "Padang",
//                "Pekanbaru",
//                "Thu, 17 Aug 2021",
//                "Thu, 17 Aug 2021",
//                "08.00 WIB",
//                "11.00 WIB",
//                "Rp. 50.000"
//        ));
//        listdata.add(new OnGoing(
//                "AHYA TRAVEL",
//                "Padang",
//                "Pekanbaru",
//                "Thu, 17 Aug 2021",
//                "Thu, 17 Aug 2021",
//                "08.00 WIB",
//                "11.00 WIB",
//                "Rp. 50.000"
//        ));
//
//        listdata.add(new OnGoing(
//                "AHYA TRAVEL",
//                "Padang",
//                "Pekanbaru",
//                "Thu, 17 Aug 2021",
//                "Thu, 17 Aug 2021",
//                "08.00 WIB",
//                "11.00 WIB",
//                "Rp. 50.000"
//        ));
//
//        listdata.add(new OnGoing(
//                "AHYA TRAVEL",
//                "Padang",
//                "Pekanbaru",
//                "Thu, 17 Aug 2021",
//                "Thu, 17 Aug 2021",
//                "08.00 WIB",
//                "11.00 WIB",
//                "Rp. 50.000"
//        ));
//
//        return listdata;
//
//    }
    @Override
    public void onClick(View view, OnGoing onGoing) {
//        Intent intent = new Intent(this, ResheduleJadwalActivity.class);
//        startActivity(intent);
    }
}