package com.example.myandroidtiptop.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;

import com.example.myandroidtiptop.R;
import com.example.myandroidtiptop.bean.Items;
import com.example.myandroidtiptop.bean.PNSub;

import java.util.List;

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.ViewHolder> {

    private List<PNSub> itemArrays ;

    //构造函数
    public TestAdapter(List<PNSub>  items ){
        itemArrays = items ;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //加载布局文件
        View view =  View.inflate(parent.getContext(), R.layout.grid_pnsub, null);
        return new TestAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //绑定内容
        //3
        holder.setData(position);   //位置，点击事件的时候要用

        PNSub item = itemArrays.get(position);
        holder.pnSub_pmn04.setText(item.getPmn04());
        holder.pnSub_pmn07.setText(item.getPmn07());
        holder.pnSub_pmn20.setText(String.valueOf(item.getPmn20()));
    }

    @Override
    public int getItemCount() {
        //获取数据列表的长度
        return itemArrays.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        //ImageView icon;
        TextView pnSub_pmn04;
        TextView pnSub_pmn07;
        TextView pnSub_pmn20;
        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(onClickListener);   //注册点击事件 整个项作为事件响应区域
            pnSub_pmn04 = itemView.findViewById(R.id.pnSub_pmn04);
            pnSub_pmn07 = itemView.findViewById(R.id.pnSub_pmn07);
            pnSub_pmn20 = itemView.findViewById(R.id.pnSub_pmn20);
            //pnSub_pmn20.setOnClickListener(onClickListener);  //注册点击事件 ，小范围
        }
        public void setData(int position){itemView.setTag(position);}
    }


    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int position = (Integer) v.getTag();
            System.out.println("已被点击位置:" + position);

            PNSub item = itemArrays.get(position);

            //Toast.makeText(v.getContext(), "you click view:" + item.getPmm01(), Toast.LENGTH_SHORT).show();
        }
    };


}
