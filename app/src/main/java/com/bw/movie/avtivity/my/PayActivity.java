package com.bw.movie.avtivity.my;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;

public class PayActivity extends BaseActivity implements View.OnClickListener {

    private ImageView pay_back;
    private TextView no_pay;
    private TextView paid;


    @Override
    protected void initView() {
        BaseActivity.doublekeydown(false);
        BaseActivity.fullScreen(PayActivity.this,true);
        setContentView(R.layout.activity_pay);
    }

    @Override
    protected void initData() {
        pay_back = findViewById(R.id.Pay_Back);
        no_pay = findViewById(R.id.No_pay);
        paid = findViewById(R.id.Paid);

        pay_back.setOnClickListener(this);
        no_pay.setOnClickListener(this);
        paid.setOnClickListener(this);
    }

    @Override
    protected void present() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.Pay_Back:
                finish();
                break;
            case R.id.No_pay:
                no_pay.setBackgroundResource(R.drawable.cinema_ed);
                no_pay.setTextColor(Color.WHITE);
                paid.setBackgroundResource(R.drawable.cinema_noed);
                paid.setTextColor(Color.BLACK);
                break;
            case R.id.Paid:
                paid.setBackgroundResource(R.drawable.cinema_ed);
                paid.setTextColor(Color.WHITE);
                no_pay.setBackgroundResource(R.drawable.cinema_noed);
                no_pay.setTextColor(Color.BLACK);
                break;
        }
    }
}
