package com.atguigu.kaoshi.fragment;

import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.atguigu.kaoshi.R;
import com.atguigu.kaoshi.base.BaseFragment;

/**
 * Created by 皇 上 on 2017/1/16.
 */

public class NetAudioFragment extends BaseFragment {

    private View view;
    private TextView tv_no_media;
    private ListView listview;

    @Override
    public View initView() {

        view = View.inflate(mContext, R.layout.fragment_net_audio, null);

        listview = (ListView) view.findViewById(R.id.listview);

        return view;
    }
}
