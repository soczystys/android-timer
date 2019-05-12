package com.myprojects.android_timer.main.data.roomexample;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class Repository {
    private EntityDao entityDao;
    private LiveData<List<Entity>> allEntities;

    public Repository(Application application) {
        EntityDatabase database = EntityDatabase.getInstance(application);
        entityDao = database.getEntityDao();
        allEntities = entityDao.getAll();
    }

    public LiveData<List<Entity>> getAllEntities() {
        return allEntities;
    }
}
