package com.bw.movie.avtivity.my;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.bean.IDUserData;
import com.bw.movie.bean.OpinionBean;
import com.bw.movie.contract.Contract;
import com.bw.movie.presenter.Presenter;
import com.bw.movie.utils.Interfaces;
import com.bw.movie.utils.SpBase;

import java.util.HashMap;
import java.util.Map;

public class OpinionActivity extends BaseActivity implements Contract.View {

    private Button Opinion_tijiao;
    private ImageView Opinion_Back;
    private Presenter presenter;
    private EditText content;
    private String scontent;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_opinion);
    }

    @Override
    protected void initData() {
        Opinion_tijiao = findViewById(R.id.Opinion_tijiao);
        Opinion_Back=findViewById(R.id.Opinion_Back);
        content = findViewById(R.id.opinion_content);

        //提交
        Opinion_tijiao.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                scontent = content.getText().toString();
                SpBase.save(OpinionActivity.this,"scontent",scontent+"");
                if (scontent.equals("")){
                    Toast.makeText(OpinionActivity.this, "内容不能为空", Toast.LENGTH_SHORT).show();
                }else{
                    String sessionid = SpBase.getString(OpinionActivity.this, "sessionId", "");
                    String userid = SpBase.getString(OpinionActivity.this, "userId", "");

                    Map<String, Object> headmap = new HashMap<>();
                    headmap.put("userId", userid + "");
                    headmap.put("sessionId", sessionid + "");
                    Map<String, Object> map = new HashMap<>();

                    map.put("content", scontent);
                    presenter.post(Interfaces.Feedback, headmap, map, OpinionBean.class);
                }
                
            }
        });
        //返回
        Opinion_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    protected void present() {

        presenter = new Presenter(this);

    }


    @Override
    public void success(Object success) {
        if(success instanceof OpinionBean){
            OpinionBean opinionBean= (OpinionBean) success;
            String message = opinionBean.getMessage();
            if(opinionBean.getStatus().equals("0000")){
                Toast.makeText(OpinionActivity.this, message, Toast.LENGTH_SHORT).show();
                Intent in = new Intent(OpinionActivity.this, OpinionFankuiActivity.class);
                startActivity(in);
                finish();
            }
        }
    }

    @Override
    public void error(String error) {
        Toast.makeText(OpinionActivity.this, error, Toast.LENGTH_SHORT).show();
    }
}
