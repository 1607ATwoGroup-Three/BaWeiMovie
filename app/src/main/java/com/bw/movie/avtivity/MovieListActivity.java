package com.bw.movie.avtivity;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.adapter.MovieRecycleAdapter;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.bean.IsShowingUpBean;
import com.bw.movie.bean.MovieRecycleBean;
import com.bw.movie.bean.PopularCinemaBean;
import com.bw.movie.bean.RegisterData;
import com.bw.movie.bean.ToBeShownSoonBean;
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

public class MovieListActivity extends BaseActivity implements View.OnClickListener, Contract.View {

    private ImageView movie_list_img;
    private TextView movie_list_whilt;
    private EditText movie_list_ed;
    private TextView movie_list_search;
    private LinearLayout movie_list_lin;
    private TextView movie_list_pop;
    private TextView movie_list_Showing;
    private TextView movie_list_soon;
    private RecyclerView movie_list_lin_recycle;
    private String type;
    private Map<String, Object> headmap;
    private Map<String, Object> map;
    private Presenter presenter;
    private List<MovieRecycleBean> list =new ArrayList<>();

    @Override
    protected void initView() {
        BaseActivity.doublekeydown(false);
        BaseActivity.fullScreen(MovieListActivity.this, true);
        setContentView(R.layout.activity_movie_list);
        movie_list_img = (ImageView) findViewById(R.id.movie_list_img);
        movie_list_img.setOnClickListener(this);
        movie_list_whilt = (TextView) findViewById(R.id.movie_list_whilt);
        movie_list_whilt.setOnClickListener(this);
        movie_list_ed = (EditText) findViewById(R.id.movie_list_ed);
        movie_list_ed.setOnClickListener(this);
        movie_list_search = (TextView) findViewById(R.id.movie_list_search);
        movie_list_search.setOnClickListener(this);
        movie_list_lin = (LinearLayout) findViewById(R.id.movie_list_lin);
        movie_list_lin.setOnClickListener(this);
        movie_list_pop = (TextView) findViewById(R.id.movie_list_pop);
        movie_list_pop.setOnClickListener(this);
        movie_list_Showing = (TextView) findViewById(R.id.movie_list_Showing);
        movie_list_Showing.setOnClickListener(this);
        movie_list_soon = (TextView) findViewById(R.id.movie_list_soon);
        movie_list_soon.setOnClickListener(this);
        movie_list_lin_recycle = (RecyclerView) findViewById(R.id.movie_list_lin_rey);
        movie_list_lin_recycle.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        String whilt = intent.getStringExtra("whilt");
        type = intent.getStringExtra("type");
        movie_list_whilt.setText(whilt);
        presenter = new Presenter(this);

        headmap = new HashMap<>();
        headmap.put("userId", SpBase.getString(MovieListActivity.this, "userId", ""));
        headmap.put("sessionId", SpBase.getString(MovieListActivity.this, "sessionId", ""));
        map = new HashMap<>();
        map.put("page", "1");
        map.put("count", "100");
    }

    @Override
    protected void present() {
        if (type != null) {
            switch (Integer.parseInt(type)) {
                case 1001:
                    get1001();
                    break;
                case 1002:
                    get1002( );
                    break;
                case 1003:
                    get1003();
                    break;
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.movie_list_pop:
                get1001();
                break;
            case R.id.movie_list_Showing:
                get1002();
                break;
            case R.id.movie_list_soon:
                get1003();
                break;
        }
    }

    public void get1001() {
        movie_list_pop.setBackgroundResource(R.drawable.cinema_ed);
        movie_list_pop.setTextColor(Color.WHITE);
        movie_list_Showing.setBackgroundResource(R.drawable.cinema_noed);
        movie_list_Showing.setTextColor(Color.BLACK);
        movie_list_soon.setBackgroundResource(R.drawable.cinema_noed);
        movie_list_soon.setTextColor(Color.BLACK);
//        查询热门电影
        presenter.get(Interfaces.SearchForHotMovies, headmap, map, PopularCinemaBean.class);
    }

    public void get1002() {
        movie_list_Showing.setBackgroundResource(R.drawable.cinema_ed);
        movie_list_Showing.setTextColor(Color.WHITE);
        movie_list_pop.setBackgroundResource(R.drawable.cinema_noed);
        movie_list_pop.setTextColor(Color.BLACK);
        movie_list_soon.setBackgroundResource(R.drawable.cinema_noed);
        movie_list_soon.setTextColor(Color.BLACK);
//        正在上映
        presenter.get(Interfaces.QueryTheListOfMoviesBeingShown, headmap, map, IsShowingUpBean.class);
    }

    public void get1003() {
        movie_list_soon.setBackgroundResource(R.drawable.cinema_ed);
        movie_list_soon.setTextColor(Color.WHITE);
        movie_list_pop.setBackgroundResource(R.drawable.cinema_noed);
        movie_list_pop.setTextColor(Color.BLACK);
        movie_list_Showing.setBackgroundResource(R.drawable.cinema_noed);
        movie_list_Showing.setTextColor(Color.BLACK);
//        即将上映
        presenter.get(Interfaces.QueryTheListOfUpcomingMovies, headmap, map, ToBeShownSoonBean.class);
    }

    @Override
    public void success(Object success) {
        if(success instanceof PopularCinemaBean){
            PopularCinemaBean bean = (PopularCinemaBean) success;
            ObjectAnimator animator = ObjectAnimator.ofFloat(movie_list_lin_recycle,"alpha", 0f,1f);
            // 表示的是:
            // 动画作用对象是mButton
            // 动画作用的对象的属性是透明度alpha
            // 动画效果是:常规 - 全透明 - 常规
            animator.setDuration(2000);
            list.clear();
            animator.start();
            for (int i = 0; i <bean.getResult().size(); i++) {
                list.add(new MovieRecycleBean(bean.getResult().get(i).getFollowMovie(),bean.getResult().get(i).getId(),
                        bean.getResult().get(i).getImageUrl(),
                        bean.getResult().get(i).getName(),
                        bean.getResult().get(i).getSummary()));
            }
            final MovieRecycleAdapter movieRecycleAdapter =new MovieRecycleAdapter(R.layout.movierecycle_item,list);
            movieRecycleAdapter.openLoadAnimation();
            movieRecycleAdapter.setDuration(500);
//            点击事件
            movieRecycleAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    Intent intent =new Intent(MovieListActivity.this,MovieDetailsActivity.class);
                    intent.putExtra("movieId",list.get(position).getId()+"");
                    intent.putExtra("islove",list.get(position).getFollowMovie()+"");
                    startActivity(intent);
                }
            });
            movie_list_lin_recycle.setAdapter(movieRecycleAdapter);
            movieRecycleAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                @Override
                public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("movieId",list.get(position).getId());
                    ImageView image =(ImageView)movieRecycleAdapter.getViewByPosition(movie_list_lin_recycle,position, R.id.movie_item_love);
                    if(list.get(position).getFollowMovie()==2){
                        list.get(position).setFollowMovie(1);
                        presenter.get(Interfaces.FocusOnMovies,headmap,map,RegisterData.class);
                        MyGlideUtil.setRoundImage(MovieListActivity.this,R.mipmap.com_icon_collection_selected,image);
                    }else {
                        list.get(position).setFollowMovie(2);
                        presenter.get(Interfaces.CancelFilmConcern,headmap,map,RegisterData.class);
                        MyGlideUtil.setRoundImage(MovieListActivity.this,R.mipmap.com_icon_collection_default,image);
                    }
                }
            });
        }else if(success instanceof IsShowingUpBean){
            IsShowingUpBean bean = (IsShowingUpBean) success;
            ObjectAnimator animator = ObjectAnimator.ofFloat(movie_list_lin_recycle,"alpha", 0f,1f);
            // 表示的是:
            // 动画作用对象是mButton
            // 动画作用的对象的属性是透明度alpha
            // 动画效果是:常规 - 全透明 - 常规
            animator.setDuration(2000);
            list.clear();
            animator.start();
            for (int i = 0; i <bean.getResult().size(); i++) {
                list.add(new MovieRecycleBean(bean.getResult().get(i).getFollowMovie(),bean.getResult().get(i).getId(),
                        bean.getResult().get(i).getImageUrl(),
                        bean.getResult().get(i).getName(),
                        bean.getResult().get(i).getSummary()));
            }
            final MovieRecycleAdapter movieRecycleAdapter =new MovieRecycleAdapter(R.layout.movierecycle_item,list);
            movieRecycleAdapter.openLoadAnimation();
            movieRecycleAdapter.setDuration(500);
            movieRecycleAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    Intent intent =new Intent(MovieListActivity.this,MovieDetailsActivity.class);
                    intent.putExtra("movieId",list.get(position).getId()+"");
                    intent.putExtra("islove",list.get(position).getFollowMovie()+"");
                    startActivity(intent);
                }
            });
            movie_list_lin_recycle.setAdapter(movieRecycleAdapter);
            movieRecycleAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                @Override
                public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("movieId",list.get(position).getId());
                    ImageView image =(ImageView)movieRecycleAdapter.getViewByPosition(movie_list_lin_recycle,position, R.id.movie_item_love);
                    if(list.get(position).getFollowMovie()==2){
                        list.get(position).setFollowMovie(1);
                        presenter.get(Interfaces.FocusOnMovies,headmap,map,RegisterData.class);
                        MyGlideUtil.setRoundImage(MovieListActivity.this,R.mipmap.com_icon_collection_selected,image);
                    }else {
                        list.get(position).setFollowMovie(2);
                        presenter.get(Interfaces.CancelFilmConcern,headmap,map,RegisterData.class);
                        MyGlideUtil.setRoundImage(MovieListActivity.this,R.mipmap.com_icon_collection_default,image);
                    }
            }
            });
        }else if(success instanceof ToBeShownSoonBean){
            ToBeShownSoonBean bean = (ToBeShownSoonBean) success;
            ObjectAnimator animator = ObjectAnimator.ofFloat(movie_list_lin_recycle,"alpha", 0f,1f);
            animator.setDuration(2000);
            list.clear();
            animator.start();
            for (int i = 0; i <bean.getResult().size(); i++) {
                list.add(new MovieRecycleBean(bean.getResult().get(i).getFollowMovie(),bean.getResult().get(i).getId(),
                        bean.getResult().get(i).getImageUrl(),
                        bean.getResult().get(i).getName(),
                        bean.getResult().get(i).getSummary()));
            }
            final MovieRecycleAdapter movieRecycleAdapter =new MovieRecycleAdapter(R.layout.movierecycle_item,list);
            movieRecycleAdapter.openLoadAnimation();
            movieRecycleAdapter.setDuration(500);
            movieRecycleAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    Intent intent =new Intent(MovieListActivity.this,MovieDetailsActivity.class);
                    intent.putExtra("movieId",list.get(position).getId()+"");
                    intent.putExtra("islove",list.get(position).getFollowMovie()+"");
                    startActivity(intent);
                }
            });
            movie_list_lin_recycle.setAdapter(movieRecycleAdapter);
            movieRecycleAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                @Override
                public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("movieId",list.get(position).getId());
                    ImageView image =(ImageView)movieRecycleAdapter.getViewByPosition(movie_list_lin_recycle,position, R.id.movie_item_love);
                    if(list.get(position).getFollowMovie()==2){
                        list.get(position).setFollowMovie(1);
                        presenter.get(Interfaces.FocusOnMovies,headmap,map,RegisterData.class);
                        MyGlideUtil.setRoundImage(MovieListActivity.this,R.mipmap.com_icon_collection_selected,image);
                    }else {
                        list.get(position).setFollowMovie(2);
                        presenter.get(Interfaces.CancelFilmConcern,headmap,map,RegisterData.class);
                        MyGlideUtil.setRoundImage(MovieListActivity.this,R.mipmap.com_icon_collection_default,image);
                    }
                }
            });
        }else if(success instanceof RegisterData){
            RegisterData data = (RegisterData) success;
            Log.e("关注电影的状态",data.getMessage()+"");
//            Toast.makeText(MovieListActivity.this,data.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void error(String error) {
//        Toast.makeText(ctx, error, Toast.LENGTH_SHORT).show();
        Log.e("error",error+"");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(presenter!=null){
            presenter.ontach();
        }
    }
}
