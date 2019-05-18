package com.myprojects.android_timer.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import com.myprojects.android_timer.R;
import com.myprojects.android_timer.main.actions.ActionsActivity;
import com.myprojects.android_timer.main.chart.ChartActivity;
import com.myprojects.android_timer.main.logs.LogsActivity;
import com.myprojects.android_timer.main.timer.TimerActivity;

public class MainActivity extends AppCompatActivity {

    private MainActivity instance;
    private static final String ACTION_VIEW_MODEL = "ACTION_VIEW_MODEL_EXTRA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        instance = this;

        CardView fabRecord = findViewById(R.id.fab_record_time);
        fabRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), TimerActivity.class));
            }
        });

        CardView fabLogs = findViewById(R.id.fab_show_logs);
        fabLogs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), LogsActivity.class));
            }
        });

        CardView fabActions = findViewById(R.id.fab_show_actions);
        fabActions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ActionsActivity.class));
            }
        });

        CardView charts = findViewById(R.id.main_charts);
        charts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ChartActivity.class));
            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
