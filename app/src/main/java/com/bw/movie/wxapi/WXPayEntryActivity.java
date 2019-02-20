package com.bw.movie.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.bw.movie.avtivity.my.PayActivity;
import com.bw.movie.utils.WeiXinUtil;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;

public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        WeiXinUtil.reg(WXPayEntryActivity.this).handleIntent(getIntent(), this);

    }
    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp baseResp) {
        if (baseResp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            int errCord = baseResp.errCode;
            if (errCord == 0) {

                Toast.makeText(this, "支付成功！", Toast.LENGTH_SHORT).show();

            } else if (errCord == -1) {
                Toast.makeText(this, "支付失败！", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "用户取消了！", Toast.LENGTH_SHORT).show();
            }
            Intent intent = new Intent(WXPayEntryActivity.this,PayActivity.class);
            intent.putExtra("code",errCord);
            startActivity(intent);
            finish();
            //这里接收到了返回的状态码可以进行相应的操作，如果不想在这个页面操作可以把状态码存在本地然后finish掉这个页面，这样就回到了你调起支付的那个页面
            //获取到你刚刚存到本地的状态码进行相应的操作就可以了
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        WeiXinUtil.reg(WXPayEntryActivity.this).handleIntent(intent, this);
    }
}
