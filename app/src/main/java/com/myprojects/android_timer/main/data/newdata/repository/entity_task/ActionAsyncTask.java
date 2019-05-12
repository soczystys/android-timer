package com.myprojects.android_timer.main.data.newdata.repository.entity_task;

import android.os.AsyncTask;
import android.util.Log;

import com.myprojects.android_timer.main.data.newdata.dao.ActionDao;
import com.myprojects.android_timer.main.data.newdata.entity.ActionEntity;
import com.myprojects.android_timer.main.data.newdata.repository.operation_type.OperationType;

public class ActionAsyncTask extends AsyncTask<ActionEntity, Void, Void> {
    private OperationType operationType;
    private ActionDao actionDao;

    private static final String TAG = "ActionAsyncTask";

    public ActionAsyncTask(ActionDao actionDao, OperationType operationType) {
        this.operationType = operationType;
        this.actionDao = actionDao;
    }

    @Override
    protected Void doInBackground(ActionEntity... entities) {
        switch (operationType) {
            case INSERT:
                actionDao.insert(entities);
                break;
            case DELETE:
                actionDao.delete(entities);
                break;
            case UPDATE:
                actionDao.update(entities);
                break;
            case DELETE_ALL:
                actionDao.deleteAll();
        }
        Log.d(TAG, "doInBackground: done");
        return null;
    }
}
