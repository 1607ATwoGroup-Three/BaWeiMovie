package com.bw.movie.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.bean.MovieRecycleBean;
import com.bw.movie.utils.MyGlideUtil;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * <p>文件描述：<p>
 * <p>作者：${adai}<p>
 * <p>创建时间：2019/1/27 14:02<p>
 * <p>更改时间：2019/1/27 14:02<p>
 * <p>版本号：1<p>
 */
public class MovieRecycleAdapter extends BaseQuickAdapter<MovieRecycleBean, BaseViewHolder> {

    public MovieRecycleAdapter(int layoutResId, @Nullable List<MovieRecycleBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MovieRecycleBean item) {
        helper.setText(R.id.movie_item_title, item.getName());
        helper.setText(R.id.movie_item_content,item.getSummary());
        MyGlideUtil.setRoundImage(mContext,item.getImageUrl(),(ImageView) helper.getView(R.id.movie_item_image));
        int i = item.getFollowMovie();
        helper.addOnClickListener(R.id.movie_item_love);
        if(i==2){
            MyGlideUtil.setRoundImage(mContext,R.mipmap.com_icon_collection_default,(ImageView) helper.getView(R.id.movie_item_love));
        }else {
            MyGlideUtil.setRoundImage(mContext,R.mipmap.com_icon_collection_selected,(ImageView) helper.getView(R.id.movie_item_love));
        }

    }
}
