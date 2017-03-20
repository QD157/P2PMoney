package com.example.wyf.p2pmoney.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bumptech.glide.Glide;
import com.example.wyf.p2pmoney.R;
import com.example.wyf.p2pmoney.bean.Image;
import com.example.wyf.p2pmoney.bean.Index;
import com.example.wyf.p2pmoney.bean.Product;
import com.example.wyf.p2pmoney.global.AppNetConfig;
import com.example.wyf.p2pmoney.ui.MyRoundProgress;
import com.example.wyf.p2pmoney.ui.MyScrollView;
import com.example.wyf.p2pmoney.utils.UIUtils;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by WYF on 2017/3/18.
 */

public class HomeFragment extends Fragment {

    @BindView(R.id.title_left)
    ImageView titleLeft;
    @BindView(R.id.title_tv)
    TextView titleTv;
    @BindView(R.id.title_right)
    ImageView titleRight;
    @BindView(R.id.vp_barner)
    ViewPager vpBarner;
    @BindView(R.id.circle_barner)
    CirclePageIndicator circleBarner;
    @BindView(R.id.textView1)
    TextView textView1;
    @BindView(R.id.p_progresss)
    MyRoundProgress pProgresss;
    @BindView(R.id.p_yearlv)
    TextView pYearlv;
    @BindView(R.id.button1)
    Button button1;
    @BindView(R.id.myscrollview)
    MyScrollView myscrollview;

    private AsyncHttpClient client = new AsyncHttpClient();
    private Index index;
    private int totalProgress;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = UIUtils.inflate(R.layout.fragment_home);
        ButterKnife.bind(this, view);
        initTitle();
        initTitle();
        initData();
        return view;
    }

    private void initData() {
        index = new Index();
        client.post(AppNetConfig.INDEX, new AsyncHttpResponseHandler(){
            @Override
            public void onSuccess(String content) {
                JSONObject jsonObject = JSON.parseObject(content);
                String proInfo = jsonObject.getString("proInfo");
                Product product = JSON.parseObject(proInfo, Product.class);

                String imageArr = jsonObject.getString("imageArr");
                List<Image> imageList = JSON.parseArray(imageArr, Image.class);
                index.product = product;
                index.imageList = imageList;
                vpBarner.setAdapter(new MyAdapter());
                circleBarner.setViewPager(vpBarner);
                totalProgress = Integer.parseInt(index.product.progress);
                new Thread(new MyRunnable()).start();
                super.onSuccess(content);
            }

            @Override
            public void onFailure(Throwable error, String content) {
                Toast.makeText(getActivity(), "请求服务器数据异常", Toast.LENGTH_SHORT).show();
                super.onFailure(error, content);
            }
        });
    }
    class MyRunnable implements Runnable{

        @Override
        public void run() {
            int tempProgress = 0;
            while (tempProgress <= totalProgress) {
                pProgresss.setProgerss(tempProgress);
                tempProgress++;
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void initTitle() {
        titleLeft.setVisibility(View.INVISIBLE);
        titleRight.setVisibility(View.INVISIBLE);
    }

    private class MyAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return index.imageList == null ? 0 : index.imageList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            String imaUrl = index.imageList.get(position).IMAURL;
            ImageView imageView = new ImageView(getActivity());
            //imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            Glide.with(getActivity()).load(imaUrl).into(imageView);
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View)object);
        }
    }
}
