package com.kjstudios.aislehiringchallenge.ui.notes.discover;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.kjstudios.aislehiringchallenge.data.UserPreferences;
import com.kjstudios.aislehiringchallenge.data.model.java.Note;
import com.kjstudios.aislehiringchallenge.data.remote.RetrofitClient;
import com.kjstudios.aislehiringchallenge.utils.Resource;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DiscoverViewModel extends ViewModel {

    final MutableLiveData notes = new MutableLiveData<Resource>();

    void getNotes(String token) {
        Call<Note> call = RetrofitClient
                .getInstance()
                .getApiEndpoint()
                .getProfileList(token);

        call.enqueue(new Callback<Note>() {
            @Override
            public void onResponse(Call<Note> call, Response<Note> response) {
                if (response.isSuccessful()) {
                    notes.setValue(new Resource.Success(response.body(), null));
                }else{
                    notes.setValue(new Resource.Error(null , new Throwable()));
                }
            }

            @Override
            public void onFailure(Call<Note> call, Throwable t) {
                notes.setValue(new Resource.Error(null, t));
            }
        });
    }


}