package com.example.wyf.p2pmoney.fragment;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

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
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.List;

import butterknife.BindView;

/**
 * Created by WYF on 2017/3/18.
 */

public class HomeFragment extends BaseFragment {

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

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected String getUrl() {
        return AppNetConfig.INDEX;
    }

    @Override
    protected RequestParams getParams() {
        return new RequestParams();
    }

    @Override
    protected void initTitle() {
        titleLeft.setVisibility(View.INVISIBLE);
        titleRight.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void initData(String content) {
        if (!TextUtils.isEmpty(content)) {
            index = new Index();
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
        }
    }

    class MyRunnable implements Runnable {
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
            container.removeView((View) object);
        }
    }
}
