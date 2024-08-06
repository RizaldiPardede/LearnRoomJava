package com.example.belajarroom.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.belajarroom.database.dao.KontakDao;
import com.example.belajarroom.database.entitas.kontak;
import com.example.belajarroom.database.entitas.recycle;

@Database(entities = {kontak.class, recycle.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase sInstance;
    private final MutableLiveData<Boolean>mIsDatabaseCreated = new MutableLiveData<Boolean>();

    @VisibleForTesting
    public static final String DATABASE_NAME = "my_database";

    public abstract KontakDao kontakDao();
    private void setDatabaseCreated(){
        mIsDatabaseCreated.postValue(true);
    }

    private void updateDatabaseCreated(final Context context){
        if (context.getDatabasePath(DATABASE_NAME).exists()){
            setDatabaseCreated();
        }
    }

    public static AppDatabase buildDatabase(final  Context context){
        return Room.databaseBuilder(context, AppDatabase.class,DATABASE_NAME).addCallback(new Callback() {
            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                super.onCreate(db);
                AppDatabase database = AppDatabase.getInstance(context);
                database.setDatabaseCreated();
            }
        }).allowMainThreadQueries().fallbackToDestructiveMigration().build();

    }

    public  static AppDatabase getInstance(final Context context){
        if (sInstance== null){
            synchronized (AppDatabase.class){
                if (sInstance==null){
                    sInstance = buildDatabase(context);
                    sInstance.updateDatabaseCreated(context.getApplicationContext());
                }
            }
        }
        return sInstance;
    }



}
