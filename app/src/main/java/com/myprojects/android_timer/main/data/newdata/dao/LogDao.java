package com.myprojects.android_timer.main.data.newdata.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.myprojects.android_timer.main.data.newdata.entity.LogEntity;

import java.util.List;

/**
 * @see LogEntity
 * @see com.myprojects.android_timer.main.data.newdata.database.AppDatabase
 */
@Dao
public interface LogDao {

    @Query("SELECT * FROM LOG_TABLE")
    LiveData<List<LogEntity>> getAll();

    @Query("SELECT * FROM LOG_TABLE WHERE ACTION_ID = :id")
    List<LogEntity> getAllWithActionId(int id);

    @Query("SELECT * FROM LOG_TABLE")
    List<LogEntity> getLogs();

    @Query("SELECT * FROM LOG_TABLE WHERE ID = :id")
    LogEntity getLogById(int id);

    @Insert
    void insert(LogEntity... logEntities);

    @Delete
    void delete(LogEntity... logEntities);

    @Update
    void update(LogEntity... logEntities);

    @Query("DELETE FROM LOG_TABLE")
    void deleteAll();

}
