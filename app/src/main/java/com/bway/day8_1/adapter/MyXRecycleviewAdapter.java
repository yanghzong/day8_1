package com.bway.day8_1.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bway.day8_1.R;
import com.bway.day8_1.bean.ShowBean;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

/**
 * Created by 择木 on 2018/12/6.
 */

public class MyXRecycleviewAdapter  extends XRecyclerView.Adapter<MyXRecycleviewAdapter.ViewHolder>{
    private Context context;
    private List<ShowBean.NewslistBean> list;
    private GenericDraweeHierarchy build;

    public MyXRecycleviewAdapter(Context context, List<ShowBean.NewslistBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView=View.inflate(context,R.layout.item_show,null);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GenericDraweeHierarchyBuilder hierarchyBuilder = new GenericDraweeHierarchyBuilder(context.getResources());
        RoundingParams circle = RoundingParams.asCircle();
        build = hierarchyBuilder.setRoundingParams(circle).build();
        holder.sdvShow.setHierarchy(build);
        holder.sdvShow.setImageURI(list.get(position).getPicUrl());
        holder.tvShow.setText(list.get(position).getTitle());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final SimpleDraweeView sdvShow;
        private final TextView tvShow;

        public ViewHolder(View itemView) {
            super(itemView);
            sdvShow = itemView.findViewById(R.id.sdv_show);
            tvShow = itemView.findViewById(R.id.tv_show);
        }
    }
}
