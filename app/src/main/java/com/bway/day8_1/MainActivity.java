package com.bway.day8_1;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.bway.day8_1.adapter.MyXRecycleviewAdapter;
import com.bway.day8_1.bean.ShowBean;
import com.bway.day8_1.inter.ICallBack;
import com.bway.day8_1.utils.HttpUtils;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private XRecyclerView xrvShow;
    private  MyXRecycleviewAdapter  myXrecycleViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();

        xrvShow.setLoadingMoreProgressStyle(ProgressStyle.BallZigZag);
        xrvShow.setRefreshProgressStyle(ProgressStyle.BallZigZag);

    }

    private void initData() {
        HttpUtils.getInstance().doget(new ICallBack() {
            @Override
            public void onSuccess(Object obj) {
                myXrecycleViewAdapter = new MyXRecycleviewAdapter(MainActivity.this, (List<ShowBean.NewslistBean>) obj);
                xrvShow.setAdapter(myXrecycleViewAdapter);
                xrvShow.setLayoutManager(new LinearLayoutManager(MainActivity.this,LinearLayoutManager.VERTICAL,false));

            }

            @Override
            public void onFailed(Exception e) {

            }
        });


    }

    private void initView() {
        xrvShow = findViewById(R.id.xrv_show);
        xrvShow.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                myXrecycleViewAdapter.notifyDataSetChanged();
                xrvShow.refreshComplete();


            }

            @Override
            public void onLoadMore() {
                new Handler().post(new Runnable() {
                    @Override
                    public void run() {
                        initData();
                        xrvShow.loadMoreComplete();
                    }
                });

            }
        });
    }
}
