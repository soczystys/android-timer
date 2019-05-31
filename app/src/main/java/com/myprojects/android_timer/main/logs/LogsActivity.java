package com.myprojects.android_timer.main.logs;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.myprojects.android_timer.R;

/**
 * {@link android.app.Activity} where u can see all saved
 * {@link com.myprojects.android_timer.main.data.newdata.entity.LogEntity} listed.
 */
public class LogsActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    LogListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logs);
        initRecyclerView();
    }

    private void initRecyclerView() {
        adapter = new LogListAdapter(getApplication());
        recyclerView = findViewById(R.id.recycler_logs);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
