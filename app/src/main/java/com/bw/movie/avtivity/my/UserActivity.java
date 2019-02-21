package com.bw.movie.avtivity.my;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.bean.IDUserData;
import com.bw.movie.bean.UpdateUserMessage;
import com.bw.movie.contract.Contract;
import com.bw.movie.presenter.Presenter;
import com.bw.movie.utils.Interfaces;
import com.bw.movie.utils.MyGlideUtil;
import com.bw.movie.utils.SpBase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class UserActivity extends BaseActivity implements View.OnClickListener, Contract.View {

    private ImageView my_message_reset;
    private ImageView my_message;
    private TextView user_name;
    private TextView user_sex;
    private TextView user_birthday;
    private TextView user_phone;
    private TextView user_emil;
    private ImageView User_Back;
    private Button user_save;
    private String[] sexArry = new String[]{"男", "女"};
    private int index = 0;
    private String sessionid;
    private String userid;
    private Presenter presenter;

    protected void initView() {
        BaseActivity.doublekeydown(false);
        BaseActivity.fullScreen(UserActivity.this, true);
        setContentView(R.layout.activity_user);

    }

    @Override
    protected void initData() {
        my_message_reset = (ImageView) findViewById(R.id.my_message_reset);
        my_message = (ImageView) findViewById(R.id.my_message);
        user_name = (TextView) findViewById(R.id.user_name);
        user_sex = (TextView) findViewById(R.id.user_sex);
        user_birthday = (TextView) findViewById(R.id.user_birthday);
        user_phone = (TextView) findViewById(R.id.user_phone);
        user_emil = (TextView) findViewById(R.id.user_emil);
        user_save = findViewById(R.id.user_save);

        my_message_reset.setOnClickListener(this);
        User_Back = (ImageView) findViewById(R.id.User_Back);
        User_Back.setOnClickListener(this);
        user_save.setOnClickListener(this);
        user_birthday.setOnClickListener(this);
        user_sex.setOnClickListener(this);
    }

    @Override
    protected void present() {
        sessionid = SpBase.getString(this, "sessionId", "");
        userid = SpBase.getString(this, "userId", "");
        presenter = new Presenter(this);
        Map<String, Object> headmap = new HashMap<>();
        headmap.put("userId", userid + "");
        headmap.put("sessionId", sessionid + "");
        Map<String, Object> map = new HashMap<>();
        presenter.get(Interfaces.QueryUserInformation, headmap, map, IDUserData.class);
    }

    @Override
    public void onClick(View v) {
        my_message_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserActivity.this, ResetPwdActivity.class);
                startActivity(intent);
            }
        });
        User_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //出生日期
        user_birthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
            }
        });
        //性别
        user_sex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSexChooseDialog();
                v.setFocusable(false);
                v.setFocusableInTouchMode(false);
                user_sex.setFocusable(true);
                user_sex.setFocusableInTouchMode(true);
            }
        });
        //保存修改的信息
        user_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, Object> headmap2 = new HashMap<>();
                headmap2.put("userId", userid + "");
                headmap2.put("sessionId", sessionid + "");
                String u_name = user_name.getText().toString();
                String u_sex = user_sex.getText().toString();
                String u_emil = user_emil.getText().toString();
                Map<String, Object> map2 = new HashMap<>();
                map2.put("nickName",u_name+"");
                map2.put("sex",u_sex+"");
                map2.put("email",u_emil+"");
                presenter.post(Interfaces.ModifyInformation, headmap2, map2, UpdateUserMessage.class);
            }
        });
    }

    @Override
    public void success(Object success) {
        if (success instanceof IDUserData) {
            IDUserData idUserData = (IDUserData) success;
            IDUserData.ResultBean result = idUserData.getResult();
            String nickName = result.getNickName();
            String headPic = result.getHeadPic();
            String phone = result.getPhone();

            //性别
            if (result.getSex() == 1) {
                user_sex.setText("男");
            } else {
                user_sex.setText("女");
            }
            //出生日期
            long currentTimeMillis = result.getBirthday();
            Date d = new Date(currentTimeMillis);
            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
            String format = f.format(d);
            user_birthday.setText(format);
            user_name.setText(nickName);
            user_phone.setText(phone);
            String register_emil = SpBase.getString(UserActivity.this, "register_emil", null);
            user_emil.setText(register_emil);
            MyGlideUtil.setCircleImage(UserActivity.this, headPic, my_message);
        }
    }

    @Override
    public void error(String error) {
        Log.e("error",error+"");
    }

    private void getData() {
        TimePickerView pvTime = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                user_birthday.setText(getTime(date));
            }
        }).setType(new boolean[]{true, true, true, false, false, false})
                .isDialog(true)
                .build();

        Dialog mDialog = pvTime.getDialog();
        if (mDialog != null) {

            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    Gravity.BOTTOM);

            params.leftMargin = 0;
            params.rightMargin = 0;
            pvTime.getDialogContainerLayout().setLayoutParams(params);

            Window dialogWindow = mDialog.getWindow();
            if (dialogWindow != null) {
                dialogWindow.setWindowAnimations(com.bigkoo.pickerview.R.style.picker_view_slide_anim);//修改动画样式
                dialogWindow.setGravity(Gravity.BOTTOM);//改成Bottom,底部显示
            }
        }
        pvTime.show();
    }
    private String getTime(Date date) {
        //可根据需要自行截取数据显示
        //impleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }
    //性别选择
    private void showSexChooseDialog() {
        //自定义对话框
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setSingleChoiceItems(sexArry, index, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {// which是被选中的位置
                index = which;
                user_sex.setText(sexArry[which]);
                dialog.dismiss();// 随便点击一个item消失对话框，不用点击确认取消
            }
        });
        builder.show();//弹出框
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(presenter!=null){
            presenter.ontach();
        }
    }
}
