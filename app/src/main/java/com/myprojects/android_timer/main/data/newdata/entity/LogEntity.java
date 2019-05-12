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

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ID")
    private int id;

//    @Index("ACTION_ID")
    @ColumnInfo(name = "ACTION_ID", index = true)
    private int actionId;

    @ColumnInfo(name = "DATE_TIME_START_SECONDS")
    private String dateTimeStartSeconds;

    @ColumnInfo(name = "DATE_TIME_START_MINUTES")
    private String dateTimeStartMinutes;

    @ColumnInfo(name = "DATE_TIME_START_HOURS")
    private String dateTimeStartHours;

    @ColumnInfo(name = "DATE_TIME_END_SECONDS")
    private String dateTimeEndSeconds;

    @ColumnInfo(name = "DATE_TIME_END_MINUTES")
    private String dateTimeEndMinutes;

    @ColumnInfo(name = "DATE_TIME_END_HOURS")
    private String dateTimeEndHours;

    @ColumnInfo(name = "TIME_RECORDED_SECONDS")
    private String timeRecordedSeconds;

    @ColumnInfo(name = "TIME_RECORDED_MINUTES")
    private String timeRecordedMinutes;

    @ColumnInfo(name = "TIME_RECORDED_HOURS")
    private String timeRecordedHours;

    public LogEntity(int actionId, String dateTimeStartSeconds,
                     String dateTimeStartMinutes, String dateTimeStartHours,
                     String dateTimeEndSeconds, String dateTimeEndMinutes,
                     String dateTimeEndHours, String timeRecordedSeconds,
                     String timeRecordedMinutes, String timeRecordedHours) {
        this.actionId = actionId;
        this.dateTimeStartSeconds = dateTimeStartSeconds;
        this.dateTimeStartMinutes = dateTimeStartMinutes;
        this.dateTimeStartHours = dateTimeStartHours;
        this.dateTimeEndSeconds = dateTimeEndSeconds;
        this.dateTimeEndMinutes = dateTimeEndMinutes;
        this.dateTimeEndHours = dateTimeEndHours;
        this.timeRecordedSeconds = timeRecordedSeconds;
        this.timeRecordedMinutes = timeRecordedMinutes;
        this.timeRecordedHours = timeRecordedHours;
    }

    public int getId() {
        return id;
    }

    public int getActionId() {
        return actionId;
    }

    public String getDateTimeStartSeconds() {
        return dateTimeStartSeconds;
    }

    public String getDateTimeStartMinutes() {
        return dateTimeStartMinutes;
    }

    public String getDateTimeStartHours() {
        return dateTimeStartHours;
    }

    public String getDateTimeEndSeconds() {
        return dateTimeEndSeconds;
    }

    public String getDateTimeEndMinutes() {
        return dateTimeEndMinutes;
    }

    public String getDateTimeEndHours() {
        return dateTimeEndHours;
    }

    public String getTimeRecordedSeconds() {
        return timeRecordedSeconds;
    }

    public String getTimeRecordedMinutes() {
        return timeRecordedMinutes;
    }

    public String getTimeRecordedHours() {
        return timeRecordedHours;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setActionId(int actionId) {
        this.actionId = actionId;
    }

    public void setDateTimeStartSeconds(String dateTimeStartSeconds) {
        this.dateTimeStartSeconds = dateTimeStartSeconds;
    }

    public void setDateTimeStartMinutes(String dateTimeStartMinutes) {
        this.dateTimeStartMinutes = dateTimeStartMinutes;
    }

    public void setDateTimeStartHours(String dateTimeStartHours) {
        this.dateTimeStartHours = dateTimeStartHours;
    }

    public void setDateTimeEndSeconds(String dateTimeEndSeconds) {
        this.dateTimeEndSeconds = dateTimeEndSeconds;
    }

    public void setDateTimeEndMinutes(String dateTimeEndMinutes) {
        this.dateTimeEndMinutes = dateTimeEndMinutes;
    }

    public void setDateTimeEndHours(String dateTimeEndHours) {
        this.dateTimeEndHours = dateTimeEndHours;
    }

    public void setTimeRecordedSeconds(String timeRecordedSeconds) {
        this.timeRecordedSeconds = timeRecordedSeconds;
    }

    public void setTimeRecordedMinutes(String timeRecordedMinutes) {
        this.timeRecordedMinutes = timeRecordedMinutes;
    }

    public void setTimeRecordedHours(String timeRecordedHours) {
        this.timeRecordedHours = timeRecordedHours;
    }
}
