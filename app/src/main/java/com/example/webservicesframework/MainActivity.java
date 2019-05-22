package com.example.webservicesframework;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.example.webservicesframework.base.RegisterBase;
import com.example.webservicesframework.network.NetWorks;
import java.util.HashMap;
import java.util.Map;

import okhttp3.RequestBody;
import rx.Observer;

/**
 * Created by YSL on 2016/8/3 0003.
 */
public class MainActivity extends AppCompatActivity {
    private Map<String, String> parm;
    private Map<String, RequestBody> parmBody;
    private TextView mTv,mTv2;
    RegisterBase registerBase=new  RegisterBase();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTv =findViewById(R.id.main_tv);
        mTv2 =findViewById(R.id.main_tv2);

        parm=new HashMap<>();
        parm.put("uphone","1234567");
        parm.put("upassword","1234567");

        mTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTv2.setText("0");
            }
        });
        mTv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //测试效果
                NetWorks.Login(parm, new Observer<RegisterBase>() {
                    @Override
                    public void onCompleted() {
                        //完成
                        Log.e("onCompleted","-----------------");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("onError","---------------------");
                        //失败
                    }

                    @Override
                    public void onNext(RegisterBase registerBase) {
                        //成功
                        mTv2.setText(""+registerBase.getCode());
                        Log.e("onNext",""+registerBase.getCode());
                    }
                });

            }
        });


    }
}
