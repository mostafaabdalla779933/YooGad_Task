package com.example.yoogad_task.view.viewModel;


import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.yoogad_task.Repo.IRepo;
import com.example.yoogad_task.model.PostModel;
import org.jetbrains.annotations.NotNull;
import java.util.List;
import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class PostViewModel extends ViewModel {

    public MutableLiveData<List<PostModel>> postsList = new MutableLiveData<>();
    public MutableLiveData<Boolean> errorLiveData = new MutableLiveData(false);
    private CompositeDisposable compositeDisposable=new CompositeDisposable();
    private IRepo repo;

    public PostViewModel(IRepo repo) {
        this.repo = repo;
    }


    // fetch data from api
    public void fetchPosts(int start){
        repo.fetchPosts(String.valueOf(start)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new io.reactivex.Observer<List<PostModel>>() {
                    @Override
                    public void onSubscribe(@NotNull Disposable d) { compositeDisposable.add(d); }

                    @Override
                    public void onNext(@NotNull List<PostModel> postModels) {
                        cachePosts(postModels);
                        repo.putStart(start);
                    }
                    @Override
                    public void onError(@NotNull Throwable e) {
                        errorLiveData.postValue(true);
                    }
                    @Override
                    public void onComplete() {}
                });
    }


    // cache data in room
    private void cachePosts(List<PostModel> postModels){

        repo.cachePosts(postModels).subscribeOn(Schedulers.io()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(@NotNull Disposable d) {
                compositeDisposable.add(d);
            }

            @Override
            public void onComplete() { }

            @Override
            public void onError(@NotNull Throwable e) { }
        });
    }


    // get data from room
    public void  getPosts(){
        repo.getPosts().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new io.reactivex.Observer<List<PostModel>>() {
                    @Override
                    public void onSubscribe(@NotNull Disposable d) {
                        compositeDisposable.add(d);
                    }
                    @Override
                    public void onNext(@NotNull List<PostModel> postModels) {
                        postsList.postValue(postModels);
                    }
                    @Override
                    public void onError(@NotNull Throwable e) {
                    }
                    @Override
                    public void onComplete() {}
                });
    }

    public void consumeError(){
        errorLiveData.postValue(false);
    }

    public int getStart(){
        return repo.getStart();
    }

    public void clearRepo() {
        if(!compositeDisposable.isDisposed()){
            compositeDisposable.dispose();
        }
    }

}
