package com.myprojects.android_timer.main.chart;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
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

/**
 * {@link android.app.Activity} for showing statistics of all {@link LogEntity} saved.
 */
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
        setupPieChart();
    }

    private void setupPieChart() {
        PieChart chart = (PieChart) findViewById(R.id.pie_chart);
        List<String> names = new ArrayList<>();
        List<Long> totalTimes = new ArrayList<>();
        Map<Integer, List<LogEntity>> map = mapAllLogEntityListGroupByActionId();
        List<Long> listOfSeconds = new ArrayList<>();
        List<String> strings = new ArrayList<>();
        CountTotalTimeOnEachActivity(map, listOfSeconds, strings);
        List<PieEntry> pieEntries = new ArrayList<>();
        for (int i = 0; i < strings.size(); i++) {
            pieEntries.add(new PieEntry(listOfSeconds.get(i), strings.get(i)));
        }
        PieDataSet dataSet = new PieDataSet(pieEntries, "Aktywności");
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        PieData data = new PieData(dataSet);
        chart.setData(data);
        chart.animateY(1200);
        chart.invalidate();
    }

    private void CountTotalTimeOnEachActivity(Map<Integer, List<LogEntity>> map, List<Long> listOfSeconds, List<String> strings) {
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
    }

    private Map<Integer, List<LogEntity>> mapAllLogEntityListGroupByActionId() {
        Map<Integer, List<LogEntity>> map = new HashMap<>();
        for (LogEntity logEntity: logEntities) {
            int key = logEntity.getActionId();
            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<LogEntity>());
            }
            map.get(key).add(logEntity);
        }
        return map;
    }
}
