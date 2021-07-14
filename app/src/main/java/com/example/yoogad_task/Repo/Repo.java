package com.example.yoogad_task.Repo;

import android.util.Log;

import com.example.yoogad_task.MyApplication;
import com.example.yoogad_task.data.local.SharedPref;
import com.example.yoogad_task.data.remote.NetworkManager;
import com.example.yoogad_task.data.local.RoomDatabase;
import com.example.yoogad_task.model.PostModel;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;

public class Repo implements IRepo {

    RoomDatabase room;
    NetworkManager networkManager;
    SharedPref sharedPref;

    public Repo(RoomDatabase room, NetworkManager networkManager, SharedPref sharedPref) {
        this.room = room;
        this.networkManager = networkManager;
        this.sharedPref = sharedPref;
    }

    @Override
    public Observable<List<PostModel>> fetchPosts(String start){
        return networkManager.getPosts(start);
    }


    @Override
    public Completable cachePosts(List<PostModel> posts){
       return room.postDao().insertPosts(posts);
    }

    @Override
    public Observable<List<PostModel>> getPosts(){
        return room.postDao().getPosts();
    }

    @Override
    public void putStart(int start){
        sharedPref.putStart(start);
    }

    @Override
    public int getStart(){
        return sharedPref.getStart();
    }

}



