package com.bw.movie.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * <p>文件描述：<p>
 * <p>作者：${adai}<p>
 * <p>创建时间：2019/1/28 17:21<p>
 * <p>更改时间：2019/1/28 17:21<p>
 * <p>版本号：1<p>
 */
public class FilmReviewRecycleAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public FilmReviewRecycleAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
//        setOnLoadMoreListener(RequestLoadMoreListener);
    }
}
