package com.bw.movie.presenter;

import android.app.Service;

import com.bw.movie.contract.Contract;
import com.bw.movie.model.Model;

import java.util.List;
import java.util.Map;

/**
 * <p>文件描述：<p>
 * <p>作者：${adai}<p>
 * <p>创建时间：2019/1/23 9:08<p>
 * <p>更改时间：2019/1/23 9:08<p>
 * <p>版本号：1<p>
 */
public class Presenter implements Contract.Presenter, Contract.MyCallBack {
    private Contract.Model model ;
    private Contract.View view;

    public Presenter(Contract.View view) {
        this.view = view;
        model =new Model();
    }

    @Override
    public void get(String url, Map<String, Object> headmap, Map<String, Object> map, Class aClass) {
        model.get(url,headmap,map,aClass,this);
    }

    @Override
    public void post(String url, Map<String, Object> headmap, Map<String, Object> map, Class aClass) {
        model.post(url,headmap,map,aClass,this);
    }

    @Override
    public void img(String url, Map<String, Object> headmap, Map<String, Object> map, List<Object> list, Class aClass) {
        model.img(url,headmap,map,list,aClass,this);
    }

    @Override
    public void success(Object success) {
        view.success(success);
    }

    @Override
    public void error(String error) {
        view.error(error);
    }
}
