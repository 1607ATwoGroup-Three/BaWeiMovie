package com.bw.movie.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.bean.NearbyCinemaData;
import com.bw.movie.utils.MyGlideUtil;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class TicketPurchaseAdapter extends BaseQuickAdapter<NearbyCinemaData.ResultBean,BaseViewHolder> {

    public TicketPurchaseAdapter(int layoutResId, @Nullable List<NearbyCinemaData.ResultBean> data) {
        super(layoutResId, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, NearbyCinemaData.ResultBean item) {
        MyGlideUtil.setRoundImage(mContext,item.getLogo(),(ImageView) helper.getView(R.id.recycle_item_image));
        helper.setText(R.id.recycle_item_name,item.getName());
        helper.setText(R.id.recycle_item_address,item.getAddress());
        int v1 = item.getDistance() / 1000;
        int v = item.getDistance() % 1000;
        helper.setText(R.id.recycle_item_distance,v1+"."+v+"km");
    }
}
