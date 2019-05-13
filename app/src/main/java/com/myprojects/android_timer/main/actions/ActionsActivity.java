package com.myprojects.android_timer.main.actions;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.myprojects.android_timer.R;
import com.myprojects.android_timer.main.data.newdata.entity.ActionEntity;

import java.util.List;

public class ActionsActivity extends AppCompatActivity {

    private ActionsViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actions);
        initRecyclerView();
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
        FloatingActionButton fab = findViewById(R.id.fab_show_count);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Count: " + adapter.getItemCount(), Snackbar.LENGTH_LONG)
                        .setAction("ActionEntity", null).show();
            }
        });
    }
}
