package com.bw.movie.adapter;

import android.support.annotation.Nullable;

import com.bw.movie.R;
import com.bw.movie.bean.MovieIdCinemaId;
import com.bw.movie.bean.NearbyCinemaData;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * <p>文件描述：这是一个<p>
 * <p>作者：${adai}<p>
 * <p>创建时间：2019/2/14 9:32<p>
 * <p>更改时间：2019/2/14 9:32<p>
 * <p>版本号：1<p>
 */
public class TicketDetailsAdapter extends BaseQuickAdapter<MovieIdCinemaId.ResultBean,BaseViewHolder> {

    public TicketDetailsAdapter(int layoutResId, @Nullable List<MovieIdCinemaId.ResultBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MovieIdCinemaId.ResultBean item) {
        helper.setText(R.id.screeningHall,item.getScreeningHall());
        helper.setText(R.id.cinema_time1,item.getBeginTime());
        helper.setText(R.id.cinema_time2,item.getEndTime());
        helper.setText(R.id.cinema_price,item.getPrice()+"");
    }
}