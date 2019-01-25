package com.bw.movie.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.amap.api.maps.LocationSource;
import com.bw.movie.contract.Contract;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>文件描述：<p>
 * <p>作者：${adai}<p>
 * <p>创建时间：2019/1/23 8:10<p>
 * <p>更改时间：2019/1/23 8:10<p>
 * <p>版本号：1<p>
 */
public abstract class BaseActivity extends AppCompatActivity {
    private static Contract.PermissionListener mListener;
    private static Activity activity ;
    private long exitTime =0;
    /**
     * context
     **/
    protected Context ctx;
    /**
     * 是否输出日志信息
     **/
    private boolean isDebug;

    /**
     * 初始化界面
     **/
    protected abstract void initView();

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 绑定事件
     */
    protected abstract void present();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity =this;
        initView();
        initData();
        present();
        ctx = this;
// screenManager.setScreenRoate(isScreenRoate, this);
    }
    /**
     *   * 通过设置全屏，设置状态栏透明
     *
     * 需要在当前的布局文件中的第一个文件设置下面这行代码
     * android:fitsSystemWindows="true"
     *
     * 调用这个方法 然后 设置true 时间栏为灰色 字体为黑色
     *                     设置false 时间栏透明 字体为白色
     *   * @param blackStatusBarText 状态栏系统字体图标是否为黑色
     * <p>
     *   * @url https://blog.csdn.net/brian512/article/details/52096445
     * <p>
     *  
     */
    public static void fullScreen(Activity activity, boolean blackStatusBarText) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                //5.x开始需要把颜色设置透明，否则导航栏会呈现系统默认的浅灰色
                Window window = activity.getWindow();
                View decorView = window.getDecorView();
                //两个 flag 要结合使用，表示让应用的主体内容占用系统状态栏的空间
                int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
                //在6.0增加了View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR，这个字段就是把状态栏标记为浅色，然后状态栏的字体颜色自动转换为深色
                window.setStatusBarColor(Color.TRANSPARENT);
                if (blackStatusBarText) {
                    option = option | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
                    window.setStatusBarColor(Color.GRAY);
                }
                decorView.setSystemUiVisibility(option);
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                //导航栏颜色也可以正常设置
            //window.setNavigationBarColor(Color.TRANSPARENT);
            } else {
                Window window = activity.getWindow();
                WindowManager.LayoutParams attributes = window.getAttributes();
                int flagTranslucentStatus = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
                int flagTranslucentNavigation = WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION;
                attributes.flags |= flagTranslucentStatus;
                    // attributes.flags |= flagTranslucentNavigation;
                window.setAttributes(attributes);
            }
        }
    }
    /**
     * 申请权限
     */
    public static void requestRuntimePermissions(String[] permissions, Contract.PermissionListener listener) {
        mListener = listener;
        List<String> permissionList = new ArrayList<>();
        // 遍历每一个申请的权限，把没有通过的权限放在集合中
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(activity, permission) !=
                    PackageManager.PERMISSION_GRANTED) {
                permissionList.add(permission);
            } else {
                mListener.granted();
            }
        }
        // 申请权限
        if (!permissionList.isEmpty()) {
            ActivityCompat.requestPermissions(activity,
                    permissionList.toArray(new String[permissionList.size()]), 1);
        }
    }

    /**
     * 申请后的处理
     */
    @Override
    public void onRequestPermissionsResult(
            int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0) {
            List<String> deniedList = new ArrayList<>();
            // 遍历所有申请的权限，把被拒绝的权限放入集合
            for (int i = 0; i < grantResults.length; i++) {
                int grantResult = grantResults[i];
                if (grantResult == PackageManager.PERMISSION_GRANTED) {
                    mListener.granted();
                } else {
                    deniedList.add(permissions[i]);
                }
            }
            if (!deniedList.isEmpty()) {
                mListener.denied(deniedList);
            }
        }
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction () == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis () - exitTime) > 2000) {
                //弹出提示，可以有多种方式
                Toast.makeText (this, "再按一次退出程序", Toast.LENGTH_SHORT).show ();
                exitTime = System.currentTimeMillis ();
            } else {
                finish ();
            }
            return true;
        }
        return super.onKeyDown (keyCode, event);
    }

}