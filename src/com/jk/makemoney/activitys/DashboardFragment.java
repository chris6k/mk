package com.jk.makemoney.activitys;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.jk.makemoney.R;
import com.jk.makemoney.beans.Account;
import com.jk.makemoney.services.AccountService;
import com.jk.makemoney.utils.TextViewUtils;
import com.jk.makemoney.utils.ThreadPool;
import com.jk.makemoney.utils.ToastUtils;
import com.jk.makemoney.utils.UserProfile;

/**
 * @author
 */
public class DashboardFragment extends BasicFragment {

    private View downloadApp;
    private View inviteFriends;
    private View todayComm;
    private View yesterdayComm;
    private View todayReward;
    private View yesterdayReward;
    private View exchangeArr;
    private View dashboardBalance;
    private AccountService accountService;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        accountService = new AccountService();
        bindComponent(view);
        initData();
        return view;
    }

    private void initData() {
        ThreadPool.getInstance().exec(new Runnable() {
            @Override
            public void run() {
                try {
                    final Account account = accountService.readAccountById(UserProfile.getInstance().getUserId());
                    if (account == null) return;
                    getHandler().post(new Runnable() {
                        @Override
                        public void run() {
                            TextViewUtils.setText(todayComm, String.valueOf(account.getTodayTaskCommission()));
                            TextViewUtils.setText(todayReward, String.valueOf(account.getTodayFriendCommission()));
                            TextViewUtils.setText(yesterdayComm, String.valueOf(account.getYestTaskCommission()));
                            TextViewUtils.setText(yesterdayReward, String.valueOf(account.getYestFriendCommission()));
                            TextViewUtils.setText(dashboardBalance, String.valueOf(account.getBalance()));
                        }
                    });
                } catch (Exception e) {
                    ToastUtils.show("获取账户信息失败，请稍候再试");
                }
            }
        });
        TextViewUtils.setText(todayComm, "0");
        TextViewUtils.setText(todayReward, "0");
        TextViewUtils.setText(yesterdayComm, "0");
        TextViewUtils.setText(yesterdayReward, "0");
        TextViewUtils.setText(dashboardBalance, "0");
    }

    private void bindComponent(View view) {
        downloadApp = view.findViewById(R.id.downloadApp);
        inviteFriends = view.findViewById(R.id.inviteFriends);
        todayComm = view.findViewById(R.id.todayCommission);
        todayReward = view.findViewById(R.id.todayReward);
        yesterdayComm = view.findViewById(R.id.yesterdayCommission);
        yesterdayReward = view.findViewById(R.id.yesterdayReward);
        exchangeArr = view.findViewById(R.id.exchangeArrow);
        dashboardBalance = view.findViewById(R.id.dashboardBalance);
        exchangeArr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ChargeBoardActivity.class);
                startActivity(intent);
            }
        });
        downloadApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IrrigationFragment myFragment = new IrrigationFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.mainBody, myFragment).commit();


            }
        });

        inviteFriends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), InviteFriendActivity.class);
                startActivity(intent);
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
        // do nothing;
    }

    @Override
    protected void initBody(View bodyContainer) {
        if (bodyContainer != null && bodyContainer instanceof ViewGroup) {
            ((ViewGroup) bodyContainer).addView(LayoutInflater.from(getActivity()).inflate(R.layout.dashboard, null, false));
        }
    }
}
