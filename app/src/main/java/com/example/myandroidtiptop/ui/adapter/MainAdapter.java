package com.example.myandroidtiptop.ui.adapter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myandroidtiptop.R;
import com.example.myandroidtiptop.bean.Items;
import com.example.myandroidtiptop.service.MyService;
import com.example.myandroidtiptop.ui.activity.GroupActivity;
import com.example.myandroidtiptop.ui.activity.ReceivingActivity;
import com.example.myandroidtiptop.ui.activity.TestActivity;
import com.example.myandroidtiptop.ui.activity.parentActivity;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private List<Items> itemArrays ;

    //构造函数
    public MainAdapter(List<Items>  items ){
        itemArrays = items ;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView icon;
        TextView tv;
        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(onClickListener);   //注册点击事件 整个项作为事件响应区域
            icon = itemView.findViewById(R.id.iv_item);
            tv = itemView.findViewById(R.id.tv_name);
        }
        public void setData(int position){itemView.setTag(position);}
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =  View.inflate(parent.getContext(), R.layout.grid_item, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //3
        holder.setData(position);   //位置，点击事件的时候要用

        Items item = itemArrays.get(position);
        holder.tv.setText(item.getName());
        holder.icon.setBackgroundResource(item.getImageId());
    }

    @Override
    public int getItemCount() {
        return itemArrays.size();
    }


    //region  点击事件
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int position = (Integer) v.getTag();
            //System.out.println("已被点击位置:" + position);

            Items item = itemArrays.get(position);

            Intent intent;

            switch (position){
                case 0:
                    //这里可以做权限判断 ，
                    intent = new Intent(v.getContext(),TestActivity.class);

                    String aa  = MyService.USER.getJob();

//                    if(wmsService.USER.getJob().equals("1") || wmsService.USER.getJob().equals("0")){
//                        Intent intent0 = new Intent(mContext,ReceivingActivity.class);
//                        mContext.startActivity(intent0);
//                        break;
//                    }else {
//                        Toast.makeText(mContext, "您没有权限访问这个模块", Toast.LENGTH_SHORT).show();
//                    }

                    //Toast.makeText(v.getContext(),"跳转至页面 :"+item.getName(),Toast.LENGTH_SHORT).show();
                    v.getContext().startActivity(intent);

                    break;
                case 1:
                    //测试复杂recyclyview显示页面
                    intent = new Intent(v.getContext(),GroupActivity.class);
                    v.getContext().startActivity(intent);
                    break;
                case 2:
                    //测试复杂recyclyview显示页面
                    intent = new Intent(v.getContext(),ReceivingActivity.class);
                    v.getContext().startActivity(intent);
                    break;

//                case 2:
//                    if(wmsService.USER.getJob().equals("2") || wmsService.USER.getJob().equals("0")){
//                        Intent intent2 = new Intent(mContext,GroundingActivity.class);
//                        mContext.startActivity(intent2);
//                        break;
//                    }else {
//                        Toast.makeText(mContext, "您没有权限访问这个模块",
//                                Toast.LENGTH_SHORT).show();
//                    }
//                    break;
//                case 3:
//                    if(wmsService.USER.getJob().equals("2") || wmsService.USER.getJob().equals("0")){
//                        Intent intent3 = new Intent(mContext,PackingActivity.class);
//                        mContext.startActivity(intent3);
//                        break;
//                    }else {
//                        Toast.makeText(mContext, "您没有权限访问这个模块",
//                                Toast.LENGTH_SHORT).show();
//                    }
//                    break;
//                case 4:
//                    if(wmsService.USER.getJob().equals("2")||wmsService.USER.getJob().equals("1")
//                            ||wmsService.USER.getJob().equals("0")){
//                        Intent intent3 = new Intent(mContext,WOReturnActivity.class);
//                        mContext.startActivity(intent3);
//                        break;
//                    }else {
//                        Toast.makeText(mContext, "您没有权限访问这个模块",
//                                Toast.LENGTH_SHORT).show();
//                    }
//                    break;
//                case 5:
//                    if(wmsService.USER.getJob().equals("2")||wmsService.USER.getJob().equals("1")
//                            ||wmsService.USER.getJob().equals("0")){
//                        Intent intent3 = new Intent(mContext,WHReturnActivity.class);
//                        mContext.startActivity(intent3);
//                        break;
//                    }else {
//                        Toast.makeText(mContext, "您没有权限访问这个模块",
//                                Toast.LENGTH_SHORT).show();
//                    }
//                    break;
//                case 6:
//                    if(wmsService.USER.getJob().equals("2") || wmsService.USER.getJob().equals("0")){
//                        Intent intent3 = new Intent(mContext,GoodsMoveActivity.class);
//                        mContext.startActivity(intent3);
//                        break;
//                    }else {
//                        Toast.makeText(mContext, "您没有权限访问这个模块",
//                                Toast.LENGTH_SHORT).show();
//                    }
//                    break;
//                case 7:
//                    if(wmsService.USER.getJob().equals("2") || wmsService.USER.getJob().equals("0")){
//                        Intent intent3 = new Intent(mContext,GoodsIptAndEptActivity.class);
//                        mContext.startActivity(intent3);
//                        break;
//                    }else {
//                        Toast.makeText(mContext, "您没有权限访问这个模块",
//                                Toast.LENGTH_SHORT).show();
//                    }
//                    break;
//                case 8:
//                    Intent intent6 = new Intent(mContext,UserActivity.class);
//                    mContext.startActivity(intent6);
//                    break;
                default:
                    Toast.makeText(v.getContext(), "功能正在开发中，敬请关注", Toast.LENGTH_SHORT).show();
                    break;

            }
        }
    };
    //endregion
}
