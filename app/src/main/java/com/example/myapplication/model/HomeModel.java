package com.example.myapplication.model;

import android.text.TextUtils;
import android.util.Log;

import com.example.mylibrary.frame.mvp.model.BaseModel;
import com.example.mylibrary.net.DataManager;
import com.example.mylibrary.net.util.BaseSubscriber;

import org.jetbrains.annotations.NotNull;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HomeModel extends BaseModel {
    protected DataManager manager;
    public HomeModel() {
        manager = DataManager.getInstance();
    }

    public  void login(){
        Log.e("zzn", "increate");


        /*manager.login("1").observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(@NotNull Disposable d) {
                        Log.d("----","onSubscribe");
                    }

                    @Override
                    public void onNext(@NotNull String s) {
                        Log.d("----","onNext");
                    }

                    @Override
                    public void onError(@NotNull Throwable e) {
                        Log.d("----","onError");
                    }

                    @Override
                    public void onComplete() {
                        Log.d("----","onComplete");
                    }
                });*/
    }

    public void test(){
        Log.e("zzn", "test");


    }
}
