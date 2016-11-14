package com.nickyluis.basic.base;

import android.content.Intent;
import android.databinding.ViewDataBinding;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.nickyluis.nplayer.MainActivity;
import com.nickyluis.nplayer.R;


public abstract class BaseActivityWithToolBar extends BaseActivity {
    //toolbar
    private Toolbar mToolbar;

    @Override
    protected void loadLayout(ViewDataBinding dataBinding) {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
            //在setSupportActionBar(toolbar);之后，不然就报错了
            getSupportActionBar().setTitle(getResources().getString(R.string.app_name));
            // getSupportActionBar().setSubtitle("副标题");
            // getSupportActionBar().setLogo(R.drawable.ic_launcher);
            // 菜单回调
            mToolbar.setOnMenuItemClickListener(onMenuItemClickListener);
        }
    }


    //监听
    private Toolbar.OnMenuItemClickListener onMenuItemClickListener = new Toolbar
            .OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {

                case R.id.action_more:
                    Snackbar.make(mToolbar, "Click More", Snackbar.LENGTH_SHORT).show();
                    break;
            }
            return true;
        }
    };

    //toolbar的菜单
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // 為了讓 Toolbar 的 Menu 有作用，這邊的程式不可以拿掉
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     * 跳转到首页
     */
    public void gotoMainPage() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    /**
     * 获取titlebar
     */
    public Toolbar getToolBar() {
        return mToolbar;
    }

}
