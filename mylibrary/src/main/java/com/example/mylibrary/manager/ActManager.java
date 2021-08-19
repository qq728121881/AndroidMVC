package com.example.mylibrary.manager;

import android.app.Activity;

import java.util.Stack;

public class ActManager {

    private static volatile ActManager sActManager = null;

    private ActManager(){}

    public static ActManager getInstance(){
        if(sActManager == null){
            synchronized (ActManager.class){
                if(sActManager == null){
                    sActManager = new ActManager();
                }
            }
        }
        return sActManager;
    }

    private Stack<Activity> activityStack; //保存Activity

    public void addActivity(Activity activity){
        if(activityStack == null){
            activityStack = new Stack<Activity>();
        }
        activityStack.add(activity);
    }

    //删除某一个Activity
    public void removeActivity(Activity activity){
        if(activityStack != null){
            activityStack.remove(activity);
        }
    }

    //删除所有Activity
    public void removeAllActivity(){
        if(activityStack != null ){
            while (!activityStack.empty()) {
                activityStack.pop();
            }
        }
    }

    //删除所有其他的Activity
    public void removeAllOtherActivity(){
        if(activityStack != null){
            Activity activity = activityStack.lastElement();
            removeAllActivity();
            addActivity(activity);
        }
    }

    //关闭所有Activity，并删除保存记录
    public void finishAllActivity(){
        if(activityStack != null ){
            while (!activityStack.empty()) {
                Activity activity = activityStack.pop();
                activity.finish();
            }
        }
    }

    /**
     * 结束除了当前Activity的所有Activity
     */
    public void finishAllOtherActivity(){

        if(activityStack == null){
            return;
        }

        for (int i = 0, size = activityStack.size(); i < size; i++){
            if (null != activityStack.get(i) && activityStack.lastElement() != activityStack.get(i)){
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
    }

}
