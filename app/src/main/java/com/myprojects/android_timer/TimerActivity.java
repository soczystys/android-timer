package com.myprojects.android_timer;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class TimerActivity extends AppCompatActivity {

    private List<RecyclerItem> actions;
    private TextView count;
    private Resources resources;
    private Drawable play;
    private Drawable pause;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        initResources();
        count = findViewById(R.id.main_text);
        initList();
        initRecyclerView();
    }

    private void initResources() {
        resources = getResources();
        play = resources.getDrawable(R.drawable.play_no_circle, getTheme());
        pause = resources.getDrawable(R.drawable.pause_no_circle, getTheme());
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recycler_main);
        ActionListAdapter adapter = new ActionListAdapter(actions, this, count, play, pause);
        recyclerView.setAdapter(adapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
    }

    private void initList() {
        actions = new ArrayList<>();
        actions.add(new RecyclerItem("sport"));
        actions.add(new RecyclerItem("praca"));
        actions.add(new RecyclerItem("nauka"));
        actions.add(new RecyclerItem("czytanie"));
        actions.add(new RecyclerItem("jedzenie"));
        actions.add(new RecyclerItem("leżenie"));
        actions.add(new RecyclerItem("myślenie"));
        actions.add(new RecyclerItem("cośtam cośtam"));
    }
}
