package com.jk.makemoney.component;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.jk.makemoney.R;

/**
 * @author chris.xue
 */
public class MkListItem {
    private TextView itemDetail;
    private TextView itemMoney;
    private TextView itemStatus;
    private View container;

    public MkListItem(Context context) {
        container = extract(context);
    }

    private View extract(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.comm_list_item, null, false);
        itemDetail = (TextView) view.findViewById(R.id.commItemDetail);
        itemMoney = (TextView) view.findViewById(R.id.commItemMoney);
        itemStatus = (TextView) view.findViewById(R.id.commItemStatus);
        return view;
    }

    public View getContainer() {
        return container;
    }

    public void setDetail(String detail) {
        itemDetail.setText(detail);
    }

    public void setMoney(String money) {
        itemMoney.setText(money);
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
