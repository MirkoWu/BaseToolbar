package com.mirkowu.basetoolbarsample;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.mirkowu.basetoolbar.BaseToolbar;
import com.mirkowu.statusbarutil.StatusBarUtil;

/**
 * 代码创建BaseToolbar
 */
public class SampleActivity extends BaseToolbarActivity {

    public static void start(Context context) {
        Intent starter = new Intent(context, SampleActivity.class);
        // starter.putExtra();
        context.startActivity(starter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_sample;
    }

    /**
     * 这里的方法可根据需要自己定制 是返回Builder 还是直接返回BaseToolbar
     * 个人比较喜欢 Builder 链式调用比较方便简洁 如果配上lambda更好
     * <p>
     * 这里 返回 null 即变为正常的没有标题栏activity
     *
     * @param builder
     * @return
     */
    @Nullable
    @Override
    protected BaseToolbar.Builder setToolbar(@NonNull BaseToolbar.Builder builder) {
        //  return null;
        return builder
                //.setBackButton(R.mipmap.back)
                .setTitle("带标题的页面")
                .addRightImage(R.mipmap.more, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(SampleActivity.this, "是不是很方便", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    protected void initialize() {
        //也可用getToolbar()方法拿到BaseToolbar，进行相应操作
        StatusBarUtil.setImmersiveTransparentStatusBar(this);//设置沉浸式透明状态栏 配合使用
        getToolbar().setStatusBarTransparent();
//        getToolbar().hideStatusBar();
    }

}
