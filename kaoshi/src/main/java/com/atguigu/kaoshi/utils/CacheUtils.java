package com.atguigu.kaoshi.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by 皇 上 on 2017/1/16.
 */

public class CacheUtils {

    /**
     * 得到缓存的文本数据
     * @param mContext
     * @param key
     * @return
     */
    public static String getString(Context mContext, String key) {
        SharedPreferences sp = mContext.getSharedPreferences("gjw",Context.MODE_PRIVATE);
        return sp.getString(key,"");
    }

    /**
     * 保存缓存数据
     * @param mContext
     * @param key
     * @param values
     */
    public static void putString(Context mContext, String key, String values) {
        SharedPreferences sp = mContext.getSharedPreferences("gjw",Context.MODE_PRIVATE);
        sp.edit().putString(key,values).commit();

    }

//    /**
//     * 得到保存播放模式
//     * @param context
//     * @param key
//     * @return
//     */
//
//    public static int getPlaymode(Context context, String key) {
//        SharedPreferences sp = context.getSharedPreferences("gjw", Context.MODE_PRIVATE);
//        return sp.getInt(key,MusicPlayerService.REPEATE_NOMAL);
//
//    }

    /**
     * 保持播放模式
     *
     * @param context
     * @param key
     * @param value
     */
    public static void setPlaymode(Context context, String key, int value) {
        SharedPreferences sp = context.getSharedPreferences("gjw", Context.MODE_PRIVATE);
        sp.edit().putInt(key,value).commit();

    }
}
