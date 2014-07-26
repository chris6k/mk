package com.jk.makemoney.beans;

import android.view.View;
import com.jk.makemoney.component.MkListItem;

import java.util.Date;

/**
 * @author chris.xue
 *         收入
 */
public class UserCommDetail extends UserBaseDetail {
    private static final long serialVersionUID = 7414335109283313922L;

    public UserCommDetail() {
    }

    public UserCommDetail(Date dateDay, String detail, int amount) {
        super(dateDay, detail, amount);
    }

    @Override
    public void fillItem(MkListItem item) {
        item.setDetail(getDetail());
        item.setMoney(String.valueOf(getAmount()));
//        item.getItemStatus().setVisibility(View.GONE);
    }
}
