package com.bw.movie.adapter;

import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.bw.movie.R;
import com.bw.movie.bean.MovieDetailBean;
import com.bw.movie.utils.MyGlideUtil;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;
import java.util.Random;

import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;

/**
 * <p>文件描述：<p>
 * <p>作者：${adai}<p>
 * <p>创建时间：2019/1/28 11:59<p>
 * <p>更改时间：2019/1/28 11:59<p>
 * <p>版本号：1<p>
 */
public class MovieStilsAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public MovieStilsAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        ImageView view = helper.getView(R.id.stils_image);
        ViewGroup.LayoutParams params = view.getLayoutParams();
        Random random =new Random();
        int height = random.nextInt(200)+400;
        params.height=height;
        view.setLayoutParams(params);
        MyGlideUtil.setRoundImage(mContext,item,view);
    }
}
