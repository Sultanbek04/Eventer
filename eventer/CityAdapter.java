package com.example.eventer;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eventer.entity.City;
import com.example.eventer.entity.Country;

import java.util.ArrayList;
import java.util.List;

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.CityViewHolder> {

    private Context context;
    private int resource;
    private List<City> cityList;

    boolean isPostEvent = false;
    List<CheckBox> checkBoxes = new ArrayList<>();


    public CityAdapter(Context context, int resource, List<City> cityList, boolean isPostEvent) {
        this.context = context;
        this.resource = resource;
        this.cityList = cityList;
        this.isPostEvent = isPostEvent;
    }

    @NonNull
    @Override
    public CityAdapter.CityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View container = inflater.inflate(resource, parent, false);
        CityViewHolder cityViewHolder = new CityViewHolder(container);

        return cityViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CityAdapter.CityViewHolder holder, int position) {
        holder.cbCity.setText(cityList.get(position).getName());
        holder.cbCity.setId(cityList.get(position).getId());
    }

    public int getLastChosenCheckedBoxCityId() {
        return checkBoxes.get(0).getId();
    }

    @Override
    public int getItemCount() {
        return cityList.size();
    }

    public class CityViewHolder extends RecyclerView.ViewHolder {
        CheckBox cbCity;

        public CityViewHolder(@NonNull View itemView) {

            super(itemView);
            cbCity = itemView.findViewById(R.id.cbCity);


            cbCity.setOnCheckedChangeListener((buttonView, isChecked) -> {

                if (checkBoxes.size() != 0) {
                    Toast.makeText(context.getApplicationContext(), checkBoxes.get(0).getText(), Toast.LENGTH_SHORT).show();
                    checkBoxes.get(0).setChecked(false);
                    checkBoxes = new ArrayList<>();
                }

                if (isChecked && isPostEvent) {
                    Intent intent = new Intent(context.getApplicationContext(), PostEventActivity.class);
                    intent.putExtra("cityId", cbCity.getId());
                    context.startActivity(intent);

                } else if (isChecked) {
                    // perform logic
                    checkBoxes.add(cbCity);
                    MainActivity.editor.remove("cityId");
                    MainActivity.editor.putInt("cityId", cbCity.getId());
                    MainActivity.editor.apply();
                }

            });

        }
    }
}
