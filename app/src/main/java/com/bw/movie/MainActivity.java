package com.bw.movie;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;

import com.bw.movie.avtivity.IndexActivity;
import com.bw.movie.avtivity.LoginActivity;
import com.bw.movie.base.BaseActivity;

public class MainActivity extends BaseActivity {
    private int index =3;
    private Handler handler =new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(index==0){
                Intent intent =new Intent(MainActivity.this,LoginActivity.class);
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

    }

    @Override
    protected void present() {
        handler.sendEmptyMessage(0);
    }
}
