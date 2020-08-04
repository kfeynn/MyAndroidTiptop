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

public class EditTextDialog extends android.app.Dialog{
    private Button positiveButton, negativeButton;
    public TextView title,units;
    public EditText editText;
    Context context;

    public EditTextDialog(Context context, String et_default, String title, String units) {
        super(context, R.style.MyDialogStyle);
        setSettingDialog(et_default,title,units);
    }

    private void setSettingDialog(String et_default, String titleSting, String unitsString) {
        View mView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_edittext, null);
        title = (TextView) mView.findViewById(R.id.title);
        editText = mView.findViewById(R.id.editText);
        units = mView.findViewById(R.id.units);
        editText.setText(et_default);
        title.setText(titleSting);
        units.setText(unitsString);
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
