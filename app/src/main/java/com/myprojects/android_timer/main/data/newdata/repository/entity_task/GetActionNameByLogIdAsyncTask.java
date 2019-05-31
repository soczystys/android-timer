package com.myprojects.android_timer.main.data.newdata.repository.entity_task;

import android.os.AsyncTask;

import com.myprojects.android_timer.main.data.newdata.dao.ActionDao;

public class GetActionNameByLogIdAsyncTask extends AsyncTask<Integer, Void, String> {

    private ActionDao actionDao;

    public GetActionNameByLogIdAsyncTask(ActionDao actionDao) {
        this.actionDao = actionDao;
    }

    @Override
    protected String doInBackground(Integer... integers) {
        return actionDao.getActionById(integers[0]).getName().toString();
    }
}