package com.bw.movie.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bw.movie.R;
import com.bw.movie.bean.MovieDetailBean;
import com.bw.movie.utils.MyGlideUtil;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;

/**
 * <p>文件描述：<p>
 * <p>作者：${adai}<p>
 * <p>创建时间：2019/1/28 11:59<p>
 * <p>更改时间：2019/1/28 11:59<p>
 * <p>版本号：1<p>
 */
public class MovieNoticeAdapter extends BaseQuickAdapter<MovieDetailBean.ResultBean.ShortFilmListBean, BaseViewHolder> {

    private JZVideoPlayerStandard mjcone;
    private JZVideoPlayerStandard mjctwo;
    private JZVideoPlayerStandard mjcthree;
    private int i=0;

    public MovieNoticeAdapter(int layoutResId, @Nullable List<MovieDetailBean.ResultBean.ShortFilmListBean> data) {
        super(layoutResId, data);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void convert(BaseViewHolder helper, MovieDetailBean.ResultBean.ShortFilmListBean item) {
        JZVideoPlayerStandard mjc = helper.getView(R.id.movienotice_video);
        mjc.thumbImageView.setScaleType(ImageView.ScaleType.FIT_XY);
        mjc.setVideoImageDisplayType(JZVideoPlayer.VIDEO_IMAGE_DISPLAY_TYPE_FILL_PARENT);
        mjc.TOOL_BAR_EXIST = false;
        mjc.setUp(item.getVideoUrl(),JZVideoPlayerStandard.SCROLL_AXIS_HORIZONTAL, "");
        MyGlideUtil.setDefaultImage(mContext,item.getImageUrl(), mjc.thumbImageView);
        if(mjc.isEnabled()){
            if(i==0){
                mjcone=mjc;
            }else if(i==1){
                mjctwo=mjc;
            }else if(i==2){
                mjcthree=mjc;
                i=0;
            }
            i++;
        }
    }
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void getInfo(Object object) {
        if((boolean) object==true){
            mjcone.release();
            mjctwo.release();
            mjcthree.release();
        }
    }

    @Override
    public void onDetachedFromRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        EventBus.getDefault().unregister(this);
    }
}
