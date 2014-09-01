package com.jk.makemoney.activitys;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.jk.makemoney.R;

/**
 * @author chris.xue
 */
public class youmiActivity extends BasicActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //initComponent();
        //initData();
    }

    @Override
    protected void onNavArrowClick(View view) {
        finish();
    }

    @Override
    protected void initTitle(View title) {
        setTitleBar("");
    }

    @Override
    protected void initBody(ViewGroup bodyContainer) {
        View view = LayoutInflater.from(this).inflate(R.layout.youmi, null, false);
        bodyContainer.addView(view);
    }
}
