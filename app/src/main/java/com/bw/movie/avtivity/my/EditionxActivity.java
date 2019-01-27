package com.bw.movie.avtivity.my;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.MainActivity;
import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.bean.EditionData;
import com.bw.movie.bean.IDUserData;
import com.bw.movie.contract.Contract;
import com.bw.movie.presenter.Presenter;
import com.bw.movie.utils.APKVersionCodeUtils;
import com.bw.movie.utils.Interfaces;
import com.bw.movie.utils.SpBase;

import java.util.HashMap;
import java.util.Map;

public class EditionxActivity extends BaseActivity implements Contract.View {

    private Presenter presenter;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_editionx);
    }

    @Override
    protected void initData() {

        TextView look_Edition=findViewById(R.id.look_Edition);

        look_Edition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sessionid = SpBase.getString(EditionxActivity.this, "sessionId", "");
                String userid = SpBase.getString(EditionxActivity.this, "userId", "");
                String versionCode = APKVersionCodeUtils.getVersionCode(EditionxActivity.this) + "";
                String versionName = APKVersionCodeUtils.getVerName(EditionxActivity.this);

                Map<String, Object> headmap = new HashMap<>();
                headmap.put("userId", userid + "");
                headmap.put("sessionId", sessionid + "");
                headmap.put("ak",versionCode);
                Map<String, Object> map = new HashMap<>();
                presenter.get(Interfaces.QueryForNewVersion, headmap, map, EditionData.class);



            }
        });

    }

    @Override
    protected void present() {
        presenter = new Presenter(this);
    }


    @Override
    public void success(Object success) {
        if (success instanceof EditionData){
            EditionData editionData= (EditionData) success;
            String message = editionData.getMessage();
            if (editionData.getStatus().equals("0000")){
                Toast.makeText(EditionxActivity.this, message, Toast.LENGTH_SHORT).show();

                int flag = editionData.getFlag();
                if (flag==1){
                    AlertDialog.Builder builder = new AlertDialog.Builder(EditionxActivity.this);
                    builder.setTitle("版本更新信息");
                    builder.setMessage("确定更新吗？");
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {
                            Intent in=new Intent(EditionxActivity.this,EditionGXActivity.class);
                            startActivity(in);
                        }
                    });
                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {

                        }
                    });
                    builder.setNeutralButton("忽略", new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {
                            Toast.makeText(EditionxActivity.this, "那好吧 ", Toast.LENGTH_SHORT).show();
                        }
                    });
                    builder.show();
                }else if(flag==2) {
                    Toast.makeText(EditionxActivity.this, "没新版本，不需要更新", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    public void error(String error) {

    }
}
