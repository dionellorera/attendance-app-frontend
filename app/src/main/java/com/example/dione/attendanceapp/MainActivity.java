package com.example.dione.attendanceapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.example.dione.attendanceapp.api.models.Currently;
import com.example.dione.attendanceapp.api.models.Weather;
import com.example.dione.attendanceapp.application.ForecastApplication;
import com.example.dione.attendanceapp.event.GetWeatherEvent;
import com.example.dione.attendanceapp.event.SendWeatherEvent;
import com.example.dione.attendanceapp.event.SendWeatherEventError;
import com.squareup.otto.Subscribe;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ForecastApplication forecastApplication;
    private Button buttonLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        initialize();
//        sendWeatherRequest();
    }

    private void initialize(){
        buttonLogin = (Button) findViewById(R.id.buttonLogin);
        buttonLogin.setOnClickListener(this);
    }

    private void sendWeatherRequest(){
//        forecastApplication = new ForecastApplication();
//        forecastApplication.mBus.post(new GetWeatherEvent(14.599512, 120.984222));
//        forecastTextView.setText("Waiting for API Response");
    }

    @Subscribe
    public void onSendWeatherEvent(SendWeatherEvent sendWeatherEvent) {
//        Weather weather = sendWeatherEvent.getWeather();
//        Currently currently = weather.getCurrently();
//        forecastTextView.setText(currently.getSummary());
    }

    @Subscribe
    public void onSendWeatherEventError(SendWeatherEventError sendWeatherEventError) {
//        forecastTextView.setText(sendWeatherEventError.getmRetroFitError().toString());
    }


    @Override
    public void onResume() {
        super.onResume();
//        forecastApplication.mBus.register(this);
    }
    @Override
    public void onPause() {
        super.onPause();
//        forecastApplication.mBus.unregister(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonLogin:
                startActivity(new Intent(this, LogsActivity.class));
                break;
            case R.id.buttonRegister:
                break;
        }
    }
}
