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
    public static void save(String key, String value) {
        SharedPreferences preferences = MyApp.context.getSharedPreferences(Sp_Name, Context.MODE_PRIVATE);
        preferences.edit().putString(key, value).commit();
    }

    //    取数据
    public static String getString( String key, String defultValue) {
        return MyApp.context.getSharedPreferences(Sp_Name, Context.MODE_PRIVATE).getString(key, defultValue);
    }

    //    清空数据
    public static void cancel() {
        SharedPreferences preferences = MyApp.context.getSharedPreferences(Sp_Name, Context.MODE_PRIVATE);
        preferences.edit().clear().commit();
    }
}
