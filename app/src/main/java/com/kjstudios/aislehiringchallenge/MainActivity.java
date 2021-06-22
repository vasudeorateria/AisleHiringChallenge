package com.kjstudios.aislehiringchallenge;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavGraph;
import androidx.navigation.fragment.NavHostFragment;

import com.kjstudios.aislehiringchallenge.data.UserPreferences;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NavHostFragment navHostFragment =
                (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        NavController navController = navHostFragment.getNavController();
        NavGraph navGraph = navController.getGraph();

        String token = new UserPreferences(this).getToken();
        if (token == null) {
            navGraph.setStartDestination(R.id.phoneNumberFragment);
            navController.setGraph(navGraph);
        }
    }
}