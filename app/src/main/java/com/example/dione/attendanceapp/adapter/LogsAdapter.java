package com.example.dione.attendanceapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.dione.attendanceapp.R;
import com.example.dione.attendanceapp.model.LogsModel;

import java.util.ArrayList;

/**
 * Created by dione on 27 Sep 2016.
 */

public class LogsAdapter extends ArrayAdapter {
    private ArrayList<LogsModel> logsModelArrayList;
    private Context context;
    private ViewHolder viewHolder;
    public LogsAdapter(Context context, int resource, ArrayList<LogsModel> logsModelArrayList) {
        super(context, resource, logsModelArrayList);
        this.context = context;
        this.logsModelArrayList = logsModelArrayList;
    }
    static class ViewHolder{
        private TextView textViewDate;
        private TextView textViewTime;
        public ViewHolder(View view){
            textViewDate = (TextView) view.findViewById(R.id.textViewDate);
            textViewTime = (TextView) view.findViewById(R.id.textViewTime);
        }
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.item_row_logs, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.textViewDate.setText(logsModelArrayList.get(position).getDateLogged());
        viewHolder.textViewTime.setText(logsModelArrayList.get(position).getTimeLogged());
        return convertView;
    }
}
