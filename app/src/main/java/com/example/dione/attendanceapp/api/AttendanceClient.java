package com.example.dione.attendanceapp.api;

import com.example.dione.attendanceapp.api.interfaces.ILogin;
import com.example.dione.attendanceapp.api.interfaces.ILogs;
import com.example.dione.attendanceapp.api.interfaces.ITimeIn;
import com.example.dione.attendanceapp.api.interfaces.ITimeOut;
import com.example.dione.attendanceapp.api.response_models.Login.Login;
import com.example.dione.attendanceapp.api.response_models.Logs.Logs;
import com.example.dione.attendanceapp.api.response_models.TimeIn.TimeIn;
import com.example.dione.attendanceapp.api.response_models.TimeOut.TimeOut;
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

    public void getTimeInResponse(String token, String timeIn, String date, Callback<TimeIn> callback) {
        ITimeIn iTimeIn = mRestAdapter.create(ITimeIn.class);
        iTimeIn.getTimeIn(token, timeIn, date, callback);
    }

    public void getTimeOutResponse(String token, String timeOut, String date, Callback<TimeOut> callback) {
        ITimeOut iTimeOut = mRestAdapter.create(ITimeOut.class);
        iTimeOut.getTimeOut(token, timeOut, date, callback);
    }

}
