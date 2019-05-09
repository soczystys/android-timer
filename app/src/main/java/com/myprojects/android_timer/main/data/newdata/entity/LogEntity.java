package com.myprojects.android_timer.main.data.newdata.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "LOG_TABLE",
foreignKeys = @ForeignKey(entity = ActionEntity.class,
                            parentColumns = "ID",
                            childColumns = "ACTION_ID",
                            onDelete = CASCADE))
public class LogEntity {

    public LogEntity(int actionId, String title, String dateTimeStart, String dateTimeEnd, String timeRecorded) {
        this.actionId = actionId;
        this.title = title;
        this.dateTimeStart = dateTimeStart;
        this.dateTimeEnd = dateTimeEnd;
        this.timeRecorded = timeRecorded;
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ID")
    private int id;

//    @Index("ACTION_ID")
    @ColumnInfo(name = "ACTION_ID", index = true)
    private int actionId;

    @ColumnInfo(name = "TITLE")
    private String title;

    @ColumnInfo(name = "DATE_TIME_START")
    private String dateTimeStart;

    @ColumnInfo(name = "DATE_TIME_END")
    private String dateTimeEnd;

    @ColumnInfo(name = "TIME_RECORDED")
    private String timeRecorded;

    public int getId() {
        return id;
    }

    public int getActionId() {
        return actionId;
    }

    public String getTitle() {
        return title;
    }

    public String getDateTimeStart() {
        return dateTimeStart;
    }

    public String getDateTimeEnd() {
        return dateTimeEnd;
    }

    public String getTimeRecorded() {
        return timeRecorded;
    }

    public void setId(int id) {
        this.id = id;
    }
}
