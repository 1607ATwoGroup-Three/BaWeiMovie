package com.bw.movie.avtivity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.adapter.FilmeRecycleAdapter;
import com.bw.movie.adapter.TicketDetailsAdapter;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.bean.CinemaDetailData;
import com.bw.movie.bean.FilmRecycleItemBean;
import com.bw.movie.bean.MovieIdCinemaId;
import com.bw.movie.bean.MoviesAccordingToTheCinemaData;
import com.bw.movie.contract.Contract;
import com.bw.movie.presenter.Presenter;
import com.bw.movie.utils.Interfaces;
import com.bw.movie.utils.MyGlideUtil;
import com.bw.movie.utils.SpBase;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import recycler.coverflow.CoverFlowLayoutManger;
import recycler.coverflow.RecyclerCoverFlow;

/**
 * 这是一个通过点击影院跳转到的一个新的Activity 展示电影 再跳转到 选座表
 */
public class CinemaDetailActivity extends BaseActivity implements Contract.View {

    private ImageView cinema_detail_image;
    private TextView cinema_detail_name;
    private TextView Cinema_ticket_address;
    private TextView cinema_detail_banner_text;
    private RecyclerCoverFlow cinema_detail_recycle_banner;
    private RecyclerView cinema_detail_recycle;
    private TextView cinema_detail_text;
    private Presenter presenter;
    private CinemaDetailData data1;
    private String movie_name;

    @Override
    protected void initView() {
        BaseActivity.doublekeydown(false);
        BaseActivity.fullScreen(CinemaDetailActivity.this, false);
        setContentView(R.layout.activity_cinema_detail);
        cinema_detail_image =findViewById(R.id.cinema_detail_image);
        cinema_detail_name =findViewById(R.id.cinema_detail_name);
        Cinema_ticket_address =findViewById(R.id.Cinema_ticket_address);
        cinema_detail_recycle_banner =findViewById(R.id.cinema_detail_recycle_banner);
        cinema_detail_recycle =findViewById(R.id.cinema_detail_recycle);
        cinema_detail_text =findViewById(R.id.cinema_detail_text);
        cinema_detail_banner_text =findViewById(R.id.cinema_detail_banner_text);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void present() {
        presenter = new Presenter(this);
        Map<String,Object> hashMap =new HashMap<>();
        hashMap.put("userId",SpBase.getString(CinemaDetailActivity.this,"userId",""));
        hashMap.put("sessionId",SpBase.getString(CinemaDetailActivity.this,"sessionId",""));
        Map<String,Object> Map =new HashMap<>();
        Map.put("cinemaId",SpBase.getString(CinemaDetailActivity.this,"cinema_id",""));

//        查询电影信息明细
        presenter.get(Interfaces.InquiryFilmInformationDetails,hashMap,Map,CinemaDetailData.class);
    }

    @Override
    public void success(Object success) {
        if(success instanceof CinemaDetailData){
            CinemaDetailData data = (CinemaDetailData) success;
            data1 = data;
            MyGlideUtil.setRoundImage(CinemaDetailActivity.this,data.getResult().getLogo(),cinema_detail_image);
            cinema_detail_name.setText(data.getResult().getName());
            Cinema_ticket_address.setText(data.getResult().getAddress());
            //        根据影院ID查询该影院当前排期的电影列表
            Map<String,Object> hMap =new HashMap<>();
            Map<String,Object> Map =new HashMap<>();
            Map.put("cinemaId",SpBase.getString(CinemaDetailActivity.this,"cinema_id",""));
            presenter.get(Interfaces.QueryCurrentMoviesBasedOnTheaterID,hMap,Map,MoviesAccordingToTheCinemaData.class);
        }else if(success instanceof MoviesAccordingToTheCinemaData){
            Cinemabanner((MoviesAccordingToTheCinemaData) success);
        }else if(success instanceof MovieIdCinemaId){
            getMovielist((MovieIdCinemaId) success);
        }
    }

    @Override
    public void error(String error) {
        Log.e("CinemaDetailActivity",error);
    }
    /**
     * 电影轮播图
     */
    private void Cinemabanner(MoviesAccordingToTheCinemaData success) {
        final MoviesAccordingToTheCinemaData data = success;
        if(data.getMessage().equals("无数据")){
            cinema_detail_recycle_banner.setVisibility(View.GONE);
            cinema_detail_banner_text.setVisibility(View.VISIBLE);
        }else{
            final List<FilmRecycleItemBean> list =new ArrayList<>();
            for (int i = 0; i <data.getResult().size(); i++) {
                FilmRecycleItemBean bean =new FilmRecycleItemBean(data.getResult().get(i).getId(),data.getResult().get(i).getImageUrl(),data.getResult().get(i).getName());
                list.add(bean);
            }
            FilmeRecycleAdapter filmeRecycleAdapter = new FilmeRecycleAdapter(CinemaDetailActivity.this, list);
            cinema_detail_recycle_banner.setAdapter(filmeRecycleAdapter);
            if(list.size()>=3){
                cinema_detail_recycle_banner.smoothScrollToPosition(2);
            }
            if(list.size()!=0){
                cinema_detail_recycle_banner.setVisibility(View.VISIBLE);
            }
            final int pos = cinema_detail_recycle_banner.getSelectedPos();
            movie_name = data.getResult().get(pos).getName();
            /**
             * 进行下一步操作 访问接口
             */
            Map<String, Object> hashmap = new HashMap<>();
            Map<String, Object> twomap = new HashMap<>();
            twomap.put("cinemasId",SpBase.getString(CinemaDetailActivity.this,"cinema_id",""));
            twomap.put("movieId",list.get(pos).getId());
            /**
             * 根据电影院ID 和电影ID 查询排期表
             */
            presenter.get(Interfaces.SearchMovieSchedulesBasedOnMovieIDAndCinemaID,hashmap,twomap,MovieIdCinemaId.class);

            cinema_detail_recycle_banner.setOnItemSelectedListener(new CoverFlowLayoutManger.OnSelected() {

                @Override
                public void onItemSelected(int position) {
                    movie_name = data.getResult().get(position).getName();
                    /**
                     * 滑动到中间的那个数据
                     */
                    Map<String, Object> hashmap = new HashMap<>();
                    Map<String, Object> twomap = new HashMap<>();
                    twomap.put("cinemasId",SpBase.getString(CinemaDetailActivity.this,"cinema_id",""));
//                    获取到最新的电影ID 存入Sp 里面
                    SpBase.save(CinemaDetailActivity.this,"movieId",list.get(position).getId()+"");

                    twomap.put("movieId",list.get(position).getId());
                    /**
                     * 根据电影院ID 和电影ID 查询排期表
                     */
                    presenter.get(Interfaces.SearchMovieSchedulesBasedOnMovieIDAndCinemaID,hashmap,twomap,MovieIdCinemaId.class);
                }
            });
            /**
             * 点击事件
             */
            filmeRecycleAdapter.getonclick(new FilmeRecycleAdapter.onclicked() {
                @Override
                public void movie(int id, int i) {
//                得到当前选中的是哪一个然后进行请求网络
                    int pos = cinema_detail_recycle_banner.getSelectedPos();
                    if(pos==i){
                        /**
                         * 点击的是显示在前面的
                         * 进行下一步操作 访问接口
                         */
                    }else{
                        cinema_detail_recycle_banner.smoothScrollToPosition(i);
                    }
                }
            });

            filmeRecycleAdapter.notifyDataSetChanged();
        }
    }
    /**
     * 电影的排期表
     */
    private void getMovielist(MovieIdCinemaId success) {
        final MovieIdCinemaId movieIdCinemaId = success;
        if(movieIdCinemaId.getResult().size()!=0){
            TicketDetailsAdapter adapter =new TicketDetailsAdapter(R.layout.ticket_detail_recycle_item,movieIdCinemaId.getResult());
            /**
             * 添加排期表点击事件
             */
            adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    Intent intent =new Intent(CinemaDetailActivity.this,SeatSelectionActivity.class);
                    intent.putExtra("Cinema_name",data1.getResult().getName());
                    intent.putExtra("Cinema_address",data1.getResult().getAddress());
//                    在传值的时候获取到最新的电影院ID  然后传送给选座
                    SpBase.save(CinemaDetailActivity.this,"cinema_id",data1.getResult().getId()+"");
                    intent.putExtra("Cinema_id",data1.getResult().getId()+"");
                    intent.putExtra("Movie_name",movie_name);
                    intent.putExtra("Movie_price",movieIdCinemaId.getResult().get(position).getPrice()+"");
                    intent.putExtra("Movie_ting",movieIdCinemaId.getResult().get(position).getScreeningHall()+"");
                    startActivity(intent);
                }
            });
            cinema_detail_recycle.setLayoutManager(new LinearLayoutManager(CinemaDetailActivity.this));
            cinema_detail_recycle.setAdapter(adapter);
            cinema_detail_recycle.setVisibility(View.VISIBLE);
            cinema_detail_text.setVisibility(View.GONE);
        }else{
            cinema_detail_recycle.setVisibility(View.GONE);
            cinema_detail_text.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.ontach();
    }
}
