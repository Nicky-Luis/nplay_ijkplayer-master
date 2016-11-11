package com.nickyluis.nplay_ijkplayer.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nickyluis.nplay_ijkplayer.R;


public class BlankFragment extends Fragment {
    public static final String ARGS_PAGE = "args_page";
    private int mPage;

    public static BlankFragment newInstance(int page) {
        Bundle args = new Bundle();

        args.putInt(ARGS_PAGE, page);
        BlankFragment fragment = new BlankFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARGS_PAGE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blank, container, false);
        TextView textView = (TextView) view.findViewById(R.id.textView);
        textView.setText("第" + mPage + "页");
        return view;
    }
}