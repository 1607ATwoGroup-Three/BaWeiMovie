package com.bw.movie.contract;

import java.util.List;
import java.util.Map;

/**
 * <p>文件描述：<p>
 * <p>作者：${adai}<p>
 * <p>创建时间：2019/1/23 9:08<p>
 * <p>更改时间：2019/1/23 9:08<p>
 * <p>版本号：1<p>
 */
public interface Contract {
    interface Model {
        void get(String url, Map<String,Object> headmap,Map<String,Object> map,Class aClass,MyCallBack myCallBack);
        void post(String url, Map<String,Object> headmap,Map<String,Object> map,Class aClass,MyCallBack myCallBack);
        void img(String url, Map<String,Object> headmap, Map<String,Object> map, List<Object> list, Class aClass, MyCallBack myCallBack);
    }

    interface View <T>{
        void success(T success);
        void error(String error);
    }

    interface Presenter {
        void get(String url, Map<String,Object> headmap,Map<String,Object> map,Class aClass);
        void post(String url, Map<String,Object> headmap,Map<String,Object> map,Class aClass);
        void img(String url, Map<String,Object> headmap,Map<String,Object> map,List<Object> list,Class aClass);
    }
    interface MyCallBack <T>{
        void success(T success);
        void error(String error);
    }
    interface PermissionListener {
//        动态获取权限成功
        void granted();
//        失败
        void denied(List<String> deniedList);
    }
}
