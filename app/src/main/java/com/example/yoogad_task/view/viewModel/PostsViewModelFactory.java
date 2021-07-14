package com.example.yoogad_task.view.viewModel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.yoogad_task.Repo.IRepo;

import org.jetbrains.annotations.NotNull;

public class PostsViewModelFactory implements ViewModelProvider.Factory {

    IRepo repo;
    public PostsViewModelFactory(IRepo repo){
        this.repo =repo;
    }


    @NonNull
    @NotNull
    @Override
    public <T extends ViewModel> T create(@NonNull @NotNull Class<T> modelClass) {
        return (T) new PostViewModel(repo);
    }
}

