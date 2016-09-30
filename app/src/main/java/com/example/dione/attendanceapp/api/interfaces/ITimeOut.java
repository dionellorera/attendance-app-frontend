package com.example.dione.attendanceapp.api.interfaces;

import com.example.dione.attendanceapp.api.response_models.TimeIn.TimeIn;
import com.example.dione.attendanceapp.api.response_models.TimeOut.TimeOut;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * Created by dione on 30 Sep 2016.
 */

public interface ITimeOut {
    @POST("/logs/time_out")
    @FormUrlEncoded
    void getTimeOut(@Field("token") String token, @Field("time_out") String timeIn, @Field("date") String date, Callback<TimeOut> callback);
}
