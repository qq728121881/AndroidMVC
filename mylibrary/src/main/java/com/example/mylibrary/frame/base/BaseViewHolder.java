package com.example.mylibrary.frame.base;

import android.content.Context;
import android.util.ArrayMap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.mylibrary.frame.util.viewcontroller.IViewControllerCommon;
import com.example.mylibrary.frame.util.viewcontroller.ViewControllerFactory;
import com.example.mylibrary.frame.util.viewlistener.IAddClickListener;

import java.util.ArrayList;
import java.util.List;

public class BaseViewHolder<M> extends RecyclerView.ViewHolder implements IAddClickListener, IViewControllerCommon {

    private int mType;

    private ViewControllerFactory mViewControllerFactory;

    private ArrayMap<Integer, Integer> mClickViews;

    private final static int CLICK = 0;
    private final static int LONG_CLICK = 1;

    public BaseViewHolder(ViewGroup parent, int res , int type) {
        super(LayoutInflater.from(parent.getContext()).inflate(res, parent, false));
        this.mType = type;
        mClickViews = new ArrayMap<>();
        mViewControllerFactory = new ViewControllerFactory(itemView, null);
    }

    public final Context getContext(){
        return itemView.getContext();
    }

    public int getType(){
        return mType;
    }

    public View getItemView(){
        return itemView;
    }

    public void setData(M data){

    }

    public void setData(int position){

    }

    @Override
    public void addClickView(int[] resIds) {
        addClickViews(resIds, CLICK);
    }

    @Override
    public void addLongClickView(int[] resIds) {
        addClickViews(resIds, LONG_CLICK);
    }

    private void addClickViews(int[] resIds, int flag){
        for(int resId : resIds){
            mClickViews.put(resId, flag);
        }
    }

    public List<View> getClickViews(){
        List<View> views = new ArrayList<>();
        try {
            for(int key : mClickViews.keySet()){
                if(CLICK == mClickViews.get(key)){
                    views.add(get(key));
                }
            }
        }catch (Exception e){
            //防止出现异常
        }
        return views;
    }

    public List<View> getLongClickViews(){
        List<View> views = new ArrayList<>();
        try {
            for(int key : mClickViews.keySet()){
                if(LONG_CLICK == mClickViews.get(key)){
                    views.add(get(key));
                }
            }
        }catch (Exception e){
            //防止出现异常
        }
        return views;
    }

    @Override
    public <T extends View> T get(int resId) {
        return (T)mViewControllerFactory.get(resId);
    }
}
