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

import com.example.raund.adapter.AdapterRecylerView;
import com.example.raund.model.History;
import com.example.raund.model.HistoryItem;
import com.example.raund.model.HistoryResponse;
import com.example.raund.retrofit.PortalClient;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

    public class HistoryActivity extends AppCompatActivity implements AdapterRecylerView.HistoryClickListener {

    RecyclerView rvHistory;
    Button BtnOnGoing;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        final AdapterRecylerView adapter = new AdapterRecylerView();
//        adapter.setListdata(getHistory());
        adapter.setListener(this);

        rvHistory = findViewById(R.id.rvHistory);
        rvHistory.setAdapter(adapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvHistory.setLayoutManager(layoutManager);


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

        //Minta data travel history
        //Buat Object Client Retrofit
        String API_BASE_URL = "https://raund.herokuapp.com/";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PortalClient client = retrofit.create(PortalClient.class);

        Call<HistoryResponse> call = client.getMyHistory("Bearer" + accessToken);
        call.enqueue(new Callback<HistoryResponse>() {


                         @Override
                         public void onResponse(Call<HistoryResponse> call, Response<HistoryResponse> response) {
                             HistoryResponse historyResponse = response.body();
                             ArrayList<History> listdata = new ArrayList<History>();

                             if(historyResponse != null && response.isSuccessful()) {
                                 List<HistoryItem> ListHistoryItem = historyResponse.getHistory();

                                 for (HistoryItem his : ListHistoryItem) {
                                     History tory = new History(
                                             his.getNamaTravel(),
                                             his.getCurrentLoc(),
                                             his.getDestination(),
                                             his.getDeparture(),
                                             his.getArrive(),
                                             his.getJamDeparture(),
                                             his.getJamArrive(),
                                             his.getTotal(),
                                             his.getJenis()

                                     );
                                     listdata.add(tory);
                                 }
                             }
                                 else{
                                     Toast.makeText(getApplicationContext(), "GAGAL dapat data", Toast.LENGTH_SHORT).show();
                                 }
                                 adapter.setListdata(listdata);
                             }


                         @Override
                         public void onFailure(Call<HistoryResponse> call, Throwable t) {
                             Toast.makeText(getApplicationContext(),"GAGAL MBENG", Toast.LENGTH_SHORT).show();

                         }
                     });


            //Button
        BtnOnGoing = findViewById(R.id.BtnOnGoing);
        BtnOnGoing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OngoingActivity();
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

    public void OngoingActivity() {
        Intent intent = new Intent(this, OngoingActivity.class);
        startActivity(intent);
    }

        @Override
        public void onClick(View view, History history) {

        }

//    public ArrayList<History> getHistory() {
//        ArrayList<History> listdata = new ArrayList<History>();
//        listdata.add(new History(
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
//        listdata.add(new History(
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
//        listdata.add(new History(
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
//        listdata.add(new History(
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
//        listdata.add(new History(
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
//        listdata.add(new History(
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
//        listdata.add(new History(
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
//        listdata.add(new History(
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
//        listdata.add(new History(
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
//        listdata.add(new History(
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
//        listdata.add(new History(
//                "AHYA TRAVEL",
//                "Padang",
//                "Pekanbaru",
//                "Thu, 17 Aug 2021",
//                "Thu, 17 Aug 2021",
//                "08.00 WIB",
//                "11.00 WIB",
//                "Rp. 50.000"
//        ));
//        listdata.add(new History(
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
//
//        return listdata;
//    }

    }
