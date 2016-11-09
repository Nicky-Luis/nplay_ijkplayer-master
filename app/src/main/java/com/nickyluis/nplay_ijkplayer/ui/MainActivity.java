package com.nickyluis.nplay_ijkplayer.ui;

import android.content.Intent;
import android.view.View;

import com.nickyluis.nplay_ijkplayer.R;
import com.nickyluis.nplay_ijkplayer.common.base.BaseActivity;

import butterknife.OnClick;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    @Override
    public int setLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void loadLayout(View v) {

    }

    @Override
    protected void setUpView() {

    }

    @Override
    public void initPresenter() {

    }

    @OnClick({R.id.btn_h, R.id.btn_v, R.id.btn_live, R.id.btn_origin})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_h:
                /**半屏播放器*/
                startActivity(HPlayerActivity.class);
                break;
            case R.id.btn_v:
                /**竖屏播放器*/
                startActivity(PlayerActivity.class);
                break;
            case R.id.btn_live:
                /**竖屏直播播放器*/
                startActivity(PlayerLiveActivity.class);
                break;
            case R.id.btn_origin:
                /**ijkplayer原生的播放器*/
                startActivity(OriginPlayerActivity.class);
                break;
        }
    }

    private void startActivity(Class<?> cls) {
        Intent intent = new Intent(MainActivity.this, cls);
        startActivity(intent);
    }
}
