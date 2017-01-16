package com.atguigu.kaoshi.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by 皇 上 on 2017/1/16.
 */

public abstract class BaseFragment extends Fragment {

    //上下文
    public Context mContext;

    /**
     * 当系统创建当前BaseFragment类的时候回调
     * @param savedInstanceState
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    /**
     * 系统创建Fragment的视图时回调此方法
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return initView();
    }

    public abstract View initView();

    /**
     * Activity创建成功时回调此方法
     * 初始化数据
     * 联网请求数据
     * 绑定数据
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    /**
     * 当子类需要：
     * 1.联网请求网络时重写此方法
     * 2.绑定数据
     */
    public void initData(){

    };

    /**
     *
     * @param hidden
     * false 显示当前类
     * true  隐藏当前类
     */
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(!hidden) {
            onRefrshData();
        }
    }

    //当子类要刷新数据的时重写该方法
    public void onRefrshData(){

    }
}

