package com.example.dione.attendanceapp.event;

import com.example.dione.attendanceapp.api.response_models.Logs.Logs;

/**
 * Created by dione on 11/08/2016.
 */
public class SendLogsListRequestEvent {
    private Logs mLogs;
    public SendLogsListRequestEvent(Logs logs) {
        this.mLogs = logs;
    }
    public Logs getLogs() {
        return mLogs;
    }
}
