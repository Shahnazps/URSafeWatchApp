package com.example.shalinimenon.mywatchapp.login;

import android.Manifest;
import android.content.Intent;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shalinimenon.mywatchapp.R;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {

    private static final int REQUEST_PHONE_CALL = 101;
    private String mPhoneNumber;
    private String mSms;
    private Intent mIntent;
    private int countDown;

    Button hrok;
    EditText hr, count;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //mPhoneNumber = getIntent().getExtras().getString("selectedFromList");
        //mSms = getIntent().getExtras().getString("message");
        mPhoneNumber = "8137871193";
        mSms = "Need Help!";

        Toast.makeText(this, "Main2Activity", Toast.LENGTH_LONG).show();
        hrok = (Button) findViewById(R.id.hrok);
        hr = (EditText) findViewById(R.id.hr);
        count = (EditText) findViewById(R.id.count);
        hrok.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String value = hr.getText().toString();
        final int rate = Integer.parseInt(value);
        String value2 = count.getText().toString();
        final int pedo = Integer.parseInt(value2);

        System.out.println("heart rate = " + rate);
        if (rate > 90 && pedo > 30 || rate > 90 && pedo == 0) {
            makePhoneCallAndSms(Main2Activity.this, mPhoneNumber);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
            @NonNull String[] permissions,
            @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_PHONE_CALL:
                startActivity(mIntent);
                break;
        }
    }

    private void makePhoneCallAndSms(final Context context, String phoneNumber) {

        countDown = 10;
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        final AlertDialog alertDialog = dialogBuilder.setTitle("Waiting for response...")
                .setMessage(getDialogMessage(countDown))
                .show();

        new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                alertDialog.setMessage(getDialogMessage(--countDown));
            }

            @Override
            public void onFinish() {
                alertDialog.dismiss();
                mIntent = new Intent(Intent.ACTION_CALL);
                mIntent.setData(Uri.parse("tel:" + mPhoneNumber));
                if (ActivityCompat.checkSelfPermission(context,
                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(Main2Activity.this, new String[] {Manifest.permission.CALL_PHONE},
                            REQUEST_PHONE_CALL);
                    return;
                }

                startActivity(mIntent);

                // Send sms
                if (ContextCompat.checkSelfPermission(Main2Activity.this,
                        Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(Main2Activity.this,
                            new String[] {Manifest.permission.SEND_SMS}, 1);
                } else {//do nothing
                    try {
                        SmsManager smsmanager = SmsManager.getDefault();
                        smsmanager.sendTextMessage(mPhoneNumber, null, mSms, null, null);
                        Toast.makeText(Main2Activity.this, "Help SMS sent", Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        Toast.makeText(Main2Activity.this, "Help SMS failed", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        }.start();


    }

    private String getDialogMessage(int countDown) {
        return "Placing call in " + countDown + " seconds";
    }

}




