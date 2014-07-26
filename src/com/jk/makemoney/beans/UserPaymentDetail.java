package com.jk.makemoney.beans;

import com.jk.makemoney.component.MkListItem;

import java.util.Date;

/**
 * @author chris.xue
 *         支出
 */
public class UserPaymentDetail extends UserBaseDetail {
    private static final long serialVersionUID = -7828623247542316548L;
    private boolean finish;

    public UserPaymentDetail(Date dateDay, String detail, int amount, boolean finish) {
        super(dateDay, detail, amount);
        this.finish = finish;
    }

    public boolean isFinish() {
        return finish;
    }

    public void setFinish(boolean finish) {
        this.finish = finish;
    }

    @Override
    public void fillItem(MkListItem item) {
        item.setDetail(getDetail());
        item.setMoney(String.valueOf(-getAmount()));
        if (finish) {
            item.setStatus("已完成");
        }
    }
}
