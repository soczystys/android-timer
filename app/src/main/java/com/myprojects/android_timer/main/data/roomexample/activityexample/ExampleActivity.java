package com.myprojects.android_timer.main.data.roomexample.activityexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.myprojects.android_timer.R;
import com.myprojects.android_timer.main.data.roomexample.Entity;
import com.myprojects.android_timer.main.data.roomexample.Repository;

import java.util.List;

public class ExampleActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example);
        RecyclerView recyclerView = findViewById(R.id.example_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        final ExampleAdapter adapter = new ExampleAdapter();
//        Repository repository = new Repository()
//        adapter.setList(new Repository(getApplicationContext()));
        recyclerView.setAdapter(adapter);
        ExampleViewModel viewModel = ViewModelProviders.of(this).
                get(ExampleViewModel.class);
        viewModel.getList().observe(this, new Observer<List<Entity>>() {
            @Override
            public void onChanged(List<Entity> entities) {
                adapter.setList(entities);
            }
        });
    }
}
