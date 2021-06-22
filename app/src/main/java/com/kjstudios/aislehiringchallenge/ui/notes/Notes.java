package com.kjstudios.aislehiringchallenge.ui.notes;


import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.kjstudios.aislehiringchallenge.R;

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
        NavigationUI.setupWithNavController(bottomNavigationView, navController);

        bottomNavigationView.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {
            }
        });

        BadgeDrawable notesFragmentBadge = bottomNavigationView.getOrCreateBadge(R.id.notesFragment);
        BadgeDrawable matchesFragmentBadge = bottomNavigationView.getOrCreateBadge(R.id.matchesFragment);

        notesFragmentBadge.setNumber(9);
        matchesFragmentBadge.setMaxCharacterCount(3);
        notesFragmentBadge.setBackgroundColor(R.drawable.badge_shape);

        matchesFragmentBadge.setNumber(50);
        matchesFragmentBadge.setMaxCharacterCount(3);
        matchesFragmentBadge.setBackgroundColor(R.drawable.badge_shape);
    }
}