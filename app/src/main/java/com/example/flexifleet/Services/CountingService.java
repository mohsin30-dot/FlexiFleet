package com.example.flexifleet.Services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class CountingService extends Service {
    public static final String TAG = " CountingService";



    // First this onCreate method will call
    @Override
    public void onCreate() {
        super.onCreate();
    }

    // when binding, this method will call
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    // when service will start, OnStartCommand method will call

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Thread countThread = new Thread(new Runnable() {
            @Override
            public void run() {
            }
        });
        countThread.start();

        startLongRunningOperation();
        return super.onStartCommand(intent, flags, startId);
    }

    private void startLongRunningOperation() {
        int i = 1;
        while(true) {
            try{
            Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Log.d(TAG, "ticking: " + i);
            i++;
        }
    }

    // when service will stop or destroy, then onDestroy will call
    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
