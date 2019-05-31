package com.myprojects.android_timer.main.data.newdata.repository.entity_task;

import android.os.AsyncTask;

import com.myprojects.android_timer.main.data.newdata.dao.ActionDao;
import com.myprojects.android_timer.main.data.newdata.entity.ActionEntity;

import java.util.List;

public class GetActionTableAsyncTask extends AsyncTask<Void, Void, List<ActionEntity>> {

    private ActionDao actionDao;

    public GetActionTableAsyncTask(ActionDao actionDao) {
        this.actionDao = actionDao;
    }

    @Override
    protected List<ActionEntity> doInBackground(Void... voids) {
        return actionDao.getActions();
    }
}
