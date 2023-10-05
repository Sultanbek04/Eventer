package com.example.eventer.implementation;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eventer.EventInformationAdapter;
import com.example.eventer.MainActivity;
import com.example.eventer.ModeratorActivity;
import com.example.eventer.NetworkService;
import com.example.eventer.R;
import com.example.eventer.api.EventApi;
import com.example.eventer.entity.Event;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventImplementation {
    private EventInformationAdapter adapter;
    private EventApi eventApi = NetworkService.getInstance().getEventApi();

    private void setUpAdapter(ArrayList<Event> eventArrayList, Context context, RecyclerView recyclerView,
                              boolean isModerator) {
        adapter = new EventInformationAdapter(context, R.layout.show_event, eventArrayList, isModerator);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context, RecyclerView.VERTICAL, isModerator);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    public void getEventsByUserId(Context context, RecyclerView recyclerView) {
        eventApi.getAllEventsByUserId(MainActivity.sharedPreferences.getInt("userId", -1))
                .enqueue(new Callback<List<Event>>() {
                    @Override
                    public void onResponse(Call<List<Event>> call, Response<List<Event>> response) {
                        setUpAdapter((ArrayList<Event>) response.body(), context, recyclerView, false);
                    }

                    @Override
                    public void onFailure(Call<List<Event>> call, Throwable t) {
                    }
                });
    }

    public void getEventsOfUser(Context context, RecyclerView recyclerView) {
        eventApi
                .getEventsOfUser(MainActivity.sharedPreferences.getInt("userId", -1))
                .enqueue(new Callback<List<Event>>() {
                    @Override
                    public void onResponse(Call<List<Event>> call, Response<List<Event>> response) {
                        setUpAdapter((ArrayList<Event>) response.body(), context, recyclerView, false);
                    }

                    @Override
                    public void onFailure(Call<List<Event>> call, Throwable t) {

                    }
                });
    }


    public void changeCheckedToTrue(int id, Context context) {
        eventApi
                .changeCheckedToTrue(id)
                .enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                    }
                });
    }

    public void deleteEventById(int id, Context context) {
        eventApi
                .deleteEvent(id)
                .enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                    }
                });
    }

    public void saveEvent(Event event, Context context) {
        eventApi
                .saveEvent(event)
                .enqueue(new Callback<Event>() {
                    @Override
                    public void onResponse(Call<Event> call, Response<Event> response) {
                        Toast.makeText(context, response.body().getName(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(context, MainActivity.class);
                        context.startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<Event> call, Throwable t) {
                    }
                });
    }




    public void getUncheckedEvents(Context context, RecyclerView recyclerView) {
        eventApi
                .getAllByCheckedIsFalse()
                .enqueue(new Callback<List<Event>>() {
                    @Override
                    public void onResponse(Call<List<Event>> call, Response<List<Event>> response) {
                        setUpAdapter((ArrayList<Event>) response.body(), context, recyclerView, true);

                    }

                    @Override
                    public void onFailure(Call<List<Event>> call, Throwable t) {
                    }
                });

    }


    public void getCheckedEventsByCityId(Context context, RecyclerView recyclerView, int cityId) {
        eventApi
                .getCheckedEventsByCityId(cityId)
                .enqueue(new Callback<List<Event>>() {
                    @Override
                    public void onResponse(Call<List<Event>> call, Response<List<Event>> response) {
                        setUpAdapter((ArrayList<Event>) response.body(), context, recyclerView, false);
                    }

                    @Override
                    public void onFailure(Call<List<Event>> call, Throwable t) {
                    }
                });
    }

    public void getCheckedEventsByCityIdAndSectionId(Context context, RecyclerView recyclerView, int cityId, int sectionId) {
        eventApi
                .getCheckedEventsByCityIdAndSectionId(cityId, sectionId)
                .enqueue(new Callback<List<Event>>() {
                    @Override
                    public void onResponse(Call<List<Event>> call, Response<List<Event>> response) {
                        setUpAdapter((ArrayList<Event>) response.body(), context, recyclerView, false);
                    }

                    @Override
                    public void onFailure(Call<List<Event>> call, Throwable t) {
                    }
                });
    }


}
