package com.bw.movie.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bw.movie.R;
import com.bw.movie.bean.MovieFilmData;
import com.bw.movie.utils.MyGlideUtil;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * <p>文件描述：<p>
 * <p>作者：${adai}<p>
 * <p>创建时间：2019/1/29 15:02<p>
 * <p>更改时间：2019/1/29 15:02<p>
 * <p>版本号：1<p>
 */
public class MovieFilmAdapter extends BaseQuickAdapter<MovieFilmData.ResultBean,BaseViewHolder> {
    public MovieFilmAdapter(int layoutResId, @Nullable List<MovieFilmData.ResultBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MovieFilmData.ResultBean item) {
        MyGlideUtil.setCircleImage(mContext,item.getCommentHeadPic(),(ImageView) helper.getView(R.id.Ping_Huifu_img));
        helper.setText(R.id.Ping_Huifu_name,item.getCommentUserName());
        helper.setText(R.id.Ping_Huifu_content,item.getCommentContent());
        SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String format = f.format(item.getCommentTime());
        helper.setText(R.id.Ping_Huifu_time,format);

        helper.setText(R.id.Ping_Huifu_DianZaiShu,item.getGreatNum()+"");
        helper.setText(R.id.Ping_Huifu_HuiFuShu,item.getReplyNum()+"");
        helper.addOnClickListener(R.id.Ping_Huifu_love);

        if(item.getIsGreat()==0){
            MyGlideUtil.setRoundImage(mContext,R.mipmap.com_icon_praise_default,(ImageView) helper.getView(R.id.Ping_Huifu_love));
        }else{
            MyGlideUtil.setRoundImage(mContext,R.mipmap.com_icon_praise_selected,(ImageView) helper.getView(R.id.Ping_Huifu_love));
        }

        
    }
}
