package com.kjstudios.aislehiringchallenge.ui.notes;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.kjstudios.aislehiringchallenge.R;
import com.kjstudios.aislehiringchallenge.data.UserPreferences;
import com.kjstudios.aislehiringchallenge.data.model.java.Likes;
import com.kjstudios.aislehiringchallenge.data.model.java.Note;
import com.kjstudios.aislehiringchallenge.data.remote.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Notes extends Fragment {

    private NotesViewModel mViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.notes, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        BottomNavigationView bottomNavigationView = view.findViewById(R.id.bottomNavigationView);

        NavHostFragment navHostFragment =
                (NavHostFragment) getChildFragmentManager().findFragmentById(R.id.bottom_navbar_navhost);
        NavController navController = navHostFragment.getNavController();
        NavigationUI.setupWithNavController(bottomNavigationView , navController);

        Call<Note> call = RetrofitClient
                .getInstance()
                .getApiEndpoint()
                .getProfileList(new UserPreferences(getContext()).getToken());

        call.enqueue(new Callback<Note>() {
            @Override
            public void onResponse(Call<Note> call, Response<Note> response) {
                Toast.makeText(getContext(), "get profile list success", Toast.LENGTH_SHORT).show();
                Likes likes = response.body().getLikes();
            }

            @Override
            public void onFailure(Call<Note> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}