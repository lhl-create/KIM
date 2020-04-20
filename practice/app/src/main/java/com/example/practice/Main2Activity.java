package com.example.practice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class Main2Activity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    private RadioGroup mRadioGroup;
    private RadioButton homeRb, newsRb, mapRb, userRb;
    private FragmentHome fragmentHome;
    private FragmentMap fragmentMap;
    private FragmentNews fragmentNews;
    private FragmentUser fragmentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initView();
        Button button=findViewById(R.id.start_Button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Main2Activity.this,Main3Activity.class);
                startActivity(intent);
            }
        });
    }

    private void initView() {
        mRadioGroup = findViewById(R.id.radioGroup);
        mRadioGroup.setOnCheckedChangeListener(this);

        homeRb = findViewById(R.id.radio_home);
        newsRb = findViewById(R.id.radio_news);
        mapRb = findViewById(R.id.radio_map);
        userRb = findViewById(R.id.radio_user);

        homeRb.setChecked(true);

        /**图片的优化，其他三个图片做类似处理
         * 底部导航的时候会发生图片的颜色变化，所以radiobutton中的照片不是一张，而是引用了自定义的选择器照片
         * 本来使用的是getResources.getDrawable,不过已经过时，所以使用ContextCompat
         */
        Drawable homeDrawable = ContextCompat.getDrawable(this, R.drawable.selector_home_drawable);
        /**
         *  当这个图片被绘制时，给他绑定一个矩形规定这个矩形
         *  参数前两个对应图片相对于左上角的新位置，后两个为图片的长宽
         */
        homeDrawable.setBounds(0, 0, 80, 80);
        /**
         *   设置图片在文字的哪个方向,分别对应左，上，右，下
         */

        homeRb.setCompoundDrawables(null, homeDrawable, null, null);

        Drawable newsDrawable = ContextCompat.getDrawable(this, R.drawable.selector_news_drawable);
        newsDrawable.setBounds(0, 0, 80, 80);
        newsRb.setCompoundDrawables(null, newsDrawable, null, null);

        Drawable mapDrawable = ContextCompat.getDrawable(this, R.drawable.selector_map_drawable);
        mapDrawable.setBounds(0, 0, 80, 80);
        mapRb.setCompoundDrawables(null, mapDrawable, null, null);

        Drawable userDrawable = ContextCompat.getDrawable(this, R.drawable.selector_user_drawable);
        userDrawable.setBounds(0, 0, 80, 80);
        userRb.setCompoundDrawables(null, userDrawable, null, null);

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        hideAllFragment(transaction);

        switch (checkedId) {
            case R.id.radio_home:
                if (fragmentHome == null) {
                    fragmentHome = new FragmentHome();
                    transaction.add(R.id.fragment_container, fragmentHome);
                } else {
                    transaction.show(fragmentHome);
                }
                break;
            case R.id.radio_news:
                if (fragmentNews == null) {
                    fragmentNews = new FragmentNews();
                    transaction.add(R.id.fragment_container, fragmentNews);
                } else {
                    transaction.show(fragmentNews);
                }
                break;
            case R.id.radio_map:
                if (fragmentMap == null) {
                    fragmentMap = new FragmentMap();
                    transaction.add(R.id.fragment_container, fragmentMap);
                } else {
                    transaction.show(fragmentMap);
                }
                break;
            case R.id.radio_user:
                if (fragmentUser == null) {
                    fragmentUser = new FragmentUser();
                    transaction.add(R.id.fragment_container, fragmentUser);
                } else {
                    transaction.show(fragmentUser);
                }
                break;
        }
        transaction.commit();
    }

    public void hideAllFragment(FragmentTransaction transaction){
        if(fragmentHome !=null){
            transaction.hide(fragmentHome);
        }
        if(fragmentNews !=null){
            transaction.hide(fragmentNews);
        }
        if(fragmentMap !=null){
            transaction.hide(fragmentMap);
        }
        if(fragmentUser !=null){
            transaction.hide(fragmentUser);
        }
    }
}

