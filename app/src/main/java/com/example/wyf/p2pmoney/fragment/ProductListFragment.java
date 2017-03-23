package com.example.wyf.p2pmoney.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.wyf.p2pmoney.R;
import com.example.wyf.p2pmoney.adapter.MyRecyclerAdapter;
import com.example.wyf.p2pmoney.bean.Product;
import com.example.wyf.p2pmoney.global.AppNetConfig;
import com.example.wyf.p2pmoney.utils.UIUtils;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by WYF on 2017/3/21.
 */

public class ProductListFragment extends Fragment {

    @BindView(R.id.rv)
    RecyclerView rv;

    private AsyncHttpClient client = new AsyncHttpClient();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = UIUtils.inflate(R.layout.fragment_product_list);
        ButterKnife.bind(this, view);
        initData();
        return view;
    }

    private void initData() {
        client.get(AppNetConfig.PRODUCT, new AsyncHttpResponseHandler(){
            @Override
            public void onSuccess(String content) {
                JSONObject jsonObject = JSON.parseObject(content);
                if (jsonObject.getBoolean("success")) {
                    String data = jsonObject.getString("data");
                    List<Product> products = JSON.parseArray(data, Product.class);
                    rv.setLayoutManager(new LinearLayoutManager(UIUtils.getContext()));
                    rv.setAdapter(new MyRecyclerAdapter(products));
                }
            }

            @Override
            public void onFailure(Throwable error, String content) {
                super.onFailure(error, content);
            }
        });
    }
}
