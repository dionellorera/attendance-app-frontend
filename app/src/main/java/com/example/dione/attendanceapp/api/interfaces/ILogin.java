package com.example.dione.attendanceapp.api.interfaces;

import com.example.dione.attendanceapp.api.response_models.Login.Login;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * Created by dione on 29 Sep 2016.
 */

public interface ILogin {
    @POST("/user/login/{id}")
    @FormUrlEncoded
    void getLogin(@Path("id") String id, @Field("username") String username, @Field("code") String code, Callback<Login> callback);
}
