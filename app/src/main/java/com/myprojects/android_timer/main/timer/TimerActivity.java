package com.myprojects.android_timer.main.timer;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.myprojects.android_timer.R;
import com.myprojects.android_timer.main.data.newdata.entity.ActionEntity;
import com.myprojects.android_timer.main.data.newdata.repository.Repository;
import java.util.List;

/**
 * {@link android.app.Activity} for recording time
 */
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
    protected BoundService mService;
    protected boolean mBound = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        repository = new Repository(getApplication());
        setContentView(R.layout.activity_timer);
        initResources();
        initList();
        initRecyclerView();
    }

    /**
     * sets resources, count, saveButton, play and pause
     */
    private void initResources() {
        resources = getResources();
        count = findViewById(R.id.main_text);
        saveButton = findViewById(R.id.fab_save);
        play = resources.getDrawable(R.drawable.play_no_circle, getTheme());
        pause = resources.getDrawable(R.drawable.pause_no_circle, getTheme());
    }

    /**
     * sets {@link RecyclerView} and adds {@link ActionListAdapter} to it
     */
    private void initRecyclerView() {
        recyclerView = findViewById(R.id.recycler_main);
        adapter = new ActionListAdapter(actions, play, pause);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
    }

    /**
     * Loads list. Should be used before {@link android.app.Activity} is running - in methods,
     * such as {@link android.app.Activity#onCreate(Bundle)}, {@link Activity#onStart()}, {@link Activity#onResume()}
     */
    private void initList() {
        actions = repository.getActions();
    }
    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this, BoundService.class);
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    }
    @Override
    protected void onStop() {
        super.onStop();
        if (mBound) {
            unbindService(mConnection);
            mBound = false;
        }
    }
    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName className,
                                       IBinder service) {
            BoundService.LocalBinder binder = (BoundService.LocalBinder) service;
            mService = binder.getService();
            saveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    repository.insertLog(adapter.getBunchOfDataToSave());
                    mService.clear();
                }
            });
            mService.init();
            mBound = true;
            mService.setTextView(count);
            mService.setDrawable(play, pause);
            adapter.setBoundService(mService);
            adapter.updateView();
        }
        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            mBound = false;
        }
    };

}
