package com.example.eventer;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eventer.entity.Event;
import com.example.eventer.entity.User;
import com.example.eventer.implementation.EventImplementation;
import com.example.eventer.implementation.SectionImplementation;
import com.example.eventer.implementation.UserImplementation;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.android.material.tabs.TabLayout;
//import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    public static SharedPreferences sharedPreferences;
    public static SharedPreferences.Editor editor;
    private BottomAppBar mBottomAppBar;
    private Toolbar mToolBar;
    private FloatingActionButton floatingActionButton;
    private AutoCompleteTextView autoCompleteTextView;
    private SwitchMaterial sMyEvents;
    static public RecyclerView rvEvents;
    static public int cityId = 0;


    int userId;
    boolean unloved = true;
    int positionOfFocusedItemOfSpinner = 0;
    UserImplementation userImplementation = new UserImplementation();

    private SectionImplementation sectionImplementation = new SectionImplementation();
    private EventImplementation eventImplementation = new EventImplementation();

    @Override
    protected void onStart() {
        super.onStart();
        userId = MainActivity.sharedPreferences.getInt("userId", -1);
        if (userId == -1) {
            sMyEvents.setVisibility(View.INVISIBLE);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


        Log.d("my", "onCreate: ");

        sharedPreferences = getSharedPreferences("settings", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        sMyEvents = findViewById(R.id.sMyEvents);
        mBottomAppBar = findViewById(R.id.bottom_app_bar);
        floatingActionButton = findViewById(R.id.btnAddEvent);
        autoCompleteTextView = findViewById(R.id.sectionItems);
        rvEvents = findViewById(R.id.rvEvents);
//        eventImplementation.getCheckedEvents(this, rvEvents);
        cityId = sharedPreferences.getInt("cityId", -1);
        if (cityId != -1) {
            eventImplementation.getCheckedEventsByCityId(this, rvEvents, cityId);

        }


        floatingActionButton.setOnClickListener(this::createEvent);
        sMyEvents.setChecked(false);
        sMyEvents.setOnCheckedChangeListener((compoundButton, isChecked) -> {
            if (isChecked) {
                autoCompleteTextView.setDropDownHeight(0);
                eventImplementation.getEventsByUserId(MainActivity.this, rvEvents);
            } else {
                backToState();
            }
        });

        mToolBar = findViewById(R.id.main_toolbar);
        setSupportActionBar(mToolBar);
        mBottomAppBar.replaceMenu(R.menu.bottom_app_bar_menu);


        mBottomAppBar.setOnMenuItemClickListener(item -> {

            switch (item.getItemId()) {
                case R.id.profile:

                    Intent intent;
                    if (userId != -1) {
                        intent = new Intent(this, ProfileActivity.class);
                    } else {
                        intent = new Intent(this, SignUpOrSignIn.class);
                    }
                    startActivity(intent);
                    break;
                case R.id.liked_events:
                    if (unloved) {
                        item.setIcon(R.drawable.dark_red2);
                        eventImplementation.getEventsOfUser(
                                MainActivity.this, rvEvents);
                    } else {
                        item.setIcon(R.drawable.unloved);
                        backToState();
                    }
                    unloved = !unloved;

            }
            return true;
        });
        autoCompleteTextView.setOnItemClickListener((adapterView, view, i, l) -> {
            if (i == 0) {
                eventImplementation.getCheckedEventsByCityId(MainActivity.this, rvEvents, cityId);
            } else {
                positionOfFocusedItemOfSpinner = i + 1;
                eventImplementation.getCheckedEventsByCityIdAndSectionId(MainActivity.this, rvEvents, cityId, i + 1);
            }
        });
        sectionImplementation.getAllSections(autoCompleteTextView, this);
    }

    public void backToState() {
        if (positionOfFocusedItemOfSpinner != 0) {
            eventImplementation.getCheckedEventsByCityIdAndSectionId(MainActivity.this,
                    rvEvents, cityId, positionOfFocusedItemOfSpinner);
            autoCompleteTextView.setDropDownHeight(Toolbar.LayoutParams.WRAP_CONTENT);
        } else {
            eventImplementation.getCheckedEventsByCityId(MainActivity.this, rvEvents, cityId);
            autoCompleteTextView.setDropDownHeight(Toolbar.LayoutParams.WRAP_CONTENT);

        }
    }

    private void createEvent(View view) {
        if (sharedPreferences.getInt("userId", -1) != -1) {
            Intent intent = new Intent(this, ChooseCountry.class);
            intent.putExtra("createEvent", true);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Please sign in or sign up", Toast.LENGTH_SHORT).show();
        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main_menu, menu);


        userImplementation.getUserById(sharedPreferences.getInt("userId", -1),
                MainActivity.this, menu.getItem(2));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.area) {
            Intent intent = new Intent(this, ChooseCountry.class);
            this.startActivity(intent);
            return true;
        } else if (id == R.id.moderator) {
            Intent intent = new Intent(this, ModeratorActivity.class);
            this.startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}