package com.example.raund;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.navigation.NavController;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.Random;

public class HomeActivity extends AppCompatActivity {
    private ImageButton BtnTraveling;
    private ImageButton BtnDelivery;
    private static  final String CHANNEL_ID = "com.example.raund.CH01";
    BottomNavigationView bottomNavigationView;
    NavController navController ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //initialize and assign variable
        BottomNavigationView bottomNavigationView= findViewById(R.id.bottomNavigation);

        //Set Home Selected
        bottomNavigationView.setSelectedItemId(R.id.home);

        //perform ItemSelectedListener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){

//                    case R.id.notifikasi:
//                            showNotification();
//                    return true;


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


//
//        bottomNavigationView = findViewById(R.id.bottomNavigationView);
//        navController = Navigation.findNavController(this, R.id.fragmentContainerView);
//        NavigationUI.setupWithNavController(bottomNavigationView,navController);

        //Button
        BtnTraveling = findViewById(R.id.BtnTraveling);
        BtnTraveling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataTravelActivity();
            }
        });

        //Button
        BtnDelivery = findViewById(R.id.BtnDelivery);
        BtnDelivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DetailSenderActivity();
            }
        });


        //Firebase subscription
        FirebaseMessaging.getInstance().subscribeToTopic("pengumuman");

        //firebase registration token
        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(
                new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if(task.isSuccessful()){
                            String token = task.getResult();
                            Log.d("fcm-token", token);
                        }
                    }
                }
        );



    }

    private void showNotification() {
        //ambil objek notification magaer
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);


        //BuatChannel
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel notificationChannel =
                    new NotificationChannel(
                            CHANNEL_ID,
                            "Channel Raund",
                            NotificationManager.IMPORTANCE_DEFAULT
                    );
            notificationManager.createNotificationChannel(notificationChannel);
        }

        //Buat Pending Intent
        Intent perjalananIntent = new Intent(this, HistoryActivity.class);

        PendingIntent pendingPerjalananIntenet = PendingIntent.getActivity(
                this,
                12345,
                perjalananIntent,
                PendingIntent.FLAG_UPDATE_CURRENT
        );

        //Buat Notifikasi
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_baseline_notifications_active_24_white)
                .setContentTitle("Jeng Jeng Maniez")
                .setContentText("Perjalanan Anda Berakhir")
                .setContentIntent(pendingPerjalananIntenet)
                .addAction(R.drawable.ic_baseline_near_me_24, "Lihat", pendingPerjalananIntenet);
                ;

        Notification notification = builder.build();

        //Tamoilkan notifikasi
        Random random = new Random(1000);

        notificationManager.notify(random.nextInt(), notification);


    }

    public void DataTravelActivity() {
        Intent intent = new Intent(this, DataTravelActivity.class);
        startActivity(intent);
    }
    public void DetailSenderActivity() {
        Intent intent = new Intent(this, DetailSenderActivity.class);
        startActivity(intent);
    }





}
