package com.mirkowu.basetoolbarsample;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.mirkowu.basetoolbar.BaseToolbar;
import com.mirkowu.statusbarutil.StatusBarUtil;

/**
 * 亮色模式的Activity
 */
public class LightModeActivity extends BaseToolbarActivity implements View.OnClickListener {
    public static void start(Context context) {
        Intent starter = new Intent(context, LightModeActivity.class);
        //  starter.putExtra();
        context.startActivity(starter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_light_mode;
    }

    @Nullable
    @Override
    protected BaseToolbar.Builder setToolbar(@NonNull BaseToolbar.Builder builder) {
        return builder.setBackButton(R.mipmap.back)
                //.setStatusBarColor(Color.TRANSPARENT)
                .setBackgroundColor(Color.WHITE)
                .setTitleTextColor(Color.BLACK)
                .setBottomDivider(Color.GRAY,1)
                .setTitle("亮色模式");
    }

    @Override
    protected void initialize() {
       //BaseToolbar 的 setStatusBarColor()方法 和这个方法二选一即可，不然会多出一个状态栏高度
        StatusBarUtil.setStatusBarColor(this,Color.WHITE);//设置状态栏颜色
        StatusBarUtil.setStatusBarLightMode(this);//设置亮色模式 （即statusBar文字颜色变为黑色）

        findViewById(R.id.mBtnNone).setOnClickListener(this);
        findViewById(R.id.mBtnUseSystem).setOnClickListener(this);
        findViewById(R.id.mBtnUseColor).setOnClickListener(this);
        findViewById(R.id.mBtnUseColorWithAlpha).setOnClickListener(this);
    }

    public int getColorId(@ColorRes int color) {
        return ContextCompat.getColor(this, color);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.mBtnNone:
                StatusBarUtil.setStatusBarLightMode(this);
                break;
            case R.id.mBtnUseSystem:
                StatusBarUtil.setStatusBarLightModeWithNoSupport(this, true);
                break;
            case R.id.mBtnUseColor:
                StatusBarUtil.setStatusBarLightModeWithNoSupport(this, Color.CYAN);
                break;
            case R.id.mBtnUseColorWithAlpha:
                StatusBarUtil.setStatusBarLightModeWithNoSupport(this, Color.GRAY, 100);
                break;

        }
    }
}
