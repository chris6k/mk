package com.jk.makemoney.beans;

import com.jk.makemoney.component.MkListItem;

import java.io.Serializable;
import java.util.*;

/**
 * @author chris.xue
 *         收支基类
 */
public abstract class UserBaseDetail implements Serializable {
    private static final long serialVersionUID = 1688594552971114764L;
    //日期
    private Date dateDay;
    //内容
    private String detail;
    //金额
    private int amount;

    protected UserBaseDetail() {
    }

    protected UserBaseDetail(Date dateDay, String detail, int amount) {
        this.dateDay = dateDay;
        this.detail = detail;
        this.amount = amount;
    }

    public Date getDateDay() {
        return dateDay;
    }

    public void setDateDay(Date dateDay) {
        this.dateDay = dateDay;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    /**
     * 将原始数据根据日期聚合,按照时间倒序排列
     *
     * @param data
     * @return
     */
    public static Map<Date, List<UserBaseDetail>> mix(List<UserBaseDetail> data) {
        if (data == null) return new TreeMap<Date, List<UserBaseDetail>>(new Comparator<Date>() {
            @Override
            public int compare(Date date, Date date2) {
                return date.compareTo(date2) * -1;
            }
        });
        Map<Date, List<UserBaseDetail>> result = new HashMap<Date, List<UserBaseDetail>>();
        for (UserBaseDetail de : data) {
            List<UserBaseDetail> detailList = result.get(de.getDateDay());
            if (detailList == null) {
                detailList = new ArrayList<UserBaseDetail>(10);
                result.put(de.getDateDay(), detailList);
            }
            detailList.add(de);
        }
        return result;
    }

    /**
     * 初始化桌面组件
     *
     * @param v
     */
    public abstract void fillItem(MkListItem item);
}
