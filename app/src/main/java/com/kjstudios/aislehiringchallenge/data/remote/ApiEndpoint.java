package com.kjstudios.aislehiringchallenge.data.remote;

import com.google.gson.JsonObject;
import com.kjstudios.aislehiringchallenge.data.model.Note;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiEndpoint {

    @FormUrlEncoded
    @POST("phone_number_login")
    Call<JsonObject> getOtp(@Field("number") String phoneNumber);

    @FormUrlEncoded
    @POST("verify_otp")
    Call<JsonObject> verifyOtp(@Field("number") String phoneNumber, @Field("otp") String otp);

    @GET("test_profile_list")
    Call<Note> getProfileList(@Header("Authorization") String AUTH_TOKEN);

}
