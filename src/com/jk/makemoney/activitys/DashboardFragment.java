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
import com.jk.makemoney.utils.*;

import net.youmi.android.offers.PointsChangeNotify;
import net.youmi.android.offers.PointsManager;


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
        PointsManager.getInstance(getActivity()).registerNotify(PointsChangeNotify());
        return view;
    }

    private PointsChangeNotify PointsChangeNotify() {
        
        return null;
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
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
                            TextViewUtils.setText(todayComm, NumberUtils.formatFloat((float) account.getTodayTaskCommission() / 100));
                            TextViewUtils.setText(todayReward, NumberUtils.formatFloat((float) account.getTodayFriendCommission() / 100));
                            TextViewUtils.setText(yesterdayComm, NumberUtils.formatFloat((float) account.getYestTaskCommission() / 100));
                            TextViewUtils.setText(yesterdayReward, NumberUtils.formatFloat((float) account.getYestFriendCommission() / 100));
                            TextViewUtils.setText(dashboardBalance, NumberUtils.formatFloat((float) account.getBalance() / 100));
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
                HelpFragment myFragment = new HelpFragment();
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
