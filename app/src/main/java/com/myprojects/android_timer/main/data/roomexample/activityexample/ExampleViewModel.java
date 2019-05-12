package com.myprojects.android_timer.main.data.roomexample.activityexample;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.myprojects.android_timer.main.data.roomexample.Entity;
import com.myprojects.android_timer.main.data.roomexample.Repository;

import java.util.List;

public class ExampleViewModel extends AndroidViewModel {

    private Repository repository;
    private LiveData<List<Entity>> list;

    public ExampleViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
        list = repository.getAllEntities();
    }

    public LiveData<List<Entity>> getList() {
        return list;
    }
}
