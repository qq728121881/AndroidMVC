package com.example.mylibrary.frame.mvp.presenter;

public interface IPresenter {

    <T> void notifyData(T result);

    void error(Throwable e);
}
