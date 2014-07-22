package com.jk.makemoney.beans;

/**
 * @author chris.xue
 *         支出
 */
public class UserPaymentDetail extends UserBaseDetail {
    private static final long serialVersionUID = -7828623247542316548L;
    private boolean finish;

    public boolean isFinish() {
        return finish;
    }

    public void setFinish(boolean finish) {
        this.finish = finish;
    }
}
