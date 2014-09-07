package com.jk.makemoney.activitys;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import com.jk.makemoney.R;
import com.jk.makemoney.beans.IrrItemDetail;
import com.jk.makemoney.com.jk.makemoney.utils.TextViewUtils;
import com.jk.makemoney.component.IrrListView;
import net.youmi.android.AdManager;
import net.youmi.android.offers.OffersManager;

import java.util.Arrays;
import java.util.List;

/**
 * @author chris.xue
 */
public class IrrigationFragment extends BasicFragment {
    private IrrListView irrListView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        bindComponent(view);
        initData();

        return view;
    }

    private void initData() {
        List<IrrItemDetail> irrItemDetails = Arrays.asList(new IrrItemDetail("小米", null, R.drawable.youmi, null),
                new IrrItemDetail("有米", null, R.drawable.youmi, null));
        irrListView.append(irrItemDetails);
        irrListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 1) {
                    AdManager adManager = AdManager.getInstance(getActivity());
                    adManager.init("149db85904b9d72c", "05e750d4dddcddab", true);
                    OffersManager offersManager = OffersManager.getInstance(getActivity());
                    offersManager.onAppLaunch();
                    offersManager.showOffersWall();
                }
            }
        });
    }

    private void bindComponent(View view) {
        irrListView = (IrrListView) view.findViewById(R.id.irrigationList);
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
        TextViewUtils.setText(title, "做任务赚现金");
    }

    @Override
    protected void initBody(View bodyContainer) {
        if (bodyContainer != null && bodyContainer instanceof ViewGroup) {
            ((ViewGroup) bodyContainer).addView(LayoutInflater.from(getActivity()).inflate(R.layout.irrigation, null, false));
        }
    }
}
