package com.atguigu.kaoshi.app;

import android.app.Application;

import com.atguigu.kaoshi.BuildConfig;


import org.xutils.x;

/**
 * 作者：尚硅谷-杨光福 on 2016/11/23 09:48
 * 微信：yangguangfu520
 * QQ号：541433511
 * 作用：代表整个应用程序，单例的
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG); // 是否输出debug日志, 开启debug会影响性能.

        // 将“12345678”替换成您申请的 APPID，申请地址： http://www.xfyun.cn
// 请勿在“ =”与 appid 之间添加任务空字符或者转义符
//        SpeechUtility.createUtility(this, SpeechConstant.APPID + "=5838f0d9");
    }
}
