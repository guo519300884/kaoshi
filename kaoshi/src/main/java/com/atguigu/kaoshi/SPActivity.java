package com.atguigu.kaoshi;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;

/**
 * Created by 皇 上 on 2017/1/16.
 */

public class SPActivity extends AppCompatActivity {

    private Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sp);

        //延迟两秒跳转
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                startMainActivity();
            }
        }, 2000);
    }


    public void startMainActivity(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //按下就跳转
        startMainActivity();
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //移除所有消息
        handler.removeCallbacksAndMessages(null);
    }

}
