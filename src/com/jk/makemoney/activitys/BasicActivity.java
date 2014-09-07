package com.jk.makemoney.activitys;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.jk.makemoney.R;


/**
 * @author chris.xue
 *         含nav bar的一个activity
 */
public abstract class BasicActivity extends Activity implements View.OnClickListener {
    private static final String TAG = "BasicActivity";
    private View arrowLeft;
    private View title;
    private ViewGroup bodyContainer;
    private Handler handler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.template);
        super.onCreate(savedInstanceState);
        arrowLeft = findViewById(R.id.navArrowLeft);
        title = findViewById(R.id.navText);
        bodyContainer = (ViewGroup) findViewById(R.id.body);
        arrowLeft.setOnClickListener(this);
        handler = new Handler(Looper.getMainLooper());
        initTitle(title);
        initBody(bodyContainer);

    }


    /**
     * 导航条左箭头点击事件。
     *
     * @param view
     */
    protected abstract void onNavArrowClick(View view);

    protected abstract void initTitle(View title);

    protected abstract void initBody(ViewGroup bodyContainer);

    /**
     * 添加标题
     *
     * @param text
     */
    protected void setTitleBar(String text) {
        if (title != null && title instanceof TextView) {
            ((TextView) title).setText(text);
        } else {
            Log.d(TAG, "'title' is not a  textview component!");
        }
    }

    @Override
    public void onClick(View view) {
        onNavArrowClick(view);
    }

    protected Handler getHandler() {
        return handler;
    }
}
