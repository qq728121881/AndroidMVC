package com.example.mylibrary.frame.mvp.model;


import com.example.mylibrary.frame.mvp.presenter.IPresenterLifeCycle;

public interface IModel extends IPresenterLifeCycle {

    <T> void notifySuccess(T result);

    void notifyError(Throwable e);
}
