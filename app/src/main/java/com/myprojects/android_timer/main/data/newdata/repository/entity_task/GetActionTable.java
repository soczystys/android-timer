package com.myprojects.android_timer.main.data.newdata.repository.entity_task;

import android.os.AsyncTask;

import com.myprojects.android_timer.main.data.newdata.dao.ActionDao;
import com.myprojects.android_timer.main.data.newdata.database.AppDatabase;
import com.myprojects.android_timer.main.data.newdata.entity.ActionEntity;

import java.util.List;

public class GetActionTable extends AsyncTask<Void, Void, List<ActionEntity>> {

    private ActionDao actionDao;

    public GetActionTable(ActionDao actionDao) {
        this.actionDao = actionDao;
    }

    @Override
    protected List<ActionEntity> doInBackground(Void... voids) {
        return actionDao.getActions();
    }
}
