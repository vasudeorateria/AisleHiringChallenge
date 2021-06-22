package com.kjstudios.aislehiringchallenge.ui.notes.discover;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.kjstudios.aislehiringchallenge.data.UserPreferences;
import com.kjstudios.aislehiringchallenge.data.model.java.Note;
import com.kjstudios.aislehiringchallenge.data.remote.RetrofitClient;
import com.kjstudios.aislehiringchallenge.utils.Resource;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DiscoverViewModel extends AndroidViewModel {

    public DiscoverViewModel(@NonNull Application application) {
        super(application);
        getNotes();
    }

    final MutableLiveData notes = new MutableLiveData<Resource>();

    void getNotes() {
        Call<Note> call = RetrofitClient
                .getInstance()
                .getApiEndpoint()
                .getProfileList(new UserPreferences(getApplication().getApplicationContext()).getToken());

        call.enqueue(new Callback<Note>() {
            @Override
            public void onResponse(Call<Note> call, Response<Note> response) {
                if (response.isSuccessful()) {
                    notes.setValue(new Resource.Success(response.body(), null));
                }else{
                    notes.setValue(new Resource.Error(response.errorBody() , null));
                }
            }

            @Override
            public void onFailure(Call<Note> call, Throwable t) {
                new Resource.Error(null, t);
            }
        });
    }


}