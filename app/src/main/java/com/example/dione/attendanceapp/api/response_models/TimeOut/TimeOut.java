package com.example.dione.attendanceapp.api.response_models.TimeOut;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by dione on 30 Sep 2016.
 */

public class TimeOut {
    @SerializedName("message")
    @Expose
    private String message;

    /**
     *
     * @return
     * The message
     */
    public String getMessage() {
        return message;
    }

    /**
     *
     * @param message
     * The message
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
