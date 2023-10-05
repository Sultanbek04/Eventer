package com.example.eventer;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class LoginAdapter extends FragmentPagerAdapter {
    private Context context;
    int toolTabs;


    public LoginAdapter(@NonNull FragmentManager fm, Context context, int toolTabs) {
        super(fm);
        this.context = context;
        this.toolTabs = toolTabs;
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                LoginTabFragment loginTabFragment = new LoginTabFragment();
                return loginTabFragment;
            case 1:
                SignUpTabFragement signUpTabFragement = new SignUpTabFragement();
                return signUpTabFragement;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return toolTabs;
    }
}
