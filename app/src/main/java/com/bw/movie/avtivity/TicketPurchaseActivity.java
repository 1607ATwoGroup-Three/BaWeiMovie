package com.bw.movie.avtivity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.adapter.TicketPurchaseAdapter;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.bean.Follow_YinyuanData;
import com.bw.movie.bean.NearbyCinemaData;
import com.bw.movie.contract.Contract;
import com.bw.movie.presenter.Presenter;
import com.bw.movie.utils.Interfaces;
import com.bw.movie.utils.SpBase;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.HashMap;
import java.util.Map;

/**
 * 这个页面的点击事件跳转到选择座位
 */

public class TicketPurchaseActivity extends BaseActivity implements Contract.View, View.OnClickListener {

    private TextView movie_ticket_name;
    private RecyclerView movie_ticket_recycle;
    private ImageView movie_ticket_back;
    private Presenter presenter;
    private Map<String, Object> hashmap = new HashMap<>();
    private Map<String, Object> map = new HashMap<>();

    @Override
    protected void initView() {
        BaseActivity.doublekeydown(false);
        BaseActivity.fullScreen(TicketPurchaseActivity.this,true);
        setContentView(R.layout.activity_ticket_purchase);
        movie_ticket_name =findViewById(R.id.movie_ticket_name);
        movie_ticket_recycle =findViewById(R.id.movie_ticket_recycle);
        movie_ticket_back = findViewById(R.id.movie_ticket_back);
        movie_ticket_back.setOnClickListener(this);
//        hashmap.put("userId", SpBase.getString(TicketPurchaseActivity.this,"userId", "0"));
//        hashmap.put("sessionId", SpBase.getString(TicketPurchaseActivity.this,"sessionId", ""));
//        接口访问只有不到10个数据
//        map.put("page", 1);
//        map.put("count", 100);
//        map.put("longitude",SpBase.getString(TicketPurchaseActivity.this,"lgt",""));
//        map.put("latitude",SpBase.getString(TicketPurchaseActivity.this,"lat",""));
        map.put("movieId",SpBase.getString(TicketPurchaseActivity.this,"movieId",""));
    }

    @Override
    protected void initData() {
        movie_ticket_name.setText(SpBase.getString(TicketPurchaseActivity.this,"moviename",""));
    }

    @Override
    protected void present() {
        presenter = new Presenter(this);
        presenter.get(Interfaces.QuerAyTheListOfCinemasCurrentlyInProductionAccordingToTheMovieID,hashmap,map,Follow_YinyuanData.class);
    }

    @Override
    public void success(Object success) {
        if(success instanceof Follow_YinyuanData){
            final Follow_YinyuanData  data = (Follow_YinyuanData) success;
            TicketPurchaseAdapter adapter =new TicketPurchaseAdapter(R.layout.cinema_recycle_item,data.getResult());
            movie_ticket_recycle.setLayoutManager(new LinearLayoutManager(this));
            movie_ticket_recycle.setAdapter(adapter);
            adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                    得到电影院的id name address
//                    得到电影的id(已经保存在了SpBase)
                    Intent intent =new Intent(TicketPurchaseActivity.this,TicketDetailsActivity.class);
                    intent.putExtra("Cinema_name",data.getResult().get(position).getName());
                    intent.putExtra("Cinema_address",data.getResult().get(position).getAddress());
                    intent.putExtra("Cinema_id",data.getResult().get(position).getId()+"");
//                    intent.putExtra("Movie_id",SpBase.getString(TicketPurchaseActivity.this,"movieId",""));
                    startActivity(intent);
                }
            });
        }
    }

    @Override
    public void error(String error) {
        Toast.makeText(ctx, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.movie_ticket_back:
                finish();
                break;
        }
    }
}
