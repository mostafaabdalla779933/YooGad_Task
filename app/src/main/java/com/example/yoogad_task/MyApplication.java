package com.example.yoogad_task;

import android.app.Application;
import android.content.Context;

public class MyApplication extends Application {

    static  private Application instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static Context getContext(){
        return instance.getApplicationContext();
    }


}
