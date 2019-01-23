package com.bw.movie.model;

import com.bw.movie.contract.Contract;
import com.bw.movie.utils.Retrofits;
import com.google.gson.Gson;

import java.util.List;
import java.util.Map;

/**
 * <p>文件描述：<p>
 * <p>作者：${adai}<p>
 * <p>创建时间：2019/1/23 9:08<p>
 * <p>更改时间：2019/1/23 9:08<p>
 * <p>版本号：1<p>
 */
public class Model implements Contract.Model {

    @Override
    public void get(String url, Map<String, Object> headmap, Map<String, Object> map, final Class aClass, final Contract.MyCallBack myCallBack) {
        Retrofits.getInstance().get(url,headmap,map).getonclcked(new Retrofits.onclick() {
            @Override
            public void success(String strjson) {
                Gson gson =new Gson();
                Object o = gson.fromJson(strjson, aClass);
                myCallBack.success(o);
            }

            @Override
            public void error(String error) {
                myCallBack.error(error);
            }
        });
    }

    @Override
    public void post(String url, Map<String, Object> headmap, Map<String, Object> map, final Class aClass, final Contract.MyCallBack myCallBack) {
        Retrofits.getInstance().post(url,headmap,map).getonclcked(new Retrofits.onclick() {
            @Override
            public void success(String strjson) {
                Gson gson =new Gson();
                Object o = gson.fromJson(strjson, aClass);
                myCallBack.success(o);
            }

            @Override
            public void error(String error) {
                myCallBack.error(error);
            }
        });
    }

    @Override
    public void img(String url, Map<String, Object> headmap, Map<String, Object> map, List<Object> list, final Class aClass, final Contract.MyCallBack myCallBack) {
        Retrofits.getInstance().image(url,headmap,map,list).getonclcked(new Retrofits.onclick() {
            @Override
            public void success(String strjson) {
                Gson gson =new Gson();
                Object o = gson.fromJson(strjson, aClass);
                myCallBack.success(o);
            }

            @Override
            public void error(String error) {
                myCallBack.error(error);
            }
        });
    }
}
