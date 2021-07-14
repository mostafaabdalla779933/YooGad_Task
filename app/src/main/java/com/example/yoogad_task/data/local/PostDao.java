package com.example.yoogad_task.data.local;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.example.yoogad_task.model.PostModel;
import java.util.List;
import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;


@Dao
public interface PostDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertPost(PostModel post);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertPosts(List<PostModel> posts);

    @Query("SELECT * FROM PostModel")
    Observable<List<PostModel>> getPosts();

}
