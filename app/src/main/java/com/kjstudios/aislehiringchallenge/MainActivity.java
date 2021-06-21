package com.kjstudios.aislehiringchallenge;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavGraph;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Bundle;

import com.kjstudios.aislehiringchallenge.data.UserPreferences;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NavHostFragment navHostFragment =
                (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        NavController navController = navHostFragment.getNavController();
        NavGraph navGraph = navController.getNavInflater().inflate(R.navigation.nav_graph);

        String token = new UserPreferences(this).getToken();
        if(token!=null){
            navGraph.setStartDestination(R.id.notesFragment);
        }
        navController.setGraph(navGraph);
    }
}