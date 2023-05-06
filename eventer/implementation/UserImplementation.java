package com.example.eventer.implementation;

import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.eventer.MainActivity;
import com.example.eventer.NetworkService;
import com.example.eventer.api.UserApi;
import com.example.eventer.entity.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserImplementation {
    UserApi userApi = NetworkService.getInstance().getUserApi();

    public void getUserById(int id, Context context, MenuItem menuItem) {
        userApi.getUserById(id)
                .enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if (!response.body().isModerator() && menuItem != null && id != -1) {
                            menuItem.setVisible(false);
                        } else if (!response.body().isModerator() && menuItem == null && id != -1) {
                            Intent intent = new Intent(context, MainActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            context.startActivity(intent);
                            Toast.makeText(context, "You are not a moderator anymore", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        menuItem.setVisible(false);
                    }
                });
    }

    public void saveUser(User user, Context context) {
        userApi.saveUser(user)
                .enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(context, "User saved", Toast.LENGTH_SHORT).show();
                            MainActivity.editor.putInt("userId", response.body().getId());
                            MainActivity.editor.putString("userName", response.body().getName());
                            MainActivity.editor.putString("userEmail", response.body().getEmail());
                            MainActivity.editor.putBoolean("isModerator", response.body().isModerator());
                            MainActivity.editor.apply();

                            Intent intent = new Intent(context, MainActivity.class);
                            context.startActivity(intent);

                        } else {
                            Toast.makeText(context, "this email already exist", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
    }

    public void findUserByEmailAndPassword(String email, String password, Context context) {
        userApi.getUserByEmailAndPassword(email, password)
                .enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        MainActivity.editor.putInt("userId", response.body().getId());
                        MainActivity.editor.putString("userName", response.body().getName());
                        MainActivity.editor.putString("userEmail", response.body().getEmail());
                        MainActivity.editor.putBoolean("isModerator", response.body().isModerator());
                        MainActivity.editor.apply();
                        Intent intent = new Intent(context, MainActivity.class);
                        context.startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

}
