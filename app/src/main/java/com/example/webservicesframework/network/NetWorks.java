package com.example.webservicesframework.network;

import com.example.webservicesframework.api.NetService;
import com.example.webservicesframework.base.RegisterBase;
import com.example.webservicesframework.utils.RetrofitUtils;
import java.util.List;
import java.util.Map;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by YSL on 2016/8/3 0003.
 */
public class NetWorks extends RetrofitUtils {

    protected static final NetService service = getRetrofit().create(NetService.class);

    //设缓存有效期为1天
    protected static final long CACHE_STALE_SEC = 60 * 60 * 24 * 1;
    //查询缓存的Cache-Control设置，使用缓存
    protected static final String CACHE_CONTROL_CACHE = "only-if-cached, max-stale=" + CACHE_STALE_SEC;
    //查询网络的Cache-Control设置。不使用缓存
    protected static final String CACHE_CONTROL_NETWORK = "max-age=0";




//    /**
//     * 方法一：List等泛型类
//     * @param q
//     * @param observer
//     */
//    public static  void Test(String q,Observer<List<ZhuanBi>> observer){
//        setSubscribe(service.login(q),observer);
//    }


    /**
     * 登录测试：Login
     * @param parm
     * @param registerBaseObserver
     */
    public static void Login(Map<String, String> parm, Observer<RegisterBase> registerBaseObserver){
        setSubscribe(service.GetLogin(parm),registerBaseObserver);
    }


    /**
     * 插入观察者-泛型
     * @param observable
     * @param observer
     * @param <T>
     */
    public static <T> void setSubscribe(Observable<T> observable, Observer<T> observer) {
        observable.subscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }



}
