package com.myprojects.android_timer.main.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.myprojects.android_timer.main.util.BunchOfDataToSave;

import java.util.Date;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "LOGS.db";
    private static final String TABLE_NAME = "LOGS_TABLE";
//    public static final String COL_1 = "ID";
    public static final String COL_2 = "TITLE";
    public static final String COL_3 = "DATETIME_BEGINNING";
    public static final String COL_4 = "DATETIME_END";
    public static final String COL_5 = "TIME_RECORDED";

    private static final String INIT_STATEMENT =
            "CREATE TABLE " + TABLE_NAME + "(" +
                    "ID INTEGER PRIMARY KEY AUTOINCREMENT, TITLE TEXT, " +
                    "DATETIME_BEGINNING TEXT, DATETIME_END TEXT, TIME_RECORDED TEXT)";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    public Cursor getLogs() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        return res;
    }

    public boolean setLog(BunchOfDataToSave bunchOfDataToSave) {
//        logs.add(bunchOfDataToSave);
        SQLiteDatabase db = this.getWritableDatabase();
        String time = convertTimeForSqlSyntax(bunchOfDataToSave);
        String startDate = convertDateForSqlSyntax(bunchOfDataToSave.getBeginningDate());
        String endDate = convertDateForSqlSyntax(bunchOfDataToSave.getEndDate());
//        String statement = "INSERT INTO " + TABLE_NAME +
//                "(" + COL_2 + (", ") + COL_3 + ", " + COL_4 + ", " + COL_5 + ") " +
//                "VALUES ( \'" + bunchOfDataToSave.getTitle() +
//                "\', \'" + startDate + "\', \'" + endDate + "\', \'" + time + "\'" + ")";
//        db.execSQL(statement);
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, bunchOfDataToSave.getTitle());
        contentValues.put(COL_3, startDate);
        contentValues.put(COL_4, endDate);
        contentValues.put(COL_5, time);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    private String convertDateForSqlSyntax(Date date) {
        String timeSufix;
        int hour = date.getHours();
        if (hour > 12) {
            hour -= 12;
            timeSufix = "PM";
        } else {
            timeSufix = "AM";
        }
        int month = date.getMonth() + 1;
        int year = date.getYear() + 1900;
        int day = date.getDay();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(year)
                .append(".").append(month/10).append(month%10)
                .append(".").append(day/10).append(day%10)
                .append(" ").append(hour/10).append(hour%10)
                .append(":").append(date.getMinutes()/10).append(date.getMinutes()%10)
                .append(":").append(date.getSeconds()/10).append(date.getMinutes()%10)
                .append(" ").append(timeSufix);
        return stringBuilder.toString();
    }

    private String convertTimeForSqlSyntax(BunchOfDataToSave bunchOfDataToSave) {
        StringBuilder stringBuilder = new StringBuilder();
        int hour = bunchOfDataToSave.getHour();
        int min = bunchOfDataToSave.getMin();
        int sec = bunchOfDataToSave.getSec();

        stringBuilder.append(hour/10).append(hour%10)
                .append(":").append(min/10).append(min%10)
                .append(":").append(sec/10).append(sec%10);
        return stringBuilder.toString();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(INIT_STATEMENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public static void main(String[] args) {
        Date date = new Date();
        System.out.println(date.getDay());
        System.out.println(date.getMonth());
        System.out.println(date.getYear());
    }
}
