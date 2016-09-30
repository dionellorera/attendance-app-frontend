package com.example.dione.attendanceapp.api.interfaces;

import com.example.dione.attendanceapp.api.response_models.TimeIn.TimeIn;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * Created by dione on 30 Sep 2016.
 */

public interface ITimeIn {
    @POST("/logs/time_in")
    @FormUrlEncoded
    void getTimeIn(@Field("token") String token, @Field("time_in") String timeIn, @Field("date") String date, Callback<TimeIn> callback);
}
