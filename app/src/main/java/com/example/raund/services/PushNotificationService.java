package com.example.raund.services;


import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.example.raund.HistoryActivity;
import com.example.raund.LoginActivity;
import com.example.raund.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Random;

public class PushNotificationService extends FirebaseMessagingService{
    private static  final String CHANNEL_ID = "com.example.raund.CH01";

    @Override
    public void onNewToken(@NonNull String s) {
        super.onNewToken(s);
        Log.d("fcm-token", s);
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        String title = remoteMessage.getNotification().getTitle();
        String message = remoteMessage.getNotification().getBody();
        showNotification(title,message);
    }

    private void showNotification(String title, String message) {
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
        Intent perjalananIntent = new Intent(getApplicationContext(), LoginActivity.class);

        PendingIntent pendingPerjalananIntenet = PendingIntent.getActivity(
                getApplicationContext(),
                12345,
                perjalananIntent,
                PendingIntent.FLAG_UPDATE_CURRENT
        );

        //Buat Notifikasi
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
                        .setSmallIcon(R.drawable.go)
                        .setContentTitle(title)
                        .setContentText(message)
                        .setContentIntent(pendingPerjalananIntenet)
                        .addAction(R.drawable.ic_baseline_near_me_24, "Lihat", pendingPerjalananIntenet);
        ;

        Notification notification = builder.build();

        //Tamoilkan notifikasi
        Random random = new Random(1000);

        notificationManager.notify(random.nextInt(), notification);


    }
}