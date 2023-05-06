package com.example.eventer.implementation;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.example.eventer.NetworkService;
import com.example.eventer.R;
import com.example.eventer.entity.Section;

import java.util.List;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SectionImplementation {

    public void getAllSections(AutoCompleteTextView sectionItems, Context context) {
        NetworkService
                .getInstance()
                .getSectionApi()
                .getAllSections().enqueue(new Callback<List<Section>>() {
                    @Override
                    public void onResponse(Call<List<Section>> call, Response<List<Section>> response) {
                        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, R.layout.drop_down_item, response.body()
                                .stream().map(Section::getName)
                                .collect(Collectors.toList()));

                        sectionItems.setAdapter(adapter);
                        return;

                    }

                    @Override
                    public void onFailure(Call<List<Section>> call, Throwable t) {
                        Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                        return;
                    }
                });
    }
}
