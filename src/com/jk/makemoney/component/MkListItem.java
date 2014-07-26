package com.jk.makemoney.component;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.jk.makemoney.R;

/**
 * @author chris.xue
 */
public class MkListItem extends ViewGroup {
    private TextView itemDetail;
    private TextView itemMoney;
    private TextView itemStatus;

    public MkListItem(Context context) {
        super(context);
        init(context, null, -1);
    }

    public MkListItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, -1);
    }

    public MkListItem(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs, defStyle);
    }

    @Override
    protected void onLayout(boolean b, int i, int i2, int i3, int i4) {

    }

    private void init(Context context, AttributeSet attrs, int defStyle) {
        View view = LayoutInflater.from(context).inflate(R.layout.comm_list_item, this, false);
        itemDetail = (TextView) view.findViewById(R.id.commItemDetail);
        itemMoney = (TextView) view.findViewById(R.id.commItemMoney);
        itemStatus = (TextView) view.findViewById(R.id.commItemStatus);
        addView(view);
    }

    public void setDetail(String detail) {
        itemDetail.setText(detail);
    }

    public void setMoney(String money) {
        itemDetail.setText(money);
    }

    public void setStatus(String status) {
        itemStatus.setText(status);
    }

    public TextView getItemDetail() {
        return itemDetail;
    }

    public TextView getItemMoney() {
        return itemMoney;
    }

    public TextView getItemStatus() {
        return itemStatus;
    }
}
