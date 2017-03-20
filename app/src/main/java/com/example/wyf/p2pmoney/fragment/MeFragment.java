package com.example.wyf.p2pmoney.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wyf.p2pmoney.R;
import com.example.wyf.p2pmoney.utils.UIUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by WYF on 2017/3/18.
 */

public class MeFragment extends Fragment {

    @BindView(R.id.title_left)
    ImageView titleLeft;
    @BindView(R.id.title_tv)
    TextView titleTv;
    @BindView(R.id.title_right)
    ImageView titleRight;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = UIUtils.inflate(R.layout.fragment_me);
        ButterKnife.bind(this, view);
        return view;
    }
}
