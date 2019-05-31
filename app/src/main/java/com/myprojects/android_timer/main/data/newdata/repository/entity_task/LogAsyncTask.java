package com.myprojects.android_timer.main.data.newdata.repository.entity_task;

import android.os.AsyncTask;
import com.myprojects.android_timer.main.data.newdata.dao.LogDao;
import com.myprojects.android_timer.main.data.newdata.entity.LogEntity;
import com.myprojects.android_timer.main.data.newdata.repository.operation_type.OperationType;

/**
 * Handles CRUD operations on {@link LogDao}, depending on {@link OperationType}
 */
public class LogAsyncTask extends AsyncTask<LogEntity, Void, Void> {
    private OperationType operationType;
    private LogDao logDao;

    public LogAsyncTask(LogDao logDao, OperationType operationType) {
        this.operationType = operationType;
        this.logDao = logDao;
    }

    @Override
    protected Void doInBackground(LogEntity... entities) {
        switch (operationType) {
            case INSERT:
                logDao.insert(entities);
                break;
            case DELETE:
                logDao.delete(entities);
                break;
            case UPDATE:
                logDao.update(entities);
                break;
            case DELETE_ALL:
                logDao.deleteAll();
        }
        return null;
    }
}
