package com.bw.movie.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.bw.movie.appliction.MyApp;

/**
 * <p>文件描述：<p>
 * <p>作者：${adai}<p>
 * <p>创建时间：2018/12/29 20:07<p>
 * <p>更改时间：2018/12/29 20:07<p>
 * <p>版本号：1<p>
 */
public class SpBase {
    private static final String Sp_Name = "user";
    //    存数据
    public static void save(Context context,String key, String value) {
        SharedPreferences preferences = context.getSharedPreferences(Sp_Name, Context.MODE_PRIVATE);
        preferences.edit().putString(key, value).commit();
    }
    //    取数据
    public static String getString(Context context,String key, String defultValue) {
        return context.getSharedPreferences(Sp_Name, Context.MODE_PRIVATE).getString(key, defultValue);
    }

    //    删除数据
    public static void remove(Context context,String index) {
        SharedPreferences preferences =context.getSharedPreferences(Sp_Name, Context.MODE_PRIVATE);
        preferences.edit().remove(index).commit();
    }
}
