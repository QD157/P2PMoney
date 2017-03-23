package com.example.wyf.p2pmoney.fragment;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.wyf.p2pmoney.ui.MyFlowLayout;
import com.example.wyf.p2pmoney.utils.UIUtils;

import java.util.Random;

/**
 * Created by WYF on 2017/3/21.
 */

public class ProductHotFragment extends Fragment {

    private String[] data = new String[]{"超级新手计划", "乐享活系列90天计划", "钱包计划", "30天理财计划(加息2%)", "90天理财计划(加息5%)", "180天理财计划(加息10%)",
            "林业局投资商业经营", "中学老师购买车辆", "屌丝下海经商计划", "新西游影视拍摄投资", "Java培训老师自己周转", "养猪场扩大经营",
            "旅游公司扩大规模", "阿里巴巴洗钱计划", "铁路局回款计划", "高级白领赢取白富美投资计划"
    };
    private GradientDrawable press = com.example.wyf.p2pmoney.utils.DrawableUtils.getGradientDrawable(0xffcecece, UIUtils.dip2px(6));

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ScrollView scrollView = new ScrollView(UIUtils.getContext());
        MyFlowLayout flowLayout = new MyFlowLayout(UIUtils.getContext());
        int padding = UIUtils.dip2px(10);
        flowLayout.setPadding(padding, padding, padding, padding);
        //flowLayout.setHorizontalSpacing(UIUtils.dip2px(6));
        //flowLayout.setVerticalSpacing(UIUtils.dip2px(8));
        for (int i = 0; i < data.length; i++){
            TextView tv = new TextView(UIUtils.getContext());
            tv.setGravity(Gravity.CENTER_HORIZONTAL);
            tv.setText(data[i]);
            tv.setClickable(true);
            tv.setTextColor(Color.WHITE);
            tv.setPadding(padding, padding, padding, padding);
            Random random = new Random();
            tv.setTextSize(20);
            int r = 30 + random.nextInt(200);
            int g = 30 + random.nextInt(204);
            int b = 30 + random.nextInt(208);
            GradientDrawable normal = com.example.wyf.p2pmoney.utils.DrawableUtils.getGradientDrawable(Color.rgb(r, g, b), UIUtils.dip2px(6));
            tv.setBackground(com.example.wyf.p2pmoney.utils.DrawableUtils.getSelector(normal, press));
            flowLayout.addView(tv);
        }
        scrollView.addView(flowLayout);
        return scrollView;
    }
}
