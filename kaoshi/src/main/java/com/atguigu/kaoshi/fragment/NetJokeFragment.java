package com.atguigu.kaoshi.fragment;

import android.view.View;

import com.atguigu.kaoshi.R;
import com.atguigu.kaoshi.base.BaseFragment;

/**
 * Created by 皇 上 on 2017/1/16.
 */
public class NetJokeFragment extends BaseFragment {


    private View view;

    @Override
    public View initView() {

        view = View.inflate(mContext, R.layout.fragment_net_joke,null);


        return view;
    }
}
