package com.nickyluis.nplay_ijkplayer.basic.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nickyluis.nplay_ijkplayer.basic.app.CommonApp;
import com.squareup.leakcanary.RefWatcher;

import butterknife.ButterKnife;

public abstract class BaseFragment extends Fragment implements IBaseView {
    private BaseActivity mActivity;
    private int viewId;
    private View rootView;

    /**
     * 初始化布局
     */
    public abstract int getLayoutRes();

    /**
     * 初始化视图
     */
    public abstract void initView();
    /**
     * 获取layout的id
     *
     * @return
     */
    public abstract int setLayoutId();

    /**
     * loadLayout
     *
     * @param rootView
     */
    public abstract void loadLayout(View rootView);

    /**
     * 设置控件
     */
    public abstract void setUpViews(View rootView);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        viewId = setLayoutId();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (0 == viewId) {
            new Exception(
                    "Please return the layout id in setLayoutId method,as simple as R.layout.cr_news_fragment_layout")
                    .printStackTrace();
            return super.onCreateView(inflater, container, savedInstanceState);
        } else {
            if (rootView == null) {
                rootView = inflater.inflate(viewId, null);
                //检测是否有内存泄露
                RefWatcher refWatcher = CommonApp.getRefWatcher(getActivity());
                refWatcher.watch(this);
                //view注入
                ButterKnife.bind(this, rootView);

                loadLayout(rootView);
                setUpViews(rootView);
            }
            ViewGroup parent = (ViewGroup) rootView.getParent();
            if (parent != null) {
                parent.removeView(rootView);
            }
            return rootView;
        }
    }

    /**
     * 获取Fragment布局文件的View
     *
     * @param inflater
     * @param container
     * @return
     */
    private View getCreateView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(getLayoutRes(), container, false);
    }

    /**
     * 获取当前Fragment状态
     *
     * @return true为正常 false为未加载或正在删除
     */
    private boolean getStatus() {
        return (isAdded() && !isRemoving());
    }

    /**
     * 获取Activity
     *
     * @return
     */
    public BaseActivity getBaseActivity() {
        if (mActivity == null) {
            mActivity = (BaseActivity) getActivity();
        }
        return mActivity;
    }

    @Override
    public void showProgress(boolean flag, String message) {
        if (getStatus()) {
            BaseActivity activity = getBaseActivity();
            if (activity != null) {
                activity.showProgress(flag, message);
            }
        }
    }

    @Override
    public void showProgress(String message) {
        showProgress(true, message);
    }

    @Override
    public void showProgress() {
        showProgress(true);
    }

    @Override
    public void showProgress(boolean flag) {
        showProgress(flag, "");
    }

    @Override
    public void hideProgress() {
        if (getStatus()) {
            BaseActivity activity = getBaseActivity();
            if (activity != null) {
                activity.hideProgress();
            }
        }
    }

    @Override
    public void showToast(int resId) {
        if (getStatus()) {
            BaseActivity activity = getBaseActivity();
            if (activity != null) {
                activity.showToast(resId);
            }
        }
    }

    @Override
    public void showToast(String msg) {
        if (getStatus()) {
            BaseActivity activity = getBaseActivity();
            if (activity != null) {
                activity.showToast(msg);
            }
        }
    }

    @Override
    public Context getContext() {
        return getActivity();
    }

    @Override
    public void close() {
    }
}
