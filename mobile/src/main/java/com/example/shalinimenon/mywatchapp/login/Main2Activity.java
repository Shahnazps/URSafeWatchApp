package com.example.shalinimenon.mywatchapp.login;

import android.Manifest;
import android.content.Intent;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shalinimenon.mywatchapp.R;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {

    private  static final int REQUEST_PHONE_CALL =101;
    String PHONE_NUMBER = getIntent().getExtras().getString("selectedFromList");
    String sms=getIntent().getExtras().getString("message");

    Button hrok;
    EditText hr,count;


            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main2);
                Toast.makeText(this, "Main2Activity", Toast.LENGTH_LONG).show();
                hrok = (Button) findViewById(R.id.hrok);
                hr = (EditText) findViewById(R.id.hr);
                count= (EditText) findViewById(R.id.count);
                hrok.setOnClickListener(this);
            }

            @Override
            public void onClick(View v) {
                String value = hr.getText().toString();
                final int rate = Integer.parseInt(value);
                String value2=count.getText().toString();
                final int pedo=Integer.parseInt(value2);

                System.out.println("heart rate = " + rate);
                if (rate > 90 && pedo>30 || rate>90 && pedo==0) {
                   makePhoneCall(Main2Activity.this, PHONE_NUMBER);
                    if (ContextCompat.checkSelfPermission(Main2Activity.this,
                            Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(Main2Activity.this,
                                new String[]{Manifest.permission.SEND_SMS}, 1);
                    } else {//do nothing
                        try {
                            SmsManager smsmanager = SmsManager.getDefault();
                            smsmanager.sendTextMessage(PHONE_NUMBER, null, sms, null, null);
                            Toast.makeText(Main2Activity.this, "sent", Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            Toast.makeText(Main2Activity.this, "failed", Toast.LENGTH_SHORT).show();
                        }
                    }

                }
            }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_PHONE_CALL:
                makePhoneCall(Main2Activity.this, PHONE_NUMBER);
                break;
        }
    }

        private void makePhoneCall(Context context, String phoneNumber) {
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:" + PHONE_NUMBER));
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(Main2Activity.this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_PHONE_CALL);
                return;
            }

            context.startActivity(intent);
        }

        }




