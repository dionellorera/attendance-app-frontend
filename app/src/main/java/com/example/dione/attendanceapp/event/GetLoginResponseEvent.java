package com.example.dione.attendanceapp.event;

/**
 * Created by dione on 29 Sep 2016.
 */

public class GetLoginResponseEvent {
    private String id;
    private String username;
    private String code;
    public GetLoginResponseEvent(String id, String username, String code){
        this.id = id;
        this.username = username;
        this.code = code;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getCode() {
        return code;
    }
}
