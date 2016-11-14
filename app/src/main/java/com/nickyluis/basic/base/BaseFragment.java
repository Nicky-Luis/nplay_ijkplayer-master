package com.nickyluis.basic.base;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nickyluis.basic.app.CommonApp;
import com.squareup.leakcanary.RefWatcher;

public abstract class BaseFragment extends Fragment implements IBaseView {
    private BaseActivity mActivity;
    private int viewId;
    private View rootView;
    protected ViewDataBinding viewDataBinding;

    /**
     * 初始化布局
     */
    public abstract void onGetArgument();

    /**
     * 获取layout的id
     *
     * @return
     */
    public abstract int setLayoutId();

    /**
     * loadLayout
     *
     * @param viewDataBinding
     */
    public abstract void loadLayout(ViewDataBinding viewDataBinding);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onGetArgument();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewId = setLayoutId();
        if (0 == viewId) {
            new Exception(
                    "Please return the layout id in setLayoutId method,as simple as R" +
                            ".layout.cr_news_fragment_layout")
                    .printStackTrace();
            return super.onCreateView(inflater, container, savedInstanceState);
        } else {
            if (rootView == null) {
                viewDataBinding = DataBindingUtil.inflate(inflater, viewId, container,
                        false);
                rootView = viewDataBinding.getRoot();
                //检测是否有内存泄露
                RefWatcher refWatcher = CommonApp.getRefWatcher(getActivity());
                refWatcher.watch(this);
                loadLayout(viewDataBinding);
            }
            ViewGroup parent = (ViewGroup) rootView.getParent();
            if (parent != null) {
                parent.removeView(rootView);
            }
            return rootView;
        }
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
