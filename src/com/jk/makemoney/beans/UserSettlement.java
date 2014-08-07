package com.jk.makemoney.beans;

import java.io.Serializable;
import java.util.Date;

/**
 * @author chris.xue
 *         用户结算信息
 */
public class UserSettlement implements Serializable {
    private static final long serialVersionUID = 4289583412130317374L;
    //ID
    private int id;
    //支付的金额
    private int payment;
    //创建日期
    private Date dateDay;
    //支付账户ID
    private String accountId;
    //支付流水号
    private String flowId;
    //支付类型
    private int paymentType;
    //支付时间
    private Date paymentTime;
    //支付状态
    private int state;

    public int getPayment() {
        return payment;
    }

    public void setPayment(int payment) {
        this.payment = payment;
    }

    public Date getDateDay() {
        return dateDay;
    }

    public void setDateDay(Date dateDay) {
        this.dateDay = dateDay;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getFlowId() {
        return flowId;
    }

    public void setFlowId(String flowId) {
        this.flowId = flowId;
    }

    public int getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(int paymentType) {
        this.paymentType = paymentType;
    }

    public Date getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(Date paymentTime) {
        this.paymentTime = paymentTime;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "UserSettlement{" +
                "id=" + id +
                ", payment=" + payment +
                ", dateDay=" + dateDay +
                ", accountId='" + accountId + '\'' +
                ", flowId='" + flowId + '\'' +
                ", paymentType=" + paymentType +
                ", paymentTime=" + paymentTime +
                ", state=" + state +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserSettlement that = (UserSettlement) o;

        if (id != that.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
