package com.nickyluis.nplayer;

import android.databinding.ViewDataBinding;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.nickyluis.nplayer.adapter.MyFragmentPagerAdapter;
import com.nickyluis.basic.base.BaseActivityWithToolBar;
import com.nickyluis.nplayer.databinding.ActivityMainBinding;

public class MainActivity extends BaseActivityWithToolBar {
    //binding对象
    private ActivityMainBinding binding;
    //
    private String[] lvs = {"List Item 01", "List Item 02", "List Item 03"};

    @Override
    public int setLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void loadLayout(ViewDataBinding dataBinding) {
        super.loadLayout(dataBinding);
        //databing绑定数据
        binding = (ActivityMainBinding) viewDataBinding;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void onInitialize() {
        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter
                (getSupportFragmentManager(), this);
        binding.mainViewPager.setAdapter(adapter);
        binding.mainTabs.setupWithViewPager(binding.mainViewPager);
        //初始化侧边栏
        initView();
    }

    ///////////////////////////private method//////////////////////////////
    private void initView() {
        //设置菜单列表
        ListView lvLeftMenu = (ListView) findViewById(R.id.lv_left_menu);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout
                .simple_list_item_1, lvs);
        lvLeftMenu.setAdapter(arrayAdapter);
        //设置返回键可用
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //创建返回键，并实现打开关/闭监听
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this,
                binding.drawer, getToolBar(), R.string.open, R.string.close){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };

        mDrawerToggle.syncState();
        binding.drawer.addDrawerListener(mDrawerToggle);
    }

}
