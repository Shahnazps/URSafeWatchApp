package com.example.shalinimenon.mywatchapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.Drawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.wearable.activity.WearableActivity;
import android.support.wearable.view.WatchViewStub;
import android.support.wearable.view.WearableDialogHelper;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.ThreadLocalRandom;

public class MainActivity extends WearableActivity {

    private TextView mTextView;
    private HeartRateBroadcastReceiver heartRateBroadcastReceiver;
    private AlertDialog mDialog;

    // private static final String TAG = "MainActivity";
    private class HeartRateBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context arg0, Intent arg1) {
            // ahr
            Log.v(this.getClass().getName(), "Value Recieved");
            if (arg1.getAction().equals("heartRateAction")) {
                float hr = arg1.getFloatExtra("HeartRate", 0);
                if (MainActivity.this.mTextView != null) {
                    MainActivity.this.mTextView.setText("Heart Rate : " + hr);
                }
            }
        }
    }

    @Override
    protected void onStart() {
        // initialize the broadcast recieiver
        heartRateBroadcastReceiver = new HeartRateBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("heartRateAction");
        registerReceiver(heartRateBroadcastReceiver, intentFilter);

        // start HeartRateWearService
        startService(new Intent(this, HeartRateWearService.class));
        super.onStart();
    }

    @Override
    protected void onStop() {
        unregisterReceiver(heartRateBroadcastReceiver);
        stopService(new Intent(this, HeartRateWearService.class));
        super.onStop();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wear);
        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {
                mTextView = (TextView) stub.findViewById(R.id.heartrate);

                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MainActivity.this);
                dialogBuilder.setTitle("Please wait...")
                        .setMessage("Fetching the Heart Rate");

                mDialog = dialogBuilder.show();

                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mTextView.setText("Current Heart Rate : " + ThreadLocalRandom.current().nextInt(60, 100 + 1));
                        mDialog.dismiss();
                    }
                }, 3000);
            }
        });

    }

}


