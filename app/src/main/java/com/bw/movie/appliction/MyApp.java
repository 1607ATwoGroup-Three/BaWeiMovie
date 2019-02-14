package com.bw.movie.appliction;

import android.app.Activity;
import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;
import android.widget.Toast;

import java.util.Locale;

import me.jessyan.autosize.AutoSize;
import me.jessyan.autosize.AutoSizeConfig;
import me.jessyan.autosize.onAdaptListener;
import me.jessyan.autosize.utils.LogUtils;

/**
 * <p>文件描述：<p>
 * <p>作者：${adai}<p>
 * <p>创建时间：2019/1/23 8:09<p>
 * <p>更改时间：2019/1/23 8:09<p>
 * <p>版本号：1<p>
 *
 *     appliction 的生命周期
 *     https://blog.csdn.net/menglele1314/article/details/51381213
 */
public class MyApp extends Application {

    /**
     * 网络监听
     */
    private ConnectivityManager mConnectivityManager;
    private NetworkInfo netInfo;
    private BroadcastReceiver myNetReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(ConnectivityManager.CONNECTIVITY_ACTION)) {

                mConnectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                netInfo = mConnectivityManager.getActiveNetworkInfo();
                if (netInfo != null && netInfo.isAvailable()) {

                    if (netInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                        /////WiFi网络
                        Toast.makeText(context, "WiFi网络", Toast.LENGTH_SHORT).show();

                    } else if (netInfo.getType() == ConnectivityManager.TYPE_ETHERNET) {
                        /////有线网络
                        Toast.makeText(context, "有线网络", Toast.LENGTH_SHORT).show();
                    } else if (netInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                        /////////4g网络
                        Toast.makeText(context, "4g网络", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    ////////网络断开
                    Toast.makeText(context, "网络断开", Toast.LENGTH_SHORT).show();
                }
            }
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        AutoSize.initCompatMultiProcess(this);
        AutoSizeConfig.getInstance().setExcludeFontScale(true).setOnAdaptListener(new onAdaptListener() {
            @Override
            public void onAdaptBefore(Object target, Activity activity) {
                //使用以下代码, 可支持 Android 的分屏或缩放模式, 但前提是在分屏或缩放模式下当用户改变您 App 的窗口大小时
                //系统会重绘当前的页面, 经测试在某些机型, 某些情况下系统不会重绘当前页面, ScreenUtils.getScreenSize(activity) 的参数一定要不要传 Application!!!
//                        AutoSizeConfig.getInstance().setScreenWidth(ScreenUtils.getScreenSize(activity)[0]);
//                        AutoSizeConfig.getInstance().setScreenHeight(ScreenUtils.getScreenSize(activity)[1]);
                LogUtils.d(String.format(Locale.ENGLISH, "%s onAdaptBefore!", target.getClass().getName()));
            }

            @Override
            public void onAdaptAfter(Object target, Activity activity) {
                LogUtils.d(String.format(Locale.ENGLISH, "%s onAdaptAfter!", target.getClass().getName()));
            }
        }).setBaseOnWidth(false).setUseDeviceSize(true);

        IntentFilter mFilter = new IntentFilter();
        mFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(myNetReceiver, mFilter);
    }

    public static Context context;

    @Override
    public void onTerminate() {
        super.onTerminate();
        if (myNetReceiver != null) {
            unregisterReceiver(myNetReceiver);
        }
    }
}
