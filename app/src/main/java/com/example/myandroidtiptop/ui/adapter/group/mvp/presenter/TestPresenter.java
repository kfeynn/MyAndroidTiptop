package com.example.myandroidtiptop.ui.adapter.group.mvp.presenter;

import  com.example.myandroidtiptop.bean.TestEntity;
import com.example.myandroidtiptop.ui.adapter.group.mvp.base.Module;
import com.example.myandroidtiptop.tool.DatasUtil;

import java.util.List;

/**
 * package: com.easyandroid.sectionadapter.mvp.presenter.TestPresenter
 * author: gyc
 * description:
 * time: create at 2017/7/8 9:53
 */

public class TestPresenter implements Module.Presenter{

    private Module.View view;

    public TestPresenter(Module.View view) {
        this.view = view;
    }

    @Override
    public void loadData(int loadType,int picId) {
        List<TestEntity.BodyBean.EListBean> datas = DatasUtil.createDatas(picId);
        if(view!=null){
            view.updateList(loadType, datas);
        }
    }
}
