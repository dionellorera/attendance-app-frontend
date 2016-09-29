package com.example.dione.attendanceapp.api;

import com.example.dione.attendanceapp.api.interfaces.ILogin;
import com.example.dione.attendanceapp.api.interfaces.ILogs;
import com.example.dione.attendanceapp.api.response_models.Login.Login;
import com.example.dione.attendanceapp.api.response_models.Logs.Logs;
import com.squareup.okhttp.OkHttpClient;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.client.OkClient;

/**
 * Created by dione on 11/08/2016.
 */
public class AttendanceClient {
    private static final String BASE_URL = "http://192.168.1.70:8080/";

    private static AttendanceClient mAttendanceClient;
    private static RestAdapter mRestAdapter;

    public static AttendanceClient getClient() {
        if (mAttendanceClient == null)
            mAttendanceClient = new AttendanceClient();
        return mAttendanceClient;
    }

    private AttendanceClient() {
        mRestAdapter = new RestAdapter.Builder()
                .setEndpoint(BASE_URL)
                .setClient(new OkClient(new OkHttpClient()))
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build(); 
    }

    public void getLogsList(String token, Callback<Logs> callback) {
        ILogs iLogs = mRestAdapter.create(ILogs.class);
        iLogs.getLogs(token, callback);
    }

    public void getLoginResponse(String id, String username, String code, Callback<Login> callback) {
        ILogin iLogs = mRestAdapter.create(ILogin.class);
        iLogs.getLogin(id, username, code, callback);
    }


}
