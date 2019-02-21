package com.bw.movie.avtivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.bean.IDUserData;
import com.bw.movie.bean.PayDanBean;
import com.bw.movie.bean.UserXiaDanData;
import com.bw.movie.contract.Contract;
import com.bw.movie.presenter.Presenter;
import com.bw.movie.ui.SeatTable;
import com.bw.movie.utils.EncryptUtil;
import com.bw.movie.utils.Interfaces;
import com.bw.movie.utils.SpBase;
import com.bw.movie.utils.WeiXinUtil;
import com.tencent.mm.opensdk.modelpay.PayReq;

import java.util.HashMap;
import java.util.Map;

/**
 * 选座 Activity
 */
public class SeatSelectionActivity extends BaseActivity implements Contract.View {

    private TextView seat_selection_name;
    private TextView seat_selection_address;
    private TextView seat_selection_movie_name;
    private TextView seat_selection_address_ting;
    private TextView seat_selection_price;
    private ImageView seat_selection_pay;
    private ImageView seat_selection_esc;
    private SeatTable seat_selection_seatTable;
    private RelativeLayout select_sit_sum;
    private int i = 0;
    private double movie_price;
    private Presenter presenter;
    private String sessionid;
    private String userid;
    private String scheduleId;
    private TextView weixin_paytext;
    private PopupWindow window;
    private RadioButton weixinbutton;
    private RadioButton zhifubaobutton;

    @Override
    protected void initView() {
        BaseActivity.doublekeydown(false);
        BaseActivity.fullScreen(SeatSelectionActivity.this, false);
        setContentView(R.layout.activity_seat_selection);
        seat_selection_name = findViewById(R.id.seat_selection_name);
        seat_selection_address = findViewById(R.id.seat_selection_address);
        seat_selection_movie_name = findViewById(R.id.seat_selection_movie_name);
        seat_selection_address_ting = findViewById(R.id.seat_selection_address_ting);
        seat_selection_price = findViewById(R.id.seat_selection_price);
        seat_selection_pay = findViewById(R.id.seat_selection_pay);
        seat_selection_esc = findViewById(R.id.seat_selection_esc);
        seat_selection_seatTable = findViewById(R.id.seat_selection_seatTable);
        select_sit_sum = findViewById(R.id.select_sit_sum);

        Intent intent = getIntent();
        String name = intent.getStringExtra("Cinema_name");
        String address = intent.getStringExtra("Cinema_address");
        String Movie_name = intent.getStringExtra("Movie_name");
        String Movie_ting = intent.getStringExtra("Movie_ting");
        movie_price = Double.parseDouble(intent.getStringExtra("Movie_price"));
        scheduleId = SpBase.getString(SeatSelectionActivity.this, "scheduleId", "");
        seat_selection_name.setText(name);
        seat_selection_address.setText(address);
        seat_selection_movie_name.setText(Movie_name);
        seat_selection_price.setText(0.00 + "");
        seat_selection_address_ting.setText(Movie_ting);
        /**
         * 设置的是 影院座位的数量 和最多选中多少个
         */
        seat_selection_seatTable.setData(8, 10);
        seat_selection_seatTable.setMaxSelected(4);//设置最多选中
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void initData() {
        seat_selection_seatTable.setSeatChecker(new SeatTable.SeatChecker() {
            //第san次
            @Override
            public boolean isValidSeat(int row, int column) {
                /**
                 * 这里的column 是列数
                 */
                /*if (column == 2||column == 7) {
                    return false;
                }*/
                return true;
            }

            @Override
            public boolean isSold(int row, int column) {
                if (row == 6 && column == 6) {
                    return true;
                }
                if (row == 5 && column == 2) {
                    return true;
                }
                if (row == 2 && column == 4) {
                    return true;
                }
                return false;
            }

            @Override
            public void checked(int row, int column) {
                i++;
                double v = movie_price * i;
                seat_selection_price.setText(v + "");
                weixin_paytext.setText("支付"+v+"元");
            }

            @Override
            public void unCheck(int row, int column) {
                if (i > 0) {
                    i--;
                    double v = movie_price * i;
                    seat_selection_price.setText(v + "");
                    weixin_paytext.setText("支付"+v+"元");
                }
            }

            @Override
            public String[] checkedSeatTxt(int row, int column) {
                return null;
            }

        });

        final View contentView = View.inflate(SeatSelectionActivity.this, R.layout.set_selection_pop, null);
        weixinbutton = contentView.findViewById(R.id.seat_selection_weixinbutton);
        zhifubaobutton = contentView.findViewById(R.id.seat_selection_zhifubaobutton);
        final ImageView seat_selection_down= contentView.findViewById(R.id.seat_selection_down);
        weixin_paytext = contentView.findViewById(R.id.seat_selection_weixin_paytext);

        /**
         * 点击了一个 下
         */
        seat_selection_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                window.dismiss();
            }
        });

        /**
         * 点击√
         */
        seat_selection_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i == 0) {
                    Toast.makeText(ctx, "请选择座位", Toast.LENGTH_SHORT).show();
                } else {
//                    select_sit_sum.setVisibility(View.GONE);
//                    select_sit_pay.setVisibility(View.VISIBLE);
//                    seat_selection_image.setVisibility(View.VISIBLE);
//                    seat_selection_seatTable.setFocusable(false);
//                    seat_selection_seatTable.setVisibility(View.GONE);
                    window = new PopupWindow(contentView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.
                            WRAP_CONTENT,true);
                    // 设置PopupWindow的背景
                    window.setBackgroundDrawable(new ColorDrawable(Color.GRAY));
                    // 设置PopupWindow是否能响应外部点击事件
                    window.setOutsideTouchable(true);
                    // 设置PopupWindow是否能响应点击事件
                    window.setTouchable(true);
                    // 显示PopupWindow，其中：
                    // 第一个参数是PopupWindow的锚点，第二和第三个参数分别是PopupWindow相对锚点的x、y偏移
                    window.showAtLocation(contentView,Gravity.BOTTOM,0,0);
                }
            }
        });
        /**
         * 点击×
         */
        seat_selection_esc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void present() {
        presenter = new Presenter(this);
        weixin_paytext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(weixinbutton.isChecked()){
                    /*微信*/
                    sessionid = SpBase.getString(SeatSelectionActivity.this, "sessionId", "");
                    userid = SpBase.getString(SeatSelectionActivity.this, "userId", "");
                    Map<String, Object> headmap = new HashMap<>();
                    headmap.put("userId", userid + "");
                    headmap.put("sessionId", sessionid + "");
                    Map<String, Object> map = new HashMap<>();
                    map.put("scheduleId",scheduleId+"");
                    map.put("amount",i+"");

                    String sign = userid + scheduleId + i + "movie";
                    Log.e("zzzzz", sign);
                    String encrypt = EncryptUtil.MD5(sign);
                    Log.e("zzzzz", encrypt);
                    map.put("sign",encrypt);
                    presenter.post(Interfaces.TicketEnquiry, headmap, map, UserXiaDanData.class);

                    Toast.makeText(ctx, "微信支付", Toast.LENGTH_SHORT).show();
                }else if(zhifubaobutton.isChecked()){
                    /*
                    支付宝支付
                    */
                }else{
                    Toast.makeText(ctx, "请选择支付方式", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void success(Object success) {
        if (success instanceof UserXiaDanData) {
            UserXiaDanData payBean = (UserXiaDanData) success;
            Log.e("payBean", payBean.toString());
            if (payBean != null && payBean.getMessage().equals("下单成功")) {
                /*PayReq req = new PayReq();
                req.appId = payBean.getAppId();
                req.nonceStr = payBean.getNonceStr();
                req.partnerId = payBean.getPartnerId();
                req.prepayId = payBean.getPrepayId();
                req.sign = payBean.getSign();
                req.timeStamp = payBean.getTimeStamp();
                req.packageValue = payBean.getPackageValue();
                //去调微信
                WeiXinUtil.reg(this).sendReq(req);
                Pay();*/
                SpBase.save(SeatSelectionActivity.this,"orderId",payBean.getOrderId()+"");
//                Toast.makeText(ctx,"下单成功！", Toast.LENGTH_SHORT).show();
                Pay();
            }else{
                Toast.makeText(ctx,payBean.getMessage()+"", Toast.LENGTH_SHORT).show();
            }
        }else if(success instanceof PayDanBean){
            PayDanBean payBean = (PayDanBean) success;
            if (payBean != null && payBean.getStatus().equals("0000")) {
                PayReq req = new PayReq();
                req.appId = payBean.getAppId();
                req.nonceStr = payBean.getNonceStr();
                req.partnerId = payBean.getPartnerId();
                req.prepayId = payBean.getPrepayId();
                req.sign = payBean.getSign();
                req.timeStamp = payBean.getTimeStamp();
                req.packageValue = payBean.getPackageValue();
                //去调微信
                WeiXinUtil.reg(this).sendReq(req);
            }else{
                Toast.makeText(ctx,payBean.getMessage()+"", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void error(String error) {
        Log.e("error",error+"");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(presenter!=null){
            presenter.ontach();
        }
    }

    private void Pay() {
        Map<String, Object> headmap2 = new HashMap<>();
        headmap2.put("userId", userid + "");
        headmap2.put("sessionId", sessionid + "");
        Map<String, Object> map2 = new HashMap<>();
        map2.put("payType", 1+ "");
        String orderId = SpBase.getString(SeatSelectionActivity.this, "orderId", null);
        map2.put("orderId", orderId + "");
        presenter.post(Interfaces.Payment, headmap2, map2, PayDanBean.class);
    }
}
