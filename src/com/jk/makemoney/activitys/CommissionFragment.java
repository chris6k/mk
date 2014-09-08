package com.jk.makemoney.activitys;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.jk.makemoney.R;
import com.jk.makemoney.beans.Account;
import com.jk.makemoney.beans.UserBilling;
import com.jk.makemoney.component.ListEventNotify;
import com.jk.makemoney.component.MkListView;
import com.jk.makemoney.services.AccountService;
import com.jk.makemoney.services.BillingService;
import com.jk.makemoney.utils.TextViewUtils;
import com.jk.makemoney.utils.ThreadPool;
import com.jk.makemoney.utils.ToastUtils;
import com.jk.makemoney.utils.UserProfile;

import java.util.List;

/**
 * @author chris.xue
 */
public class CommissionFragment extends BasicFragment {
    private MkListView mkListView;
    private TextView totalComm;
    private TextView totalPayment;
    private TextView totalBalance;
    private AccountService accountService;
    private BillingService billingService;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        accountService = new AccountService();
        billingService = new BillingService();
        bindComponent(view);
        initData();
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private void bindComponent(View view) {
        mkListView = (MkListView) view.findViewById(R.id.commDetailList);
        totalComm = (TextView) view.findViewById(R.id.totalCommissionText);
        totalPayment = (TextView) view.findViewById(R.id.totalPaymentText);
        totalBalance = (TextView) view.findViewById(R.id.totalBalanceText);
        mkListView.addUpdateNotify(new ListEventNotify() {
            @Override
            public void update(int lastCount, MkListView listView) {
                loadBillingData(lastCount, 10);
            }
        });
    }

    private void initData() {
        ThreadPool.getInstance().exec(new Runnable() {
            @Override
            public void run() {
                try {
                    final Account account = accountService.readAccountById(UserProfile.getInstance().getUserId());
                    if (account == null) {
                        ToastUtils.show("读取账户信息失败，请稍候再试");
                    } else {
                        getHandler().post(new Runnable() {
                            @Override
                            public void run() {
                                TextViewUtils.setText(totalComm, String.valueOf(account.getCommission()));
                                TextViewUtils.setText(totalPayment,
                                        String.valueOf(account.getCommission() - account.getBalance()));
                                TextViewUtils.setText(totalBalance, String.valueOf(account.getBalance()));
                            }
                        });
                    }
                } catch (Exception e) {
                    ToastUtils.show("获取账户信息失败，请稍候重试");
                }
            }
        });
        loadBillingData(1, 10);
        //初始化默认值
        totalComm.setText("0");
        totalPayment.setText("0");
        totalBalance.setText("0");
        mkListView.setAdapter(null);
    }

    private void loadBillingData(final int offset, final int count) {
        ThreadPool.getInstance().exec(new Runnable() {
            @Override
            public void run() {
                try {
                    List<UserBilling> billingList = billingService.getBilling(offset, count);
                    mkListView.append(billingList);
                } catch (Exception e) {
                    ToastUtils.show("获取账户信息失败，请稍候重试");
                }
            }
        });
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
