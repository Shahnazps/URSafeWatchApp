package com.example.shalinimenon.mywatchapp.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shalinimenon.mywatchapp.R;

public class MainActivity extends AppCompatActivity {
    Databasehelper db;
    Button signup;
    EditText id,pwd;
    TextView login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db=new Databasehelper(this);
        signup=(Button)findViewById(R.id.signup);
        id=(EditText)findViewById(R.id.id);
        pwd=(EditText)findViewById(R.id.pwd);
        pwd.setTransformationMethod(new AsteriskPasswordTransformationMethod());
        login=(TextView)findViewById(R.id.textView);
        login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                if(id.getText().toString().equals("admin")&&pwd.getText().toString().equals("password")){
                    Toast.makeText(getApplicationContext(),"Login Success",Toast.LENGTH_LONG).show();
                    Intent myintent=new Intent(MainActivity.this,Home.class);
                    startActivity(myintent);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Login Unsuccessful",Toast.LENGTH_LONG).show();
                }
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myintent=new Intent(MainActivity.this,Main3Activity.class);
                startActivity(myintent);
            }
        });
    }
    public class AsteriskPasswordTransformationMethod extends PasswordTransformationMethod {
        @Override
        public CharSequence getTransformation(CharSequence source, View view) {
            return new PasswordCharSequence(source);
        }

        private class PasswordCharSequence implements CharSequence {
            private CharSequence mSource;
            public PasswordCharSequence(CharSequence source) {
                mSource = source; // Store char sequence
            }
            public char charAt(int index) {
                return '*'; // This is the important part
            }
            public int length() {
                return mSource.length(); // Return default
            }
            public CharSequence subSequence(int start, int end) {
                return mSource.subSequence(start, end); // Return default
            }
        }
    };
}
