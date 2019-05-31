package com.myprojects.android_timer.main.actions;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.myprojects.android_timer.main.data.newdata.entity.ActionEntity;
import com.myprojects.android_timer.main.data.newdata.repository.Repository;
import java.util.List;

/**
 * @see ActionsActivity
 * @see ActionAddActivity
 */
public class ActionsViewModel extends AndroidViewModel{

    private Repository repository;
    private LiveData<List<ActionEntity>> allActions;

    public ActionsViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
        allActions = repository.getAllActions();
    }

    public void insert(ActionEntity entity) {
        repository.insertAction(entity);
    }

    public void delete(ActionEntity entity) {
        repository.deleteAction(entity);
    }

    public void update(ActionEntity entity) {
        repository.updateAction(entity);
    }

    public void deleteAll() {
        repository.deleteAllActions();
    }

    public LiveData<List<ActionEntity>> getAllActions() {
        return allActions;
    }
}
