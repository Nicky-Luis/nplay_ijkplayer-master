package com.nickyluis.nplay_ijkplayer;

import android.view.View;

import com.nickyluis.nplay_ijkplayer.adapter.MyFragmentPagerAdapter;
import com.nickyluis.nplay_ijkplayer.basic.base.BaseActivity;
import com.nickyluis.nplay_ijkplayer.databinding.ActivityMainBinding;

public class MainActivity extends BaseActivity {

    @Override
    public int setLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void loadLayout(View v) {
    }

    //通过databind绑定数据
    @Override
    public boolean setDataBindingFlag() {
        return true;
    }

    @Override
    protected void onInitialize() {
        //databing绑定数据
        ActivityMainBinding binding = (ActivityMainBinding) viewDataBinding;
        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(getSupportFragmentManager(),
                this);
        binding.mainTabVp.setAdapter(adapter);

        //TabLayout
        binding. tablayout.setupWithViewPager(binding.mainTabVp);

    }

    @Override
    public void initPresenter() {

    }

}
