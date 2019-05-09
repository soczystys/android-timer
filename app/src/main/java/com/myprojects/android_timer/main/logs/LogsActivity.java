package com.myprojects.android_timer.main.logs;

import android.database.Cursor;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.myprojects.android_timer.R;
import com.myprojects.android_timer.main.data.olddata.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class LogsActivity extends AppCompatActivity {

    DatabaseHelper db;
    List<ActionLog> logs;
    RecyclerView recyclerView;
    LogListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new DatabaseHelper(this);
        setContentView(R.layout.activity_logs);
        initList();
        initRecyclerView();
    }

    private void initRecyclerView() {
        adapter = new LogListAdapter(logs);
        recyclerView = findViewById(R.id.recycler_logs);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initList() {
        Cursor res = db.getLogs();
        logs = new ArrayList<>();
        while(res.moveToNext()) {
            logs.add(new ActionLog(res.getString(1), res.getString(2),
                    res.getString(3), res.getString(4)));
        }
    }
}
