package com.example.myandroidtiptop.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myandroidtiptop.R;
import com.example.myandroidtiptop.bean.PNSub;
import com.example.myandroidtiptop.service.MyService;
import com.example.myandroidtiptop.tool.ActionList;
import com.example.myandroidtiptop.ui.adapter.MainAdapter;
import com.example.myandroidtiptop.ui.adapter.TestAdapter;
import com.example.myandroidtiptop.ui.dialog.InputDialog;

import java.util.List;

public class TestActivity extends BaseActivity {

    private Button btn_input_dnnum;
    private TextView tv_show_dnnum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);  // 隐藏标题栏
        setContentView(R.layout.activity_test);
        init();
    }

    private void init() {
        setTitle(itemArray[0]);
        back();
        //btn_input_boxnum = findViewById(R.id.btn_input_boxnum);
        btn_input_dnnum = findViewById(R.id.btn_input_dnnum);
        //btn_input_receiving = findViewById(R.id.btn_input_receiving);
        tv_show_dnnum = findViewById(R.id.tv_show_dnnum);
        btn_input_dnnum.setOnClickListener(new MyClickListener());
        //btn_input_boxnum.setOnClickListener(this);
        //btn_input_receiving.setOnClickListener(this);
        //mRecyclerView = findViewById(R.id.gridview_receiving);
        //mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        //receivingAdapter = new ReceivingAdapter(this);
    }

    @Override
    protected void showPnsubs(String dnnum) {
        super.showPnsubs(dnnum);
        tv_show_dnnum.setText("送货单号：" + dnnum);
        closeKeyboard();


        //  加载数据。
        List<PNSub> pubs =  MyService.pn.getPnsubs();
        RecyclerView recyclerView = findViewById(R.id.gridview_receiving);
        //线性布局
        //LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //横向排列
        //layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        //网格布局
        GridLayoutManager layoutManager = new GridLayoutManager(this,1);
        //瀑布流布局
        //StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(layoutManager);
        TestAdapter adapter = new TestAdapter(pubs);
        recyclerView.setAdapter(adapter);

        //  加载数据。
        //receivingAdapter.notifyDataChanged();

    }


    //内部类实现事件监听
    class MyClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_input_dnnum://此处是对布局中设置的id直接进行判断，
                    //Toast.makeText(LoginActivity.this, "哈哈哈，我被点击了2", Toast.LENGTH_SHORT).show();

//                    EditText username = findViewById(R.id.username);
//                    EditText password = findViewById(R.id.password);

                    //System.out.println(ActionList.ACTION_LOGIN);

//                    Intent intent = new Intent(ActionList.ACTION_LOGIN);
//                    intent.putExtra("username",username.getText().toString());
//                    intent.putExtra("password", password.getText().toString());
//                    intent.putExtra("username","GT36434");
//                    intent.putExtra("password", "GT36434");
//                    LoginActivity.this.sendBroadcast(intent);

                    inputDialog("查询送货单", "送货单号", 1);
                    break;
            }
        }
    }

    private void inputDialog(String title, String key, final int Listenertype) {
        final InputDialog dialog = new InputDialog(this, title, key);
        //处理
        final Intent intent = new Intent(ActionList.ACTION_QUERY_DNNUM);
        //确定按钮
        dialog.setOnPositiveListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (Listenertype) {
                    case 1://输入送货单号

                        //Toast.makeText(TestActivity.this, "确定按钮", Toast.LENGTH_SHORT).show();

                        String dnnum = dialog.et_value.getText().toString() + "\r";

                        if(dnnum == null || dnnum.equals("\r")) {
                            //通过接口查一个最新的 有效订单号
                            dnnum = "GDRJ200624002" ;  //难输，给个默认值
                        }

                        intent.putExtra("qr_data", dnnum);
                        sendBroadcast(intent);
                        break;
//                    case 2://输入箱号
//                        String boxnum = dialog.et_value.getText().toString() + "\r";
//                        intent.putExtra("qr_data", boxnum);
//                        sendBroadcast(intent);
//                        break;
                }
                //取消对话框
                dialog.cancel();
            }
        });
        //取消按钮
        dialog.setOnNegativeListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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


}
