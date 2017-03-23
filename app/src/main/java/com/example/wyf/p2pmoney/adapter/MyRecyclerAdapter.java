package com.example.wyf.p2pmoney.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wyf.p2pmoney.R;
import com.example.wyf.p2pmoney.bean.Product;
import com.example.wyf.p2pmoney.ui.RoundProgress;

import java.util.List;

/**
 * Created by WYF on 2017/3/21.
 */

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.ViewHolder> {

    private final List<Product> list;

    public MyRecyclerAdapter(List<Product> list){
        this.list =list;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        TextView pName;
        TextView pMoney;
        TextView pYearlv;
        TextView pSuodingdays;
        TextView pMinzouzi;
        RoundProgress pProgress;

        public ViewHolder(View itemView) {
            super(itemView);
            pName = (TextView)itemView.findViewById(R.id.p_name);
            pMoney = (TextView)itemView.findViewById(R.id.p_money);
            pYearlv = (TextView)itemView.findViewById(R.id.p_yearlv);
            pSuodingdays = (TextView)itemView.findViewById(R.id.p_suodingdays);
            pMinzouzi = (TextView)itemView.findViewById(R.id.p_minzouzi);
            pProgress = (RoundProgress)itemView.findViewById(R.id.p_progresss);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Product product = list.get(position);
        holder.pName.setText(product.name);
        holder.pMoney.setText(product.money);
        holder.pYearlv.setText(product.yearLv);
        holder.pSuodingdays.setText(product.suodingDays);
        holder.pMinzouzi.setText(product.minTouMoney);
        holder.pProgress.setProgress(Integer.parseInt(product.progress));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
