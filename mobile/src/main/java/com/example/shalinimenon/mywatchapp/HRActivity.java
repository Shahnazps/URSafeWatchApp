package com.example.shalinimenon.mywatchapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class HRActivity extends AppCompatActivity {
    private HeartRateBroadcastReceiver heartRateBroadcastReceiver;

    private class HeartRateBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context arg0, Intent arg1) {
            // ahr
            Log.v(this.getClass().getName(), "Value Recieved");
            if (arg1.getAction().equals("heartRateAction")) {
                float hr = arg1.getFloatExtra("HeartRate", 0);
                ((TextView)HRActivity.this.findViewById(R.id.heartRateTextView)).setText("Heart Rate : " + hr);
            }
        }
    }
    @Override
    protected void onStart() {
        super.onStart();
        // ahr
        heartRateBroadcastReceiver = new HeartRateBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("heartRateAction");
        registerReceiver(heartRateBroadcastReceiver, intentFilter);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hr);


    }
}
