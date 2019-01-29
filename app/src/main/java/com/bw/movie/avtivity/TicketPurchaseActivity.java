package com.bw.movie.avtivity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.adapter.CinemaAdapter;
import com.bw.movie.adapter.TicketPurchaseAdapter;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.bean.NearbyCinemaData;
import com.bw.movie.contract.Contract;
import com.bw.movie.presenter.Presenter;
import com.bw.movie.utils.Interfaces;
import com.bw.movie.utils.SpBase;

import java.util.HashMap;
import java.util.Map;

public class TicketPurchaseActivity extends BaseActivity implements Contract.View {

    private TextView movie_ticket_name;
    private RecyclerView movie_ticket_recycle;
    private ImageView movie_ticket_back;
    private Presenter presenter;
    private Map<String, Object> hashmap = new HashMap<>();
    private Map<String, Object> map = new HashMap<>();

    @Override
    protected void initView() {
        BaseActivity.doublekeydown(false);
        setContentView(R.layout.activity_ticket_purchase);
        movie_ticket_name =findViewById(R.id.movie_ticket_name);
        movie_ticket_recycle =findViewById(R.id.movie_ticket_recycle);
        movie_ticket_back = findViewById(R.id.movie_ticket_back);
        hashmap.put("userId", SpBase.getString(TicketPurchaseActivity.this,"userId", "0"));
        hashmap.put("sessionId", SpBase.getString(TicketPurchaseActivity.this,"sessionId", ""));
//        接口访问只有不到10个数据
        map.put("page", 1);
        map.put("count", 100);
        map.put("longitude",SpBase.getString(TicketPurchaseActivity.this,"lgt",""));
        map.put("latitude",SpBase.getString(TicketPurchaseActivity.this,"lat",""));
    }

    @Override
    protected void initData() {
        movie_ticket_name.setText(SpBase.getString(TicketPurchaseActivity.this,"moviename",""));
    }

    @Override
    protected void present() {
        presenter = new Presenter(this);
        presenter.get(Interfaces.SearchForNearbyCinemas,hashmap,map,NearbyCinemaData.class);
    }

    @Override
    public void success(Object success) {
        if(success instanceof NearbyCinemaData){
            NearbyCinemaData  data = (NearbyCinemaData) success;
            TicketPurchaseAdapter adapter =new TicketPurchaseAdapter(R.layout.cinema_recycle_item,data.getResult());
            movie_ticket_recycle.setLayoutManager(new LinearLayoutManager(this));
            movie_ticket_recycle.setAdapter(adapter);
        }
    }

    @Override
    public void error(String error) {
        Toast.makeText(ctx, error, Toast.LENGTH_SHORT).show();
    }
}
