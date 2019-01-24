package com.bw.movie.avtivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.bean.LoginData;
import com.bw.movie.contract.Contract;
import com.bw.movie.presenter.Presenter;
import com.bw.movie.utils.LocationUtil;
import com.bw.movie.utils.SpBase;
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
    private TextView youke;
    private MapView show_mapView;

    private LocationSource.OnLocationChangedListener mListener = null;//定位监听器
    private LocationUtil locationUtil;
    private AMap aMap;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_login);

        SpBase.save("cb",false+"");
        boolean cb = Boolean.parseBoolean(SpBase.getString("cb", false + ""));
        if (cb){
            String phone = SpBase.getString( "log_phone", "");
            String pwd = SpBase.getString("log_pwd", "");
            login_phone.setText(phone);
            login_pwd.setText(pwd);
            login_jizhumima.setChecked(true);
        }

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
        login_jizhumima = findViewById(R.id.login_jizhumima);
        login_zidongdenglu = findViewById(R.id.login_zidongdenglu);
        youke = findViewById(R.id.Youke);

        //点击事件
        login_Zhuce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String log_phone = login_phone.getText().toString();
                String log_pwd = login_pwd.getText().toString();
                if (login_jizhumima.isChecked()){
                    SpBase.save("log_phone",log_phone);
                    SpBase.save("log_pwd",log_pwd);
                    SpBase.save("cb",true+"");
                    SpBase.save("log_phone",log_phone);
                }else {
                    SpBase.cancel();
                }
            }
        });

        //游客模式
        youke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, ShowActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void present() {
        Presenter presenter=new Presenter(this);
        Map<String,String> map=new HashMap<>();
        map.put("phone",login_phone.getText()+"");
        map.put("pwd",login_pwd.getText()+"");
    }


    @Override
    public void success(Object success) {
        LoginData loginData= (LoginData) success;
        if(loginData.getStatus().equals("0000")){

        }
    }

    @Override
    public void error(String error) {

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
                SpBase.save("lat",lat+"");
                SpBase.save("lgt",lgt+"");
                SpBase.save("str",str);
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
}
