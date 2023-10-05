package com.example.eventer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;

import com.example.eventer.implementation.EventImplementation;


public class ModeratorActivity extends AppCompatActivity {

    EventImplementation eventImplementation = new EventImplementation();
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moderator);
        recyclerView = findViewById(R.id.rvEventsInformation);
        eventImplementation.getUncheckedEvents(this, recyclerView);


    }
}