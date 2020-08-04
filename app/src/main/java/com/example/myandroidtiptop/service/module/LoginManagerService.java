package com.example.myandroidtiptop.service.module;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;

import com.example.myandroidtiptop.bean.User;
import com.example.myandroidtiptop.service.MyReceiver;
import com.example.myandroidtiptop.service.MyService;
import com.example.myandroidtiptop.tool.ActionList;
import com.example.myandroidtiptop.tool.HttpPath;
import com.example.myandroidtiptop.tool.HttpReq;
import com.example.myandroidtiptop.ui.activity.LoginActivity;
import com.google.gson.Gson;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class LoginManagerService implements MyReceiver.HalLoginCallback {

    private List<BasicNameValuePair> list = null;
    private JSONObject jsonObject;
    private Context context;


    public LoginManagerService(Context context) {
        this.context = context;
        //注册登录回调接口对象
        MyReceiver.getInstance().registerHalLoginCallback(this);
    }

    @Override
    public void onLogin(Intent intent) {

        //处理由activity请求过来的登录逻辑 ，并回调 广播回 activity

        String username = intent.getStringExtra("username");
        String password = intent.getStringExtra("password");
        list = new ArrayList<BasicNameValuePair>();
        list.add(new BasicNameValuePair("employee", username));
        list.add(new BasicNameValuePair("password", password));
        getUrlData(list, HttpPath.getLoginPath(), ActionList.GET_LOGIN_HANDLER_TYPE, LoginActivity.context);

    }

    public void login(String backData) {
        System.out.println("login login" + backData);
        try {
            jsonObject = new JSONObject(backData);
            String msg = jsonObject.getString("message");
            String data = jsonObject.getString("data");
            Gson gson = new Gson();
            MyService.USER = gson.fromJson(data, User.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //得到结果 发送广播回Activity
        Intent intent = new Intent(ActionList.ACTION_SHOW_LOGIN);
        if (MyService.USER == null) {
            intent.putExtra("ifLogin", false);
            context.sendBroadcast(intent);
        } else {
            intent.putExtra("ifLogin", true);
            context.sendBroadcast(intent);
        }
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
                case ActionList.GET_LOGIN_HANDLER_TYPE:
                    login(data);
                    break;
            }
        }
    };

}
