package com.example.myandroidtiptop.service;

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.IBinder;

import com.example.myandroidtiptop.R;
import com.example.myandroidtiptop.bean.Box;
import com.example.myandroidtiptop.bean.PN;
import com.example.myandroidtiptop.bean.User;
import com.example.myandroidtiptop.service.module.LoginManagerService;
import com.example.myandroidtiptop.service.module.ReceivingManagerService;
import com.example.myandroidtiptop.tool.PopUtil;

import java.util.ArrayList;
import java.util.HashMap;

public class MyService extends Service {
    public static User USER;
    public static PN pn;
    public static boolean ifScan = true;
    public static SoundPool sp;
    public static HashMap<Integer, Integer> soundPoolMap = new HashMap<Integer, Integer>();
    public static ArrayList<Box> scanedBox = new ArrayList<Box>();//存放已扫描箱子
    private LoginManagerService loginManager;
    private ReceivingManagerService receivingManager;
    public static PopUtil popUtil;

    public MyService(){}

    //动态广播通常注册在Service的onCreate方法当中，在Service销毁的时候，会解除注册。
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        //throw new UnsupportedOperationException("Not yet implemented");
        return  null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        System.out.println("service started");

        //注册回调函数 （分发业务到各自功能类）(构造函数)
        loginManager = new LoginManagerService(this);
        receivingManager  = new ReceivingManagerService(this);

        //注册广播 (通过MyReceiver对象调用)
        MyReceiver.getInstance().registerReceiver(this);

        MyService.popUtil = PopUtil.createPopUtil(this);
        sp = new SoundPool(10, AudioManager.STREAM_MUSIC, 5);
        //soundPoolMap.put(1, sp.load(this, R.raw.bibi, 1));

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println("service destroyed");

    }
}
