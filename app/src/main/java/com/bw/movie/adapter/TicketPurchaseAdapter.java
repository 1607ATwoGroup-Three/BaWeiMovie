package com.bw.movie.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bw.movie.R;
import com.bw.movie.bean.Follow_YinyuanData;
import com.bw.movie.utils.MyGlideUtil;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class TicketPurchaseAdapter extends BaseQuickAdapter<Follow_YinyuanData.ResultBean,BaseViewHolder> {

    public TicketPurchaseAdapter(int layoutResId, @Nullable List<Follow_YinyuanData.ResultBean> data) {
        super(layoutResId, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, Follow_YinyuanData.ResultBean item) {
        MyGlideUtil.setRoundImage(mContext,item.getLogo(),(ImageView) helper.getView(R.id.recycle_item_image));
        helper.setText(R.id.recycle_item_name,item.getName());
        helper.setText(R.id.recycle_item_address,item.getAddress());
    }
}
