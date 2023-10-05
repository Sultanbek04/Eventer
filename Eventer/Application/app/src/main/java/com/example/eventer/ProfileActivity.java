package com.example.eventer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.eventer.implementation.EventImplementation;
import com.google.android.material.textfield.TextInputLayout;

public class ProfileActivity extends AppCompatActivity {
    private Button btnLogout;
    private TextInputLayout name;
    private TextInputLayout email;

    EventImplementation eventImplementation = new EventImplementation();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        btnLogout = findViewById(R.id.btnLogout);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);

        name.getEditText().setText(MainActivity.sharedPreferences.getString("userName", "null"));
        email.getEditText().setText(MainActivity.sharedPreferences.getString("userEmail", "null"));

        btnLogout.setOnClickListener(view -> {
            eventImplementation.getCheckedEventsByCityId(MainActivity.rvEvents.getContext(), MainActivity.rvEvents, MainActivity.cityId);
            MainActivity.editor.remove("userId");
            MainActivity.editor.apply();
            finish();
        });

    }
}