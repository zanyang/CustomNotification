package com.example.myservice;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;

import com.example.myservice.service.MyService;
import com.example.myservice.util.DebugLog;

import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn_start_service)
    Button btnStartService;
    @BindDrawable(R.mipmap.programmer)
    Drawable drawable;
    @BindView(R.id.iv_imageview)
    ImageView ivImageview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_start_service)
    public void onClick() {
        DebugLog.e("test");
        Intent intent = new Intent(MainActivity.this, MyService.class);
        startService(intent);
        ivImageview.setImageDrawable(drawable);
    }

}
