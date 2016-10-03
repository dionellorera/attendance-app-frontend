package com.example.dione.attendanceapp.manager;

import android.content.Context;

import com.example.dione.attendanceapp.api.AttendanceClient;
import com.example.dione.attendanceapp.api.response_models.Login.Login;
import com.example.dione.attendanceapp.api.response_models.Logs.Logs;
import com.example.dione.attendanceapp.api.response_models.TimeIn.TimeIn;
import com.example.dione.attendanceapp.api.response_models.TimeOut.TimeOut;
import com.example.dione.attendanceapp.event.GetLoginResponseEvent;
import com.example.dione.attendanceapp.event.GetLogsListResponseEvent;
import com.example.dione.attendanceapp.event.GetTimeinResponseEvent;
import com.example.dione.attendanceapp.event.GetTimeoutResponseEvent;
import com.example.dione.attendanceapp.event.SendLoginRequestEvent;
import com.example.dione.attendanceapp.event.SendLogsListRequestEvent;
import com.example.dione.attendanceapp.event.SendTimeinRequestEvent;
import com.example.dione.attendanceapp.event.SendTimeoutRequestEvent;
import com.example.dione.attendanceapp.event.SendWeatherEventError;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import retrofit.Callback;
import retrofit.RetrofitError;

/**
 * Created by dione on 11/08/2016.
 */
public class AttendanceManager {
    private Context mContext;
    private Bus mBus;
    private AttendanceClient sAttendanceClient;
    public AttendanceManager(Context context, Bus bus) {
        this.mContext = context;
        this.mBus = bus;
        sAttendanceClient = AttendanceClient.getClient();
    }

    @Subscribe
    public void onGetAttendanceList(GetLogsListResponseEvent getLogsListResponseEvent) {
        Callback<Logs> callback = new Callback<Logs>() {
            @Override
            public void success(Logs weather, retrofit.client.Response response) {
                mBus.post(new SendLogsListRequestEvent(weather));
            }

            @Override
            public void failure(RetrofitError error) {
                mBus.post(new SendWeatherEventError(error));
            }
        };
        sAttendanceClient.getLogsList(getLogsListResponseEvent.getToken(), callback);
    }

    @Subscribe
    public void onGetLoginResponse(GetLoginResponseEvent getLoginResponseEvent) {
        Callback<Login> callback = new Callback<Login>() {
            @Override
            public void success(Login login, retrofit.client.Response response) {
                mBus.post(new SendLoginRequestEvent(login));
            }
            @Override
            public void failure(RetrofitError error) {
                Login login = new Login();
                SendLoginRequestEvent sendLoginRequestEvent = new SendLoginRequestEvent(login);
                sendLoginRequestEvent.getmLogin().setMessage("Failed to Login");
                mBus.post(sendLoginRequestEvent);
            }
        };
        sAttendanceClient.getLoginResponse(getLoginResponseEvent.getId(), getLoginResponseEvent.getUsername(), getLoginResponseEvent.getCode(), callback);
    }

    @Subscribe
    public void onGetLoginResponse(GetTimeinResponseEvent getTimeinResponseEvent) {
        Callback<TimeIn> callback = new Callback<TimeIn>() {
            @Override
            public void success(TimeIn timeIn, retrofit.client.Response response) {
                mBus.post(new SendTimeinRequestEvent(timeIn));
            }

            @Override
            public void failure(RetrofitError error) {
                mBus.post(new SendWeatherEventError(error));
            }
        };
        sAttendanceClient.getTimeInResponse(getTimeinResponseEvent.getToken(), getTimeinResponseEvent.getTimeIn(), getTimeinResponseEvent.getDate(), callback);
    }

    @Subscribe
    public void onGetTimeoutResponseEvent(GetTimeoutResponseEvent getTimeoutResponseEvent) {
        Callback<TimeOut> callback = new Callback<TimeOut>() {
            @Override
            public void success(TimeOut timeOut, retrofit.client.Response response) {
                mBus.post(new SendTimeoutRequestEvent(timeOut));
            }

            @Override
            public void failure(RetrofitError error) {
                mBus.post(new SendWeatherEventError(error));
            }
        };
        sAttendanceClient.getTimeOutResponse(getTimeoutResponseEvent.getToken(), getTimeoutResponseEvent.getTimeIn(), getTimeoutResponseEvent.getDate(), callback);
    }
}
