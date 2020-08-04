package com.example.myandroidtiptop.service;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Vibrator;
import android.widget.Toast;

import com.example.myandroidtiptop.tool.ActionList;
import com.example.myandroidtiptop.tool.PopUtil;
import com.example.myandroidtiptop.ui.activity.LoginActivity;

public class MyReceiver extends BroadcastReceiver {
    private Context context;
    private static MyReceiver myReceiver;


    public final static int SCAN_STATUS_RECEIVING_DNNUM = 1;
    public final static int SCAN_STATUS_RECEIVING_BOXNUM = 2;
    public final static int SCAN_STATUS_GROUNDING_BOXNUM = 3;
    public final static int SCAN_STATUS_GROUNDING_WADDR = 4;
    public final static int SCAN_STATUS_FALSE = 0;
    public final static int SCAN_STATUS_INPUT_FALSE = 5;
    public final static int SCAN_STATUS_PACKING_PKNUM = 6;
    public final static int SCAN_STATUS_PACKING_BOXNUM = 7;
    public final static int SCAN_STATUS_WORETURN_WORNUM = 8;
    public final static int SCAN_STATUS_WORETURN_BOXNUM = 9;
    public final static int SCAN_STATUS_IQC_BOXNUM = 10;
    public final static int SCAN_STATUS_WHRETURN_WHRNUM = 11;
    public final static int SCAN_STATUS_WHRETURN_BOXNUM = 12;
    public final static int SCAN_STATUS_GDSIPTANDEPT_BOXNUM = 13;
    public final static int SCAN_STATUS_GDSIPTANDEPT_WADDR = 14;
    public final static int SCAN_STATUS_GOODSMOVE_BOXNUM = 15;
    public final static int SCAN_STATUS_GOODSMOVE_WADDR = 16;
    public final static int SCAN_STATUS_GROUNDING_LABEL = 17;
    public final static int SCAN_STATUS_ALLOT_AKNUM = 18;     //调拨查询
    public final static int SCAN_STATUS_ALLOT_BOXNUM = 19;    //厂内调拨扫描条码
    public final static int SCAN_STATUS_DUMPING_BKNUM = 20;     //下阶料查询
    public final static int SCAN_STATUS_APART_BKNUM = 21;     //拆解单查询
    public final static int SCAN_STATUS_WHRETURN1_WHRNUM = 22;    //仓退交接查询
    public final static int SCAN_STATUS_RECEIVING1_BOXNUM = 23;    //送货单交接查询
    public final static int SCAN_STATUS_RETURN_DNNUM = 24;  //退货单常量
    public final static int SCAN_STATUS_RETURN_BOXNUM = 25;       //退货单扫描小箱号常量
    public static int scan_status;
    public static long lastClickTime = 0;
    public static final int MIN_CLICK_DELAY_TIME = 500;
    public String qr_data;

    //接口对象 , (调用业务实现类方法)
    HalLoginCallback loginCallback;

    HalReceivingCallback receivingCallback;


    //获取MyReceiver对象
    public static MyReceiver getInstance() {
        if (myReceiver != null) {
            return myReceiver;
        }
        myReceiver = new MyReceiver();
        return myReceiver;
    }

    // 此广播接收并处理Activity传过来的广播并分发到对应的module处理单元去。
    // 由服务 动态注册此广播
    @SuppressLint("MissingPermission")
    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        //throw new UnsupportedOperationException("Not yet implemented");
        String action = intent.getAction();

        System.out.println("onReceive接收到了信息");
//        if(!MyService.ifScan){
//            MyService.sp.play(MyService.soundPoolMap.get(1), 1, 1, 0, 0, 1);
//            Vibrator vibrator = (Vibrator)context.getSystemService(context.VIBRATOR_SERVICE);
//            vibrator.vibrate(3000);
//            return;
//        }

        //把业务分发到对应实现类中去。
        if (intent.getAction().equals(ActionList.action_boot)) {
            Intent sayHelloIntent = new Intent(context, LoginActivity.class);
            sayHelloIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(sayHelloIntent);
        } else if (action.equals(ActionList.ACTION_LOGIN)) {
//            String username = intent.getStringExtra("username");
//            String password = intent.getStringExtra("password");
            loginCallback.onLogin(intent);
        } else if (action.equals(ActionList.ACTION_INPUT_RECEIVING)) {//强制收货
            receivingCallback.onInputReceiving(intent);
        }else if (action.equals(ActionList.ACTION_QUERY_DNNUM)) {
//            String dn_num = intent.getStringExtra("qr_data");
            receivingCallback.onQueryDnnum(intent);
        }else if (action.equals(ActionList.ACTION_QR_DATA_RECEIVED)) {
            qr_data = intent.getStringExtra("qr_data");
            getScanStatus(qr_data);
            //if (!qr_data.equals("") && qr_data.substring(qr_data.length() - 1).equals("\r")) {
            if (!qr_data.equals("") ) {
                switch (scan_status) {
                    /**********************************收货模块***************************************/
                    case SCAN_STATUS_RECEIVING_DNNUM://收货扫描送货单
                        receivingCallback.onScanDnnum(qr_data);
                        break;
                    case SCAN_STATUS_RECEIVING_BOXNUM://收货扫描箱号
                        receivingCallback.onScanBoxnum(intent, qr_data);
                        break;
                }
//                String qr_data = intent.getStringExtra("qr_data"); //参数
//                //把业务分发到对应实现类中去。
//                //loginCallback.onLogin(intent);
//                receivingCallback.onQueryDnnum(intent);
            }
        }
    }

    //注册广播方法， 由service调用
    public void registerReceiver(Context txt) {
        context = txt.getApplicationContext();
        IntentFilter inFilter = new IntentFilter();
        inFilter.addAction(ActionList.ACTION_LOGIN);
        inFilter.addAction(ActionList.ACTION_QUERY_DNNUM);
        inFilter.addAction(ActionList.ACTION_QR_DATA_RECEIVED);

        context.registerReceiver(this, inFilter);
    }

    //登录模块 回调接口
    public static interface HalLoginCallback {
        public void onLogin(Intent intent);
    }

    public static interface HalReceivingCallback {
        public void onQueryDnnum(Intent intent);

        public void onInputReceiving(Intent intent);
        public void onScanDnnum(String qr_data);
        public void onScanBoxnum(Intent intent,String qr_data);
    }

    public void registerHalLoginCallback(HalLoginCallback callback) {
        loginCallback = callback;
    }

    public void registerHalReceivingCallback(HalReceivingCallback callback) {
        receivingCallback = callback;
    }




    private void getScanStatus(String qr_data) {
        //qr_data = intent.getStringExtra("data");
        if (qr_data.length() <= 8) {
            scan_status = SCAN_STATUS_INPUT_FALSE;
            return;
        }
        String markString = qr_data.substring(0, 2);
        String activityName = getRunningActivityName();
        if (markString.equals("GD")) {
            if (activityName.equals("ReceivingActivity")) {         //送货单收货
                scan_status = SCAN_STATUS_RECEIVING_DNNUM;
            } else if (activityName.equals("Join1Activity")) {       //送货单交接
                scan_status = SCAN_STATUS_RECEIVING1_BOXNUM;
            } else if (activityName.equals("ReturnActivity")) {       //退货单交接
                scan_status = SCAN_STATUS_RETURN_DNNUM;
            } else {
                scan_status = SCAN_STATUS_FALSE;
            }
        } else if (markString.equals("GB")) {   //小跳吗
            if (activityName.equals("ReceivingActivity")) {
                scan_status = SCAN_STATUS_RECEIVING_BOXNUM;
            } else if (activityName.equals("ReturnActivity")) {  //退货扫描小箱号
                scan_status = SCAN_STATUS_RETURN_BOXNUM;
            } else if (activityName.equals("GroundingActivity")) {
                scan_status = SCAN_STATUS_GROUNDING_BOXNUM;
            } else if (activityName.equals("PackingActivity")) {
                scan_status = SCAN_STATUS_PACKING_BOXNUM;
            } else if (activityName.equals("AllotActivity")) {    //厂内调拨条码扫描
                scan_status = SCAN_STATUS_ALLOT_BOXNUM;
            } else if (activityName.equals("WOReturnActivity")) {
                scan_status = SCAN_STATUS_WORETURN_BOXNUM;
            } else if (activityName.equals("IQCActivity")) {
                scan_status = SCAN_STATUS_IQC_BOXNUM;
            } else if (activityName.equals("WHReturnActivity")) {
                scan_status = SCAN_STATUS_WHRETURN_BOXNUM;
            } else if (activityName.equals("GoodsIptAndEptActivity")) {
                scan_status = SCAN_STATUS_GDSIPTANDEPT_BOXNUM;
            } else if (activityName.equals("GoodsMoveActivity")) {
                scan_status = SCAN_STATUS_GOODSMOVE_BOXNUM;
            } else {
                scan_status = SCAN_STATUS_FALSE;
            }
        } else if (markString.equals("33")) {
            if (activityName.equals("PackingActivity")) {
                scan_status = SCAN_STATUS_PACKING_PKNUM;
            } else {
                scan_status = SCAN_STATUS_FALSE;
            }
        } else if (markString.equals("34")) {
            if (activityName.equals("WOReturnActivity")) {
                scan_status = SCAN_STATUS_WORETURN_WORNUM;
            } else {
                scan_status = SCAN_STATUS_FALSE;
            }
        } else if (markString.equals("11")) {
            if (activityName.equals("PackingActivity")) {
                scan_status = SCAN_STATUS_PACKING_PKNUM;
            } else {
                scan_status = SCAN_STATUS_FALSE;
            }
        } else if (markString.equals("24")) {
            if (activityName.equals("WHReturnActivity")) {
                scan_status = SCAN_STATUS_WHRETURN_WHRNUM;
            } else if (activityName.equals("JoinActivity")) {     //仓退交接
                scan_status = SCAN_STATUS_WHRETURN1_WHRNUM;
            } else {
                scan_status = SCAN_STATUS_FALSE;
            }
        } else if (markString.equals("LB")) {
            if (activityName.equals("GroundingActivity")) {
                scan_status = SCAN_STATUS_GROUNDING_LABEL;
            } else {
                scan_status = SCAN_STATUS_FALSE;
            }
        } else if (markString.equals("14")) {              //厂内调拨
            if (activityName.equals("AllotActivity")) {
                scan_status = SCAN_STATUS_ALLOT_AKNUM;
            } else {
                scan_status = SCAN_STATUS_FALSE;
            }
        } else if (markString.equals("3D")) {              //下阶报废
            if (activityName.equals("DumpingActivity")) {
                scan_status = SCAN_STATUS_DUMPING_BKNUM;
            } else {
                scan_status = SCAN_STATUS_FALSE;
            }
        } else if (markString.equals("38")) {              //组合拆解单
            if (activityName.equals("ApartActivity")) {
                scan_status = SCAN_STATUS_APART_BKNUM;
            } else {
                scan_status = SCAN_STATUS_FALSE;
            }
        } else {
            markString = qr_data.substring(2, 7);
            if (markString.equals("rvv32")) {
                if (activityName.equals("GroundingActivity")) {
                    scan_status = SCAN_STATUS_GROUNDING_WADDR;
                } else if (activityName.equals("GoodsIptAndEptActivity")) {
                    scan_status = SCAN_STATUS_GDSIPTANDEPT_WADDR;
                } else if (activityName.equals("GoodsMoveActivity")) {
                    scan_status = SCAN_STATUS_GOODSMOVE_WADDR;
                }
            } else if (activityName.equals("GoodsIptAndEptActivity")) {
                scan_status = SCAN_STATUS_GDSIPTANDEPT_BOXNUM;
            }
        }

    }
    //获取当前运行的 ActivityName
    private String getRunningActivityName() {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        String runningActivity = activityManager.getRunningTasks(1).get(0).topActivity.getClassName();
        runningActivity = runningActivity.replace("com.example.myandroidtiptop.ui.activity.", ""); //去除前缀
        return runningActivity;
    }

}