package com.example.raund;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class ServicePerjalanan extends Service {
    public ServicePerjalanan() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("Pinokio","Service sedang dijalankan");
        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public void onDestroy() {
        Log.d("Pinokio","Service sedang dihentikan");
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
    return null;
    }

}