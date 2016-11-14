package com.nickyluis.nplayer.fragment;

import android.databinding.ViewDataBinding;
import android.os.Bundle;

import com.nickyluis.nplayer.databinding.FragmentBlankBinding;
import com.nickyluis.nplayer.R;
import com.nickyluis.nplayer.basic.base.BaseFragment;


public class BlankFragment extends BaseFragment {
    //参数
    public static final String ARGS_PAGE = "args_page";
    //页数
    private int mPage;
    //binding对象
    private FragmentBlankBinding binding;

    //对象实例化
    public static BlankFragment newInstance(int page) {
        Bundle args = new Bundle();

        args.putInt(ARGS_PAGE, page);
        BlankFragment fragment = new BlankFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onGetArgument() {
        mPage = getArguments().getInt(ARGS_PAGE);
    }

    @Override
    public int setLayoutId() {
        return R.layout.fragment_blank;
    }

    @Override
    public void loadLayout(ViewDataBinding viewDataBinding) {
        //databing绑定数据
        binding = (FragmentBlankBinding) viewDataBinding;
        binding.textView.setText("第" + mPage + "页");
    }

}