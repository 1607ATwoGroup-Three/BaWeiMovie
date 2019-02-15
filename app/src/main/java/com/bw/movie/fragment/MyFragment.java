package com.bw.movie.fragment;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.avtivity.LoginActivity;
import com.bw.movie.avtivity.my.EditionxActivity;
import com.bw.movie.avtivity.my.FollowActivity;
import com.bw.movie.avtivity.my.HornActivity;
import com.bw.movie.avtivity.my.OpinionActivity;
import com.bw.movie.avtivity.my.PayActivity;
import com.bw.movie.avtivity.my.UserActivity;
import com.bw.movie.bean.IDUserData;
import com.bw.movie.bean.PostImageData;
import com.bw.movie.bean.UserSigninBean;
import com.bw.movie.contract.Contract;
import com.bw.movie.presenter.Presenter;
import com.bw.movie.utils.Interfaces;
import com.bw.movie.utils.MyGlideUtil;
import com.bw.movie.utils.SpBase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyFragment extends Fragment implements Contract.View, View.OnClickListener {
    private ImageView my_head_image;
    private TextView my_Name;
    private LinearLayout my_message;
    private LinearLayout my_care;
    private LinearLayout my_pay;
    private LinearLayout my_opinion;
    private LinearLayout my_edition;
    private LinearLayout my_logoff;
    private ImageView my_horn;
    private Presenter presenter;
    private Map<String, Object> headmap;
    private Map<String, Object> map;
    private Button Sign_in;
    private String sessionid;
    private String userid;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_my, null);
        initView(view);
        presenter();
        return view;
    }

    private void presenter() {
        sessionid = SpBase.getString(getContext(), "sessionId", "");
        userid = SpBase.getString(getContext(), "userId", "");
        presenter = new Presenter(this);
        headmap = new HashMap<>();
        headmap.put("userId", userid + "");
        headmap.put("sessionId", sessionid + "");
        Map<String, Object> map = new HashMap<>();
        presenter.get(Interfaces.QueryUserInformation, headmap, map, IDUserData.class);
    }

    private void initView(View view) {

        my_head_image = (ImageView) view.findViewById(R.id.my_head_image);
        my_Name = (TextView) view.findViewById(R.id.my_Name);
        my_message = (LinearLayout) view.findViewById(R.id.my_message);
        my_care = (LinearLayout) view.findViewById(R.id.my_care);
        my_pay = (LinearLayout) view.findViewById(R.id.my_pay);
        my_opinion = (LinearLayout) view.findViewById(R.id.my_opinion);
        my_edition = (LinearLayout) view.findViewById(R.id.my_edition);
        my_logoff = (LinearLayout) view.findViewById(R.id.my_logoff);
        my_horn = (ImageView) view.findViewById(R.id.my_horn);
        Sign_in = (Button) view.findViewById(R.id.Sign_in);

        my_head_image.setOnClickListener(this);
        my_Name.setOnClickListener(this);
        my_message.setOnClickListener(this);
        my_care.setOnClickListener(this);
        my_pay.setOnClickListener(this);
        my_opinion.setOnClickListener(this);
        my_edition.setOnClickListener(this);
        my_logoff.setOnClickListener(this);
        my_horn.setOnClickListener(this);
        Sign_in.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.my_horn:
                Intent inhorn = new Intent(getContext(), HornActivity.class);
                startActivity(inhorn);
                break;
            case R.id.my_head_image:
                Intent intent1 = new Intent(Intent.ACTION_PICK);
                intent1.setType("image/*");
                startActivityForResult(intent1, 0);
                break;
            case R.id.my_Name:
                break;
            case R.id.my_message:
                Intent intent = new Intent(getContext(), UserActivity.class);
                startActivity(intent);
                break;
            case R.id.my_care:
                Intent inCare = new Intent(getContext(), FollowActivity.class);
                startActivity(inCare);
                break;
            case R.id.my_pay:
                Intent inPay = new Intent(getContext(), PayActivity.class);
                startActivity(inPay);
                break;
            case R.id.my_opinion:
                Intent in = new Intent(getContext(), OpinionActivity.class);
                startActivity(in);
                break;
            case R.id.my_edition:
                Intent ine = new Intent(getContext(), EditionxActivity.class);
                startActivity(ine);
                break;
            case R.id.my_logoff:
                Intent inlo = new Intent(getContext(), LoginActivity.class);
                startActivity(inlo);
                getActivity().finish();
                break;
            case R.id.Sign_in:
                Map<String, Object> headmap2 = new HashMap<>();
                headmap2.put("userId", userid + "");
                headmap2.put("sessionId", sessionid + "");
                Map<String, Object> map2 = new HashMap<>();
                presenter.get(Interfaces.UserCheckIn, headmap2, map2, UserSigninBean.class);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            return;
        }
        if (requestCode == 0) {
            String filePath = getFilePath(null, requestCode, data);
            /**
             * 这里是用的上传头像
             */
            Map<String, Object> map = new HashMap<>();
            List<Object> list = new ArrayList<>();
            list.add(filePath);
            presenter.img(Interfaces.UploadHead, headmap, map, list, PostImageData.class);
//            objects.add(filePath);
//            fpl_image_adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void success(Object success) {
        if (success instanceof IDUserData) {
            IDUserData idUserData = (IDUserData) success;
            IDUserData.ResultBean result = idUserData.getResult();
            String nickName = result.getNickName();
            String headPic = result.getHeadPic();
            my_Name.setText(nickName);
            MyGlideUtil.setCircleImage(getContext(), headPic, my_head_image);
        } else if (success instanceof PostImageData) {
            PostImageData data = (PostImageData) success;
            if (data.getStatus().equals("0000")) {
                MyGlideUtil.setCircleImage(getContext(), data.getHeadPath(), my_head_image);
                Toast.makeText(getContext(), data.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }else if(success instanceof UserSigninBean){
            UserSigninBean userSigninBean= (UserSigninBean) success;
            final String message = userSigninBean.getMessage();
            if (userSigninBean.getStatus().equals("0000")){
                Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void error(String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
    }


    /**
     * 得到图片的路径
     *
     * @param fileName
     * @param requestCode
     * @param data
     * @return
     */
    public String getFilePath(String fileName, int requestCode, Intent data) {
        if (requestCode == 1) {
            return fileName;
        } else if (requestCode == 0) {
            Uri uri = data.getData();
            String[] proj = {MediaStore.Images.Media.DATA};
            Cursor actualimagecursor = getActivity().managedQuery(uri, proj, null, null, null);
            int actual_image_column_index = actualimagecursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            actualimagecursor.moveToFirst();
            String img_path = actualimagecursor
                    .getString(actual_image_column_index);
            // 4.0以上平台会自动关闭cursor,所以加上版本判断,OK
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.ICE_CREAM_SANDWICH)
                actualimagecursor.close();
            return img_path;
        }
        return null;
    }
}
