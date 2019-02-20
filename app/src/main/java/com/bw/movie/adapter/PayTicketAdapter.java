package com.bw.movie.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.bean.PayTicketJiluData;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class PayTicketAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<PayTicketJiluData.ResultBean> plist;
    private int type;
    private final int ONE=0;
    private final int TWO=1;

    public PayTicketAdapter(Context context, List<PayTicketJiluData.ResultBean> mlist) {
        this.context = context;
        this.plist = mlist;
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
                view=LayoutInflater.from(context).inflate(R.layout.my_pay_recy,null);
                holder=new OneHolder(view);
                break;
            case TWO:
                view=LayoutInflater.from(context).inflate(R.layout.my_pay_recyw,null);
                holder=new TwoHolder(view);
                break;
        }

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof OneHolder){
            ((OneHolder) viewHolder).Pay_name.setText(plist.get(i).getMovieName());
            ((OneHolder) viewHolder).Pay_Dingdan.setText("订单号："+plist.get(i).getOrderId());
            ((OneHolder) viewHolder).Pay_YinYuan.setText("影院："+plist.get(i).getCinemaName());
            ((OneHolder) viewHolder).Pay_Time.setText("时间："+plist.get(i).getBeginTime());
            ((OneHolder) viewHolder).Pay_YinTing.setText("影厅："+plist.get(i).getScreeningHall()+"");
            ((OneHolder) viewHolder).Pay_Shu.setText("数量："+plist.get(i).getAmount()+"张");
            ((OneHolder) viewHolder).Pay_Money.setText("金额："+plist.get(i).getPrice()+"元");
        }else if(viewHolder instanceof TwoHolder){
            ((TwoHolder) viewHolder).Pay_Wname.setText(plist.get(i).getMovieName());
            ((TwoHolder) viewHolder).Pay_WDingdan.setText("订单号："+plist.get(i).getOrderId()+"");
            ((TwoHolder) viewHolder).Pay_WYinYuan.setText("影院："+plist.get(i).getCinemaName());
            ((TwoHolder) viewHolder).Pay_WTime.setText("下单时间："+longToDate(plist.get(i).getCreateTime()));
            ((TwoHolder) viewHolder).Pay_Wgo.setText(plist.get(i).getBeginTime()+"-"+plist.get(i).getEndTime());
            ((TwoHolder) viewHolder).Pay_WShu.setText("数量："+plist.get(i).getAmount()+"张");
            ((TwoHolder) viewHolder).Pay_WYinTing.setText("影厅："+plist.get(i).getScreeningHall()+"");
            ((TwoHolder) viewHolder).Pay_WMoney.setText("金额："+plist.get(i).getPrice()+"元");
        }
    }

    @Override
    public int getItemCount() {
        if (type%2==0){
            return plist.size();
        }else {
            return plist.size();
        }
    }

    @Override
    public int getItemViewType(int position) {
        return type;
    }

    class OneHolder extends RecyclerView.ViewHolder{

        private TextView Pay_name;
        private Button Pay_go;
        private TextView Pay_Dingdan;
        private TextView Pay_YinYuan;
        private TextView Pay_YinTing;
        private TextView Pay_Time;
        private TextView Pay_Shu;
        private TextView Pay_Money;

        public OneHolder(@NonNull View itemView) {
            super(itemView);
            Pay_name=itemView.findViewById(R.id.Pay_name);
            Pay_go=itemView.findViewById(R.id.Pay_go);
            Pay_Dingdan=itemView.findViewById(R.id.Pay_Dingdan);
            Pay_YinYuan=itemView.findViewById(R.id.Pay_YinYuan);
            Pay_YinTing=itemView.findViewById(R.id.Pay_YinTing);
            Pay_Time=itemView.findViewById(R.id.Pay_Time);
            Pay_Shu=itemView.findViewById(R.id.Pay_Shu);
            Pay_Money=itemView.findViewById(R.id.Pay_Money);
        }
    }
    class TwoHolder extends RecyclerView.ViewHolder{

        private TextView Pay_Wname;
        private TextView Pay_Wgo;
        private TextView Pay_WDingdan;
        private TextView Pay_WYinYuan;
        private TextView Pay_WYinTing;
        private TextView Pay_WTime;
        private TextView Pay_WShu;
        private TextView Pay_WMoney;

        public TwoHolder(@NonNull View itemView) {
            super(itemView);
            Pay_Wname=itemView.findViewById(R.id.Pay_Wname);
            Pay_Wgo=itemView.findViewById(R.id.Pay_Wgo);
            Pay_WDingdan=itemView.findViewById(R.id.Pay_WDingdan);
            Pay_WYinYuan=itemView.findViewById(R.id.Pay_WYinYuan);
            Pay_WYinTing=itemView.findViewById(R.id.Pay_WYinTing);
            Pay_WTime=itemView.findViewById(R.id.Pay_WTime);
            Pay_WShu=itemView.findViewById(R.id.Pay_WShu);
            Pay_WMoney=itemView.findViewById(R.id.Pay_WMoney);
        }
    }
    public static String longToDate(long lo){
        Date date = new Date(lo);
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return sd.format(date);
    }

}
