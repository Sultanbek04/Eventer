package com.example.eventer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

public class SignUpOrSignIn extends AppCompatActivity {

    ViewPager viewPager;
    TabLayout tabLayout;

    ConstraintLayout signUp;
    ConstraintLayout login;
    float v = 0;

    @Override
    protected void onStart() {
        super.onStart();

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_or_sign_in);

        viewPager = findViewById(R.id.view_pager);


        tabLayout = findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Login"));
        tabLayout.addTab(tabLayout.newTab().setText("Sign up"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        viewPager.setCurrentItem(0);
                        return;
                    case 1:
                        viewPager.setCurrentItem(1);
                        return;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        final LoginAdapter loginAdapter = new LoginAdapter(getSupportFragmentManager(), this, tabLayout.getTabCount());

        viewPager.setAdapter(loginAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setTranslationY(300);


    }
}