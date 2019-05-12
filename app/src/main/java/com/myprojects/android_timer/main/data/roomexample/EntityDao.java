package com.myprojects.android_timer.main.data.roomexample;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface EntityDao {
    @Query("SELECT * FROM ENTITY_TABLE")
    LiveData<List<Entity>> getAll();

    @Query("SELECT * FROM ENTITY_TABLE WHERE name LIKE :name")
    List<Entity> getEntity(String name);

    @Insert
    void insert(Entity... entities);

    @Query("DELETE FROM ENTITY_TABLE")
    void deleteAll();
}
