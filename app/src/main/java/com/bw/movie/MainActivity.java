package com.bw.movie;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.bw.movie.base.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void initView() {
        BaseActivity.fullScreen(this,true);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void initData() {
        TextView view =findViewById(R.id.tv);
    }

    @Override
    protected void present() {

    }

}
