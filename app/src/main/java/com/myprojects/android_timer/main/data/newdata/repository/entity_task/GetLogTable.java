package com.myprojects.android_timer.main.data.newdata.repository.entity_task;

import android.os.AsyncTask;

import com.myprojects.android_timer.main.data.newdata.dao.LogDao;
import com.myprojects.android_timer.main.data.newdata.database.AppDatabase;
import com.myprojects.android_timer.main.data.newdata.entity.LogEntity;

import java.util.List;

public class GetLogTable extends AsyncTask<Void, Void, List<LogEntity>> {

    private LogDao logDao;

    public GetLogTable(LogDao logDao) {
        this.logDao = logDao;
    }

    @Override
    protected List<LogEntity> doInBackground(Void... voids) {
        return logDao.getLogs();
    }
}
