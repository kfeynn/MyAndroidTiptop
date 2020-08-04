package com.example.myandroidtiptop.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.myandroidtiptop.R;
import com.example.myandroidtiptop.tool.ActionList;

/**
 * Created by Administrator on 2018/12/25.此为公用的基础显示界面
 */

public abstract class BaseActivity extends parentActivity {
    protected TextView title;
    public static String[] itemArray = new String[]{"收取货物","IQC检验","上架扫描","发料扫描","工单退料","仓退验货","物料调拨","物料追踪","厂内调拨","下阶报废","组合拆解","仓退交接","送货单交接","退货单扫描","个人中心","更多"};
    protected ImageButton btn_titlebar_left;
    protected EditText et_search;
    protected Button btn_search;
    public Context baseContext;
    private static volatile Activity mCurrentActivity;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE); //隐藏标题栏
        //setContentView(R.layout.titlebar_back); // ? 可以不要
        baseContext = this.getApplicationContext();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN); //隐藏状态栏
    }

    protected void setTitle(String titleString){
        title = findViewById(R.id.tv_title);
        title.setText(titleString);
    }

    protected void setSearchEdit(String hintString){
        et_search = findViewById(R.id.rec_edt);
        et_search.setHint(hintString);
    }

    //回到主菜单按钮动作
    protected void back(){
        btn_titlebar_left = findViewById(R.id.btn_titlebar_left);
        btn_titlebar_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(context, MainActivity.class);
                startActivity(myIntent);
                finish();
            }
        });
    }

    protected void onSearchButtonListen(){
        btn_search = findViewById(R.id.rec_btn);
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActionList.ACTION_QR_DATA_RECEIVED);
                String qr_data = et_search.getText().toString()+ "\r";
                intent.putExtra("qr_data", qr_data);
                sendBroadcast(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        setCurrentActivity(this);
    }
    public static Activity getCurrentActivity() {
        return mCurrentActivity;
    }

    private void setCurrentActivity(Activity activity) {
        mCurrentActivity = activity;
    }

//    //重写过账请求方法
//    protected abstract void confirmJoin();
}
