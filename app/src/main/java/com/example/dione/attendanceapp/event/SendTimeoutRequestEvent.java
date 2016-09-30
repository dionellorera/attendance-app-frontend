package com.example.dione.attendanceapp.event;

import com.example.dione.attendanceapp.api.response_models.TimeOut.TimeOut;

/**
 * Created by dione on 30 Sep 2016.
 */

public class SendTimeoutRequestEvent {
    private TimeOut mTimeOut;
    public SendTimeoutRequestEvent(TimeOut timeOut) {
        this.mTimeOut = timeOut;
    }

    public TimeOut getmTimeOut() {
        return mTimeOut;
    }
}
