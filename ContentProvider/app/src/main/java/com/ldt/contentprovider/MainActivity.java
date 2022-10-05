package com.ldt.contentprovider;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.PackageManagerCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lvContact;
    ArrayList<Contact>  contactArrayList;
    ContactAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Lê Đức Tiên - 2050531200309");
        anhXa();

        adapter = new ContactAdapter(MainActivity.this, R.layout.contact_item, contactArrayList);
        lvContact.setAdapter(adapter);

        lvContact.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, ContactDetailActivity.class);
                intent.putExtra("name", contactArrayList.get(i).getName());
                intent.putExtra("number", contactArrayList.get(i).getNumber());
                startActivity(intent);
            }
        });

    }

    private void anhXa() {
        lvContact = findViewById(R.id.lvContact);
        contactArrayList = getPhoneContacts();
    }


    private ArrayList<Contact> getPhoneContacts() {

        // Yêu cầu quyền
        try {
            if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_CONTACTS}, 0);
            }
        } catch (Exception e) {}

        ArrayList<Contact> contacts = new ArrayList<>();

        ContentResolver contentResolver = getContentResolver();
        Uri uri= ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        Cursor cursor = contentResolver.query(uri, null, null, null, null);
//        Log.i("CONTACT_PROVIDER", "TOTAL # of Contacts ::: " + Integer.toString(cursor.getCount()));


        if(cursor.getCount()>0) {
            while(cursor.moveToNext()) {
                @SuppressLint("Range") String contactName = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                @SuppressLint("Range") String contactNumber = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                contacts.add(new Contact(contactName, contactNumber));
//                Log.i("CONTACT_PROVIDER", "Contact name ::: " + contactName + " ::: " + contactNumber);
            }
        }
        return contacts;
    }
}