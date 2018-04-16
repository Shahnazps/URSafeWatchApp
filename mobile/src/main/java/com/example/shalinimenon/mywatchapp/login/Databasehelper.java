package com.example.shalinimenon.mywatchapp.login;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by SHAHNAZ on 19-03-2018.
 */

public class Databasehelper extends SQLiteOpenHelper {
    public Databasehelper(Context context) {
        super(context, "URSafeLogin.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table user(name text,email text,password text,mobile integer primary key)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists user");
        onCreate(db);

    }
    public Boolean insert(String name, String email,String password,int mobile)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("name",name);
        cv.put("email",email);
        cv.put("password",password);
        cv.put("mobile",mobile);
        long ins=db.insert("user",null, cv);
        if(ins ==1)
            return false;
        else
            return true;
    }
    public Boolean chkemail(String email)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("Select * from user where email=?",new String[]{email} );
        if(cursor.getCount()>0)return false;
        else
            return true;
    }
}
