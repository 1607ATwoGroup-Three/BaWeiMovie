package com.bw.movie.avtivity.my;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.bw.movie.R;
import com.bw.movie.avtivity.RegisterActivity;

public class UserActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView my_message_reset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        initView();
    }

    private void initView() {

        my_message_reset = (ImageView) findViewById(R.id.my_message_reset);
        my_message_reset.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        my_message_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(UserActivity.this,ResetPwdActivity.class);
                startActivity(intent);
            }
        });
    }
}
