package com.example.shalinimenon.mywatchapp.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.shalinimenon.mywatchapp.HRActivity;
import com.example.shalinimenon.mywatchapp.R;

public class Home extends AppCompatActivity {
    Button mes,con,read, heartRate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mes=(Button)findViewById(R.id.alert);
        con=(Button)findViewById(R.id.contacts);
        read=(Button)findViewById(R.id.data);
        heartRate = findViewById(R.id.heartRate);

        mes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Home.this,Main4Activity.class);
                startActivity(intent);
            }
        });
        con.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myintent=new Intent(Home.this,ContactList.class);
                startActivity(myintent);
            }
        });
        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Home.this,Main2Activity.class);
                startActivity(intent);
            }
        });

        heartRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Home.this,HRActivity.class);
                startActivity(intent);
            }
        });
    }
}
