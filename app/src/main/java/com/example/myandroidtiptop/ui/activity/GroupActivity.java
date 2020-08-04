package com.example.myandroidtiptop.ui.activity;

import android.graphics.Color;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.example.myandroidtiptop.R;
import com.example.myandroidtiptop.bean.TestEntity;
import com.example.myandroidtiptop.tool.ListUtil;
import com.example.myandroidtiptop.ui.adapter.GroupAdapter;
import com.example.myandroidtiptop.ui.adapter.group.adapter.SectionedRecyclerViewAdapter;
import com.example.myandroidtiptop.ui.adapter.group.adapter.SectionedSpanSizeLookup;
import com.example.myandroidtiptop.ui.adapter.group.listener.LoadMoreListener;
import com.example.myandroidtiptop.ui.adapter.group.mvp.base.Module;
import com.example.myandroidtiptop.ui.adapter.group.mvp.presenter.TestPresenter;
import com.example.myandroidtiptop.ui.adapter.group.widgets.SectionedGridDivider;

import java.util.ArrayList;
import java.util.List;

public class GroupActivity extends AppCompatActivity implements SwipeRefreshLayout
        .OnRefreshListener, Module.View  {

    //@BindView(R.id.swip_root)
    SwipeRefreshLayout refreshLayout;
    //@BindView(R.id.rv)
    RecyclerView rv;
    private TestPresenter mPresenter;
    private LoadMoreListener loadMoreListener;
    private boolean isPull = true;//是否下拉刷新
    private GroupAdapter mAdapter;
    private List<TestEntity.BodyBean.EListBean> mDatas = new ArrayList<>();
    private GridLayoutManager mGridLayoutManager;
    private SectionedGridDivider mDivider;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);
        init();

        mPresenter = new TestPresenter(this);
        initAdapter();
    }

    private void init()
    {
        refreshLayout = findViewById(R.id.swip_root);
        rv =  findViewById(R.id.rv);
    }

    private void initAdapter() {
        //获取图片地址信息
//        String drawableName = "home0" ;
//        final int picId = this.getResources().getIdentifier(drawableName , "drawable", this.getPackageName());
        final int picId = 111;

        mAdapter = new GroupAdapter(mDatas, this);
        mGridLayoutManager = new GridLayoutManager(this, 3);
        mGridLayoutManager.setSpanSizeLookup(new SectionedSpanSizeLookup(mAdapter,mGridLayoutManager));
        rv.setLayoutManager(mGridLayoutManager);
        rv.setAdapter(mAdapter);
        mDivider = new SectionedGridDivider(this, 50, Color.parseColor("#F5F5F5"));
        rv.addItemDecoration(mDivider);

        loadMoreListener = new LoadMoreListener(mGridLayoutManager) {
            @Override
            public void onLoadMore() {
                isPull = false;
                isLoading = true;
                mAdapter.changeMoreStatus(SectionedRecyclerViewAdapter.LOADING_MORE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mPresenter.loadData(1,picId);
                    }
                }, 1000);
            }
        };
        rv.setOnScrollListener(loadMoreListener);

        refreshLayout.setOnRefreshListener(this);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mPresenter.loadData(1,picId);
            }
        }, 300);
    }


    @Override
    public void onRefresh() {

        //获取图片地址信息
        String drawableName = "home1" ;
        final int picId = this.getResources().getIdentifier(drawableName , "drawable", this.getPackageName());

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mPresenter.loadData(0,picId);
            }
        }, 1000);
    }

    @Override
    public void showLoading(String msg) {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String errorMsg) {   }

    @Override
    public void updateList(int type, List<TestEntity.BodyBean.EListBean> datas) {
        loadMoreListener.isLoading = false;
        if (refreshLayout.isRefreshing()) {
            refreshLayout.setRefreshing(false);
        }
        if (isPull) {
            if (!ListUtil.isEmpty(datas)) {
                mAdapter.getData().clear();
                mAdapter.notifyDataSetChanged();
                mAdapter.addMoreData(datas);
                if (loadMoreListener.isFullAScreen(rv)) {//显示item满一屏了
                    mAdapter.changeMoreStatus(SectionedRecyclerViewAdapter.PULLUP_LOAD_MORE);
                } else {
                    mAdapter.changeMoreStatus(SectionedRecyclerViewAdapter.LOADING_FINISH);
                }
                mAdapter.notifyDataSetChanged();
            } else {
                //显示空布局
            }
        } else {
            if (datas.size() > 0) {
                mAdapter.addMoreData(datas);
                mAdapter.changeMoreStatus(SectionedRecyclerViewAdapter.PULLUP_LOAD_MORE);
            } else {
                mAdapter.changeMoreStatus(SectionedRecyclerViewAdapter.LOADING_FINISH);
            }
        }

    }

}

