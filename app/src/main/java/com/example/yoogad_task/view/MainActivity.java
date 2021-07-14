package com.example.yoogad_task.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.View;

import com.example.yoogad_task.MyApplication;
import com.example.yoogad_task.Repo.Repo;
import com.example.yoogad_task.data.local.RoomDatabase;
import com.example.yoogad_task.data.local.SharedPref;
import com.example.yoogad_task.data.remote.NetworkManager;
import com.example.yoogad_task.view.viewModel.PostViewModel;
import com.example.yoogad_task.databinding.ActivityMainBinding;
import com.example.yoogad_task.view.viewModel.PostsViewModelFactory;
import com.google.android.material.snackbar.Snackbar;

import org.jetbrains.annotations.NotNull;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    PostViewModel postViewModel;
    PostAdapter postAdapter;
    int start  ;
    int page = 1;
    private static final int size  = NetworkManager.size;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initViewModel();

        initRecycleView();

        start  = postViewModel.getStart();

        postViewModel.getPosts();

        if (start == 0)
        postViewModel.fetchPosts(0);


        postViewModel.postsList.observe(this, postModels -> {
            postAdapter.setPostsList(postModels);
            binding.progressBar.setVisibility(View.GONE);
        });


        // observer on erroelivedata
        postViewModel.errorLiveData.observe(this, error->{
            if(error){
                Snackbar.make(findViewById(android.R.id.content), "connection faild", Snackbar.LENGTH_INDEFINITE)
                        .setAction("reload", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                postViewModel.fetchPosts(start);
                            }
                        }).show();
                postViewModel.consumeError();
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        postViewModel.clearRepo();
    }

    private void initViewModel(){
        postViewModel = new ViewModelProvider(this,
                new PostsViewModelFactory(new Repo(RoomDatabase.getInstance(MyApplication.getContext()), NetworkManager.getInstance(), SharedPref.getInstance()))).get(PostViewModel.class);
    }

    // initialize recycleview and make it support pagination
    private void initRecycleView(){

        postAdapter = new PostAdapter();

        binding.recyclerView.setAdapter(postAdapter);

        binding.recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull @NotNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull @NotNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int post = ((GridLayoutManager) Objects.requireNonNull(recyclerView.getLayoutManager())).findLastVisibleItemPosition();

                if(post  >= page * size + start -1){
                    binding.progressBar.setVisibility(View.VISIBLE);
                    start =start + size;
                    postViewModel.fetchPosts(start);
                }
            }
        });
    }
}