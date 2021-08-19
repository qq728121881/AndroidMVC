package com.example.mylibrary.frame.mvp.presenter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mylibrary.frame.base.BaseViewHolder;
import com.example.mylibrary.frame.util.viewlistener.OnItemChildClickListener;
import com.example.mylibrary.frame.util.viewlistener.OnItemClickListener;
import com.example.mylibrary.frame.util.viewlistener.OnSingleClickListener;

import java.util.ArrayList;
import java.util.List;

public abstract class AdapterPresenter<M> extends RecyclerView.Adapter<BaseViewHolder> {

    private Context mContext;
    protected List<M> mData;
    private OnItemClickListener mOnItemClickListener;
    private OnItemChildClickListener mOnItemChildClickListener;

    public AdapterPresenter(Context context) {
        this.mContext = context;
        mData = new ArrayList<>();
    }

    public final Context getContext(){
        return mContext;
    }

    public void addOnItemClickListener(OnItemClickListener listener){
        this.mOnItemClickListener = listener;
    }

    public void addOnItemChildClickListener(OnItemChildClickListener listener){
        this.mOnItemChildClickListener = listener;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return inCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        inBindViewHolder(holder, position);
        notifyItemClickListener(holder, position);
        notifyItemChildClickListener(holder, position);
    }

    public abstract BaseViewHolder inCreateViewHolder(ViewGroup parent, int viewType);

    public void inBindViewHolder(@NonNull BaseViewHolder holder, int position){
        if (position >= mData.size()) return;
        holder.setData(getItem(position));
        holder.setData(position);
    }

    public void clearData(){
        if(mData != null){
            mData.clear();
        }
    }

    public void refreshData(){
        if(mData != null && !mData.isEmpty()){
            mData.clear();
            notifyDataSetChanged();
        }
    }

    public void setData(List<M> data){
        mData.addAll(data);
        notifyDataSetChanged();
    }

    public void resetData(List<M> data){
        mData.clear();
        mData.addAll(data);
        notifyDataSetChanged();
    }

    public void removeItem(int position){
        mData.remove(position);
        notifyDataSetChanged();
    }

    public List<M> getData() {
        return mData;
    }

    public M getItem(int position){
        if (position < 0 || position >= mData.size()) return null;
        return mData.get(position);
    }

    @Override
    public int getItemCount() {
        if(mData == null){
            return 0;
        }
        return mData.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private void notifyItemClickListener(final BaseViewHolder holder, final int position){
        if(mOnItemClickListener != null){
            holder.getItemView().setOnClickListener(new OnSingleClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onClick(holder, position);
                }
            }));
            holder.getItemView().setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mOnItemClickListener.onLongClick(holder, position);
                    return true;
                }
            });
        }
    }

    private void notifyItemChildClickListener(final BaseViewHolder holder, final int position){
        if(mOnItemChildClickListener != null){
            if(holder != null){
                List<View> clickViews = holder.getClickViews();
                for(View v : clickViews){
                    v.setOnClickListener(new OnSingleClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mOnItemChildClickListener.onChildClick(holder, position, v);
                        }
                    }));
                }

                List<View> longClickViews = holder.getLongClickViews();
                for(View v : longClickViews){
                    v.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            mOnItemChildClickListener.onChildLongClick(holder, position, v);
                            return true;
                        }
                    });
                }
            }
        }
    }
}
