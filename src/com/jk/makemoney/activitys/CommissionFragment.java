package com.jk.makemoney.activitys;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.jk.makemoney.R;
import com.jk.makemoney.beans.UserBilling;
import com.jk.makemoney.com.jk.makemoney.utils.DateTimeUtils;
import com.jk.makemoney.component.MkListView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chris.xue
 */
public class CommissionFragment extends BasicFragment {
    private MkListView mkListView;
    private TextView totalComm;
    private TextView totalPayment;
    private TextView totalBalance;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        bindComponent(view);
        initData();
        return view;
    }


    private void bindComponent(View view) {
        mkListView = (MkListView) view.findViewById(R.id.commDetailList);
        totalComm = (TextView) view.findViewById(R.id.totalCommissionText);
        totalPayment = (TextView) view.findViewById(R.id.totalPaymentText);
        totalBalance = (TextView) view.findViewById(R.id.totalBalanceText);
    }

    private void initData() {
        totalComm.setText("100");
        totalPayment.setText("10");
        totalBalance.setText("90");
        List<UserBilling> demoData = new ArrayList<UserBilling>(10);
        demoData.add(new UserBilling(DateTimeUtils.convert("2014-07-24"), "收入", 10, 0));
        demoData.add(new UserBilling(DateTimeUtils.convert("2014-07-25"), "收入", 20, 0));
        demoData.add(new UserBilling(DateTimeUtils.convert("2014-07-24"), "收入", 30, 0));
        demoData.add(new UserBilling(DateTimeUtils.convert("2014-07-25"), "收入", 20, 0));
        demoData.add(new UserBilling(DateTimeUtils.convert("2014-07-26"), "收入", 10, 0));
        demoData.add(new UserBilling(DateTimeUtils.convert("2014-07-26"), "收入", 10, 0));
        demoData.add(new UserBilling(DateTimeUtils.convert("2014-07-26"), "支付宝取现", 0, 5));
        demoData.add(new UserBilling(DateTimeUtils.convert("2014-07-26"), "支付宝取现", 0, 5));
        mkListView.append(demoData);
    }

    @Override
    protected void onNavArrowClick(View view) {
        DrawerLayout drawerLayout = (DrawerLayout) getActivity().findViewById(R.id.mainLayout);
        View menuLayout = getActivity().findViewById(R.id.mainMenu);
        if (drawerLayout != null) {
            if (drawerLayout.isDrawerOpen(menuLayout)) {
                drawerLayout.closeDrawer(menuLayout);
            } else {
                drawerLayout.openDrawer(menuLayout);
            }
        }
    }

    @Override
    protected void initTitle(View title) {
        if (title instanceof TextView) {
            ((TextView) title).setText("收支情况");
        }
    }

    @Override
    protected void initBody(View bodyContainer) {
        if (bodyContainer != null && bodyContainer instanceof ViewGroup) {
            ((ViewGroup) bodyContainer).addView(LayoutInflater.from(getActivity()).inflate(R.layout.commission, null, false));
        }
    }
}
