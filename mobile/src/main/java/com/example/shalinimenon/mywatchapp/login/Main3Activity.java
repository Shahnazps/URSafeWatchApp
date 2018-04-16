package com.example.shalinimenon.mywatchapp.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shalinimenon.mywatchapp.R;

public class Main3Activity extends AppCompatActivity {
    Databasehelper db;
    Button signup;
    EditText name,pwd,email,cpwd,mobile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        db=new Databasehelper(this);
        name=(EditText)findViewById(R.id.name);
        pwd=(EditText)findViewById(R.id.pwd);
        pwd.setTransformationMethod(new AsteriskPasswordTransformationMethod());
        cpwd=(EditText)findViewById(R.id.confpwd);
        cpwd.setTransformationMethod(new AsteriskPasswordTransformationMethod());
        email=(EditText)findViewById(R.id.email);
        mobile=(EditText)findViewById(R.id.mobile);
        signup=(Button)findViewById(R.id.signup);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String n=name.getText().toString();
                String p=pwd.getText().toString();
                String cp=cpwd.getText().toString();
                String e=email.getText().toString();
                String m=mobile.getText().toString();
                int mob=Integer.parseInt(m);
                if(n.equals("")||p.equals("")||cp.equals("")||e.equals("")||m.equals(""))
                {
                    Toast.makeText(getApplicationContext(), "Fields are empty", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(p.equals(cp))
                    {
                        Boolean chkemail=db.chkemail(e);
                        if(chkemail==true)
                        {
                            Boolean insert=db.insert(n,e,p,mob);
                            if(insert==true)
                            {
                                Toast.makeText(getApplicationContext(), "Registration successful", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(), "Email already exists", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                Intent intent=new Intent(Main3Activity.this,MainActivity.class);
                startActivity(intent);
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
