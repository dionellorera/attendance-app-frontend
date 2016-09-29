package com.example.dione.attendanceapp.model;

/**
 * Created by dione on 27 Sep 2016.
 */

public class LogsModel {
    private String dateLogged;
    private String timeInLog = "";
    private String timeOutLog = "";
    public LogsModel(String dateLogged, String timeInLog, String timeOutLog){
        this.dateLogged = dateLogged;
        this.timeInLog = timeInLog;
        this.timeOutLog = timeOutLog;
    }

    public String getDateLogged() {
        return dateLogged;
    }

    public String getTimeInLog() {
        return timeInLog;
    }

    public String getTimeOutLog() {
        return timeOutLog;
    }
}
