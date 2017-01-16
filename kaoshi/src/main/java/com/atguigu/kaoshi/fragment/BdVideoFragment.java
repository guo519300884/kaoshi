package com.atguigu.kaoshi.fragment;

import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by 皇 上 on 2017/1/16.
 */
public class BdVideoFragment extends com.atguigu.kaoshi.base.BaseFragment {
    private TextView textView;
    @Override
    public View initView() {
        textView = new TextView(mContext);

        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.BLACK);
        textView.setTextSize(30);

        return this.textView;
    }

    @Override
    public void initData() {
        super.initData();
        textView.setText("本地视频");
    }
}
