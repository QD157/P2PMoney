package com.example.wyf.p2pmoney.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.wyf.p2pmoney.R;
import com.example.wyf.p2pmoney.activity.LoginActivity;
import com.example.wyf.p2pmoney.activity.UserInfoActivity;
import com.example.wyf.p2pmoney.bean.Login;
import com.example.wyf.p2pmoney.global.AppNetConfig;
import com.example.wyf.p2pmoney.global.BaseActivity;
import com.loopj.android.http.RequestParams;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by WYF on 2017/3/18.
 */

public class MeFragment extends BaseFragment {

    @BindView(R.id.title_left)
    ImageView titleLeft;
    @BindView(R.id.title_tv)
    TextView titleTv;
    @BindView(R.id.title_right)
    ImageView titleRight;
    @BindView(R.id.imageView1)
    CircleImageView imageView1;
    @BindView(R.id.icon_time)
    RelativeLayout iconTime;
    @BindView(R.id.textView11)
    TextView textView11;
    @BindView(R.id.relativeLayout1)
    RelativeLayout relativeLayout1;
    @BindView(R.id.chongzhi)
    ImageView chongzhi;
    @BindView(R.id.tixian)
    ImageView tixian;
    @BindView(R.id.ll_touzi)
    TextView llTouzi;
    @BindView(R.id.ll_touzi_zhiguan)
    TextView llTouziZhiguan;
    @BindView(R.id.ll_zichang)
    TextView llZichang;
    @BindView(R.id.ll_zhanquan)
    TextView llZhanquan;

    @Override
    protected RequestParams getParams() {
        return new RequestParams();
    }

    @Override
    protected String getUrl() {
        return "";
    }

    @Override
    protected void initData(String content) {
        isLogin();
    }

    private void isLogin() {
        SharedPreferences sp = getActivity().getSharedPreferences("user_info", Context.MODE_PRIVATE);
        String uf_acc = sp.getString("UF_ACC", "");
        if (TextUtils.isEmpty(uf_acc)) {
            showLoginDialog();
        } else {
            doUser();
        }
    }

    private void doUser() {
        Login login = ((BaseActivity) getActivity()).getLogin();
        textView11.setText(login.UF_ACC);
        Glide.with(getActivity()).load(AppNetConfig.IMG).asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                imageView1.setImageBitmap(resource);
            }
        });
    }

    private void showLoginDialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("登录").setMessage("必须先登录...go...");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ((BaseActivity) getActivity()).gotoActivity(LoginActivity.class, null);
            }
        });
        builder.setCancelable(false);
        builder.show();
    }

    @Override
    protected void initTitle() {
        titleTv.setText("我的资产");
        titleLeft.setVisibility(View.INVISIBLE);
        titleRight.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.title_right)
    public void userSetting(View view){
        ((BaseActivity)getActivity()).gotoActivity(UserInfoActivity.class, null);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_me;
    }

}
