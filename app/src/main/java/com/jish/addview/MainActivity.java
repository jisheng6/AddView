package com.jish.addview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private AddSubView av;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        av = (AddSubView) findViewById(R.id.av);
        //设置商品最大数
        av.setMaxValue(20);

        //设置商品加减点击按钮的回调执行的逻辑
        av.setOnNumberChangerListener(new AddSubView.OnNumberChangerListener() {
            @Override
            public void OnNumberChanger(int value) {
                //属于我自己的业务逻辑
                Toast.makeText(MainActivity.this, "变化的数量值"+value, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
