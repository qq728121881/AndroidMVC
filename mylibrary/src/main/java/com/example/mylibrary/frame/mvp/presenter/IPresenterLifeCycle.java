package com.example.mylibrary.frame.mvp.presenter;

public interface IPresenterLifeCycle {

    void bindPresenter(IPresenter presenter);

    void onPresenterDestroy();
}
