package com.example.myandroidtiptop.ui.dialog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.myandroidtiptop.R;

/**
 * Created by Administrator on 2018/8/17.
 */

public class InputDialog extends android.app.Dialog{
    private Button positiveButton, negativeButton;
    public TextView tv_key, tv_title;
    public EditText et_value;
    Context context;

    public InputDialog(Context context, String title, String key) {
        super(context, R.style.MyDialogStyle);
        setSettingDialog(title,key);
    }

    private void setSettingDialog(String title, String key) {
        //加载布局文件
        View mView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_input, null);
        tv_title = (TextView) mView.findViewById(R.id.title);
        tv_key = (TextView) mView.findViewById(R.id.tv_key);
        et_value = mView.findViewById(R.id.et_value);
        tv_title.setText(title);
        tv_key.setText(key);
        positiveButton = (Button) mView.findViewById(R.id.positiveButton);
        negativeButton = (Button) mView.findViewById(R.id.negativeButton);
        //this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        super.setContentView(mView);
    }


    @Override
    public void setContentView(int layoutResID) {
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
    }

    @Override
    public void setContentView(View view) {
    }

    /**
     * 确定键监听器
     * @param listener
     */
    public void setOnPositiveListener(View.OnClickListener listener){
        positiveButton.setOnClickListener(listener);
    }
    /**
     * 取消键监听器
     * @param listener
     */
    public void setOnNegativeListener(View.OnClickListener listener){
        negativeButton.setOnClickListener(listener);
    }

}
