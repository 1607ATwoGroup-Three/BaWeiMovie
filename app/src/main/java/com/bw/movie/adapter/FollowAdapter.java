package com.bw.movie.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.bean.Follow_DianyinData;
import com.bw.movie.bean.Follow_YinyuanData;
import com.bw.movie.utils.MyGlideUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class FollowAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<Follow_DianyinData.ResultBean> mDYlist;
    private List<Follow_YinyuanData.ResultBean> mYYlist;
    private int type;
    private final int ONE=0;
    private final int TWO=1;

    public FollowAdapter(Context context, List<Follow_DianyinData.ResultBean> mDYlist, List<Follow_YinyuanData.ResultBean> mYYlist) {
        this.context = context;
        this.mDYlist = mDYlist;
        this.mYYlist = mYYlist;
    }

    public void setType(int type) {
        this.type = type;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=null;
        RecyclerView.ViewHolder holder=null;
        switch (i){
            case ONE:
                view=LayoutInflater.from(context).inflate(R.layout.my_follow_dianyin,null);
                holder=new OneHolder(view);
                break;
            case TWO:
                view=LayoutInflater.from(context).inflate(R.layout.my_follow_yinyuan,null);
                holder=new TwoHolder(view);
                break;
        }

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof OneHolder){
            ((OneHolder) viewHolder).follow_content.setText(mDYlist.get(i).getSummary());
            ((OneHolder) viewHolder).follow_name.setText(mDYlist.get(i).getName());
            ((OneHolder) viewHolder).follow_time.setText(longToDate(mDYlist.get(i).getReleaseTime()));
            for (int j = 0; j <mDYlist.size() ; j++) {
                MyGlideUtil.setRoundImage(context,mDYlist.get(i).getImageUrl(),((OneHolder) viewHolder).follow_img);
            }
        }else if(viewHolder instanceof TwoHolder){
            ((TwoHolder) viewHolder).recycle_item_name.setText(mYYlist.get(i).getName()+"");
            ((TwoHolder) viewHolder).recycle_item_address.setText(mYYlist.get(i).getAddress()+"");
            for (int j = 0; j <mYYlist.size() ; j++) {
                MyGlideUtil.setRoundImage(context,mYYlist.get(i).getLogo(),((TwoHolder) viewHolder).recycle_item_image);
            }
        }
    }

    @Override
    public int getItemCount() {
        if (type%2==0){
            return mDYlist.size();
        }else {
            return mYYlist.size();
        }
    }

    @Override
    public int getItemViewType(int position) {
        return type;
    }

    class OneHolder extends RecyclerView.ViewHolder{

        private  ImageView follow_img;
        private  TextView follow_name;
        private  TextView follow_content;
        private  TextView follow_time;

        public OneHolder(@NonNull View itemView) {
            super(itemView);
            follow_img=itemView.findViewById(R.id.Follow_img);
            follow_name=itemView.findViewById(R.id.Follow_name);
            follow_content=itemView.findViewById(R.id.Follow_content);
            follow_time=itemView.findViewById(R.id.Follow_time);
        }
    }
    class TwoHolder extends RecyclerView.ViewHolder{

        private ImageView recycle_item_image;
        private TextView recycle_item_name;
        private TextView recycle_item_address;

        public TwoHolder(@NonNull View itemView) {
            super(itemView);
            recycle_item_image=itemView.findViewById(R.id.recycle_item_image);
            recycle_item_name=itemView.findViewById(R.id.recycle_item_name);
            recycle_item_address=itemView.findViewById(R.id.recycle_item_address);
        }
    }
    public static String longToDate(long lo){
        Date date = new Date(lo);
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
        return sd.format(date);
    }
}
