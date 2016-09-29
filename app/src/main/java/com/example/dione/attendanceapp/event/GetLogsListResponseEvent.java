package com.example.dione.attendanceapp.event;

/**
 * Created by dione on 11/08/2016.
 */
public class GetLogsListResponseEvent {
    private String token;
    public GetLogsListResponseEvent(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
