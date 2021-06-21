package com.kjstudios.aislehiringchallenge.data.remote;

import com.google.gson.JsonObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiEndpoint {

    @FormUrlEncoded
    @POST("phone_number_login")
    Call<JsonObject> getOtp(@Field("number") String phoneNumber);

    @FormUrlEncoded
    @POST("verify_otp")
    Call<JsonObject> verifyOtp(@Field("number") String phoneNumber, @Field("otp") String otp);

}
