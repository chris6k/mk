package com.jk.makemoney.beans;

import android.text.TextUtils;
import com.jk.makemoney.component.IrrListItem;

import java.io.Serializable;

/**
 * @author chris.xue
 *         任务描述
 */
public class IrrItemDetail implements Serializable {
    private static final long serialVersionUID = -1518063146130256079L;
    //赚钱任务名
    private String name;
    //icon路径
    private String iconPath;
    //icon id
    private int iconId;
    //任务action
    private String action;

    public IrrItemDetail() {
    }

    public IrrItemDetail(String name, String iconPath, int iconId, String action) {
        this.name = name;
        this.iconPath = iconPath;
        this.iconId = iconId;
        this.action = action;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIconPath() {
        return iconPath;
    }

    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    @Override
    public String toString() {
        return "IrrItemDetail{" +
                "name='" + name + '\'' +
                ", iconPath='" + iconPath + '\'' +
                ", iconId=" + iconId +
                ", action='" + action + '\'' +
                '}';
    }

    public void fill(IrrListItem irrListItem) {
        irrListItem.setDetail(name);
        if (iconId > 0) {
            irrListItem.setItemIcon(iconId);
        } else if (!TextUtils.isEmpty(iconPath)) {
            irrListItem.setItemIcon(iconPath);
        }
    }
}
