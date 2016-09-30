package com.example.dione.attendanceapp.application;

import android.app.Application;

import com.example.dione.attendanceapp.bus.BusProvider;
import com.example.dione.attendanceapp.manager.AttendanceManager;
import com.orm.SugarContext;
import com.squareup.otto.Bus;

import net.danlew.android.joda.JodaTimeAndroid;

/**
 * Created by dione on 11/08/2016.
 */
public class AttendanceApplication extends Application {
    private AttendanceManager mAttendanceManager;
    public Bus mBus = BusProvider.getInstance();
    @Override
    public void onCreate() {
        super.onCreate();
        mAttendanceManager = new AttendanceManager(this, mBus);
        mBus.register(mAttendanceManager);
        mBus.register(this);
        SugarContext.init(this);
        JodaTimeAndroid.init(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        SugarContext.terminate();
    }
}
