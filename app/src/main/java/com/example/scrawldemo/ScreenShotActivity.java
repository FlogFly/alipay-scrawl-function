package com.example.scrawldemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;

import com.example.scrawldemo.util.BitmapUtil;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Project:AndroidDemo
 * Author:dyping
 * Date:2017/4/11 14:51
 */

public class ScreenShotActivity extends Activity {

    String path;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_screen_shot);
        ButterKnife.bind(this);
        path = this.getExternalFilesDir(Environment.DIRECTORY_PICTURES)+"/ChintScreenShot.png";
    }


    @OnClick(R.id.screen_shot)
    public void onViewClicked() {
        BitmapUtil.SaveScreenShot(this,path);

        Intent intent = new Intent();
        intent.putExtra("path",path);
        intent.setClass(this,ScrawlActivity.class);
        startActivity(intent);

    }
}
