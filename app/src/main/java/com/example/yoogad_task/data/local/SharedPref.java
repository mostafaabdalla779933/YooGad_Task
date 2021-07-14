package com.example.yoogad_task.data.local;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.yoogad_task.MyApplication;


public class SharedPref {

    private static final String START ="start";
    private static SharedPref instance = null ;
    private final SharedPreferences shared;

    private SharedPref(){
        shared =  MyApplication.getContext().getSharedPreferences("posts", Context.MODE_MULTI_PROCESS);
    }

    public static SharedPref getInstance(){
        if(instance==null){
            synchronized (SharedPref.class){
                if(instance==null){
                    instance=new SharedPref();
                }
            }
        }
        return instance;
    }

    public void putStart(int start){
        shared.edit().putInt(START ,start).commit();
    }

    public int getStart(){
        return shared.getInt(START, 0);
    }
}
