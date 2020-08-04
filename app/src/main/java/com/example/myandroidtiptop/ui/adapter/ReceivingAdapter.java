package com.example.myandroidtiptop.ui.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.donkingliang.groupedadapter.holder.BaseViewHolder;
import com.example.myandroidtiptop.R;
import com.example.myandroidtiptop.bean.PNSub;
import com.example.myandroidtiptop.service.MyService;
import com.example.myandroidtiptop.ui.groupedadapter.MyGroupedRecyclerViewAdapter;

/**
 * Created by Administrator on 2018/8/9.
 */

public class ReceivingAdapter extends MyGroupedRecyclerViewAdapter {


    public ReceivingAdapter(Context context) {
        super(context);
    }

    @Override
    //返回组的数量
    public int getGroupCount() {
        return MyService.pn == null ? 0 : MyService.pn.getPnsubs().size();
    }

    @Override
    //返回当前组的子项数量
    public int getChildrenCount(int groupPosition) {
        if (!isExpand(groupPosition)) {
            return 0;
        }
        return MyService.pn == null ? 0 : MyService.pn.getPnsubs().get(groupPosition).getBoxs().size();
    }

    @Override
    public boolean hasHeader(int groupPosition) {
        return true;
    }

    @Override
    public boolean hasFooter(int groupPosition) {
        return false;
    }

    @Override
    //返回头部的布局id。(如果hasHeader返回false，这个方法不会执行)
    public int getHeaderLayout(int viewType) {
        return R.layout.grid_pnsub;
    }

    @Override
    //返回尾部的布局id。(如果hasFooter返回false，这个方法不会执行)
    public int getFooterLayout(int viewType) {
        return 0;
    }

    @Override
    //返回子项的布局id。
    public int getChildLayout(int viewType) {
        return R.layout.grid_boxlist;
    }


    @Override
    public void onBindHeaderViewHolder(BaseViewHolder holder, int groupPosition) {
        if (MyService.pn != null) {
            holder.setText(R.id.pnSub_pmn20, MyService.pn.getPnsubs().get(groupPosition).getPmn20() + "");
            holder.setText(R.id.pnSub_pmn04, MyService.pn.getPnsubs().get(groupPosition).getPmn04() + "");
            holder.setText(R.id.pnSub_pmn07,MyService.pn.getPnsubs().get(groupPosition).getPmn07() +"");
        } else {
            holder.setText(R.id.pnSub_pmn20, "");
            holder.setText(R.id.pnSub_pmn04, "");
            holder.setText(R.id.pnSub_pmn07, "");
        }
        //箭头切换
        ImageView ivState = holder.get(R.id.iv_state);
        if (MyService.pn.getPnsubs().get(groupPosition).isExpand()) {
            ivState.setRotation(90);//图片旋转90度
        } else {
            ivState.setRotation(0);
        }
    }

    @Override
    public void onBindFooterViewHolder(BaseViewHolder holder, int groupPosition) {

    }

    @Override
    public void onBindChildViewHolder(BaseViewHolder holder, int groupPosition, int childPosition) {
        //没有用 ？
//        holder.setText(R.id.tv_boxnum, "箱号：" + MyService.pn.getPnsubs().get(groupPosition).getBoxs().
//                get(childPosition).getBoxnum() + "");
//        holder.setText(R.id.tv_pmn041, "品名：" + MyService.pn.getPnsubs().get(groupPosition).getBoxs().
//                get(childPosition).getPmn041() + "");
//        holder.setText(R.id.tv_box_pmn20, "采购数量：" + MyService.pn.getPnsubs().get(groupPosition).getBoxs().
//                get(childPosition).getPmn20() + "");
    }

    /**
     * 判断当前组是否展开
     *
     * @param groupPosition
     * @return
     */
    public boolean isExpand(int groupPosition) {
        PNSub entity = MyService.pn.getPnsubs().get(groupPosition);
        return entity.isExpand();
    }

    /**
     * 展开一个组
     *
     * @param groupPosition
     */
    public void expandGroup(int groupPosition) {
        expandGroup(groupPosition, false);
    }

    /**
     * 展开一个组
     *
     * @param groupPosition
     * @param animate
     */
    public void expandGroup(int groupPosition, boolean animate) {
        PNSub entity = MyService.pn.getPnsubs().get(groupPosition);
        entity.setExpand(true);
        if (animate) {
            notifyChildrenInserted(groupPosition);
        } else {
            notifyDataChanged();
        }
    }

    /**
     * 收起一个组
     *
     * @param groupPosition
     */
    public void collapseGroup(int groupPosition) {
        collapseGroup(groupPosition, false);
    }

    /**
     * 收起一个组
     *
     * @param groupPosition
     * @param animate
     */
    public void collapseGroup(int groupPosition, boolean animate) {
        PNSub entity = MyService.pn.getPnsubs().get(groupPosition);
        entity.setExpand(false);
        if (animate) {
            notifyChildrenRemoved(groupPosition);
        } else {
            notifyDataChanged();
        }
    }


}
