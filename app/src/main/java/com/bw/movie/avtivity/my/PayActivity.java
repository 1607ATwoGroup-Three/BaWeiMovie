package com.bw.movie.avtivity.my;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.adapter.FollowAdapter;
import com.bw.movie.adapter.PayTicketAdapter;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.bean.Follow_DianyinData;
import com.bw.movie.bean.Follow_YinyuanData;
import com.bw.movie.bean.PayDanBean;
import com.bw.movie.bean.PayTicketJiluData;
import com.bw.movie.contract.Contract;
import com.bw.movie.presenter.Presenter;
import com.bw.movie.utils.Interfaces;
import com.bw.movie.utils.SpBase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PayActivity extends BaseActivity implements View.OnClickListener,Contract.View {

    private ImageView pay_back;
    private TextView no_pay;
    private TextView paid;
    private Presenter presenter;
    private String sessionid;
    private String userid;
    private List<PayTicketJiluData.ResultBean> mlist=new ArrayList<>();
    private RecyclerView pay_recy;
    private PayTicketAdapter adapter;

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
        pay_recy = findViewById(R.id.Pay_Recy);

        pay_back.setOnClickListener(this);
        no_pay.setOnClickListener(this);
        paid.setOnClickListener(this);

        pay_recy.setLayoutManager(new LinearLayoutManager(this));
        adapter = new PayTicketAdapter(this,mlist);
        pay_recy.setAdapter(adapter);
    }

    @Override
    protected void present() {
        presenter = new Presenter(this);

        sessionid = SpBase.getString(this, "sessionId", "");
        userid = SpBase.getString(this, "userId", "");
        Map<String, Object> headmap = new HashMap<>();
        headmap.put("userId", userid + "");
        headmap.put("sessionId", sessionid + "");
        Map<String, Object> map = new HashMap<>();
        map.put("page", 1);
        map.put("count", 100);
        map.put("status",1);
        presenter.get(Interfaces.QueryListOfUserPurchaseRecords, headmap, map, PayTicketJiluData.class);
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

                Map<String, Object> headmap = new HashMap<>();
                headmap.put("userId", userid + "");
                headmap.put("sessionId", sessionid + "");
                Map<String, Object> map = new HashMap<>();
                map.put("page", 1);
                map.put("count", 100);
                map.put("status",1);
                presenter.get(Interfaces.QueryListOfUserPurchaseRecords, headmap, map, PayTicketJiluData.class);
                break;
            case R.id.Paid:
                paid.setBackgroundResource(R.drawable.cinema_ed);
                paid.setTextColor(Color.WHITE);
                no_pay.setBackgroundResource(R.drawable.cinema_noed);
                no_pay.setTextColor(Color.BLACK);

                Map<String, Object> headmap2 = new HashMap<>();
                headmap2.put("userId", userid + "");
                headmap2.put("sessionId", sessionid + "");
                Map<String, Object> map2 = new HashMap<>();
                map2.put("page", 1);
                map2.put("count", 100);
                map2.put("status",2);
                presenter.get(Interfaces.QueryListOfUserPurchaseRecords, headmap2, map2, PayTicketJiluData.class);
                break;
        }
    }

    @Override
    public void success(Object success) {
        if (success instanceof PayTicketJiluData) {
            mlist.clear();
            PayTicketJiluData payTicketJiluData = (PayTicketJiluData) success;
            List<PayTicketJiluData.ResultBean> result = payTicketJiluData.getResult();
            for (int i = 0; i <result.size() ; i++) {
                String orderId = result.get(i).getOrderId();
                Log.e("qwe",orderId);
                SpBase.save(PayActivity.this,"orderId",orderId);
            }
            mlist.addAll(result);
            for (int i = 0; i < result.size(); i++) {
                int status = result.get(i).getStatus();
                if (status==1){
                    adapter.setType(0);
                }else if (status==2){
                    adapter.setType(1);
                }
            }
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void error(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }
}
