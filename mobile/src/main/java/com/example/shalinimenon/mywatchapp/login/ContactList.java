package com.example.shalinimenon.mywatchapp.login;

import android.content.Intent;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.example.shalinimenon.mywatchapp.R;

public class ContactList extends AppCompatActivity {


    ListView l1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conatct_list);
        l1=(ListView)findViewById(R.id.listview);

    }
    public void get(View v)
    {
        Cursor cursor=getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null,null,null,null);
        startManagingCursor(cursor);
        String[] from={ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,ContactsContract.CommonDataKinds.Phone.NUMBER,ContactsContract.CommonDataKinds.Phone._ID};
        int[] to={android.R.id.text1,android.R.id.text2};
        SimpleCursorAdapter simpleCursorAdapter=new SimpleCursorAdapter(this,android.R.layout.simple_expandable_list_item_2,cursor,from,to);
        l1.setAdapter(simpleCursorAdapter);
        l1.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        l1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(ContactList.this,Emergency.class);
                intent.putExtra("Contact",l1.getItemAtPosition(i).toString());
                String selectedFromList = (String) (l1.getItemAtPosition(i));
                Intent myintent=new Intent(ContactList.this,Emergency.class);
                myintent.putExtra("Message",selectedFromList);
                startActivity(myintent);
                startActivity(intent);
            }
        });

    }
}
