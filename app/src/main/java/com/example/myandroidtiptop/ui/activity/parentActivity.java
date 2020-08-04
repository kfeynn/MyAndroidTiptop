package com.example.myandroidtiptop.ui.activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.myandroidtiptop.R;
import com.example.myandroidtiptop.service.MyService;
import com.example.myandroidtiptop.tool.ActionList;
import com.example.myandroidtiptop.tool.PopUtil;
import com.example.myandroidtiptop.ui.dialog.MyDialog;

import org.json.JSONException;
import org.json.JSONObject;
//Activity AppCompatActivity
public class parentActivity extends AppCompatActivity {
    public static Context context;
    private JSONObject jsonObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        //注册广播  由处理module 回传 activity
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ActionList.ACTION_SHOW_LOGIN);
        intentFilter.addAction(ActionList.ACTION_SHOW_PNSUBS);
        intentFilter.addAction(ActionList.ACTION_SHOW_UPDATE_PNSUBS);
        registerReceiver(receiver, intentFilter);
    }

    //为继承其的子类 activity 提供需要对应重载的方法（不同功能的页面重载不同的方法）。
    protected void login(boolean ifLogin){}
    protected void showPnsubs(String dunum){}
    protected void updatePnsubs(double Weight, boolean ifStop, String pmn04,boolean ifInput) {}
    protected void confirmReceiving(String hintString){}  //是否确定收货
    protected void confirmReturn(String hintString){}    //退货单提交
    protected void confirmPacking(String content){}
    protected void confirmPacking(){}
    protected void confirmGrounding(){}
    protected void confirmWoReturn(){}
    protected void confirmWhReturn(){}
    protected void confirmIQC(){}
    protected void confirmGoodsMove(){}

    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            System.out.println("parentActivity receiver");
            String action = intent.getAction();
            if (action.equals(ActionList.ACTION_SHOW_LOGIN)) {
                boolean ifLogin = intent.getBooleanExtra("ifLogin", false);
                //调用登录继承activity 重写后的实际方法。
                login(ifLogin);
            } else if (action.equals(ActionList.ACTION_SHOW_PNSUBS)) {
                String dnnum = intent.getStringExtra("dnnum");
                //调用收货继承activity 重写后的实际方法。
                showPnsubs(dnnum);
            }
            else if (action.equals(ActionList.ACTION_SHOW_UPDATE_PNSUBS)){  //收货单更新数量  box
                boolean ifStop = intent.getBooleanExtra("ifStop", false);
                double inputWeight = intent.getDoubleExtra("inputWeight",0.00);
                String pmn04 = intent.getStringExtra("pmn04");
                boolean ifInput = intent.getBooleanExtra("ifInput", false);
                updatePnsubs(inputWeight, ifStop,pmn04, ifInput);

            }
            //else if ....继续扩展 由service回传给activity 业务

        }
    };



    //以下为相关activity中对应按钮触发相关操作
    public void commonDialog(final String hintString, final int Listenertype) {
        final MyDialog dialog = new MyDialog(this, hintString);
        dialog.setOnPositiveListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (Listenertype) {
                    case 1://是否扫描包装票
                        dialog.cancel();
                        break;
                    case 2://是否确定收货
                        confirmReceiving(hintString);
                        break;
                    case 3://是否确定上架
                        confirmGrounding();
                        break;
                    case 4://是否确定发料
                        confirmPacking();
                        closeKeyboard();
                        break;
                    case 5://是否确定退料
                        confirmWoReturn();
                        closeKeyboard();
                        break;
                    case 6://是否确认检测结果
                        confirmIQC();
                        break;
                    case 7://是否确认仓退
                        confirmWhReturn();
                        break;
                    case 8://是否确认调拨
                        confirmGoodsMove();
                        break;
                    case 9://是否确认强制上架
                        confirmGrounding();
                        break;
                    case 10://是否确定退货
                        confirmReturn(hintString);
                        break;
                }
                dialog.cancel();
            }
        });
        dialog.setOnNegativeListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (Listenertype) {
                    case 1://是否扫描包装票
                        dialog.cancel();
                        break;
                    case 2://是否确定收货
                        break;
                    case 3://是否确定上架
                        break;
                    case 4://是否确定发料
                        break;
                    case 5://是否确定退料
                        break;
                    case 6://是否确认检测结果
                        break;
                    case 7://是否确认仓退
                        break;
                    case 8://是否确认调拨
                        break;
                    case 9://是否确认强制上架
                        break;
                    case 10://是否确认强制退货
                        break;
                }
                dialog.cancel();
            }
        });
        dialog.show();
        WindowManager windowManager = getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
        lp.width = (int) (display.getWidth()); //设置宽度
        dialog.getWindow().setAttributes(lp);
    }

    //关闭输入框
    public void closeKeyboard() {
        View view = getWindow().peekDecorView();
        if (view != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    //页面提示信息
    public void backStauts(String backData) {
        if (backData.equals("获取失败")) {
            PopUtil.showPopUtil("网络连接失败，请重新检查");
        } else {
            try {
                jsonObject = new JSONObject(backData);
                String message = jsonObject.getString("message");
                int status = jsonObject.getInt("status");
                System.out.println(message);
                if (status == 200) {
                    Toast.makeText(this, message,Toast.LENGTH_SHORT).show();
                } else {
                    PopUtil.showPopUtil(message);
                }
                // String data = jsonObject.getString("data");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //销毁
        unregisterReceiver(receiver);
        MyService.pn = null;
//        MyService.PKBoxList = null;
//        MyService.AKBoxList = null;    //销毁
//        MyService.BKBoxList = null;    //销毁
//        MyService.CKBoxList = null;    //销毁
//        MyService.whrBoxList = null;    //仓退数据列表销毁
//        MyService.RnBoxList = null;
//        MyService.imptAndEmptItemsList = null;
//        MyService.imgsBoxList = null;
//        MyService.iqcItemList = null;
//        MyService.scanedBox.clear();
        finish();
        System.out.println("close activty");
    }


    public Handler mhandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String data = msg.getData().getString("data");
            backStauts(data);
        }
    };


}


