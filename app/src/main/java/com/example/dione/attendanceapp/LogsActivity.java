package com.example.dione.attendanceapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.dione.attendanceapp.adapter.LogsAdapter;
import com.example.dione.attendanceapp.api.response_models.Logs.Result;
import com.example.dione.attendanceapp.application.AttendanceApplication;
import com.example.dione.attendanceapp.event.GetLogsListResponseEvent;
import com.example.dione.attendanceapp.event.SendLogsListRequestEvent;
import com.example.dione.attendanceapp.helpers.Helpers;
import com.example.dione.attendanceapp.model.LogsModel;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dione on 27 Sep 2016.
 */

public class LogsActivity extends AppCompatActivity {
    private ProgressDialog progressDialog;
    AttendanceApplication attendanceApplication;
    private ListView listViewLogs;
    private ArrayList<LogsModel> logsModelArrayList;
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
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(true);
        progressDialog.setMessage(getString(R.string.message_loading));
        progressDialog.show();
        attendanceApplication.mBus.post(new GetLogsListResponseEvent(token));
    }
    private void initViews(){

        listViewLogs = (ListView) findViewById(R.id.listViewLogs);
    }

    private void displayData(List<Result> resultsList){
        logsModelArrayList = new ArrayList<>();
        for (int i=0;i<resultsList.size();i++){
            logsModelArrayList.add(new LogsModel(resultsList.get(i).getDate(),
                        resultsList.get(i).getTimeIn(),
                        resultsList.get(i).getTimeOut()));
        }
        LogsAdapter logsAdapter = new LogsAdapter(this, R.layout.item_row_logs, logsModelArrayList);
        listViewLogs.setAdapter(logsAdapter);
        logsAdapter.notifyDataSetChanged();
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
        progressDialog.dismiss();
    }
}
