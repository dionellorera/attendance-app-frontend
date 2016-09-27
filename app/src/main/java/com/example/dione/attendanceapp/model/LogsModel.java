package com.example.dione.attendanceapp.model;

/**
 * Created by dione on 27 Sep 2016.
 */

public class LogsModel {
    private String dateLogged;
    private String timeLogged;
    public LogsModel(String dateLogged, String timeLogged){
        this.dateLogged = dateLogged;
        this.timeLogged = timeLogged;
    }

    public String getDateLogged() {
        return dateLogged;
    }

    public String getTimeLogged() {
        return timeLogged;
    }
}
