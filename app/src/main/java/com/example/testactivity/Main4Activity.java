package com.example.testactivity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class Main4Activity extends AppCompatActivity {
    private RadioGroup daohang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        ViewPager viewPager=(ViewPager) findViewById(R.id.mainViewPager);
        PagerAdapter pageAdapter = new PagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pageAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);

        daohang=(RadioGroup) findViewById(R.id.radiogroup);//获取单选按钮组
        //为单选按钮组添加事件监听
        daohang.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton RB=(RadioButton) findViewById(i);//获取被选择的单选按钮
                if(RB.getText().equals("首页")){
                    Intent intent = new Intent(Main4Activity.this,Main4Activity.class);
                    Main4Activity.this.startActivity(intent);
                }
                else if(RB.getText().equals("横图")){
                    Intent intent = new Intent(Main4Activity.this,HengtuActivity.class);
                    startActivity(intent);
                }

            }
        });

    }
}
