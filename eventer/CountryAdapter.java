package com.example.eventer;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eventer.entity.Country;
import com.example.eventer.implementation.CityImplementation;

import java.util.ArrayList;
import java.util.List;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.CountryViewHolder> {

    private Context context;
    private int resource;
    private List<Country> countryList = new ArrayList<>();
    private boolean isPostEvent;

    public boolean isPostEvent() {
        return isPostEvent;
    }

    public void setPostEvent(boolean postEvent) {
        isPostEvent = postEvent;
    }

    private CityImplementation cityImplementation = new CityImplementation();

    private List<Integer> checkedBoxes = new ArrayList<>();


    public List<Integer> getCheckedBoxes() {
        return checkedBoxes;
    }

    public CountryAdapter(Context context, int resource, List<Country> countryList, boolean isPostEvent) {
        this.context = context;
        this.resource = resource;
        this.countryList = countryList;
        this.isPostEvent = isPostEvent;
    }

    @NonNull
    @Override
    public CountryAdapter.CountryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View container = inflater.inflate(resource, parent, false);
        CountryViewHolder countryViewHolder = new CountryViewHolder(container);

        return countryViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CountryAdapter.CountryViewHolder holder, int position) {

        holder.cbCountry.setText(countryList.get(position).getName());
        holder.cbCountry.setId(position + 1);
    }

    @Override
    public int getItemCount() {
        return countryList.size();
    }

    public class CountryViewHolder extends RecyclerView.ViewHolder {
        RadioButton cbCountry;


        public CountryViewHolder(@NonNull View itemView) {
            super(itemView);
            cbCountry = itemView.findViewById(R.id.cbCountry);

            cbCountry.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (isChecked && isPostEvent()) {
                    cbCountry.setChecked(false);

                    Intent intent = new Intent(context.getApplicationContext(), ChooseCity.class);
                    intent.putExtra("countryId", cbCountry.getId());
                    intent.putExtra("isPostEvent", true);

                    context.startActivity(intent);
                } else if (isChecked) {
                    // perform logic
                    cbCountry.setChecked(false);

                    Intent intent = new Intent(context.getApplicationContext(), ChooseCity.class);
                    intent.putExtra("countryId", cbCountry.getId());

                    context.startActivity(intent);

                }
            });
        }
    }
}
