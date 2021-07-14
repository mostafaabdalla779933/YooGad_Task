package com.example.yoogad_task.data.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.TypeConverters;

import com.example.yoogad_task.model.PostModel;


@Database(entities = PostModel.class, version = 1)
public abstract class RoomDatabase extends androidx.room.RoomDatabase {

    private static RoomDatabase instance;
    public abstract PostDao postDao();

    public static RoomDatabase getInstance(Context context){
        if(instance == null)
        {
            synchronized (RoomDatabase.class){
                if(instance == null)
                {
                    instance = Room.databaseBuilder(context.getApplicationContext(), RoomDatabase.class, "Posts Database")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return instance;
    }
}