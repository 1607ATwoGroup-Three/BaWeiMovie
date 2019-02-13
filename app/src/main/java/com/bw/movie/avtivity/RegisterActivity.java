package com.bw.movie.avtivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.Selection;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.bean.RegisterData;
import com.bw.movie.contract.Contract;
import com.bw.movie.presenter.Presenter;
import com.bw.movie.utils.EncryptUtil;
import com.bw.movie.utils.Interfaces;
import com.bw.movie.utils.SpBase;
import com.xw.repo.XEditText;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends BaseActivity implements Contract.View {

    private XEditText register_name;
    private XEditText register_sex;
    private XEditText register_birthday;
    private XEditText register_phone;
    private XEditText register_emil;
    private XEditText register_pwd;
    private Button register_button;

    private String[] sexArry=new String[]{"男","女"};
    private int index=0;
    private boolean phone=false;
    private Map<String,Object> queryMap=new HashMap<>();
    private int sexType=1;
    private Presenter presenter;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_register);
        BaseActivity.doublekeydown(false);
        BaseActivity.fullScreen(RegisterActivity.this,false);
    }

    @Override
    protected void initData() {
        register_name = findViewById(R.id.Register_name);
        register_sex = findViewById(R.id.Register_sex);
        register_birthday = findViewById(R.id.Register_birthday);
        register_phone = findViewById(R.id.Register_phone);
        register_emil = findViewById(R.id.Register_emil);
        register_pwd = findViewById(R.id.Register_pwd);
        register_button = findViewById(R.id.Register_button);

        //出生日期
        register_birthday.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    getData();
                    v.setFocusable(false);
                    v.setFocusableInTouchMode(false);
                    v.setFocusable(true);
                    v.setFocusableInTouchMode(true);
                }
            }
        });
        //性别
        register_sex.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    showSexChooseDialog();
                    v.setFocusable(false);
                    v.setFocusableInTouchMode(false);
                    register_sex.setFocusable(true);
                    register_sex.setFocusableInTouchMode(true);
                }
            }
        });

        //判断手机号格式
        register_phone.setOnXTextChangeListener (new XEditText.OnXTextChangeListener () {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String trim = register_phone.getTrimmedString ().trim ();
                if (!trim.isEmpty ()) {
                    String substring = trim.substring (0, 1);
                    if (!substring.equals ("1")) {
                        Toast.makeText (RegisterActivity.this, "手机号格式不正确", Toast.LENGTH_SHORT).show ();
                        register_phone.setText ("");
                    }
                    if (trim.length () > 1) {
                        String substring1 = trim.substring (1, 2);
                        if (substring1.equals ("3") || substring1.equals ("5") || substring1.equals ("7") || substring1.equals ("8")) {

                        } else {
                            Toast.makeText (RegisterActivity.this, "手机号格式不正确", Toast.LENGTH_SHORT).show ();
                            register_phone.setText ("1");
                            Editable etext = register_phone.getText ();
                            Selection.setSelection (etext, etext.length ());
                        }
                    }
                }
            }
        });
        //手机号框失去焦点进行正则验证
        register_phone.setOnFocusChangeListener (new View.OnFocusChangeListener () {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    String trim = register_phone.getTrimmedString ().trim ();
                    if (!trim.isEmpty ()) {
                        phone = isPhone (trim);
                        if (!phone)
                            Toast.makeText (RegisterActivity.this, "请按照正确的邮箱格式输入", Toast.LENGTH_SHORT).show ();
                        else phone = true;
                    }
                }
            }
        });

        //注册按钮
        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean b=allNotEmptp();
                if (b){
                    setParam();
                    Map<String,Object> headmap =new HashMap<>();
                    presenter.post(Interfaces.Register,headmap,queryMap,RegisterData.class);
                }
            }
        });

    }

    @Override
    protected void present() {
        presenter = new Presenter(this);
    }

    //注册前判断
    private boolean allNotEmptp() {
        if (register_name.getTrimmedString ().trim ().isEmpty ()) {
            Toast.makeText (this, "请输入您的昵称", Toast.LENGTH_SHORT).show ();
            return false;
        }
        if (register_sex.getTrimmedString ().trim ().isEmpty ()) {
            Toast.makeText (this, "请选择您的性别", Toast.LENGTH_SHORT).show ();
            return false;
        }
        if (register_birthday.getTrimmedString ().trim ().isEmpty ()) {
            Toast.makeText (this, "请选择您的出生日期", Toast.LENGTH_SHORT).show ();
            return false;
        }
        if (!phone) {
            Toast.makeText (this, "请正确输入您的手机号", Toast.LENGTH_SHORT).show ();
            return false;
        }
        if (register_emil.getTrimmedString ().trim ().isEmpty ()) {
            Toast.makeText (this, "请输入您的邮箱", Toast.LENGTH_SHORT).show ();
            return false;
        }
        if (register_pwd.getTrimmedString ().trim ().isEmpty ()) {
            Toast.makeText (this, "请输入您的登录密码", Toast.LENGTH_SHORT).show ();
            return false;
        }
        return true;
    }
    //注册前传参
    private void setParam() {
        queryMap.put ("nickName", register_name.getTrimmedString ().trim ());
        queryMap.put ("phone", register_phone.getTrimmedString ().trim ());
        String pwd = register_pwd.getTrimmedString ().trim ();
        String encrypt = EncryptUtil.encrypt (pwd);
        queryMap.put ("pwd", encrypt);
        queryMap.put ("pwd2", encrypt);

        if (register_sex.getTrimmedString ().trim ().equals ("男")) {
            sexType = 1;
        } else {
            sexType = 2;
        }
        queryMap.put ("sex", sexType);
        queryMap.put ("birthday", register_birthday.getTrimmedString ().trim ());
        queryMap.put ("imei", "123456");
        queryMap.put ("ua", "小米/红米");
        queryMap.put ("screenSize", "5.0");
        queryMap.put ("os", "android");
        queryMap.put ("email", register_emil.getTrimmedString ().trim ());
    }


    //性别选择
    private void showSexChooseDialog(){
        //自定义对话框
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setSingleChoiceItems(sexArry, index, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {// which是被选中的位置
                index=which;
                register_sex.setText(sexArry[which]);
                dialog.dismiss();// 随便点击一个item消失对话框，不用点击确认取消
            }
        });
        builder.show();//弹出框
    }

    //出生日期三级联动 自定义Dialog
    private void getData() {
        TimePickerView pvTime = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                register_birthday.setText (getTime (date));
            }
        }).setType (new boolean[]{true, true, true, false, false, false})
                .isDialog (true)
                .build ();

        Dialog mDialog = pvTime.getDialog ();
        if (mDialog != null) {

            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams (
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    Gravity.BOTTOM);

            params.leftMargin = 0;
            params.rightMargin = 0;
            pvTime.getDialogContainerLayout ().setLayoutParams (params);

            Window dialogWindow = mDialog.getWindow ();
            if (dialogWindow != null) {
                dialogWindow.setWindowAnimations (com.bigkoo.pickerview.R.style.picker_view_slide_anim);//修改动画样式
                dialogWindow.setGravity (Gravity.BOTTOM);//改成Bottom,底部显示
            }
        }
        pvTime.show ();
    }
    private String getTime(Date date) {
        //可根据需要自行截取数据显示
        //impleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat format = new SimpleDateFormat ("yyyy-MM-dd");
        return format.format (date);
    }

    /**
     * 正则验证手机号
     *
     * @param phone
     * @return
     */
    public boolean isPhone(String phone) {
        String regex = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$";
        if (phone.length () != 11) {
            Toast.makeText (this, "手机号应为11位数", Toast.LENGTH_SHORT).show ();
            return false;
        } else {
            Pattern p = Pattern.compile (regex);
            Matcher m = p.matcher (phone);
            boolean isMatch = m.matches ();
            if (!isMatch) {
                Toast.makeText (this, "请填入正确的手机号", Toast.LENGTH_SHORT).show ();
            }
            return isMatch;
        }
    }

    @Override
    public void success(Object success) {
        RegisterData registerData= (RegisterData) success;
        String message = registerData.getMessage();
        if (registerData.getStatus().equals("0000")){
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            String ter_phone = register_phone.getText().toString();
            SpBase.save(RegisterActivity.this,"ter_phone",ter_phone);
            Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
            startActivity(intent);
        }else {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void error(String error) {
        Toast.makeText(ctx, error, Toast.LENGTH_SHORT).show();
    }
}
