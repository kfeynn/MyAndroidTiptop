package com.example.myandroidtiptop.ui.groupedadapter.holder;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.SparseArray;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myandroidtiptop.R;
import com.example.myandroidtiptop.service.MyService;
import com.example.myandroidtiptop.tool.PopUtil;


/**
 * 通用的RecyclerView.ViewHolder。提供了根据viewId获取View的方法。
 * 提供了对View、TextView、ImageView的常用设置方法。
 */
public class BaseViewHolder extends RecyclerView.ViewHolder {

    private SparseArray<View> mViews;
    private static String TAG = "MyBaseViewHolder";
    public BaseViewHolder(View itemView) {
        super(itemView);
        mViews = new SparseArray<>();
    }

    /**
     * 获取item对应的ViewDataBinding对象
     *
     * @param <T>
     * @return
     */
    public <T extends ViewDataBinding> T getBinding() {
        return DataBindingUtil.getBinding(this.itemView);
    }

    /**
     * 根据View Id 获取对应的View
     *
     * @param viewId
     * @param <T>
     * @return
     */
    public <T extends View> T get(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = this.itemView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    //******** 提供对View、TextView、ImageView的常用设置方法 ******//

    public BaseViewHolder setText(int viewId, String text) {
        TextView tv = get(viewId);
        tv.setText(text);
        return this;
    }

    public BaseViewHolder setText(int viewId, int textRes) {
        TextView tv = get(viewId);
        tv.setText(textRes);
        return this;
    }

    public BaseViewHolder setTextColor(int viewId, int textColor) {
        TextView view = get(viewId);
        view.setTextColor(textColor);
        return this;
    }

    public BaseViewHolder setTextSize(int viewId, int size) {
        TextView view = get(viewId);
        view.setTextSize(size);
        return this;
    }

    public BaseViewHolder setImageResource(int viewId, int resId) {
        ImageView view = get(viewId);
        view.setImageResource(resId);
        return this;
    }

    public BaseViewHolder setImageBitmap(int viewId, Bitmap bitmap) {
        ImageView view = get(viewId);
        view.setImageBitmap(bitmap);
        return this;
    }


    public BaseViewHolder setImageDrawable(int viewId, Drawable drawable) {
        ImageView view = get(viewId);
        view.setImageDrawable(drawable);
        return this;
    }


    public BaseViewHolder setBackgroundColor(int viewId, int color) {
        View view = get(viewId);
        view.setBackgroundColor(color);
        return this;
    }

    public BaseViewHolder setBackgroundRes(int viewId, int backgroundRes) {
        View view = get(viewId);
        view.setBackgroundResource(backgroundRes);
        return this;
    }

    public BaseViewHolder setVisible(int viewId, boolean visible) {
        View view = get(viewId);
        view.setVisibility(visible ? View.VISIBLE : View.GONE);
        return this;
    }

    public BaseViewHolder setVisible(int viewId, int visible) {
        View view = get(viewId);
        view.setVisibility(visible);
        return this;
    }

    //region setClickListener 废弃
/*    public BaseViewHolder setClickListener(int viewId, final int groupPosition, final String gate_name,
                                           final List<GroundingGood> goodslist,boolean ifClickable){
        final Button button = get(viewId);
        if(ifClickable){
            button.setBackgroundResource(R.drawable.shape);
            button.setClickable(true);
        }else {
            button.setBackgroundColor(Color.GRAY);
            button.setClickable(false);
        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e(TAG, "onClick: groupPosition="+groupPosition + gate_name);
                button.setBackgroundColor(Color.GRAY);
                button.setClickable(false);
                if(gate_name.equals(KbService.GATE_LEFT)){
                    KbService.leftGroundingAddr.getGoodsList().get(groupPosition).setChange(true);
                }else if(gate_name.equals(KbService.GATE_RIGHT)){
                    KbService.rightGroundingAddr.getGoodsList().get(groupPosition).setChange(true);
                }
            }
        });
        return this;
    }*/
    //endregion

    public BaseViewHolder setEditText(int viewId, String text, final int groupPosition, final String type, final BaseViewHolder holder) {
        final EditText et = get(viewId);
        et.setHint(text);
        et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(type.equals("qct07")){
                    if(et.getText().toString().equals(".")){
                        PopUtil.showPopUtil("输入有误，请重新输入",true);
                        et.setText("");
                    }else {
                        //zyq 要开启
//                        MyService.iqcItemList.get(groupPosition).setQct07(et.getText().toString());
//                        if(!et.getText().toString().equals("")){
//
//                            if(Double.valueOf(et.getText().toString())>Double.valueOf( MyService.iqcItemList.get(groupPosition).getQct11())){
//                                PopUtil.showPopUtil("输入不良数量大于检验量，请重新输入",true);
//                                et.setText("");
//                                holder.setBackgroundColor(R.id.linearLayout_iqc,Color.WHITE);
//                            }else if(Double.valueOf(et.getText().toString())>0){
//                                holder.setBackgroundColor(R.id.linearLayout_iqc,Color.RED);
//                            }else {
//                                holder.setBackgroundColor(R.id.linearLayout_iqc,Color.WHITE);
//                            }
//                        }else {
//                            holder.setBackgroundColor(R.id.linearLayout_iqc,Color.WHITE);
//                        }

                    }

                }else if(type.equals("ta_qctt01")){
                    //zyq 要开启
                    //MyService.iqcItemList.get(groupPosition).setTa_qctt01(et.getText().toString());
                }
            }
        });
        return this;
    }

    //region  setRadioGroup废弃
/*    public BaseViewHolder setRadioGroup(int radioGroupId, final int groupPosition) {
        final RadioGroup radioGroup = get(radioGroupId);
        RadioButton radioButton1 = get(R.id.rbt_ok);
        RadioButton radioButton2 = get(R.id.rbt_ng);
        if(wmsService.iqcItemList.get(groupPosition).getQct08().equals("1")){
            radioButton1.setChecked(true);
        }else if(wmsService.iqcItemList.get(groupPosition).getQct08().equals("2")){
            radioButton2.setChecked(true);
        }
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int id = radioGroup.getCheckedRadioButtonId();
                switch (id){
                    case R.id.rbt_ok:
                        wmsService.iqcItemList.get(groupPosition).setQct08("1");
                        break;
                    case R.id.rbt_ng:
                        wmsService.iqcItemList.get(groupPosition).setQct08("2");
                        break;

                }
            }
        });

        return this;
    }*/
//endregion
}
