package com.bw.movie.avtivity;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.amap.api.location.AMapLocation;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.fragment.CinemaFragment;
import com.bw.movie.fragment.FilmFragment;
import com.bw.movie.fragment.MyFragment;
import com.bw.movie.utils.LocationUtil;
import com.bw.movie.utils.SpBase;


public class ShowActivity extends AppCompatActivity implements View.OnClickListener{


    private FrameLayout show_frage;
    private ImageView show_btn_Cinema;
    private ImageView show_btn_My;
    private ImageView show_btn_Film;
    private FragmentManager manager;
    private Fragment[] fragment = new Fragment[3];
    private ImageView[] img = new ImageView[6];
    private ImageView show_btn_Cinema_selected;
    private ImageView show_btn_My_selected;
    private ImageView show_btn_Film_selected;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BaseActivity.fullScreen(this, false);
        setContentView(R.layout.activity_show);
        initView();
        initData();
        present();
    }

    public void initView() {
        show_frage = findViewById(R.id.show_frage);
        show_btn_Film = findViewById(R.id.show_btn_Film_default);
        show_btn_My = findViewById(R.id.show_btn_My_default);
        show_btn_Cinema = findViewById(R.id.show_btn_Cinema_default);
        show_btn_Cinema_selected = findViewById(R.id.show_btn_Cinema_selected);
        show_btn_My_selected = findViewById(R.id.show_btn_My_selected);
        show_btn_Film_selected = findViewById(R.id.show_btn_Film_selected);
        manager = getSupportFragmentManager();

        show_btn_Cinema.setOnClickListener(this);
        show_btn_Film.setOnClickListener(this);
        show_btn_My.setOnClickListener(this);
    }

    public void initData() {

        fragment[0] = new FilmFragment();
        fragment[1] = new CinemaFragment();
        fragment[2] = new MyFragment();
        img[0] = show_btn_Film;
        img[1] = show_btn_Film_selected;
        img[2] = show_btn_My;
        img[3] = show_btn_My_selected;
        img[4] = show_btn_Cinema;
        img[5] = show_btn_Cinema_selected;
    }

    public void present() {
        setfragmentdisplay(0);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.show_btn_My_default:
                setfragmentdisplay(2);
                setimagedisplay(2);
                break;
            case R.id.show_btn_Cinema_default:
                setfragmentdisplay(1);
                setimagedisplay(4);
                break;
            case R.id.show_btn_Film_default:
                setfragmentdisplay(0);
                setimagedisplay(0);
                break;
        }
    }

    public void setfragmentdisplay(int position) {
        FragmentTransaction transaction = manager.beginTransaction();
        //如果没有fragment就在framelayout里面加上
        if (!fragment[position].isAdded()) {
            transaction.add(R.id.show_frage, fragment[position], "" + position);
        }
        //把所有的fragment设为隐藏
        for (Fragment fragment : fragment) {
            transaction.hide(fragment);
        }

        //把选中的设为显示
        transaction.show(fragment[position]);
        transaction.commit();
    }

    /**
     *这是判断选中的是哪一个
     * @param position
     */
    public void setimagedisplay(int position){
        for (int i = 0; i <img.length; i++) {
            if(i%2==0){
                img[i].setVisibility(View.VISIBLE);
            }else{
                img[i].setVisibility(View.GONE);
            }
        }
        img[position+1].setVisibility(View.VISIBLE);
        img[position].setVisibility(View.GONE);
        ObjectAnimator animator = ObjectAnimator.ofFloat(img[position], "rotation", 0f, 60f,0f);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(img[position+1], "alpha", 0f, 1f);
        // 表示的是:
        // 动画作用对象是mButton
        // 动画作用的对象的属性是旋转alpha
        // 动画效果是:0 - 360
        animator.setDuration(1000);
        animator2.setDuration(1500);
        animator.start();
        animator2.start();
    }
}