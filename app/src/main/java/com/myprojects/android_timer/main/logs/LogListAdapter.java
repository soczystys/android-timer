package com.myprojects.android_timer.main.logs;

import android.app.Application;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.myprojects.android_timer.R;
import com.myprojects.android_timer.main.data.newdata.entity.ActionEntity;
import com.myprojects.android_timer.main.data.newdata.entity.LogEntity;
import com.myprojects.android_timer.main.data.newdata.repository.Repository;

import java.util.List;

public class LogListAdapter extends RecyclerView.Adapter<LogListAdapter.LogHolder> {

//    List<ActionLog> logs;
    List<ActionEntity> actions;
    List<LogEntity> logs;
    Repository repository;

    public LogListAdapter(Application application) {
        repository = new Repository(application);
        this.actions = repository.getActions();
        this.logs = repository.getLogs();
    }

    @NonNull
    @Override
    public LogHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.log_item_view, viewGroup, false);
        LogListAdapter.LogHolder holder = new LogListAdapter.LogHolder (view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull LogHolder logHolder, int position) {
        final LogEntity reference = logs.get(position);
        logHolder.title.setText(repository.getActionNameByLogId(reference.getId()));

        logHolder.startDateTitle.setText("start date:");
        logHolder.startDateField.setText(reference.getStartDateAsString());
        logHolder.endDateTitle.setText("end date:");
        logHolder.endDateField.setText(reference.getEndDateAsString());
        logHolder.timeTitle.setText("total time:");
        logHolder.timeField.setText(reference.getTotalTimeAsString());
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
