package com.jk.makemoney.activitys;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.jk.makemoney.R;

/**
 * @author chris.xue
 *         含nav bar的一个activity
 */
public abstract class BasicFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = "BasicActivity";
    private View arrowLeft;
    private View title;
    private View bodyContainer;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View template = inflater.inflate(R.layout.template, null, false);
        arrowLeft = template.findViewById(R.id.navArrowLeft);
        title = template.findViewById(R.id.navText);
        bodyContainer = template.findViewById(R.id.body);
        arrowLeft.setOnClickListener(this);
        initTitle(title);
        initBody(bodyContainer);
        return template;
    }

    /**
     * 导航条左箭头点击事件。
     *
     * @param view
     */
    protected abstract void onNavArrowClick(View view);

    protected abstract void initTitle(View title);

    protected abstract void initBody(View bodyContainer);

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
}
