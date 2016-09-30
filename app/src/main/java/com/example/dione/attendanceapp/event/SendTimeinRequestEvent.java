package com.example.dione.attendanceapp.event;

import com.example.dione.attendanceapp.api.response_models.TimeIn.TimeIn;

/**
 * Created by dione on 30 Sep 2016.
 */

public class SendTimeinRequestEvent {
    private TimeIn mTimeIn;
    public SendTimeinRequestEvent(TimeIn timeIn) {
        this.mTimeIn = timeIn;
    }

    public TimeIn getmTimeIn() {
        return mTimeIn;
    }
}
