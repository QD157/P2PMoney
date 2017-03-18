package com.example.wyf.p2pmoney;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.wyf.p2pmoney.fragment.HomeFragment;
import com.example.wyf.p2pmoney.fragment.MeFragment;
import com.example.wyf.p2pmoney.fragment.MoreFragment;
import com.example.wyf.p2pmoney.fragment.TouziFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends FragmentActivity {

    @BindView(R.id.iv_home)
    ImageView ivHome;
    @BindView(R.id.tv_home)
    TextView tvHome;
    @BindView(R.id.ll_home)
    LinearLayout llHome;
    @BindView(R.id.iv_touzi)
    ImageView ivTouzi;
    @BindView(R.id.tv_touzi)
    TextView tvTouzi;
    @BindView(R.id.ll_touzi)
    LinearLayout llTouzi;
    @BindView(R.id.iv_me)
    ImageView ivMe;
    @BindView(R.id.tv_me)
    TextView tvMe;
    @BindView(R.id.ll_me)
    LinearLayout llMe;
    @BindView(R.id.iv_more)
    ImageView ivMore;
    @BindView(R.id.tv_more)
    TextView tvMore;
    @BindView(R.id.ll_more)
    LinearLayout llMore;
    @BindView(R.id.content)
    FrameLayout content;

    private HomeFragment homeFragment;
    private TouziFragment touziFragment;
    private MeFragment meFragment;
    private MoreFragment moreFragment;
    private FragmentTransaction ft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        selected(0);
    }

    @OnClick({R.id.ll_home, R.id.ll_touzi, R.id.ll_me, R.id.ll_more})
    public void changeTab(View v) {
        switch (v.getId()) {
            case R.id.ll_home:
                selected(0);
                break;
            case R.id.ll_touzi:
                selected(1);
                break;
            case R.id.ll_me:
                selected(2);
                break;
            case R.id.ll_more:
                selected(3);
                break;
        }
    }

    private void selected(int i) {
        FragmentManager fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        hideFragment();
        resetTab();
        switch (i) {
            case 0:
                if (homeFragment == null) {
                    homeFragment = new HomeFragment();
                    ft.add(R.id.content, homeFragment);
                }
                ft.show(homeFragment);
                ivHome.setImageResource(R.drawable.bid01);
                tvHome.setTextColor(getResources().getColor(R.color.home_back_selected));
                break;
            case 1:
                if (touziFragment == null) {
                    touziFragment = new TouziFragment();
                    ft.add(R.id.content, touziFragment);
                }
                ft.show(touziFragment);
                ivTouzi.setImageResource(R.drawable.bid03);
                tvTouzi.setTextColor(getResources().getColor(R.color.home_back_selected));
                break;
            case 2:
                if (meFragment == null) {
                    meFragment = new MeFragment();
                    ft.add(R.id.content, meFragment);
                }
                ft.show(meFragment);
                ivMe.setImageResource(R.drawable.bid05);
                tvMe.setTextColor(getResources().getColor(R.color.home_back_selected));
                break;
            case 3:
                if (moreFragment == null) {
                    moreFragment = new MoreFragment();
                    ft.add(R.id.content, moreFragment);
                }
                ft.show(moreFragment);
                ivMore.setImageResource(R.drawable.bid07);
                tvMore.setTextColor(getResources().getColor(R.color.home_back_selected));
                break;
        }
        ft.commit();
    }

    private void resetTab() {
        ivHome.setImageResource(R.drawable.bid02);
        ivTouzi.setImageResource(R.drawable.bid04);
        ivMe.setImageResource(R.drawable.bid06);
        ivMore.setImageResource(R.drawable.bid08);

        tvHome.setTextColor(getResources().getColor(R.color.home_back_unselected));
        tvTouzi.setTextColor(getResources().getColor(R.color.home_back_unselected));
        tvMe.setTextColor(getResources().getColor(R.color.home_back_unselected));
        tvMore.setTextColor(getResources().getColor(R.color.home_back_unselected));
    }

    private void hideFragment() {
        if (homeFragment != null) {
            ft.hide(homeFragment);
        }
        if (touziFragment != null) {
            ft.hide(touziFragment);
        }
        if (meFragment != null) {
            ft.hide(meFragment);
        }
        if (moreFragment != null) {
            ft.hide(moreFragment);
        }
    }
}
