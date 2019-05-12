package com.myprojects.android_timer.main.data.roomexample;

import androidx.room.PrimaryKey;

@androidx.room.Entity(tableName = "ENTITY_TABLE")
public class Entity {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;

    public Entity(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }
}
