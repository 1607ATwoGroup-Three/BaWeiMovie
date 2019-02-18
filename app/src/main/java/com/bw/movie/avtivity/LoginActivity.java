package com.bw.movie.avtivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.bw.movie.MainActivity;
import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.bean.LoginData;
import com.bw.movie.contract.Contract;
import com.bw.movie.presenter.Presenter;
import com.bw.movie.utils.EncryptUtil;
import com.bw.movie.utils.Interfaces;
import com.bw.movie.utils.LocationUtil;
import com.bw.movie.utils.SpBase;
import com.bw.movie.utils.WeChatUtil;
import com.bw.movie.utils.WeiXinUtil;
import com.bw.movie.wxapi.WXEntryActivity;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.xw.repo.XEditText;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends BaseActivity implements Contract.View,LocationSource{

    private Button login_button;
    private TextView login_Zhuce;
    private XEditText login_phone;
    private XEditText login_pwd;
    private CheckBox login_jizhumima;
    private CheckBox login_zidongdenglu;
    private SharedPreferences.Editor edit;
    private MapView show_mapView;
    private ImageView login_wx;

    private LocationSource.OnLocationChangedListener mListener = null;//定位监听器
    private LocationUtil locationUtil;
    private AMap aMap;
    private Presenter presenter;
    private String log_pwd;
    private String log_phone;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_login);
        BaseActivity.doublekeydown(true);
        BaseActivity.fullScreen(LoginActivity.this,false);
    }

    @Override
    protected void initData() {
        show_mapView=findViewById(R.id.login_mapView);
        if(aMap==null){
            aMap =show_mapView.getMap();
        }
        setLocationCallBack();
        aMap.setLocationSource(this);
        //设置缩放级别
        aMap.moveCamera(CameraUpdateFactory.zoomTo(15));
        //显示定位层并可触发，默认false
        aMap.setMyLocationEnabled(true);


        login_Zhuce = findViewById(R.id.login_Zhuce);
        login_button = findViewById(R.id.login_button);
        login_phone = findViewById(R.id.login_phone);
        login_pwd = findViewById(R.id.login_pwd);
        login_wx = findViewById(R.id.login_wx);
        login_jizhumima = findViewById(R.id.login_jizhumima);
        login_zidongdenglu = findViewById(R.id.login_zidongdenglu);
        boolean cb = Boolean.parseBoolean(SpBase.getString(LoginActivity.this,"cb", false + ""));
        String phone = SpBase.getString(LoginActivity.this,"ter_phone",null);
        login_phone.setText(phone+"");
        if (cb){
            String pwd = SpBase.getString(LoginActivity.this,"log_pwd", null);
            login_pwd.setText(pwd);
            login_jizhumima.setChecked(true);
        }

        //点击事件
        login_Zhuce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        //登录
        login_button.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View v) {

                Map<String,Object> headmap=new HashMap<>();
                Map<String,Object> map=new HashMap<>();
                map.put("phone",login_phone.getText()+"");
                String pwd = login_pwd.getTrimmedString ().trim ();//XEdit得值的方法
                String encrypt = EncryptUtil.encrypt (pwd);//加密
                map.put("pwd",encrypt);
                SpBase.save(LoginActivity.this,"pwd",pwd+"");
                presenter.post(Interfaces.Land,headmap,map,LoginData.class);
            }
        });
        login_wx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!WeChatUtil.wechat(LoginActivity.this).isWXAppInstalled()) {
                    Toast.makeText(LoginActivity.this, "请先安装应用", Toast.LENGTH_SHORT).show();
                } else {
                    //  验证
                    SendAuth.Req req = new SendAuth.Req();
                    req.scope = "snsapi_userinfo";
                    req.state = "wechat_sdk_demo_test";
                    WeiXinUtil.reg(LoginActivity.this).sendReq(req);
                }
            }
        });
    }

    @Override
    protected void present() {
        presenter = new Presenter(this);
    }


    @Override
    public void success(Object success) {
        LoginData loginData= (LoginData) success;
        String message = loginData.getMessage();
        if(loginData.getStatus().equals("0000")){
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LoginActivity.this, ShowActivity.class);
            String sessionId = loginData.getResult().getSessionId();
            int userId = loginData.getResult().getUserId();
            SpBase.save(LoginActivity.this,"sessionId",sessionId);
            SpBase.save(LoginActivity.this,"userId",userId+"");
            log_phone = login_phone.getText().toString().trim();
            log_pwd = login_pwd.getText().toString().trim();
            SpBase.save(LoginActivity.this,"ter_phone",log_phone);
            if (login_jizhumima.isChecked()){
                SpBase.save(LoginActivity.this,"log_pwd", log_pwd);
                SpBase.save(LoginActivity.this,"cb",true+"");
            }else{
                SpBase.remove(LoginActivity.this,"log_pwd");
                SpBase.remove(LoginActivity.this,"cb");
            }
            startActivity(intent);
            finish();
        }else {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void error(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

//    这是定位
    private void setLocationCallBack() {
        locationUtil = new LocationUtil();
        locationUtil.setLocationCallBack(new LocationUtil.ILocationCallBack() {
            @Override
            public void callBack(String str,double lat,double lgt,AMapLocation aMapLocation) {
                mListener.onLocationChanged(aMapLocation);
                Log.e("经度",lat+"");
                Log.e("玮度",lgt+"");
                Log.e("城区",str);
                SpBase.save(LoginActivity.this,"lat",lat+"");
                SpBase.save(LoginActivity.this,"lgt",lgt+"");
                SpBase.save(LoginActivity.this,"str",str);
            }
        });
    }

    //    这是一个定位的回调
    @Override
    public void activate(OnLocationChangedListener onLocationChangedListener) {
        mListener = onLocationChangedListener;
        locationUtil.startLocate(getApplicationContext());
    }

    @Override
    public void deactivate() {
        mListener = null;
    }

    @Override
    protected void onResume() {
        super.onResume();
        BaseActivity.doublekeydown(true);
        String phone = SpBase.getString(LoginActivity.this,"ter_phone","");
        login_phone.setText(phone);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
