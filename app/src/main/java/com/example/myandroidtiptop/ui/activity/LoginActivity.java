package com.example.myandroidtiptop.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myandroidtiptop.R;
import com.example.myandroidtiptop.bean.User;
import com.example.myandroidtiptop.tool.ActionList;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends parentActivity {

    //public static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button btn_click = findViewById(R.id.btn_login);
        btn_click.setOnClickListener(new  MyClickListener());

        //启动服务 。（由服务调用注册广播）
        Intent serviceIntent = new Intent();
        serviceIntent.setPackage(getPackageName());
        serviceIntent.setAction("com.example.myandroidtiptop.action.main");
        this.startService(serviceIntent);

    }

    //内部类实现事件监听
    class MyClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_login://此处是对布局中设置的id直接进行判断，
                    //Toast.makeText(LoginActivity.this, "哈哈哈，我被点击了2", Toast.LENGTH_SHORT).show();

                    EditText username = findViewById(R.id.username);
                    EditText password = findViewById(R.id.password);

                    //System.out.println(ActionList.ACTION_LOGIN);

                    Intent intent = new Intent(ActionList.ACTION_LOGIN);
//                    intent.putExtra("username",username.getText().toString());
//                    intent.putExtra("password", password.getText().toString());
                    intent.putExtra("username","GT36434");
                    intent.putExtra("password", "GT36434");
                    LoginActivity.this.sendBroadcast(intent);
                    break;
            }
        }
    }

    @Override
    protected void login(boolean ifLogin) {
        System.out.println("login successs");
        if (ifLogin) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "账号或密码错误，请重新输入", Toast.LENGTH_LONG).show();
        }
    }


//    private Handler mhandler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            System.out.println("loginActivity handler");
//            String data = msg.getData().getString("data");
//            getUpdateVersion(data);
//        }
//    };
    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }
}
