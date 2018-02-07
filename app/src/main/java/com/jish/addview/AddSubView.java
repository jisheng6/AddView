package com.jish.addview;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * date:2017/8/8
 * author:易宸锋(dell)
 * function:组合式自定义控件,自定义删除,增加按钮
 * 1.类继承ViewGroup下的任意自定义控件
 * 2.覆写他的构造方法
 */

public class AddSubView extends LinearLayout implements View.OnClickListener {

    private ImageView mIv_sub;
    private ImageView mIv_add;
    private TextView mTv_value;
    private int mValue;

    //1.创建对象的时候
    public AddSubView(Context context) {
        this(context, null);
    }

    //2.XML中使用的时候回调
    public AddSubView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    //3.在XML中使用,且使用Style风格中.
    public AddSubView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        //把一个布局文件实例化,并且加载到AddSuVIew类中
        View inflate = View.inflate(context, R.layout.add_sub_view, this);
        //初始化控件
        mIv_sub = (ImageView) inflate.findViewById(R.id.iv_sub);
        mIv_add = (ImageView) inflate.findViewById(R.id.iv_add);
        mTv_value = (TextView) inflate.findViewById(R.id.tv_value);

        mIv_add.setOnClickListener(this);
        mIv_sub.setOnClickListener(this);

        //获取Value值
        int value = getValue();
        //设置valus的值
        setValue(value);

    }

    //当前数量值,默认为1,设置对此值获取,设置的方法
    private int value = 1;
    //最大商品数量值
    private int mMaxValue = 5;
    //最小商品数量值
    private int mMinValue = 1;

    //暴露出去设置最大的商品数量
    public void setMaxValue(int maxValue) {
        mMaxValue = maxValue;
    }

    /**
     * 这里获取Value是从UI那里拿到值
     * @return
     */
    public int getValue() {
        //从TextView里拿出其内容,去掉左右两边的空格
        String trim = mTv_value.getText().toString().trim();
        if (!TextUtils.isEmpty(trim)) {
            //获取出来,因为其值是字符串,所以要进行Int型转换
            value = Integer.valueOf(trim);
        }
        return value;
    }

    /**
     * 使设置Value值时,直接对UI进行更新
     * @param value
     */
    public void setValue(int value) {
        mValue = value;
        mTv_value.setText(value + "");
    }

    //根据点击事件对TextView做加减的操作
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            //添加
            case R.id.iv_add:
                addNumber();
                break;
            //减少
            case R.id.iv_sub:
                subNumber();
                break;
            default:
                break;
        }
    }

    private void addNumber() {
        if (value < mMaxValue) {
            value++;
        }
        setValue(value);

        //B.当按钮使Value值发送变化时,回调该接口方法.
        if (mOnNumberChangerListener != null) {
            mOnNumberChangerListener.OnNumberChanger(value);
        }
    }

    private void subNumber() {
        if (value > mMinValue) {
            value--;
        }
        setValue(value);
        //B.当按钮使Value值发送变化时,回调该接口方法.
        if (mOnNumberChangerListener != null) {
            mOnNumberChangerListener.OnNumberChanger(value);
        }
    }

    /**
     * B.定义接口,及所要调用的接口方法,当商品数量发生变化是,回调给接口
     */
    public interface OnNumberChangerListener {
        void OnNumberChanger(int value);
    }

    //B.定义接口对象
    private OnNumberChangerListener mOnNumberChangerListener;

    //B.设置方法接收外界传过来的接口对象方法.
    public void setOnNumberChangerListener(OnNumberChangerListener onNumberChangerListener) {
        mOnNumberChangerListener = onNumberChangerListener;
    }

}
