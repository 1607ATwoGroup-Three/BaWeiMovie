package com.bw.movie.avtivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.fragment.ViewPager_fourFragment;
import com.bw.movie.fragment.ViewPager_oneFragment;
import com.bw.movie.fragment.ViewPager_threeFragment;
import com.bw.movie.fragment.ViewPager_twoFragment;
import com.bw.movie.utils.SpBase;

import java.util.ArrayList;
import java.util.List;

public class IndexActivity extends BaseActivity {

    private ViewPager mViewpagerVp;
    private RadioGroup mViewpagerRg;
    private List<Fragment> mlist = new ArrayList<>();

    @Override
    protected void initView() {
        BaseActivity.fullScreen(this,true);
        setContentView(R.layout.activity_index);
        String index = SpBase.getString(IndexActivity.this,"index", 2+"");
        if(index.equals("1")){
            startActivity(new Intent(IndexActivity.this,LoginActivity.class));
//          overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            finish();
        }
    }

    @Override
    protected void initData() {
        mViewpagerVp = findViewById(R.id.index_viewpager);
        mViewpagerRg = findViewById(R.id.viewpager_rg);
        mlist.add(new ViewPager_oneFragment());
        mlist.add(new ViewPager_twoFragment());
        mlist.add(new ViewPager_threeFragment());
        mlist.add(new ViewPager_fourFragment());
        mViewpagerVp.setAdapter(new viewPager(getSupportFragmentManager()));
    }

    @Override
    protected void present() {
        mViewpagerVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                ((RadioButton) mViewpagerRg.getChildAt(i)).setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int i) {
            }
        });
        mViewpagerVp.setOnTouchListener(new View.OnTouchListener() {
            float startX;
            float startY;//没有用到
            float endX;
            float endY;//没有用到
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        startX=event.getX();
                        startY=event.getY();
                        break;
                    case MotionEvent.ACTION_UP:
                        endX=event.getX();
                        endY=event.getY();
                        WindowManager windowManager= (WindowManager)getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
                        //获取屏幕的宽度
                        Point size = new Point();
                        windowManager.getDefaultDisplay().getSize(size);
                        int width=size.x;
                        //首先要确定的是，是否到了最后一页，然后判断是否向左滑动，并且滑动距离是否符合，我这里的判断距离是屏幕宽度的4分之一（这里可以适当控制）
                        if(mViewpagerVp.getCurrentItem()==(mlist.size()-1)&&startX-endX>=(width/5)){
                            SpBase.save(IndexActivity.this,"index",1+"");
                            startActivity(new Intent(IndexActivity.this,LoginActivity.class));
//                            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                            finish();
                        }
                        break;
                }
                return false;
            }
        });
        mViewpagerRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.viewpager_rb1:
                        mViewpagerVp.setCurrentItem(0);
                        break;
                    case R.id.viewpager_rb2:
                        mViewpagerVp.setCurrentItem(1);
                        break;
                    case R.id.viewpager_rb3:
                        mViewpagerVp.setCurrentItem(2);
                        break;
                    case R.id.viewpager_rb4:
                        mViewpagerVp.setCurrentItem(3);
                        break;
                }
            }
        });
    }


    private class viewPager extends FragmentPagerAdapter {

        public viewPager(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            return mlist.get(i);
        }

        @Override
        public int getCount() {
            return mlist.size();
        }
    }
}
