package com.example.mylibrary.frame.mvp.model;


import com.example.mylibrary.frame.mvp.presenter.IPresenter;

import java.net.ConnectException;

public class BaseModel implements IModel{

    private IPresenter mPresenter = null;
    private final Object mLock = new Object();

    @Override
    public <T> void notifySuccess(T result) {
        if(mPresenter != null){
            synchronized (mLock){
                if(mPresenter != null){
                    mPresenter.notifyData(result);
                }
            }
        }
    }

    @Override
    public void notifyError(Throwable e) {
        if(mPresenter != null){
            synchronized (mLock){
                if(mPresenter != null){
                    mPresenter.error(e);
                }
            }
        }
    }

    @Override
    public void bindPresenter(IPresenter presenter) {
        synchronized (mLock){
            this.mPresenter = presenter;
        }
    }

    @Override
    public void onPresenterDestroy() {
        mPresenter = null;
    }

    protected void throwNoNetException(){
        notifyError(new ConnectException());
    }
}
