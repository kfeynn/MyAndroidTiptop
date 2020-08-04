package com.example.myandroidtiptop.service.module;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.example.myandroidtiptop.bean.Box;
import com.example.myandroidtiptop.bean.PN;
import com.example.myandroidtiptop.bean.PNSub;
import com.example.myandroidtiptop.service.MyReceiver;
import com.example.myandroidtiptop.service.MyService;
import com.example.myandroidtiptop.tool.ActionList;
import com.example.myandroidtiptop.tool.HttpPath;
import com.example.myandroidtiptop.tool.HttpReq;
import com.example.myandroidtiptop.ui.activity.BaseActivity;
import com.example.myandroidtiptop.ui.activity.LoginActivity;
import com.example.myandroidtiptop.ui.activity.ReceivingActivity;
import com.example.myandroidtiptop.ui.activity.TestActivity;
import com.google.gson.Gson;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import  com.example.myandroidtiptop.tool.PopUtil;

public class ReceivingManagerService implements MyReceiver.HalReceivingCallback {

    private List<BasicNameValuePair> list = null;
    private JSONObject jsonObject;
    private Context context;
    private String qr_data;

     public ReceivingManagerService(Context context){
        this.context=context;
         //注册 回调接口对象。
        MyReceiver.getInstance().registerHalReceivingCallback(this);
     }

    @Override
    public void onQueryDnnum(Intent intent) {
        //处理由activity请求过来的 请求逻辑 ，并回调 广播回 activity
        //String dn_num = intent.getStringExtra("dn_num");
        String dn_num = intent.getStringExtra("qr_data");
        list = new ArrayList<BasicNameValuePair>();
        //list.add(new BasicNameValuePair("dn_num", dn_num));
        list.add(new BasicNameValuePair("dn_num", dn_num));
        //调用获取数据 ， （指定返回页面 没有用？）ReceivingActivity,TestActivity
        getUrlData(list, HttpPath.getDnMaterialListPath(), ActionList.GET_PN_HANDLER_TYPE, BaseActivity.context);

    }

    public void getUrlData(List<BasicNameValuePair> list, String url, int type, Context context) {
        HttpReq httpReq = new HttpReq(list, url, mhandler, type, context);
        httpReq.start();
    }

    //Handler作用： 主线程与子线程通信
    private Handler mhandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String data = msg.getData().getString("data");
            switch (msg.what) {
                case ActionList.GET_PN_HANDLER_TYPE:
                    System.out.println(data);
                    getPnBean(data);
                    break;
                case ActionList.GET_BOX_BY_BOXNUM_HANDLER_TYPE:
                    System.out.println(data);
                    getBoxBean(data);
            }
        }
    };


    @Override
    public void onScanDnnum(String qr_data) {
        MyService.scanedBox.clear();
        this.qr_data = qr_data;
        list = new ArrayList<BasicNameValuePair>();
        list.add(new BasicNameValuePair("dn_num", qr_data));
        getUrlData(list, HttpPath.getDnMaterialListPath(), ActionList.GET_PN_HANDLER_TYPE, ReceivingActivity.context);
    }

    @Override
    public void onScanBoxnum(Intent intent, String qr_data) {
        if (MyService.pn != null) {
            for (Box box : MyService.scanedBox) {
                if (qr_data.equals(box.getBoxnum())) {
                    PopUtil.showPopUtil("此标贴已扫描，请务重复扫描");
                    return;
                }
            }
            list = new ArrayList<BasicNameValuePair>();
            list.add(new BasicNameValuePair("boxnum", qr_data));
            getUrlData(list, HttpPath.getBoxByBoxnumPath(), ActionList.GET_BOX_BY_BOXNUM_HANDLER_TYPE, ReceivingActivity.context);
        } else {
            PopUtil.showPopUtil("请先扫描送货单的二维码");
        }
    }

    //解决double减法精度失真
    public  double subDouble(double m1, double m2) {
        BigDecimal p1 = new BigDecimal(Double.toString(m1));
        BigDecimal p2 = new BigDecimal(Double.toString(m2));
        return p1.subtract(p2).doubleValue();
    }

    public void getBoxBean(String data) {
        Intent intent = new Intent(ActionList.ACTION_SHOW_UPDATE_PNSUBS);
        if (data.equals("获取失败")) {
            MyService.pn = null;
            PopUtil.showPopUtil("送货单号错误或者网络连接失败，请重新检查");
        } else {
            try {
                jsonObject = new JSONObject(data);
                String msg = jsonObject.getString("message");
                String data1 = jsonObject.getString("data");
                Gson gson = new Gson();
                if (msg.equals("success")) {
                    Box box = gson.fromJson(data1, Box.class);
                    double boxPmn20 = box.getPmn20();
                    double inputWeight = 0;
                    double i = 0;
                    boolean flag1 = true;
                    for (PNSub pnSub : MyService.pn.getPnsubs()){
                        if(box.getPmn04().equals(pnSub.getPmn04())){
                            i = i + pnSub.getPmn20();
                            flag1 = false;//判断物料是否属于这张送货单
                        }
                    }
                    if(flag1){
                        PopUtil.showPopUtil("物料不属于此送货单，请检查并重新扫描标签");
                        return;
                    }
                    if(boxPmn20 > i){
                        PopUtil.showPopUtil("标贴条码数量超出送货数量，请检查并重新扫描标签");
                        return;
                    }
                    for1:
                    for (PNSub pnSub : MyService.pn.getPnsubs()) {
                        if (box.getPmn04().equals(pnSub.getPmn04()) && pnSub.getPmn20()!=0 ) {
                            double pnsubPmn20 = pnSub.getPmn20();
                            if (pnSub.getPmn86().equals("kg")) {
                                pnSub.setIfChangeWeight(true);
                            }
                            if (box.getPmn20() >= pnSub.getPmn20()) {
                                box.setPmn20(pnSub.getPmn20());
                                Box box1 = (Box) box.clone();
                                pnSub.boxs.add(box1);
                                pnSub.setReal_pmn87(pnSub.getReal_pmn87() + box1.getPmn20());

                                boxPmn20=subDouble(boxPmn20,pnSub.getPmn20());//zyq
                                //boxPmn20 = boxPmn20 - pnSub.getPmn20();
                                box.setPmn20(boxPmn20);
                                pnSub.setPmn20(0);

                            } else if (box.getPmn20() < pnSub.getPmn20()) {
                                pnSub.setPmn20(pnSub.getPmn20() - box.getPmn20());
                                Box box1 = (Box) box.clone();
                                pnSub.boxs.add(box1);
                                pnSub.setReal_pmn87(pnSub.getReal_pmn87() + box1.getPmn20());
                                box.setPmn20(0);
                            }
                            if (box.getPmn20() == 0) {
                                boolean flag = true;
                                if (pnSub.getPmn86().equals("kg")) {
                                    for2:
                                    for (PNSub pnSub1 : MyService.pn.getPnsubs()) {
                                        if (pnSub.getPmn04().equals(pnSub1.getPmn04())) {
                                            if (pnSub1.getPmn20() != 0) {
                                                flag = false;
                                                break for2;
                                            } else {
                                                inputWeight = inputWeight + pnSub1.getPmn87();
                                            }
                                        }
                                    }
                                    if (flag) {
                                        intent.putExtra("inputWeight", inputWeight);
                                        intent.putExtra("pmn04", pnSub.getPmn04());
                                    } else {
                                        intent.putExtra("inputWeight", 0.0);
                                    }
                                }
                                break for1;
                            }
                        }
                    }
                    if (box.getPmn20() != 0) {
                        PopUtil.showPopUtil("标贴条码数量超出送货数量，请检查并重新扫描标签");
                        MyService.pn = null;
                        Intent intent1 = new Intent(ActionList.ACTION_SHOW_PNSUBS);
                        intent1.putExtra("dnnum", "");
                        context.sendBroadcast(intent);
                        return;
                    }
                    MyService.scanedBox.add(box);
                    boolean ifStopFlag = true;
                    for (PNSub pnSub : MyService.pn.getPnsubs()) {
                        if (pnSub.getPmn20() != 0) {
                            ifStopFlag = false;
                            break;
                        }
                    }
                    if (ifStopFlag) {
                        intent.putExtra("ifStop", true);  //判定是否扫描完，后续用来判断是否可以调用收货
                    } else {
                        intent.putExtra("ifStop", false);
                    }
                    intent.putExtra("ifInput", false);
                    context.sendBroadcast(intent);
                    return;
                } else {
                    PopUtil.showPopUtil(msg);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }
    }


    public void getPnBean(String data) {
        if (data.equals("获取失败")) {
            MyService.pn = null;
            PopUtil.showPopUtil("送货单号错误或者网络连接失败，请重新检查");  //提示信息 框
            Toast.makeText(this.context,"送货单号错误或者网络连接失败，请重新检查",Toast.LENGTH_LONG).show();
        } else {
            try {
                jsonObject = new JSONObject(data);
                String msg = jsonObject.getString("message");
                String data1 = jsonObject.getString("data");
                Gson gson = new Gson();
                if (msg.equals("success")) {
                    MyService.pn = gson.fromJson(data1, PN.class);
                    for(PNSub pnSub : MyService.pn.getPnsubs()){
                        pnSub.setPmn20_copy(pnSub.getPmn20());
                    }
                    if(MyService.pn.getStatus()==2){
                    //if (false) {
                        //送货单已收货
                        PopUtil.showPopUtil("此送货单已收货");
                        MyService.pn = null;
                    }
                    Intent intent = new Intent(ActionList.ACTION_SHOW_PNSUBS);
                    //intent.putExtra("dnnum", qr_data);
                    intent.putExtra("dnnum", MyService.pn.getDnnum());
                    context.sendBroadcast(intent);

                } else {
                    Toast.makeText(this.context,msg,Toast.LENGTH_LONG).show();
                    //提示会出错 ！ 这里没有做 。
                    PopUtil.showPopUtil(msg);
                    MyService.pn = null;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    //强制收货
    public void onInputReceiving(Intent intent) {
        intent = new Intent(ActionList.ACTION_SHOW_UPDATE_PNSUBS);
        boolean flag = true;
        intent.putExtra("ifInput", true);
        if (MyService.pn == null) {
            PopUtil.showPopUtil("您要收货的物料为空，请检查");
            return;
        }
        double inputWeight = 0;
        for (PNSub pnSub : MyService.pn.getPnsubs()) {
            if (pnSub.getPmn86().equals("kg") && (pnSub.getBoxs().size() > 0) && pnSub.isIfChangeWeight()) {
                flag = false;
                for (PNSub pnSub1 : MyService.pn.getPnsubs()) {
                    if (pnSub.getPmn04().equals(pnSub1.getPmn04()) && pnSub.getBoxs().size() > 0) {
                        inputWeight = inputWeight + ((pnSub1.getPmn20_copy() - pnSub1.getPmn20()) / pnSub1.getPmn20_copy()) * pnSub1.getPmn87();
                    }
                }
                intent.putExtra("inputWeight", inputWeight);
                intent.putExtra("pmn04", pnSub.getPmn04());
                context.sendBroadcast(intent);
                return;
            }

        }
        if (flag) {
            intent.putExtra("ifStop", true);
            context.sendBroadcast(intent);
        }

    }
}
