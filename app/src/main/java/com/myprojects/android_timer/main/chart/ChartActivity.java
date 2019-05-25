package com.myprojects.android_timer.main.chart;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.myprojects.android_timer.R;
import com.myprojects.android_timer.main.data.newdata.entity.ActionEntity;
import com.myprojects.android_timer.main.data.newdata.entity.LogEntity;
import com.myprojects.android_timer.main.data.newdata.repository.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChartActivity extends AppCompatActivity {

    private static final String TAG = "ChartActivity";
    private Repository repository;
    private List<LogEntity> logEntities;
    private List<ActionEntity> actionEntities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);
        repository = new Repository(getApplication());
        actionEntities = repository.getActions();
        logEntities = repository.getLogs();
        Log.d(TAG, "onCreate: actions" + actionEntities);
        Log.d(TAG, "onCreate: logs" + logEntities);

//        PieChart chart = (PieChart) findViewById(R.id.pie_chart);
        setupPieChart();
    }

    private void setupPieChart2(PieChart chart) {
        float rainfall[] = {98.8f, 123.8f, 161.6f, 24.2f, 52f,
                58.2f, 35.4f, 13.8f, 78.4f, 203.4f, 240.2f, 159.7f};

        String monthNames[] = {"Jan", "Feb", "Mar", "Apr", "May", "Jun",
                "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};

        // populating a list of Pie entries
        List<PieEntry> pieEntries = new ArrayList<>();
        for (int i = 0; i < rainfall.length; i++) {
            pieEntries.add(new PieEntry(rainfall[i], monthNames[i]));
        }

        PieDataSet dataSet = new PieDataSet(pieEntries, "Rainfall for Vancouver");
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        PieData data = new PieData(dataSet);
        Log.d(TAG, "setupPieChart: " + pieEntries);
        //get the chart

        chart.setData(data);
        chart.animateY(5000);
        chart.invalidate();
    }

    private void setupPieChart() {
        PieChart chart = (PieChart) findViewById(R.id.pie_chart);
        List<String> names = new ArrayList<>();
        List<Long> totalTimes = new ArrayList<>();
        Map<Integer, List<LogEntity>> map = new HashMap<>();
        for (LogEntity logEntity: logEntities) {
            int key = logEntity.getActionId();
            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<LogEntity>());
            }
            map.get(key).add(logEntity);
        }
        List<Long> listOfSeconds = new ArrayList<>();
        List<String> strings = new ArrayList<>();
        for (Map.Entry<Integer, List<LogEntity>> entry: map.entrySet()) {
            List<LogEntity> list = entry.getValue();
            Long longgg = new Long(0);
            for (LogEntity logEntity: list) {
                longgg += logEntity.getTimeRecordedSeconds() +
                        logEntity.getTimeRecordedMinutes() * 60 +
                        logEntity.getTimeRecordedHours() * 3600;
            }
            listOfSeconds.add(longgg);
            for (ActionEntity actionEntity: actionEntities) {
                if (actionEntity.getId() == entry.getKey() && !strings.contains(actionEntity.getName())) {
                    strings.add(actionEntity.getName());
                }
            }
        }
        List<PieEntry> pieEntries = new ArrayList<>();
        for (int i = 0; i < strings.size(); i++) {
            pieEntries.add(new PieEntry(listOfSeconds.get(i), strings.get(i)));
        }
        PieDataSet dataSet = new PieDataSet(pieEntries, "Aktywności");
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        PieData data = new PieData(dataSet);

        Log.d(TAG, "setupPieChart: strings: " + strings);
        Log.d(TAG, "setupPieChart: seconds: " + listOfSeconds);

        chart.setData(data);
        chart.animateY(1200);
        chart.invalidate();
    }
}
