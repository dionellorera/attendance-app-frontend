package com.example.dione.attendanceapp.event;

import com.example.dione.attendanceapp.api.models.Weather;

/**
 * Created by dione on 11/08/2016.
 */
public class SendWeatherEvent {
    private Weather mWeather;
    public SendWeatherEvent(Weather weather) {
        this.mWeather = weather;
    }
    public Weather getWeather() {
        return mWeather;
    }
}
