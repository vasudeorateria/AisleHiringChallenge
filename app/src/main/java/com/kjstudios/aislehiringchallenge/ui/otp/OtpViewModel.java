package com.kjstudios.aislehiringchallenge.ui.otp;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.JsonObject;
import com.kjstudios.aislehiringchallenge.data.remote.RetrofitClient;
import com.kjstudios.aislehiringchallenge.utils.Resource;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OtpViewModel extends ViewModel {

    final MutableLiveData otp_verify_status = new MutableLiveData<Resource>();

    void verifyOtp(String countryCode, String phoneNumber, String otp) {

        Call<JsonObject> call = RetrofitClient
                .getInstance()
                .getApiEndpoint()
                .verifyOtp((countryCode + phoneNumber), otp);

        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    otp_verify_status.setValue(new Resource.Success(response.body(), null));
                } else {
                    otp_verify_status.setValue(new Resource.Error(null, new Throwable()));
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                otp_verify_status.setValue(new Resource.Error(null, t));
            }
        });
    }
}