package com.jk.makemoney;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.jk.makemoney.com.jk.makemoney.utils.TextViewUtils;

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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        bindComponent(view);
        initData();
        return view;
    }

    private void initData() {
        TextViewUtils.setText(todayComm, "12.8");
        TextViewUtils.setText(todayReward, "12.8");
        TextViewUtils.setText(yesterdayComm, "12.8");
        TextViewUtils.setText(yesterdayReward, "12.8");
        TextViewUtils.setText(dashboardBalance, "120");
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
                Toast.makeText(getActivity(), "I'm exchangeArr", Toast.LENGTH_SHORT).show();
            }
        });
        downloadApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "I'm downloadApp", Toast.LENGTH_SHORT).show();
            }
        });
        inviteFriends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "I'm inviteFriends", Toast.LENGTH_SHORT).show();

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
