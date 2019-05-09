package com.myprojects.android_timer.main.chart;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.myprojects.android_timer.R;
import com.myprojects.android_timer.main.data.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class MainChartActivity extends AppCompatActivity {

    private static final String TAG = "main.chart";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_chart);
        PieChart chart = (PieChart) findViewById(R.id.pie_chart);
//        setupPieChart2(chart);
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
        Cursor res = new DatabaseHelper(this).getMainChartData();
        List<Integer> smallList = new ArrayList<>();
        List<String> smallList2 = new ArrayList<>();
        List<PieEntry> pieEntries = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (res.moveToNext()) {
            pieEntries.add(new PieEntry((float)res.getInt(1), res.getString(0)));
//            smallList.add(res.getInt(1));
//            smallList2.add(res.getString(0));
//            sb.append(res.getString(1));
//            sb.append(res.getString(0));
//            pieEntries.add(new PieEntry(smallList.get(i).floatValue(), smallList2.get(i++)));

        }

        Log.d(TAG, "onCreate: " + sb.toString());
        Log.d(TAG, "onCreate: " + smallList);
        Log.d(TAG, "onCreate: " + smallList2);
        Log.d(TAG, "entries: " + pieEntries);
        PieDataSet dataSet = new PieDataSet(pieEntries, "Diagram");
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        PieData data = new PieData(dataSet);

        PieChart chart = findViewById(R.id.pie_chart);
        chart.setData(data);
        chart.animateY(1000);
        chart.invalidate();
    }
}
