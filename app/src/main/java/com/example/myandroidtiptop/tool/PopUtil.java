package com.example.myandroidtiptop.tool;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Vibrator;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.myandroidtiptop.R;
import com.example.myandroidtiptop.service.MyService;
import com.example.myandroidtiptop.ui.activity.BaseActivity;
import com.example.myandroidtiptop.ui.activity.parentActivity;

public class PopUtil extends PopupWindow {

    private static Context mContext;
    private static View mPopWindow;
    public static TextView textView;

    public PopUtil(Context context) {
        this.mContext = context;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        mPopWindow = inflater.inflate(R.layout.toast, null);
        textView = (TextView) mPopWindow.findViewById(R.id.tv_toast);
    }

    public static PopUtil createPopUtil(Context context){
        PopUtil popUtil = new PopUtil(context);
        // 把View添加到PopWindow中
        popUtil.setContentView(mPopWindow);
        //设置SelectPicPopupWindow弹出窗体的宽
        popUtil.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        //设置SelectPicPopupWindow弹出窗体的高
        popUtil.setHeight(dip2px(context, 60));
        //  设置SelectPicPopupWindow弹出窗体可点击
        popUtil.setFocusable(false);
        //   设置背景透明
        popUtil.setBackgroundDrawable(new ColorDrawable(0x00000000));
        popUtil.setBackgroundDrawable(new BitmapDrawable());
        popUtil.setOutsideTouchable(true);
        return popUtil;
    }

    public static void showPopUtil(String msg){
        MyService.popUtil = PopUtil.createPopUtil(BaseActivity.getCurrentActivity());
        LayoutInflater inflater = LayoutInflater.from(BaseActivity.getCurrentActivity());
        // 引入窗口配置文件 - 即弹窗的界面
        View view = inflater.inflate(R.layout.toast, null);
        MyService.popUtil.textView.setText(msg);
        MyService.popUtil.showAtLocation(view, Gravity.BOTTOM,0,0);
        //MyService.sp.play(MyService.soundPoolMap.get(1), 1, 1, 0, 0, 1);  //播放声音
        MyService.ifScan = false;
        Vibrator vibrator = (Vibrator)mContext.getSystemService(mContext.VIBRATOR_SERVICE);
        vibrator.vibrate(1000);
    }

    public static void showPopUtil(String msg, boolean ifMusic){
        MyService.popUtil = PopUtil.createPopUtil(BaseActivity.getCurrentActivity());
        LayoutInflater inflater = LayoutInflater.from(BaseActivity.getCurrentActivity());
        // 引入窗口配置文件 - 即弹窗的界面
        View view = inflater.inflate(R.layout.toast, null);
        MyService.popUtil.textView.setText(msg);
        MyService.popUtil.showAtLocation(view, Gravity.BOTTOM,0,0);
        if(ifMusic){
            MyService.sp.play(MyService.soundPoolMap.get(1), 1, 1, 0, 0, 1);
            MyService.ifScan = false;
        }
    }







    public static void closePopUtil(){
        MyService.popUtil.dismiss();
    }

    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }



}
