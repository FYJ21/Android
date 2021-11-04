package com.example.culculator;

import android.app.Activity;
import android.app.Application;

import java.util.ArrayList;

public class SysApplication extends Application {
    private ArrayList<Activity> mList = new ArrayList<Activity>();
    private static SysApplication instance;

    private SysApplication(){

    }

    public synchronized static SysApplication getInstance(){
        if(null == instance){
            instance = new SysApplication();
        }
        return instance;
    }

    public void addActivity(Activity activity){
        mList.add(activity);
    }

    public void exit(){
        try{
            for(int i = 0 ; i < mList.size() ; i++){
                if(mList.get(i) != null){
                    mList.get(i).finish();
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            System.exit(0);
        }
    }

    public void onLowMemory(){
        super.onLowMemory();
        System.gc();
    }
}
