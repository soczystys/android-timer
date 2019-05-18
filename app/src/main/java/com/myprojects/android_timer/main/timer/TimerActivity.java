package com.myprojects.android_timer.main.timer;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.myprojects.android_timer.R;
import com.myprojects.android_timer.main.actions.ActionsViewModel;
import com.myprojects.android_timer.main.data.newdata.entity.ActionEntity;
import com.myprojects.android_timer.main.data.newdata.entity.LogEntity;
import com.myprojects.android_timer.main.data.newdata.repository.Repository;
import com.myprojects.android_timer.main.data.olddata.DatabaseHelper;

import java.util.List;

public class TimerActivity extends AppCompatActivity {

    private static final String TAG = "TimerActivity";
    private List<ActionEntity> actions;
    Repository repository;
    private TextView count;
    private Resources resources;
    private Drawable play;
    private Drawable pause;
    private FloatingActionButton saveButton;
    private RecyclerView recyclerView;
    private ActionListAdapter adapter;
//    DatabaseHelper db;
    private ActionsViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        repository = new Repository(getApplication());
        setContentView(R.layout.activity_timer);
        initResources();
        initList();
        initRecyclerView();
        initSaveButton();
    }

    private void initSaveButton() {
        saveButton = findViewById(R.id.fab_save);
//        db = new DatabaseHelper(this);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                db.setLog(adapter.getBunchOfDataToSave());
                repository.insertLog(adapter.getBunchOfDataToSave());
            }
        });
    }

    private void initResources() {
        resources = getResources();
        count = findViewById(R.id.main_text);
        play = resources.getDrawable(R.drawable.play_no_circle, getTheme());
        pause = resources.getDrawable(R.drawable.pause_no_circle, getTheme());
    }

    private void initRecyclerView() {
        recyclerView = findViewById(R.id.recycler_main);
        adapter = new ActionListAdapter(actions, this, count, play, pause);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
    }

    private void initList() {
        viewModel = ViewModelProviders.of(this).get(ActionsViewModel.class);
        viewModel.getAllActions().observe(this, new Observer<List<ActionEntity>>() {
            @Override
            public void onChanged(List<ActionEntity> actionEntities) {
                adapter.setList(actionEntities);
            }
        });
    }
}
