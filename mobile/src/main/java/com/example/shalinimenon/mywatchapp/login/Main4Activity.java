package com.example.shalinimenon.mywatchapp.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.shalinimenon.mywatchapp.R;

public class Main4Activity extends AppCompatActivity {
    EditText mes;
    Button back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        mes=(EditText)findViewById(R.id.editText3);
        back=(Button)findViewById(R.id.button4);
        String message=mes.getText().toString();
        Intent myintent=new Intent(Main4Activity.this,Main2Activity.class);
        myintent.putExtra("Message",message);
        startActivity(myintent);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Main4Activity.this,Home.class);
                startActivity(intent);
            }
        });

    }
}
