package com.example.myandroidtiptop.ui.dialog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.TextView;

import com.example.myandroidtiptop.R;

/**
 * @Function: 自定义对话框
 */
public class MyDialog extends android.app.Dialog {
    public Button positiveButton, negativeButton;
    public TextView title, hint;
    Context context;

    public MyDialog(Context context, String hintString) {
        super(context, R.style.MyDialogStyle);
        setSettingDialog(hintString);
    }

    private void setSettingDialog(String hintString) {
        View mView = LayoutInflater.from(getContext()).inflate(R.layout.dialog, null);
        title = (TextView) mView.findViewById(R.id.title);
        hint = (TextView) mView.findViewById(R.id.hint);
        hint.setText(hintString);
        positiveButton = (Button) mView.findViewById(R.id.positiveButton);
        negativeButton = (Button) mView.findViewById(R.id.negativeButton);
        //this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        super.setContentView(mView);
    }


    @Override
    public void setContentView(int layoutResID) {
    }

    @Override
    public void setContentView(View view, LayoutParams params) {
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
