package com.example.yoogad_task.data.remote;

import com.example.yoogad_task.model.PostModel;
import java.util.List;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface APIService {

    @GET
    Observable<List<PostModel>> getRequest(@Url String api,
                                           @Query("_start") String start,
                                           @Query("_limit") String limit);
}
