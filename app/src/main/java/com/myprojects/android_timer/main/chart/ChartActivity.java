package com.myprojects.android_timer.main.chart;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.myprojects.android_timer.R;
import com.myprojects.android_timer.main.data.newdata.entity.ActionEntity;
import com.myprojects.android_timer.main.data.newdata.entity.LogEntity;
import com.myprojects.android_timer.main.data.newdata.repository.Repository;

import java.util.List;

public class ChartActivity extends AppCompatActivity {

    private static final String TAG = "ChartActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);
        Repository repository = new Repository(getApplication());
        List<ActionEntity> actionEntities = repository.getActions();
        List<LogEntity> logEntities = repository.getLogs();
        Log.d(TAG, "onCreate: actions" + actionEntities);
        Log.d(TAG, "onCreate: logs" + logEntities);
    }
}
