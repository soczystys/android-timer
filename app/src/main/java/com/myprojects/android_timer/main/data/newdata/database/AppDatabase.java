package com.myprojects.android_timer.main.data.newdata.database;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.myprojects.android_timer.main.data.newdata.dao.ActionDao;
import com.myprojects.android_timer.main.data.newdata.dao.LogDao;
import com.myprojects.android_timer.main.data.newdata.entity.ActionEntity;
import com.myprojects.android_timer.main.data.newdata.entity.LogEntity;

@Database(entities = {ActionEntity.class, LogEntity.class}, version = 6, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static final String TAG = "AppDatabase";

    public abstract LogDao getLogDao();
    public abstract ActionDao getActionDao();

    private static AppDatabase instance;

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class, "app_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        Log.d(TAG, "getInstance: done");
        return instance;
    }

    private static Callback roomCallback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };



    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        //        private NoteDao noteDao;
        private ActionDao actionDao;

        private PopulateDbAsyncTask(AppDatabase db) {
            actionDao = db.getActionDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            actionDao.insert(new ActionEntity("sport", "każda aktywność w ciągu dnia"));
            actionDao.insert(new ActionEntity("praca", "rejestruj swoją pracę i lepiej zarządzaj czasem"));
            actionDao.insert(new ActionEntity("nauka", "twoje zadania związane z nauką"));
            actionDao.insert(new ActionEntity("czytanie", "bardzo ważna umiejetność"));
            actionDao.insert(new ActionEntity("sen", "Przeciętny człowiek potrzebuje około 7h snu"));
            return null;
        }
    }
}