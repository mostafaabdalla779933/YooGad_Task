package com.example.yoogad_task.data.remote;


import com.example.yoogad_task.model.PostModel;

import java.util.List;
import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkManager {


    private static final String url="https://jsonplaceholder.typicode.com/";
    private static final String api ="posts";
    private static APIService retrofit;
    private static NetworkManager instance =null;
    public static final int size = 30;



    private NetworkManager(){
        retrofit=new Retrofit
                .Builder()
                .baseUrl(url)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(APIService.class);

    }

    public static NetworkManager getInstance(){
        if(instance==null){
            synchronized (NetworkManager.class){
                if(instance==null){
                    instance=new NetworkManager();
                }
            }
        }
        return instance;
    }


    public Observable<List<PostModel>> getPosts(String start){
      return   retrofit.getRequest(api,start,String.valueOf(size));
    }

}
