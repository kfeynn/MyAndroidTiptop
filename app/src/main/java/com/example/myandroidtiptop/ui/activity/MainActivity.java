package com.example.myandroidtiptop.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.myandroidtiptop.R;
import com.example.myandroidtiptop.bean.Items;
import com.example.myandroidtiptop.ui.adapter.MainAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    public static String[] itemArray = new String[]{"收取货物","IQC检验","上架扫描","发料扫描","工单退料",
            "仓退验货","物料调拨","物料追踪","个人中心","更多"};

    private List<Items> itemArrays =  new ArrayList<>();

    //实例化操作页面列表
    private void initItems() {
        for (int i = 0; i < itemArray.length; i++) {
            //地址使用变量循环
            String drawableName = "home"+i;
            int sid = this.getResources().getIdentifier(drawableName , "drawable", this.getPackageName()); //图片地址
            //String aa = itemArray[i];
            Items item = new Items(itemArray[i],sid);
            itemArrays.add(item);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initItems();
        RecyclerView recyclerView = findViewById(R.id.recycler_view);

        //线性布局
        //LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //横向排列
        //layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        //网格布局
        GridLayoutManager layoutManager = new GridLayoutManager(this,2);

        //瀑布流布局
        //StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(layoutManager);
        MainAdapter adapter = new MainAdapter(itemArrays);
        recyclerView.setAdapter(adapter);
    }


}
