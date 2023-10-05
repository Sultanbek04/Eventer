package com.example.eventer;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.eventer.entity.Event;
import com.example.eventer.entity.UserLikedEvents;
import com.example.eventer.implementation.EventImplementation;
import com.example.eventer.implementation.UserImplementation;
import com.ms.square.android.expandabletextview.ExpandableTextView;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventInformationAdapter extends RecyclerView.Adapter<EventInformationAdapter.EventInformationViewHolder> {

    private Context context;
    private int resource;
    int counter = 0;
    List<Integer> likedEventListByUser = new ArrayList<>();
    private ArrayList<Event> eventList;
    EventImplementation eventImplementation = new EventImplementation();
    UserImplementation userImplementation = new UserImplementation();
    RecyclerView mRecyclerView;

    public boolean isModeratorPage() {
        return isModeratorPage;
    }

    public void setModeratorPage(boolean moderatorPage) {
        isModeratorPage = moderatorPage;
    }

    private boolean isModeratorPage;


    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);

        mRecyclerView = recyclerView;
    }

    @NonNull
    @Override
    public EventInformationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View container = inflater.inflate(resource, parent, false);
        EventInformationViewHolder eventInformationViewHolder = new EventInformationViewHolder(container);

        return eventInformationViewHolder;
    }

    public EventInformationAdapter(Context context, int resource, ArrayList<Event> eventList, boolean isModeratorPage) {
        this.context = context;
        this.resource = resource;
        this.eventList = eventList;
        this.isModeratorPage = isModeratorPage;
        try {
            this.likedEventListByUser = (ArrayList<Integer>) NetworkService.getInstance().getEventApi().getLikedEventIdByUserId(
                    MainActivity.sharedPreferences.getInt("userId", -1)
            ).execute().body();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull EventInformationViewHolder holder, int position) {
        if (isModeratorPage == false) {
            holder.btnReject.setVisibility(View.INVISIBLE);
            holder.btnAccept.setVisibility(View.INVISIBLE);

        } else {
            holder.ivLiked.setVisibility(View.INVISIBLE);
            holder.tvNumberOfLikes.setVisibility(View.INVISIBLE);
        }

        holder.tvNumberOfLikes.setText(String.valueOf(eventList.get(position).getNumberOfLikes()));
        holder.tvNameOfTheEvent.setId(eventList.get(position).getId());
        holder.tvNameOfTheEvent.setText(eventList.get(position).getName());
        holder.description.setText(eventList.get(position).getDescription());
        holder.tvEventDateAndTime.setText(eventList.get(position).getEventDate() + "  " + eventList.get(position).getEventTime());
        if (eventList.get(position).getImagePath() != null) {
            Picasso.get().load("http://10.1.198.153:55555/download-image/" + eventList.get(position).getImagePath()).into(holder.ivEvent);
        } else {
            holder.ivEvent.setImageResource(R.drawable.no_image);
        }

        for (int i = 0; i < likedEventListByUser.size(); i++) {
            if (holder.tvNameOfTheEvent.getId() == likedEventListByUser.get(i)) {
                holder.ivLiked.setImageResource(R.drawable.dark_red2);
                holder.ivLiked.setTag("liked");
            }
        }
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }


    public class EventInformationViewHolder extends RecyclerView.ViewHolder {

        ImageView ivEvent;
        ImageView ivLiked;
        TextView tvNameOfTheEvent;
        ExpandableTextView description;

        TextView tvEventDateAndTime;

        Button btnAccept;

        Button btnReject;

        TextView tvNumberOfLikes;


        public EventInformationViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNameOfTheEvent = itemView.findViewById(R.id.eventName);
            description = itemView.findViewById(R.id.eventDescription);
            tvEventDateAndTime = itemView.findViewById(R.id.eventDateAndTime);
            tvNumberOfLikes = itemView.findViewById(R.id.tvNumberOfLikes);

            ivEvent = itemView.findViewById(R.id.ivEvent);
            ivLiked = itemView.findViewById(R.id.liked);

            btnAccept = itemView.findViewById(R.id.btnAccept);
            btnReject = itemView.findViewById(R.id.btnReject);
            btnAccept.setOnClickListener(view -> {
                eventImplementation.changeCheckedToTrue(tvNameOfTheEvent.getId(), context.getApplicationContext());

                refreshRecycleView();
            });

            btnReject.setOnClickListener(view -> {
                eventImplementation.deleteEventById(tvNameOfTheEvent.getId(), context.getApplicationContext());
                refreshRecycleView();

            });


            ivLiked.setOnClickListener(view -> {

                if (ivLiked.getTag() != "liked" && MainActivity.sharedPreferences.getInt("userId", -1) != -1) {
                    ivLiked.setImageResource(R.drawable.dark_red2);
                    NetworkService.getInstance().getEventApi().incrementLike(tvNameOfTheEvent.getId()).enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            NetworkService.getInstance().getEventApi().getNumberOfLikes(tvNameOfTheEvent.getId())
                                    .enqueue(new Callback<Integer>() {
                                        @Override
                                        public void onResponse(Call<Integer> call, Response<Integer> response) {
                                            tvNumberOfLikes.setText(response.body().toString());
                                            UserLikedEvents userLikedEvents = new UserLikedEvents(MainActivity.sharedPreferences
                                                    .getInt("userId", -1), tvNameOfTheEvent.getId());
                                            NetworkService.getInstance().getEventApi().saveLikedEventByUser(userLikedEvents).enqueue(new Callback<UserLikedEvents>() {
                                                @Override
                                                public void onResponse(Call<UserLikedEvents> call, Response<UserLikedEvents> response) {
                                                    Toast.makeText(context, "saved", Toast.LENGTH_SHORT).show();
                                                    ivLiked.setTag("liked");

                                                }

                                                @Override
                                                public void onFailure(Call<UserLikedEvents> call, Throwable t) {
                                                    Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                                                }
                                            });
                                        }

                                        @Override
                                        public void onFailure(Call<Integer> call, Throwable t) {

                                        }
                                    });
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                } else if (ivLiked.getTag() == "liked" && MainActivity.sharedPreferences.getInt("userId", -1) != -1) {
                    ivLiked.setImageResource(R.drawable.unloved);
                    NetworkService.getInstance().getEventApi().decrementLike(tvNameOfTheEvent.getId()).enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            NetworkService.getInstance().getEventApi().getNumberOfLikes(tvNameOfTheEvent.getId())
                                    .enqueue(new Callback<Integer>() {
                                        @Override
                                        public void onResponse(Call<Integer> call, Response<Integer> response) {
                                            tvNumberOfLikes.setText(response.body().toString());
                                            NetworkService.getInstance()
                                                    .getEventApi()
                                                    .deleteLikedEventByUser(tvNameOfTheEvent.getId()).enqueue(new Callback<Void>() {
                                                        @Override
                                                        public void onResponse(Call<Void> call, Response<Void> response) {
                                                            ivLiked.setTag("unliked");
                                                        }

                                                        @Override
                                                        public void onFailure(Call<Void> call, Throwable t) {

                                                        }
                                                    });
                                        }

                                        @Override
                                        public void onFailure(Call<Integer> call, Throwable t) {
                                            Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    });
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                } else {
                    Toast.makeText(context, "Please log in or sign up", Toast.LENGTH_SHORT).show();
                }
            });


        }

        private void refreshRecycleView() {
            userImplementation.getUserById(MainActivity.sharedPreferences.getInt("userId", -1), context.getApplicationContext()
                    , null);
            eventList.removeIf(s -> s.getId() == tvNameOfTheEvent.getId());
            EventInformationAdapter adapter = new EventInformationAdapter(context, R.layout.show_event, eventList, true);
            LinearLayoutManager layoutManager = new LinearLayoutManager(context, RecyclerView.VERTICAL, false);
            mRecyclerView.setLayoutManager(layoutManager);
            mRecyclerView.setAdapter(adapter);
        }
    }
}
