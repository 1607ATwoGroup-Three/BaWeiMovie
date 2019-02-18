package com.bw.movie.avtivity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.adapter.TicketDetailsAdapter;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.bean.MovieDetailBean;
import com.bw.movie.bean.MovieIdCinemaId;
import com.bw.movie.contract.Contract;
import com.bw.movie.presenter.Presenter;
import com.bw.movie.utils.Interfaces;
import com.bw.movie.utils.MyGlideUtil;
import com.bw.movie.utils.SpBase;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.HashMap;
import java.util.Map;

public class TicketDetailsActivity extends BaseActivity implements Contract.View {

    private TextView Cinema_ticket_name;
    private TextView Cinema_ticket_address;
    private ImageView Movie_ticket_img;
    private TextView Movie_ticket_name;
    private TextView time_ticket_text;
    private TextView Movie_ticket_type;
    private TextView Movie_ticket_director;
    private TextView Movie_ticket_long;
    private TextView Movie_ticket_address;
    private RecyclerView time_ticket_recycle;
    private Presenter presenter;
    private Map<String, Object> headmap;
    private Map<String, Object> map;
    private String moviename;
    private String cinema_name;
    private String cinema_address;

    @Override
    protected void initView() {
        BaseActivity.doublekeydown(false);
        BaseActivity.fullScreen(TicketDetailsActivity.this, true);
        setContentView(R.layout.activity_ticket_details);
        Intent intent = getIntent();
        cinema_name = intent.getStringExtra("Cinema_name");
        cinema_address = intent.getStringExtra("Cinema_address");
//        影院的ID
        String cinema_id = intent.getStringExtra("Cinema_id");
        Log.e("TicketDetailsActivity",cinema_id+"");
        SpBase.save(TicketDetailsActivity.this,"cinema_id",cinema_id+"");
        Cinema_ticket_name =findViewById(R.id.Cinema_ticket_name);
        Cinema_ticket_name.setText(cinema_name);
        Cinema_ticket_address =findViewById(R.id.Cinema_ticket_address);
        Cinema_ticket_address.setText(cinema_address);
        Movie_ticket_img =findViewById(R.id.Movie_ticket_img);
        Movie_ticket_name =findViewById(R.id.Movie_ticket_name);
        Movie_ticket_type =findViewById(R.id.Movie_ticket_type);
        Movie_ticket_director =findViewById(R.id.Movie_ticket_director);
        Movie_ticket_long =findViewById(R.id.Movie_ticket_long);
        Movie_ticket_address =findViewById(R.id.Movie_ticket_address);
        time_ticket_recycle =findViewById(R.id.time_ticket_recycle);
        time_ticket_text =findViewById(R.id.time_ticket_text);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void present() {
        presenter = new Presenter(this);
        headmap = new HashMap<>();
        map = new HashMap<>();
        headmap.put("userId", SpBase.getString(TicketDetailsActivity.this, "userId", ""));
        headmap.put("sessionId", SpBase.getString(TicketDetailsActivity.this, "sessionId", ""));
        map.put("movieId", SpBase.getString(TicketDetailsActivity.this, "movieId", ""));
        presenter.get(Interfaces.CheckOutTheDetailsOfTheMovie,headmap,map,MovieDetailBean.class);
    }

    @Override
    public void success(Object success) {
        if(success instanceof MovieDetailBean){
            MovieDetailBean bean = (MovieDetailBean) success;
            MovieDetailBean.ResultBean resultBean = bean.getResult();
            moviename = resultBean.getName();
            Movie_ticket_name.setText(resultBean.getName());
            Movie_ticket_address.setText("产地:"+resultBean.getPlaceOrigin());
            Movie_ticket_long.setText("时长:"+resultBean.getDuration());
            Movie_ticket_type.setText("类型:"+resultBean.getName());
            Movie_ticket_director.setText("导演:"+resultBean.getDirector());
            MyGlideUtil.setRoundImage(ctx,resultBean.getImageUrl(),Movie_ticket_img);
            Map<String, Object> hashmap = new HashMap<>();
            Map<String, Object> twomap = new HashMap<>();
            twomap.put("cinemasId",SpBase.getString(TicketDetailsActivity.this, "cinema_id", ""));
            twomap.put("movieId",SpBase.getString(TicketDetailsActivity.this, "movieId", ""));
            presenter.get(Interfaces.SearchMovieSchedulesBasedOnMovieIDAndCinemaID,hashmap,twomap,MovieIdCinemaId.class);
        }else if(success instanceof MovieIdCinemaId){
            final MovieIdCinemaId movieIdCinemaId = (MovieIdCinemaId) success;
            if(movieIdCinemaId.getResult().size()!=0){
                TicketDetailsAdapter adapter =new TicketDetailsAdapter(R.layout.ticket_detail_recycle_item,movieIdCinemaId.getResult());
                time_ticket_recycle.setLayoutManager(new LinearLayoutManager(TicketDetailsActivity.this));
                time_ticket_recycle.setAdapter(adapter);
                time_ticket_text.setVisibility(View.GONE);
                /**
                 * 条目点击事件
                 */
                adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        Intent intent =new Intent(TicketDetailsActivity.this,SeatSelectionActivity.class);
                        intent.putExtra("Cinema_name",cinema_name);
                        intent.putExtra("Cinema_address",cinema_address);
                        intent.putExtra("Movie_name",moviename+"");
                        intent.putExtra("Movie_ting",movieIdCinemaId.getResult().get(position).getScreeningHall());
                        intent.putExtra("Movie_price",movieIdCinemaId.getResult().get(position).getPrice()+"");
                        startActivity(intent);
                    }
                });

            }else{
                time_ticket_text.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public void error(String error) {
        Log.e("TicketDetailsActivity",error);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(presenter!=null){
            presenter.ontach();
        }
    }
}
