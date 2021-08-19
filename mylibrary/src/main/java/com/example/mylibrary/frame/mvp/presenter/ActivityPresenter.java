package com.example.mylibrary.frame.mvp.presenter;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;


import com.example.mylibrary.frame.base.BaseActivity;
import com.example.mylibrary.frame.mvp.model.IModel;
import com.example.mylibrary.frame.mvp.view.IView;
import com.example.mylibrary.tool.log.DLog;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

public abstract class ActivityPresenter<M extends IModel, V extends IView> extends BaseActivity implements IPresenter{

    private M m;
    private V v;
    private Reference<V> mViewRef;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            mViewRef = new WeakReference<>(getRootViewClass().newInstance());
            m = getRootModelClass().newInstance();
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
            DLog.e(e.toString());
        }

        v = mViewRef.get();
        v.createView(getLayoutInflater(), null, savedInstanceState);
        v.setActivityContext(bindActivity());
        v.bindPresenter(this);
        setContentView(v.getRootView());
        v.findViews();

        m.bindPresenter(this);

        inCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        inDestroy();
        if(m != null){
            m.onPresenterDestroy();
        }
        if(isViewAttached()){
            v.onPresenterDestroy();
        }
    }

    @Override
    public <T> void notifyData(T t) {
        onSuccess(t);
    }

    @Override
    public void error(Throwable e) {
        onError(e);
    }

    public M getModelRef() throws FrameNotReadyException{
        if(m == null){
            throw new FrameNotReadyException("ModelRef is Not Ready");
        }else {
            return m;
        }
    }

    public V getViewRef() throws FrameNotReadyException{
        if(isViewAttached()){
            return v;
        }else {
            throw new FrameNotReadyException("ViewRef is Not Ready");
        }
    }

    protected boolean isViewAttached() {
        return v != null;
    }

    public abstract Class<M> getRootModelClass();

    public abstract Class<V> getRootViewClass();

    public abstract Activity bindActivity();

    public abstract void inCreate(Bundle savedInstanceState);

    protected void inDestroy(){}

    public <T> void onSuccess(T result) {

    }

    public void onError(Throwable e){}

    protected Activity getContext() {
        return this;
    }
}
