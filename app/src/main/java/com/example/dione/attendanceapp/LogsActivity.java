package com.example.dione.attendanceapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dione.attendanceapp.adapter.LogsAdapter;
import com.example.dione.attendanceapp.api.response_models.Logs.Result;
import com.example.dione.attendanceapp.application.AttendanceApplication;
import com.example.dione.attendanceapp.event.GetLogsListResponseEvent;
import com.example.dione.attendanceapp.event.GetTimeinResponseEvent;
import com.example.dione.attendanceapp.event.GetTimeoutResponseEvent;
import com.example.dione.attendanceapp.event.SendLogsListRequestEvent;
import com.example.dione.attendanceapp.event.SendTimeinRequestEvent;
import com.example.dione.attendanceapp.event.SendTimeoutRequestEvent;
import com.example.dione.attendanceapp.helpers.Helpers;
import com.example.dione.attendanceapp.model.LogsModel;
import com.squareup.otto.Subscribe;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by dione on 27 Sep 2016.
 */

public class LogsActivity extends AppCompatActivity implements View.OnClickListener {
    private ProgressDialog progressDialog;
    AttendanceApplication attendanceApplication;
    private ListView listViewLogs;
    private TextView textViewDateToday;
    private Button buttonTimeIn;
    private ArrayList<LogsModel> logsModelArrayList;
    private LogsAdapter logsAdapter;
    private String token;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logs);
        setTitle(getString(R.string.app_title));
        getToken();
        initApi();
        initViews();
    }
    private void getToken(){
        Intent intent = getIntent();
        if (intent!=null){
            token=intent.getStringExtra(Constants.TOKEN);
        }
    }
    private void initApi(){
        attendanceApplication = new AttendanceApplication();
        sendLogsListRequest();
    }
    private void sendLogsListRequest(){
        showCancellableProgressDialog();
        attendanceApplication.mBus.post(new GetLogsListResponseEvent(token));
    }

    private void sendTimeInRequest(){
        showCancellableProgressDialog();
        attendanceApplication.mBus.post(new GetTimeinResponseEvent(token, String.valueOf(LocalTime.now()).split("\\.")[0],String.valueOf(LocalDate.now())));
    }

    private void sendTimeOutRequest(){
        showCancellableProgressDialog();
        attendanceApplication.mBus.post(new GetTimeoutResponseEvent(token, String.valueOf(LocalTime.now()).split("\\.")[0],String.valueOf(LocalDate.now())));
    }

    private void showCancellableProgressDialog(){
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(true);
        progressDialog.setMessage(getString(R.string.message_loading));
        progressDialog.show();
    }
    private void initViews(){
        listViewLogs = (ListView) findViewById(R.id.listViewLogs);
        textViewDateToday = (TextView) findViewById(R.id.textViewDateToday);
        textViewDateToday.setText("Date Today: "+String.valueOf(LocalDate.now()));
        buttonTimeIn = (Button) findViewById(R.id.buttonTimeIn);
        buttonTimeIn.setOnClickListener(this);
    }

    private void displayData(List<Result> resultsList){
        logsModelArrayList = new ArrayList<>();
        for (int i=0;i<resultsList.size();i++){
            logsModelArrayList.add(new LogsModel(resultsList.get(i).getDate().split("T")[0],
                        resultsList.get(i).getTimeIn(),
                        resultsList.get(i).getTimeOut()));
            saveLogsToLocalDatabase(resultsList.get(i).getDate().split("T")[0],
                    resultsList.get(i).getTimeIn(),
                    resultsList.get(i).getTimeOut());
        }
        logsAdapter = new LogsAdapter(this, R.layout.item_row_logs, logsModelArrayList);
        listViewLogs.setAdapter(logsAdapter);
        logsAdapter.notifyDataSetChanged();

    }

    private void saveLogsToLocalDatabase(String date, String timeIn, String timeOut){
        List<LogsModel> logsList = LogsModel.find(LogsModel.class, "date_logged = ?", date);
        if (logsList.size() > 0){
            Log.d("error_debug", "is existing");
        }else{
            LogsModel logsModel = new LogsModel(date, timeIn, timeOut);
            logsModel.save();
        }
    }

    private boolean hastimeIn(){
        boolean isExist = false;
        List<LogsModel> logsList = LogsModel.find(LogsModel.class, "date_logged = ?", String.valueOf(LocalDate.now()));
        if (logsList.size() > 0){
            isExist = true;
        }
        return isExist;
    }

    private boolean hasTimeOut(){
        boolean isExist = false;
        List<LogsModel> logsList = LogsModel.find(LogsModel.class, "date_logged = ?", String.valueOf(LocalDate.now()));
        if (logsList.size()>0){
            if (!logsList.get(0).getTimeOutLog().isEmpty()){
                isExist = true;
            }
        }

        return isExist;
    }

    private void changeButtonAction(){
        if (hasTimeOut()){
            buttonTimeIn.setText("Tomorrow :)");
        }else{
            if (hastimeIn()){
                buttonTimeIn.setText(getString(R.string.text_logout));
            }
        }
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

    @Subscribe
    public void onSendLogListEvent(SendLogsListRequestEvent sendLogsListRequestEvent) {
        displayData(sendLogsListRequestEvent.getLogs().getResult());
        Toast.makeText(this, sendLogsListRequestEvent.getLogs().getMessage(), Toast.LENGTH_SHORT).show();
        changeButtonAction();
        progressDialog.dismiss();
    }

    @Subscribe
    public void onSendTimeInEvent(SendTimeinRequestEvent sendTimeinRequestEvent) {
        logsModelArrayList.add(new LogsModel(String.valueOf(LocalDate.now()),
                String.valueOf(LocalTime.now()).split("\\.")[0],
                "00:00:00"));
        logsAdapter.notifyDataSetChanged();
        LogsModel logsModel = new LogsModel(String.valueOf(LocalDate.now()), String.valueOf(LocalTime.now()).split("\\.")[0], "");
        logsModel.save();
        changeButtonAction();
        Toast.makeText(this, sendTimeinRequestEvent.getmTimeIn().getMessage(),Toast.LENGTH_SHORT).show();
        progressDialog.dismiss();
    }

    @Subscribe
    public void onSendTimeOutEvent(SendTimeoutRequestEvent sendTimeoutRequestEvent) {
        List<LogsModel> logsModelDb = LogsModel.find(LogsModel.class, "date_logged = ?", String.valueOf(LocalDate.now()));
        LogsModel updateLogsModel = LogsModel.findById(LogsModel.class, logsModelDb.get(0).getId());
        updateLogsModel.setTimeOutLog(String.valueOf(LocalTime.now()).split("\\.")[0]);
        updateLogsModel.save();
        int index = 0;
        for(LogsModel logsModel : logsModelArrayList){
            if(logsModel.getDateLogged().equals(String.valueOf(LocalDate.now()))){
                logsModelArrayList.set(index, updateLogsModel);
                break;
            }
            index++;
        }
        logsAdapter.notifyDataSetChanged();
        changeButtonAction();
        Toast.makeText(this, sendTimeoutRequestEvent.getmTimeOut().getMessage(),Toast.LENGTH_SHORT).show();
        progressDialog.dismiss();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonTimeIn:
                if (buttonTimeIn.getText().toString().toLowerCase().equals("login")){
                    sendTimeInRequest();
                }else if(buttonTimeIn.getText().toString().toLowerCase().equals("logout")){
                    sendTimeOutRequest();
                }else{
                    Toast.makeText(this, "Come back tomorrow :D", Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }
}
