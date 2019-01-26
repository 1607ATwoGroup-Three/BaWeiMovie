package com.bw.movie.avtivity.my;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;

public class PayActivity extends BaseActivity {

    private ImageView pay_back;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_pay);
    }

    @Override
    protected void initData() {
        pay_back = findViewById(R.id.Pay_Back);

        pay_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void present() {

    }

}
