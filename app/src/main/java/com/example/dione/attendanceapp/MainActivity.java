package com.example.dione.attendanceapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dione.attendanceapp.application.AttendanceApplication;
import com.example.dione.attendanceapp.event.GetLoginResponseEvent;
import com.example.dione.attendanceapp.event.SendLoginRequestEvent;
import com.example.dione.attendanceapp.event.SendLogsListRequestEvent;
import com.example.dione.attendanceapp.event.SendWeatherEventError;
import com.example.dione.attendanceapp.helpers.Helpers;
import com.squareup.otto.Subscribe;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ProgressDialog progressDialog;
    private EditText editTextId;
    private EditText editTextUsername;
    private EditText editTextCode;
    private AttendanceApplication attendanceApplication;
    private Button buttonLogin;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        initialize();
    }

    private void initialize(){
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(true);
        progressDialog.setMessage(getString(R.string.message_loading));
        buttonLogin = (Button) findViewById(R.id.buttonLogin);
        buttonLogin.setOnClickListener(this);
        attendanceApplication = new AttendanceApplication();
        editTextId = (EditText) findViewById(R.id.editTextId);
        editTextUsername = (EditText) findViewById(R.id.editTextUsername);
        editTextCode = (EditText) findViewById(R.id.editTextCode);
    }

    private void sendLoginRequest(){
        progressDialog.show();
        attendanceApplication.mBus.post(new GetLoginResponseEvent(editTextId.getText().toString(), editTextUsername.getText().toString(), editTextCode.getText().toString()));
    }

    @Subscribe
    public void onSendLoginEvent(SendLoginRequestEvent sendLoginRequestEvent) {
        if (sendLoginRequestEvent.getmLogin().getMessage().equals("success")){
            Intent intent = new Intent(this, LogsActivity.class);
            intent.putExtra(Constants.TOKEN, sendLoginRequestEvent.getmLogin().getToken());
            startActivity(intent);
        }else{
            Toast.makeText(this, "Failed to Login", Toast.LENGTH_SHORT).show();
        }
        progressDialog.dismiss();
    }

    @Subscribe
    public void onSendWeatherEventError(SendWeatherEventError sendWeatherEventError) {
//        forecastTextView.setText(sendWeatherEventError.getmRetroFitError().toString());
    }


    @Override
    public void onResume() {
        super.onResume();
        attendanceApplication.mBus.register(this);
    }
    @Override
    public void onPause() {
        super.onPause();
        attendanceApplication.mBus.unregister(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonLogin:
                if (Helpers.isInputValid(editTextCode, this) && Helpers.isInputValid(editTextId, this) && Helpers.isInputValid(editTextUsername, this)){
                    sendLoginRequest();
                }
                break;
        }
    }


}
