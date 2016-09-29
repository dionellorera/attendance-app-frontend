package com.example.dione.attendanceapp.api.interfaces;

import com.example.dione.attendanceapp.api.response_models.Logs.Logs;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * Created by dione on 11/08/2016.
 */
public interface ILogs {
    @POST("/logs/list")
    @FormUrlEncoded
    void getLogs(@Field("token") String token, Callback<Logs> callback);
}
