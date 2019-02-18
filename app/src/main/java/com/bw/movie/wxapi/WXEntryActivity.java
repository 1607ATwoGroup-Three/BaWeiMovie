package com.bw.movie.wxapi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.bw.movie.avtivity.ShowActivity;
import com.bw.movie.bean.LoginData;
import com.bw.movie.contract.Contract;
import com.bw.movie.presenter.Presenter;
import com.bw.movie.utils.Interfaces;
import com.bw.movie.utils.SpBase;
import com.bw.movie.utils.WeiXinUtil;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;

import java.util.HashMap;
import java.util.Map;

public class WXEntryActivity extends AppCompatActivity implements IWXAPIEventHandler,Contract.View {

    private static final String TAG = "WXEntryActivity";

    private Presenter presenter;
    String code;
    private boolean mSuccess;
    private Map<String, Object> headmap;
    private HashMap<String, Object> map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter=new Presenter(this);
        WeiXinUtil.reg(WXEntryActivity.this).handleIntent(getIntent(), this);
        //自动登录
    }
    @Override
    public void onReq(BaseReq baseReq) {
        Log.e(TAG,"1111111");
    }

    // 第三方应用发送到微信的请求处理后的响应结果，会回调到该方法
    @Override
    public void onResp(final BaseResp baseResp) {
        switch (baseResp.errCode) {
            case BaseResp.ErrCode.ERR_OK:
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //得到code
                        code = ((SendAuth.Resp) baseResp).code;
                        headmap = new HashMap<>();
                        map = new HashMap<>();
                        map.put("code",code);
                        Log.d("fantasychongwxlogin", code.toString()+ "");

                        presenter.post(Interfaces.WeChatlanding,headmap,map,LoginData.class);
                    }
                });
                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED://用户拒绝授权
                finish();
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                //取消
                break;
        }
    }
    @Override
    public void success(Object data) {
        if (data instanceof LoginData){
            String message = ((LoginData) data).getMessage();
            Log.e("aaa",((LoginData) data).getMessage()+((LoginData) data).getResult().getUserInfo().getNickName());
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            Intent intent1 = new Intent(WXEntryActivity.this,ShowActivity.class);
            SpBase.save(WXEntryActivity.this,"sessionId",((LoginData) data).getResult().getSessionId());
            SpBase.save(WXEntryActivity.this,"userId",((LoginData) data).getResult().getUserId()+"");
//            intent1.putExtra("userid",((LoginData) data).getResult().getUserId());
//            intent1.putExtra("sion",((LoginData) data).getResult().getSessionId());
            Log.e("下单前存入的请求头",((LoginData) data).getResult().getUserId()+","+((LoginData) data).getResult().getSessionId());
            //封装code
            startActivity(intent1);
            if (mSuccess) {
                Intent intent = new Intent(WXEntryActivity.this, ShowActivity.class);
                //封装code
                SpBase.save(WXEntryActivity.this,"sessionId",((LoginData) data).getResult().getSessionId());
                SpBase.save(WXEntryActivity.this,"userId",((LoginData) data).getResult().getUserId()+"");
//                intent.putExtra("userid",((LoginData) data).getResult().getUserId());
//                intent.putExtra("sion",((LoginData) data).getResult().getSessionId());
                startActivity(intent);
            }else {
                mSuccess=true;
                if (Interfaces.WeChatlanding.equals(message)) {
                    //SpUtil.put(Contacts.mIsLogin,true);
                    Intent intent = new Intent(WXEntryActivity.this, ShowActivity.class);
                    //封装code
                    intent.putExtra("userid",((LoginData) data).getResult().getUserId());
                    intent.putExtra("sion",((LoginData) data).getResult().getSessionId());
                    startActivity(intent);
                }
            }
        }
    }
    @Override
    public void error(String msg) {
        Log.e("微信登录",msg);
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
