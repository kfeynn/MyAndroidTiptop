package com.example.myandroidtiptop.ui.adapter.group.mvp.base;


import com.example.myandroidtiptop.bean.TestEntity;

import java.util.List;

/**
 * package: com.easyandroid.sectionadapter.mvp.base.Module
 * author: gyc
 * description:
 * time: create at 2017/7/8 9:51
 */

public class Module {

    public interface View extends BaseView{
        void updateList(int type, List<TestEntity.BodyBean.EListBean> datas);
    }

    public interface Presenter extends BasePresenter{
        void loadData(int loadType,int picId);
    }
}
