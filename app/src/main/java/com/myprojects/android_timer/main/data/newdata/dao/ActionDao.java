package com.myprojects.android_timer.main.data.newdata.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.myprojects.android_timer.main.data.newdata.entity.ActionEntity;

import java.util.List;

@Dao
public interface ActionDao {

    @Query("SELECT * FROM ACTION_TABLE")
    LiveData<List<ActionEntity>> getAll();

    @Query("SELECT * FROM ACTION_TABLE WHERE NAME LIKE :name")
    List<ActionEntity> getAction(String name);

    @Insert
    void insert(ActionEntity... entities);

    @Delete
    void delete(ActionEntity... entities);

    @Update
    void update(ActionEntity... entities);

    @Query("DELETE FROM ACTION_TABLE")
    void deleteAll();

}
