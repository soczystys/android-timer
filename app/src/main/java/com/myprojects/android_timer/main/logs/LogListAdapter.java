package com.myprojects.android_timer.main.logs;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.myprojects.android_timer.R;
import com.myprojects.android_timer.main.timer.ActionListAdapter;

import java.util.List;

public class LogListAdapter extends RecyclerView.Adapter<LogListAdapter.LogHolder> {

    List<ActionLog> logs;

    public LogListAdapter(List<ActionLog> logs) {
        this.logs = logs;
    }

    @NonNull
    @Override
    public LogHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.log_item_view, viewGroup, false);
        LogListAdapter.LogHolder holder = new LogListAdapter.LogHolder (view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull LogHolder logHolder, int i) {
        final ActionLog reference = logs.get(i);
        logHolder.title.setText(reference.getTitle());
        logHolder.startDateTitle.setText("start date:");
        logHolder.startDateField.setText(reference.getBeginningDate());
        logHolder.endDateTitle.setText("end date:");
        logHolder.endDateField.setText(reference.getEndDate());
        logHolder.timeTitle.setText("total time:");
        logHolder.timeField.setText(reference.getTime());
    }

    @Override
    public int getItemCount() {
        return logs.size();
    }

    public class LogHolder extends RecyclerView.ViewHolder{

        public ConstraintLayout layout;
        public TextView title;
        public TextView timeTitle;
        public TextView timeField;
        public TextView startDateTitle;
        public TextView startDateField;
        public TextView endDateTitle;
        public TextView endDateField;

        public LogHolder (@NonNull View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.log_item_layout);
            title = itemView.findViewById(R.id.text_log_title);
            timeTitle = itemView.findViewById(R.id.text_log_time_title);
            timeField = itemView.findViewById(R.id.text_log_time_field);
            startDateTitle = itemView.findViewById(R.id.text_log_start_date_title);
            startDateField = itemView.findViewById(R.id.text_log_start_date_field);
            endDateTitle = itemView.findViewById(R.id.text_log_end_date_title);
            endDateField = itemView.findViewById(R.id.text_log_end_date_field);
        }
    }
}
