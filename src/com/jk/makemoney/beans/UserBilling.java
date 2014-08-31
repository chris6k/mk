package com.jk.makemoney.beans;

import com.jk.makemoney.com.jk.makemoney.utils.NumberUtils;
import com.jk.makemoney.component.MkListItem;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.*;

/**
 * @author chris.xue
 *         用户记账
 */
public class UserBilling implements Serializable {
    private static final long serialVersionUID = 1688594552971114764L;
    //日期
    private Date dateDay;
    //内容
    private String description;
    //入账
    private int debits;
    //出帐
    private int credits;


    public UserBilling() {
    }

    public UserBilling(Date dateDay, String description, int debits, int credits) {
        this.dateDay = dateDay;
        this.description = description;
        this.debits = debits;
        this.credits = credits;
    }

    public UserBilling(JSONObject jsonObject) throws JSONException {
        if (jsonObject.has("dateDay")) {
            this.dateDay = (Date) jsonObject.get("dateDay");
        }
        if (jsonObject.has("description")) {
            this.description = jsonObject.getString("description");
        }
        if (jsonObject.has("debits")) {
            this.debits = jsonObject.getInt("debits");
        }
        if (jsonObject.has("credits")) {
            this.credits = jsonObject.getInt("credits");
        }
    }

    public Date getDateDay() {
        return dateDay;
    }

    public void setDateDay(Date dateDay) {
        this.dateDay = dateDay;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDebits() {
        return debits;
    }

    public void setDebits(int debits) {
        this.debits = debits;
    }

    /**
     * 将原始数据根据日期聚合,按照时间倒序排列
     *
     * @param data
     * @return
     */
    public static Map<Date, List<UserBilling>> mix(List<UserBilling> data) {
        if (data == null) return new TreeMap<Date, List<UserBilling>>(new Comparator<Date>() {
            @Override
            public int compare(Date date, Date date2) {
                return date.compareTo(date2) * -1;
            }
        });
        Map<Date, List<UserBilling>> result = new HashMap<Date, List<UserBilling>>();
        for (UserBilling de : data) {
            List<UserBilling> userBillings = result.get(de.getDateDay());
            if (userBillings == null) {
                userBillings = new ArrayList<UserBilling>(10);
                result.put(de.getDateDay(), userBillings);
            }
            userBillings.add(de);
        }
        return result;
    }

    /**
     * 初始化桌面组件
     *
     * @param item
     */
    public void fillItem(MkListItem item) {
        item.setDetail(getDescription());
        if (credits == 0) {
            item.setMoney(String.valueOf(NumberUtils.formatFloat((float) debits / 100)));
        } else {
            item.setMoney(String.valueOf(NumberUtils.formatFloat((float) credits / 100)));
            item.setStatus("已完成");
        }
    }
}
