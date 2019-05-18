package com.myprojects.android_timer.main.data.newdata.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.myprojects.android_timer.main.data.newdata.dao.ActionDao;
import com.myprojects.android_timer.main.data.newdata.dao.LogDao;
import com.myprojects.android_timer.main.data.newdata.database.AppDatabase;
import com.myprojects.android_timer.main.data.newdata.entity.ActionEntity;
import com.myprojects.android_timer.main.data.newdata.entity.LogEntity;
import com.myprojects.android_timer.main.data.newdata.repository.entity_task.ActionAsyncTask;
import com.myprojects.android_timer.main.data.newdata.repository.entity_task.GetActionTable;
import com.myprojects.android_timer.main.data.newdata.repository.entity_task.GetLogTable;
import com.myprojects.android_timer.main.data.newdata.repository.entity_task.LogAsyncTask;
import com.myprojects.android_timer.main.data.newdata.repository.entity_task.getActionNameByLogIdAsyncTask;
import com.myprojects.android_timer.main.data.newdata.repository.operation_type.OperationType;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class Repository {
    private ActionDao actionDao;
    private LogDao logDao;
    private LiveData<List<LogEntity>> allLogs;
    private LiveData<List<ActionEntity>> allActions;

    public Repository(Application application) {
        AppDatabase appDatabase = AppDatabase.getInstance(application);
        actionDao = appDatabase.getActionDao();
        logDao = appDatabase.getLogDao();
        allActions = actionDao.getAll();
        allLogs = logDao.getAll();
    }

//    public ActionEntity getActionById(int id) {
//        return actionDao.getActionById(id);

//    }

//    public LogEntity getLogById(int id) {
//        return logDao.getLogById(id);
//    }
    public String getActionNameByLogId(int id) {
        try {
            return new getActionNameByLogIdAsyncTask(actionDao).execute(id).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<ActionEntity> getActions() {
        try {
            return new GetActionTable(actionDao).execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<LogEntity> getLogs() {
        try {
            return new GetLogTable(logDao).execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void insertAction(ActionEntity... entities) {
        new ActionAsyncTask(actionDao, OperationType.INSERT).execute(entities);
    }

    public void updateAction(ActionEntity... entities) {
        new ActionAsyncTask(actionDao, OperationType.UPDATE).execute(entities);
    }

    public void deleteAction(ActionEntity... entities) {
        new ActionAsyncTask(actionDao, OperationType.DELETE).execute(entities);
    }

    public void deleteAllActions() {
        new ActionAsyncTask(actionDao, OperationType.DELETE_ALL).execute(new ActionEntity("1","1"));
    }

    public void insertLog(LogEntity... entities) {
        new LogAsyncTask(logDao, OperationType.INSERT).execute(entities);
    }

    public void updateLog(LogEntity... entities) {
        new LogAsyncTask(logDao, OperationType.UPDATE).execute(entities);
    }

    public void deleteLog(LogEntity... entities) {
        new LogAsyncTask(logDao, OperationType.DELETE).execute(entities);
    }

    public void deleteAllLogs() {
        new LogAsyncTask(logDao, OperationType.DELETE_ALL).execute(
//                new LogEntity(-1,null,null,null,null));
                new LogEntity(-1,1,
                        1,1,
                        1,1,
                        1,1,1,1));
    }

    public LiveData<List<LogEntity>> getAllLogs() {
        return allLogs;
    }

    public LiveData<List<ActionEntity>> getAllActions() {
        return allActions;
    }
}
