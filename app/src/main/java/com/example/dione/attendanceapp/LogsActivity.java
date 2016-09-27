package com.example.dione.attendanceapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.dione.attendanceapp.adapter.LogsAdapter;
import com.example.dione.attendanceapp.model.LogsModel;

import java.util.ArrayList;

/**
 * Created by dione on 27 Sep 2016.
 */

public class LogsActivity extends AppCompatActivity {
    private ListView listViewLogs;
    private ArrayList<LogsModel> logsModelArrayList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logs);
        initViews();
        addDummyData();
    }
    private void initViews(){
        listViewLogs = (ListView) findViewById(R.id.listViewLogs);
    }
    private void addDummyData(){
        logsModelArrayList = new ArrayList<>();
        for (int i=0;i<1000;i++){
            logsModelArrayList.add(new LogsModel("September 2, 2016", "12:01pm"));
        }
        displayData();
    }

    private void displayData(){
        LogsAdapter logsAdapter = new LogsAdapter(this, R.layout.item_row_logs, logsModelArrayList);
        listViewLogs.setAdapter(logsAdapter);
        logsAdapter.notifyDataSetChanged();
    }
}
