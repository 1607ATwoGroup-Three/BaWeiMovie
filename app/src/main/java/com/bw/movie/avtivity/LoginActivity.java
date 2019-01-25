package com.bw.movie.avtivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

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
import com.bw.movie.utils.EncryptUtil;
import com.bw.movie.utils.Interfaces;
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
    private Presenter presenter;
    private String log_pwd;
    private String log_phone;

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
        //登录
        login_button.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View v) {

                Map<String,Object> headmap=new HashMap<>();
                Map<String,Object> map=new HashMap<>();
                map.put("phone",login_phone.getText()+"");
                String pwd = login_pwd.getTrimmedString ().trim ();
                String encrypt = EncryptUtil.encrypt (pwd);
                map.put("pwd",encrypt);
                presenter.post(Interfaces.Land,headmap,map,LoginData.class);

                log_phone = login_phone.getText().toString();
                log_pwd = login_pwd.getText().toString();
                if (login_jizhumima.isChecked()){
                    SpBase.save("log_phone", log_phone);
                    SpBase.save("log_pwd", log_pwd);
                    SpBase.save("cb",true+"");
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
        presenter = new Presenter(this);
    }


    @Override
    public void success(Object success) {
        LoginData loginData= (LoginData) success;
        String message = loginData.getMessage();
        if(loginData.getStatus().equals("0000")){
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LoginActivity.this, ShowActivity.class);
            startActivity(intent);

            String sessionId = loginData.getResult().getSessionId();
            int userId = loginData.getResult().getUserId();
            SpBase.save("sessionId",sessionId);
            SpBase.save("userId",userId+"");
            Toast.makeText(this, sessionId, Toast.LENGTH_SHORT).show();
            Toast.makeText(this, userId+"", Toast.LENGTH_SHORT).show();
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

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("121111111","11111111111");
        String phone = SpBase.getString("ter_phone","");
        login_phone.setText(phone);
    }
}
