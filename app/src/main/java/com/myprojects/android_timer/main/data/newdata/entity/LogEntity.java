package com.myprojects.android_timer.main.data.newdata.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

/**
 * Table of entities that are saved as a time recorded
 * @see ActionEntity
 */
@Entity(tableName = "LOG_TABLE",
foreignKeys = @ForeignKey(entity = ActionEntity.class,
                            parentColumns = "ID",
                            childColumns = "ACTION_ID",
                            onDelete = CASCADE))
public class LogEntity {

    public LogEntity(int actionId, int dateTimeStartYear,
                     int dateTimeStartMonth,
                     int dateTimeStartDAY,
                     int dateTimeEndYear,
                     int dateTimeEndMonth,
                     int dateTimeEndDay,
                     int timeRecordedHours,
                     int timeRecordedMinutes,
                     int timeRecordedSeconds) {
        this.actionId = actionId;
        this.dateTimeStartYear = dateTimeStartYear;
        this.dateTimeStartMonth = dateTimeStartMonth;
        this.dateTimeStartDAY = dateTimeStartDAY;
        this.dateTimeEndYear = dateTimeEndYear;
        this.dateTimeEndMonth = dateTimeEndMonth;
        this.dateTimeEndDay = dateTimeEndDay;
        this.timeRecordedHours = timeRecordedHours;
        this.timeRecordedMinutes = timeRecordedMinutes;
        this.timeRecordedSeconds = timeRecordedSeconds;
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ID")
    private int id;

    @ColumnInfo(name = "ACTION_ID", index = true)
    private int actionId;

    @ColumnInfo(name = "DATE_TIME_START_YEAR")
    private int dateTimeStartYear;

    @ColumnInfo(name = "DATE_TIME_START_MONTH")
    private int dateTimeStartMonth;

    @ColumnInfo(name = "DATE_TIME_START_DAY")
    private int dateTimeStartDAY;

    @ColumnInfo(name = "DATE_TIME_END_YEAR")
    private int dateTimeEndYear;

    @ColumnInfo(name = "DATE_TIME_END_MONTH")
    private int dateTimeEndMonth;

    @ColumnInfo(name = "DATE_TIME_END_DAY")
    private int dateTimeEndDay;

    @ColumnInfo(name = "TIME_RECORDED_HOURS")
    private int timeRecordedHours;

    @ColumnInfo(name = "TIME_RECORDED_MINUTES")
    private int timeRecordedMinutes;

    @ColumnInfo(name = "TIME_RECORDED_SECONDS")
    private int timeRecordedSeconds;

    public int getId() {
        return id;
    }

    public int getActionId() {
        return actionId;
    }

    public int getDateTimeStartYear() {
        return dateTimeStartYear;
    }

    public int getDateTimeStartMonth() {
        return dateTimeStartMonth;
    }

    public int getDateTimeStartDAY() {
        return dateTimeStartDAY;
    }

    public int getDateTimeEndYear() {
        return dateTimeEndYear;
    }

    public int getDateTimeEndMonth() {
        return dateTimeEndMonth;
    }

    public int getDateTimeEndDay() {
        return dateTimeEndDay;
    }

    public int getTimeRecordedHours() {
        return timeRecordedHours;
    }

    public int getTimeRecordedMinutes() {
        return timeRecordedMinutes;
    }

    public int getTimeRecordedSeconds() {
        return timeRecordedSeconds;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "LogEntity{" +
                "id=" + id +
                ", actionId=" + actionId +
                ", dateTimeStartYear=" + dateTimeStartYear +
                ", dateTimeStartMonth=" + dateTimeStartMonth +
                ", dateTimeStartDAY=" + dateTimeStartDAY +
                ", dateTimeEndYear=" + dateTimeEndYear +
                ", dateTimeEndMonth=" + dateTimeEndMonth +
                ", dateTimeEndDay=" + dateTimeEndDay +
                ", timeRecordedHours=" + timeRecordedHours +
                ", timeRecordedMinutes=" + timeRecordedMinutes +
                ", timeRecordedSeconds=" + timeRecordedSeconds +
                '}';
    }

    public String getStartDateAsString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(dateTimeStartYear).append("-")
                .append(dateTimeStartMonth / 10).append(dateTimeStartMonth % 10).append("-")
                .append(dateTimeStartDAY / 10).append(dateTimeStartDAY % 10);
        return stringBuilder.toString();
    }

    public String getEndDateAsString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(dateTimeEndYear).append("-")
                .append(dateTimeEndMonth / 10).append(dateTimeEndMonth % 10).append("-")
                .append(dateTimeEndDay / 10).append(dateTimeEndDay % 10);
        return stringBuilder.toString();
    }

    public String getTotalTimeAsString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(timeRecordedHours).append(":")
                .append(timeRecordedMinutes / 10).append( timeRecordedMinutes % 10).append(":")
                .append(timeRecordedSeconds / 10).append( timeRecordedSeconds % 10);
        return stringBuilder.toString();
    }
}
