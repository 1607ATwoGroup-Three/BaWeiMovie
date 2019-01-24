package com.bw.movie;

import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.bw.movie.avtivity.IndexActivity;
import com.bw.movie.avtivity.LoginActivity;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.contract.Contract;

import java.util.List;

public class MainActivity extends BaseActivity {
    private int index =3;
    private int i=1;
    private List<String> list;
    private static String[] PERMISSIONS_STORAGE = {
//            读写SD卡的权限
            "android.permission.READ_EXTERNAL_STORAGE",
            "android.permission.WRITE_EXTERNAL_STORAGE",
//            联网的权限
            "android.permission.INTERNET",
//            相机
//            "android.permission.CAMERA",
//            定位
            "android.permission.ACCESS_FINE_LOCATION"};

    private Handler handler =new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(index==0){
                Intent intent =new Intent(MainActivity.this,IndexActivity.class);
                startActivity(intent);
                finish();
            }
            index--;
            handler.sendEmptyMessageDelayed(0,1000);
        }
    };
    @Override
    protected void initView() {
        BaseActivity.fullScreen(this,false);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void initData() {
        if (Build.VERSION.SDK_INT >= 23) {//判断当前系统是不是Android6.0
            requestRuntimePermissions(PERMISSIONS_STORAGE, new Contract.PermissionListener() {
                @Override
                public void granted() {
                    //权限申请通过
                    i++;
                    if(i==PERMISSIONS_STORAGE.length){
                        handler.sendEmptyMessage(0);
                    }
                }

                @Override
                public void denied(List<String> deniedList) {
                    //权限申请未通过
                    for (String denied : deniedList) {
                        if (denied.equals("android.permission.ACCESS_FINE_LOCATION")) {
                            Toast.makeText(ctx, "定位失败,请检查是否打开定位权限！", Toast.LENGTH_SHORT).show();
                        } else if(denied.equals("android.permission.READ_EXTERNAL_STORAGE")|denied.equals("android.permission.WRITE_EXTERNAL_STORAGE")){
                            Toast.makeText(ctx, "没有文件读写权限,请检查是否打开！", Toast.LENGTH_SHORT).show();
                        }else if(denied.equals("android.permission.INTERNET")){
                            Toast.makeText(ctx, "联网失败，请检查是否打开联网权限", Toast.LENGTH_SHORT).show();
                        }/*else if(denied.equals("android.permission.CAMERA")){
                            Toast.makeText(ctx, "获取相机失败，请给相机权限否则后续功能无法正常使用", Toast.LENGTH_SHORT).show();
                        }*/
                    }
                }
            });
        }else{
            handler.sendEmptyMessage(0);
        }
    }

    @Override
    protected void present() {

    }

}
