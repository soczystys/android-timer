package com.myprojects.android_timer.main.data.roomexample;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Entity.class}, version = 1, exportSchema = false)
public abstract class EntityDatabase extends RoomDatabase {

    public abstract EntityDao getEntityDao();

    private static EntityDatabase instance;

    public static synchronized EntityDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    EntityDatabase.class, "entity_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static Callback roomCallback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new InitAsyncTask(instance).execute();
        }
    };

    private static class InitAsyncTask extends AsyncTask<Void, Void, Void> {
        private EntityDao entityDao;

        public InitAsyncTask(EntityDatabase db) {
            this.entityDao = db.getEntityDao();
        }


        @Override
        protected Void doInBackground(Void... voids) {
            entityDao.insert(new Entity("example name 1"));
            entityDao.insert(new Entity("example name 2"));
            entityDao.insert(new Entity("example name 3"));
            entityDao.insert(new Entity("example name 4"));
            entityDao.insert(new Entity("example name 5"));
            entityDao.insert(new Entity("example name 6"));
            entityDao.insert(new Entity("example name 7"));
            entityDao.insert(new Entity("example name 8"));
            entityDao.insert(new Entity("example name 9"));
            return null;
        }
    }
}
