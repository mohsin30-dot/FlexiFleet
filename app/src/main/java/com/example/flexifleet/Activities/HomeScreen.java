package com.example.flexifleet.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.TextView;

import com.example.flexifleet.BroadcastReceivers.InternetDetector;
import com.example.flexifleet.R;
import com.example.flexifleet.Services.CountingService;

public class HomeScreen extends AppCompatActivity {

    TextView phoneContacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        phoneContacts = findViewById(R.id.phoneContacts);


        // Broadcast receiver

        InternetDetector internetDetector = new InternetDetector();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        registerReceiver(internetDetector, intentFilter);
    }

    // Start longRunning provider

    public void startLongService(View view) {
        Intent intent = new Intent(this, CountingService.class);
        startService(intent);
    }

    // Read contacts from phoneAPP
    public void readPhoneContacts(View view) {

        StringBuilder contactsBuilder = new StringBuilder("");

        String[] projection = {
                ContactsContract.Contacts.DISPLAY_NAME_PRIMARY,
        };
        ContentResolver contentResolver = getContentResolver();
        Cursor cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI, projection,
                null, null, null);


        if(cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()){

                contactsBuilder.append(cursor.getString( 0 ) + "\n");

            }
        }

        phoneContacts.setText(contactsBuilder.toString());
    }





}