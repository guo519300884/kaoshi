package com.atguigu.kaoshi.fragment;


import android.content.Intent;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.atguigu.kaoshi.R;
import com.atguigu.kaoshi.activity.ShowImageAndGifActivity;
import com.atguigu.kaoshi.adapter.NetAudioFragmentAdapter;
import com.atguigu.kaoshi.base.BaseFragment;
import com.atguigu.kaoshi.bean.NetAudioBean;
import com.atguigu.kaoshi.utils.CacheUtils;
import com.atguigu.kaoshi.utils.Constants;
import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.common.util.LogUtil;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by 皇 上 on 2017/1/16.
 */

public class NetAudioFragment extends BaseFragment {


    private static final String TAG = NetAudioFragment.class.getSimpleName();
    @Bind(R.id.listview)
    ListView listview;

    @Bind(R.id.progressbar)
    ProgressBar progressbar;

    @Bind(R.id.tv_no_media)
    TextView tvNomedia;

    @Bind(R.id.refresh)
    MaterialRefreshLayout refreshLayout;

    private boolean isLoadMore = false;

    private List<NetAudioBean.ListBean> datas;
    private NetAudioFragmentAdapter myAdapter;


    @Override
    public View initView() {
        Log.e(TAG, "网络音频UI被初始化了");
        View view = View.inflate(mContext, R.layout.fragment_net_audio, null);
//        refreshLayout = (MaterialRefreshLayout) view.findViewById(R.id.refresh);
        ButterKnife.bind(this, view);

        //监听下拉刷新和上滑加载
        refreshLayout.setMaterialRefreshListener(new MyMaterialRefreshListener());

        //设置点击事件
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                NetAudioBean.ListBean listEntity = datas.get(position);
                if (listEntity != null) {
                    //3.传递视频列表
                    Intent intent = new Intent(mContext, ShowImageAndGifActivity.class);
                    if (listEntity.getType().equals("gif")) {
                        String url = listEntity.getGif().getImages().get(0);
                        intent.putExtra("url", url);
                        mContext.startActivity(intent);
                    } else if (listEntity.getType().equals("image")) {
                        String url = listEntity.getImage().getBig().get(0);
                        intent.putExtra("url", url);
                        mContext.startActivity(intent);
                    }
                }


            }
        });

        return view;
    }

    private class MyMaterialRefreshListener extends MaterialRefreshListener {
        @Override
        public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
            Toast.makeText(mContext, "下拉刷新", Toast.LENGTH_SHORT).show();
            isLoadMore = false;

            getDataFromNet();
        }

        @Override
        public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
            super.onRefreshLoadMore(materialRefreshLayout);
            Toast.makeText(mContext, "上滑加载", Toast.LENGTH_SHORT).show();
            isLoadMore = true;

            getDataFromNet();
        }
    }


    @Override
    public void initData() {
        super.initData();
        Log.e(TAG, "网络音频数据初始化了");

        String saveJson = CacheUtils.getString(mContext, Constants.NET_AUDIO_URL);
        if (!TextUtils.isEmpty(saveJson)) {
            processData(saveJson);
        }

        getDataFromNet();

    }


    private void getDataFromNet() {
        RequestParams reques = new RequestParams(Constants.NET_AUDIO_URL);
        x.http().get(reques, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {

                CacheUtils.putString(mContext, Constants.NET_AUDIO_URL, result);
                LogUtil.e("onSuccess==" + result);
                processData(result);

                if (!isLoadMore) {
                    refreshLayout.finishRefresh();

                } else {
                    refreshLayout.finishRefreshLoadMore();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                LogUtil.e("onError==" + ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {
                LogUtil.e("onCancelled==" + cex.getMessage());
            }

            @Override
            public void onFinished() {
                LogUtil.e("onFinished==");
            }
        });
    }

    private void processData(String json) {
        if (!isLoadMore) {

            NetAudioBean netAudioBean = paraseJons(json);

            Log.e("TAG", netAudioBean.getList().get(0).getText() + "00000000000000000");

            datas = netAudioBean.getList();

            if (datas != null && datas.size() > 0) {
                //有视频
                tvNomedia.setVisibility(View.GONE);
                //设置适配器
                myAdapter = new NetAudioFragmentAdapter(mContext, datas);
                listview.setAdapter(myAdapter);
            } else {
                //没有视频
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                    tvNomedia.setVisibility(View.VISIBLE);
                }
            }
        } else {
            NetAudioBean data = paraseJons(json);
            datas.addAll(data.getList());
            myAdapter.notifyDataSetChanged();
        }

        progressbar.setVisibility(View.GONE);


    }

    /**
     * json 解析数据
     *
     * @param json
     * @return
     */
    private NetAudioBean paraseJons(String json) {
        NetAudioBean netAudioBean = new Gson().fromJson(json, NetAudioBean.class);
        return netAudioBean;

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


}