package com.example.dione.attendanceapp.event;

/**
 * Created by dione on 30 Sep 2016.
 */

public class GetTimeinResponseEvent {
    private String token;
    private String timeIn;
    private String date;

    public GetTimeinResponseEvent(String token, String timeIn, String date){
        this.token = token;
        this.timeIn = timeIn;
        this.date = date;
    }

    public String getToken() {
        return token;
    }

    public String getTimeIn() {
        return timeIn;
    }

    public String getDate() {
        return date;
    }
}
