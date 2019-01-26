package com.bw.movie.avtivity.my;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;

public class FollowActivity extends BaseActivity {

    private ImageView Follow_back;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_follow);
    }

    @Override
    protected void initData() {
        Follow_back=findViewById(R.id.Follow_back);

        Follow_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void present() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) and run LayoutCreator again
    }
}
