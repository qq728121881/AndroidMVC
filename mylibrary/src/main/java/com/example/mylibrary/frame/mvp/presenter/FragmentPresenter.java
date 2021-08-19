package com.example.mylibrary.frame.mvp.presenter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.example.mylibrary.frame.base.BaseFragment;
import com.example.mylibrary.frame.mvp.model.IModel;
import com.example.mylibrary.frame.mvp.view.IView;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

public abstract class FragmentPresenter<M extends IModel, V extends IView> extends BaseFragment implements IPresenter{

    private M m;
    private V v;
    private Reference<V> mViewRef;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            mViewRef = new WeakReference<>(getRootViewClass().newInstance());
            m = getRootModelClass().newInstance();
        } catch (InstantiationException | IllegalAccessException | java.lang.InstantiationException e) {
            e.printStackTrace();
        }

        v = mViewRef.get();
        v.bindPresenter(this);
        v.setActivityContext(getActivity());
        m.bindPresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v.createView(inflater, container, savedInstanceState);
        v.findViews();
        return v.getRootView();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        inViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroy() {
        inDestroy();
        if(isViewAttached()){
             v.onPresenterDestroy();
        }
        if(m != null){
            m.onPresenterDestroy();
        }
        super.onDestroy();
    }

    public boolean isViewAttached() {
        return v != null;
    }

    @Override
    public <T> void notifyData(T result) {
        onSuccess(result);
    }

    @Override
    public void error(Throwable e) {
        onError(e);
    }

    protected M getModelRef() throws FrameNotReadyException{
        if(m == null){
            throw new FrameNotReadyException("ModelRef is Not Ready");
        }else {
            return m;
        }
    }

    protected V getViewRef() throws FrameNotReadyException{
        if(isViewAttached()){
            return v;
        }else {
            throw new FrameNotReadyException("ViewRef is Not Ready");
        }
    }

    protected abstract Class<M> getRootModelClass();

    protected abstract Class<V> getRootViewClass();

    protected abstract void inViewCreated(View view, @Nullable Bundle savedInstanceState);

    protected void inDestroy() {

    }

    protected abstract <T> void onSuccess(T result);

    protected void onError(Throwable e){}
}
