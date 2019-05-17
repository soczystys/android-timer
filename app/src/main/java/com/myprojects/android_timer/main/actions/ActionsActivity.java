package com.myprojects.android_timer.main.actions;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.myprojects.android_timer.R;
import com.myprojects.android_timer.main.data.newdata.entity.ActionEntity;

import java.util.ArrayList;
import java.util.List;

public class ActionsActivity extends AppCompatActivity {
    private static final int NEW_ACTION = 229;
    private ActionsViewModel viewModel;
    private FloatingActionButton fab;
    private static ActionsActivity instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instance = this;
        setContentView(R.layout.activity_actions);
        initRecyclerView();
        iniFab();
    }

    private void iniFab() {
        fab = findViewById(R.id.fab_show_count);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addActivityIntent = new Intent(instance, ActionAddActivity.class);
                addActivityIntent.putStringArrayListExtra("LIST_OF_TITLES",
                        (ArrayList<String>) getTitlesList());
                startActivityForResult(addActivityIntent, NEW_ACTION);
            }
        });
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.activity_actions_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        final ActionsActivityAdapter adapter = new ActionsActivityAdapter();
        recyclerView.setAdapter(adapter);
        viewModel = ViewModelProviders.of(this).get(ActionsViewModel.class);
        viewModel.getAllActions().observe(this, new Observer<List<ActionEntity>>() {
            @Override
            public void onChanged(List<ActionEntity> actionEntities) {
                adapter.setList(actionEntities);
            }
        });
    }

    private List<String> getTitlesList() {
        List<String> list = new ArrayList<>();
        List<ActionEntity> actions = viewModel.getAllActions().getValue();
        for (ActionEntity actionEntity:actions) {
            list.add(actionEntity.getName());
        }
        return list;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            ActionEntity actionEntity = data.getParcelableExtra("RESULT_NEW_ACTION");
            if (actionEntity != null) {
                viewModel.insert(actionEntity);
                Snackbar.make(fab, "new item saved", Snackbar.LENGTH_LONG)
                        .setAction("ActionEntity",null).show();
            }
        }
    }
}
