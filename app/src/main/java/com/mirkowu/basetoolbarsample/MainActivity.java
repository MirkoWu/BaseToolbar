package com.mirkowu.basetoolbarsample;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.mirkowu.basetoolbar.BaseToolbar;
import com.mirkowu.statusbarutil.StatusBarUtil;

/**
 * XML 中使用BaseToolBar
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private BaseToolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StatusBarUtil.setImmersiveTransparentStatusBar(this);//沉浸式透明状态栏

        mToolbar = findViewById(R.id.mToolbar);

        //标题会根据左右中宽度最大那个为准 设置margin，保存标题居中。
        // 如果超过屏幕一半，则无法显示标题，注意使用。
        mToolbar.setTitle("我是标题");
        mToolbar.setTitleTextColor(Color.BLACK);
        mToolbar.setBottomDivider(getColorId(R.color.colorAccent), 5);//此处高度单位为px
        mToolbar.setTitleMode(BaseToolbar.CENTER);
        //统一设置副文本的颜色 添加文本时不设置是默认都是这个颜色，需定制时，可调用带文本的颜色的方法
        mToolbar.setSubTextColor(Color.GRAY);

        mToolbar.setBackgroundColor(getColorId(R.color.colorPrimary));//设置背景颜色

        // mToolbar.setStatusBarTransparent();//设置透明的状态栏
        mToolbar.setStatusBarColor(getColorId(R.color.colorAccent));//设置透明的状态栏

        findViewById(R.id.mBtnSwitchTitleMode).setOnClickListener(this);
        findViewById(R.id.mBtnAddLeftImage).setOnClickListener(this);
        findViewById(R.id.mBtnAddLeftText).setOnClickListener(this);
        findViewById(R.id.mBtnAddRightImage).setOnClickListener(this);
        findViewById(R.id.mBtnAddRightText).setOnClickListener(this);
        findViewById(R.id.mBtnAddRightView).setOnClickListener(this);
        findViewById(R.id.mBtnShowHideDivider).setOnClickListener(this);

        findViewById(R.id.mBtnShowHideStatusBar).setOnClickListener(this);
        findViewById(R.id.mBtnWithBase).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.mBtnAddLeftImage://设置返回按钮，如有需要可在onBackPressed()处拦截
                if (v.isSelected()) {
                    mToolbar.setBackButton(R.mipmap.back_white);
                } else {
                    mToolbar.setBackButton(R.mipmap.back_white, "返回", Color.WHITE,16);
                }
                v.setSelected(!v.isSelected());
                break;
            case R.id.mBtnSwitchTitleMode://
                mToolbar.setTitleMode(1 - mToolbar.getTitleMode());
                break;
            case R.id.mBtnAddLeftText:
                mToolbar.addLeftText("左菜单", Color.WHITE,16,
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(MainActivity.this, "左菜单", Toast.LENGTH_SHORT).show();
                            }
                        });
                break;
            case R.id.mBtnAddRightImage://布局没有对左右添加的布局宽度做限制，可以根据自己需求定制。
                mToolbar.addRightImage(R.mipmap.more, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                break;
            case R.id.mBtnAddRightText:
                mToolbar.addRightText("右菜单", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                break;
            case R.id.mBtnAddRightView://如果文本和图片不能满足 可以调用addLeftView(),addRightView(),addCenterView() 添加布局
                TextView view = new TextView(this);
                view.setText("自由布局");
                mToolbar.addRightView(view);
                break;
            case R.id.mBtnShowHideDivider://显示隐藏分割线
                boolean showDivider = mToolbar.getBottomDivider().getVisibility() == View.VISIBLE;
                if (showDivider) {
                    mToolbar.hideBottomDivider();
                } else {
                    mToolbar.setBottomDivider(getColorId(R.color.colorAccent), 5);
                }
                break;
            case R.id.mBtnShowHideStatusBar://显示隐藏statusBar 配合StatusBarUtil使用效果更佳
                boolean showStatusBar = mToolbar.getStatusBar().getVisibility() == View.VISIBLE;
                if (showStatusBar) {
                    mToolbar.hideStatusBar();
                } else {
                    StatusBarUtil.setImmersiveTransparentStatusBar(this);
                    //mToolbar.setStatusBarTransparent();
                    mToolbar.setStatusBarColor(getColorId(R.color.colorAccent));
                }
                break;
            case R.id.mBtnWithBase:
                SampleActivity.start(this);
                break;
        }

    }

    public int getColorId(@ColorRes int color) {
        return ContextCompat.getColor(this, color);
    }


}
