package com.myprojects.android_timer.main.data.newdata.repository;

import android.app.Activity;
import android.app.Application;

import androidx.lifecycle.LiveData;

import com.myprojects.android_timer.main.data.newdata.dao.ActionDao;
import com.myprojects.android_timer.main.data.newdata.dao.LogDao;
import com.myprojects.android_timer.main.data.newdata.database.AppDatabase;
import com.myprojects.android_timer.main.data.newdata.entity.ActionEntity;
import com.myprojects.android_timer.main.data.newdata.entity.LogEntity;
import com.myprojects.android_timer.main.data.newdata.repository.entity_task.ActionAsyncTask;
import com.myprojects.android_timer.main.data.newdata.repository.entity_task.GetActionTableAsyncTask;
import com.myprojects.android_timer.main.data.newdata.repository.entity_task.GetLogTableAsyncTask;
import com.myprojects.android_timer.main.data.newdata.repository.entity_task.LogAsyncTask;
import com.myprojects.android_timer.main.data.newdata.repository.entity_task.GetActionNameByLogIdAsyncTask;
import com.myprojects.android_timer.main.data.newdata.repository.operation_type.OperationType;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * @see AppDatabase
 * @see ActionAsyncTask
 * @see LogAsyncTask
 * @see GetActionNameByLogIdAsyncTask
 * @see GetLogTableAsyncTask
 * @see GetActionTableAsyncTask
 * @see OperationType
 */
public class Repository {
    private ActionDao actionDao;
    private LogDao logDao;
    private LiveData<List<LogEntity>> allLogs;
    private LiveData<List<ActionEntity>> allActions;

    /**
     * Should be invoked inside {@link android.app.Activity} class because of need of the {@link Application} parameter
     * @see Activity#getApplication()
     * @param application is a parameter of an {@link android.app.Activity}
     */
    public Repository(Application application) {
        AppDatabase appDatabase = AppDatabase.getInstance(application);
        actionDao = appDatabase.getActionDao();
        logDao = appDatabase.getLogDao();
        allActions = actionDao.getAll();
        allLogs = logDao.getAll();
    }
    public String getActionNameByLogId(int id) {
        try {
            return new GetActionNameByLogIdAsyncTask(actionDao).execute(id).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @return list which doesn't track whether items have been changed during Activity, as the opposite of
     * {@link #getAllActions()}
     * @return valid list of {@link ActionEntity} in the time of this method invoke.
     */
    public List<ActionEntity> getActions() {
        try {
            return new GetActionTableAsyncTask(actionDao).execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * @return list which doesn't track whether items have been changed during Activity, as the opposite of
     * {@link #getAllLogs()}
     * @return valid list of {@link LogEntity} in the time of this method invoke.
     */
    public List<LogEntity> getLogs() {
        try {
            return new GetLogTableAsyncTask(logDao).execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * This method is required to be implemented not in the current thread.
     * @see ActionAsyncTask
     * @param entities list of {@link ActionEntity} to insert
     */
    public void insertAction(ActionEntity... entities) {
        new ActionAsyncTask(actionDao, OperationType.INSERT).execute(entities);
    }
    /**
     * This method is required to be implemented not in the current thread.
     * @see ActionAsyncTask
     * @param entities list of {@link ActionEntity} to update
     */
    public void updateAction(ActionEntity... entities) {
        new ActionAsyncTask(actionDao, OperationType.UPDATE).execute(entities);
    }
    /**
     * This method is required to be implemented not in the current thread.
     * @see ActionAsyncTask
     * @param entities list of {@link ActionEntity} to delete
     */
    public void deleteAction(ActionEntity... entities) {
        new ActionAsyncTask(actionDao, OperationType.DELETE).execute(entities);
    }

    /**
     * Deletes all {@link Repository#allActions}
     * This method is required to be implemented not in the current thread.
     * @see ActionAsyncTask
     */
    public void deleteAllActions() {
        new ActionAsyncTask(actionDao, OperationType.DELETE_ALL).execute(new ActionEntity("1","1"));
    }
    /**
     * This method is required to be implemented not in the current thread.
     * @see ActionAsyncTask
     * @param entities list of {@link LogEntity} to insert
     */
    public void insertLog(LogEntity... entities) {
        new LogAsyncTask(logDao, OperationType.INSERT).execute(entities);
    }
    /**
     * This method is required to be implemented not in the current thread.
     * @see ActionAsyncTask
     * @param entities list of {@link LogEntity} to update
     */
    public void updateLog(LogEntity... entities) {
        new LogAsyncTask(logDao, OperationType.UPDATE).execute(entities);
    }
    /**
     * This method is required to be implemented not in the current thread.
     * @see ActionAsyncTask
     * @param entities list of {@link LogEntity} to delete
     */
    public void deleteLog(LogEntity... entities) {
        new LogAsyncTask(logDao, OperationType.DELETE).execute(entities);
    }
    /**
     * Deletes all {@link Repository#allLogs}
     * This method is required to be implemented not in the current thread.
     * @see ActionAsyncTask
     */
    public void deleteAllLogs() {
        new LogAsyncTask(logDao, OperationType.DELETE_ALL).execute(
//                new LogEntity(-1,null,null,null,null));
                new LogEntity(-1,1,
                        1,1,
                        1,1,
                        1,1,1,1));
    }
    /**
     * @return {@link LiveData} this is notified when the list is changed by any means.
     * @see Repository#getAllActions()
     * @see Repository#getLogs()
     */
    public LiveData<List<LogEntity>> getAllLogs() {
        return allLogs;
    }

    /**
     * @return {@link LiveData} this is notified when the list is changed by any means.
     * @see Repository#getAllLogs()
     * @see Repository#getActions()
     */
    public LiveData<List<ActionEntity>> getAllActions() {
        return allActions;
    }
}
