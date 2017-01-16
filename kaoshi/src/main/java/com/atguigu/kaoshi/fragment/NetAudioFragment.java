package com.atguigu.kaoshi.fragment;

import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.atguigu.kaoshi.R;
import com.atguigu.kaoshi.adapter.NetAudioAdapter;
import com.atguigu.kaoshi.base.BaseFragment;
import com.cjj.MaterialRefreshLayout;

import org.xutils.view.annotation.ViewInject;

/**
 * Created by 皇 上 on 2017/1/16.
 */

public class NetAudioFragment extends BaseFragment {

    private View view;

    @ViewInject(R.id.tv_no_media)
    private ListView listview;

    @ViewInject(R.id.listview)
    private TextView tv_no_media;

    @ViewInject(R.id.refresh)
    MaterialRefreshLayout refreshLayout;

    private NetAudioAdapter adapter;



    @Override
    public View initView() {

        view = View.inflate(mContext, R.layout.fragment_net_audio, null);

        listview = (ListView) view.findViewById(R.id.listview);

        return view;
    }
}
