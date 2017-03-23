package com.example.wyf.p2pmoney.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wyf.p2pmoney.R;
import com.example.wyf.p2pmoney.fly.ShakeListener;
import com.example.wyf.p2pmoney.fly.StellarMap;
import com.example.wyf.p2pmoney.utils.UIUtils;

import java.util.Random;

/**
 * Created by WYF on 2017/3/21.
 */

public class ProductRecommendFragment extends Fragment {

    private String[] data = new String[]{"超级新手计划", "乐享活系列90天计划", "钱包计划", "30天理财计划(加息2%)", "90天理财计划(加息5%)", "180天理财计划(加息10%)",
            "林业局投资商业经营", "中学老师购买车辆", "屌丝下海经商计划", "新西游影视拍摄投资", "Java培训老师自己周转", "养猪场扩大经营",
            "旅游公司扩大规模", "阿里巴巴洗钱计划", "铁路局回款计划", "高级白领赢取白富美投资计划"
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = UIUtils.inflate(R.layout.fragment_product_recommend);
        final StellarMap stellarMap = new StellarMap(UIUtils.getContext());
        stellarMap.setAdapter(new RecommendAdapter());
        //9行6列
        stellarMap.setRegularity(6, 9);
        //边距
        int padding = UIUtils.dip2px(10);
        stellarMap.setInnerPadding(padding, padding, padding, padding);
        //设置默认页面第一组数据
        stellarMap.setGroup(0, true);
        ShakeListener shakeListener = new ShakeListener(UIUtils.getContext());
        shakeListener.setOnShakeListener(new ShakeListener.OnShakeListener() {
            @Override
            public void onShake() {
                stellarMap.zoomIn(); //跳到下一页
            }
        });
        return stellarMap;
    }

    class RecommendAdapter implements StellarMap.Adapter{

        @Override
        public int getGroupCount() {
            return 2;
        }

        @Override
        public int getCount(int group) {
            int size = data.length/getGroupCount();
            if(group == getGroupCount() - 1){
                // 最后一页, 将除不尽,余下来的数量追加在最后一页, 保证数据完整不丢失
                size += data.length%getGroupCount();
            }
            return size;
        }

        @Override
        public View getView(int group, int position, View convertView) {
            //确保数据都展示
            position += (group)*getCount(group - 1);

            TextView tv = new TextView(UIUtils.getContext());
            tv.setText(data[position]);
            int size = 16 + new Random().nextInt(10);
            tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, size);
            Random random = new Random();
            int r = 30 + random.nextInt(200);
            int g = 30 + random.nextInt(204);
            int b = 30 + random.nextInt(208);
            tv.setTextColor(Color.rgb(r, g, b));
            return tv;
        }

        @Override
        public int getNextGroupOnZoom(int group, boolean isZoomIn) {
            if(group == getGroupCount() - 1){
                group = -1;
            }
            return group + 1;
//            if(isZoomIn){
//                if(group > 0){
//                    group--;
//                }else{
//                    group = getGroupCount() - 1;
//                }
//            }else{
//                if(group < getGroupCount() - 1){
//                    group++;
//                }else{
//                    group = 0;
//                }
//            }
//            return group;
        }
    }
}
