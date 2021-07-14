package com.example.yoogad_task.Repo;

import com.example.yoogad_task.model.PostModel;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;

public interface IRepo {
    Observable<List<PostModel>> fetchPosts(String start);

    Completable cachePosts(List<PostModel> posts);

    Observable<List<PostModel>> getPosts();

    void putStart(int start);

    int getStart();
}
