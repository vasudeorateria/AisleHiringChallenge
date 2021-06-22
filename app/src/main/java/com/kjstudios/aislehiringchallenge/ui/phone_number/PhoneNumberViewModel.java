package com.kjstudios.aislehiringchallenge.ui.phone_number;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.JsonObject;
import com.kjstudios.aislehiringchallenge.data.remote.RetrofitClient;
import com.kjstudios.aislehiringchallenge.utils.Resource;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhoneNumberViewModel extends ViewModel {

    final MutableLiveData otp_send_status = new MutableLiveData<Resource>();

    void sendOtp(String countryCode, String phoneNumber) {

        Call<JsonObject> call = RetrofitClient
                .getInstance()
                .getApiEndpoint()
                .getOtp(countryCode + phoneNumber);

        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    otp_send_status.setValue(new Resource.Success(response.body(), null));
                } else {
                    otp_send_status.setValue(new Resource.Error(null, new Throwable()));
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                otp_send_status.setValue(new Resource.Error(null, t));
            }
        });
    }
}