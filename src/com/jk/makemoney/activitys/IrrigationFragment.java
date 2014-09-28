package com.jk.makemoney.activitys;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import cn.waps.AppConnect;
import com.adsmogo.offers.MogoOffer;
import com.jk.makemoney.R;
import com.jk.makemoney.beans.IrrItemDetail;
import com.jk.makemoney.component.IrrListView;
import com.jk.makemoney.services.TaskService;
import com.jk.makemoney.utils.PlatformEnum;
import com.jk.makemoney.utils.TextViewUtils;
import com.jk.makemoney.utils.ToastUtils;
import net.youmi.android.offers.OffersManager;

import java.util.Arrays;
import java.util.List;

/**
 * @author chris.xue
 */
public class IrrigationFragment extends BasicFragment {
    private IrrListView irrListView;
    private TaskService taskService;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        taskService = new TaskService();
        bindComponent(view);
        initData();

        return view;
    }

    private void initData() {
        List<IrrItemDetail> irrItemDetails = Arrays.asList(new IrrItemDetail("小米", null, R.drawable.youmi, null),
                new IrrItemDetail("有米", null, R.drawable.youmi, null),
                new IrrItemDetail("万普", null, R.drawable.youmi, null),
                new IrrItemDetail("芒果", null, R.drawable.youmi, null));
        irrListView.append(irrItemDetails);
        irrListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) {
                    //TODO 测试代码
                    boolean succ = taskService.finishTask(PlatformEnum.YOUMI.getCode(),
                            String.valueOf(System.currentTimeMillis()), 100, "下载有米赢得积分");
                    if (succ) {
                        ToastUtils.show("获取到积分");
                    }
                }
                //有米
                if (i == 1) {
                    OffersManager offersManager = OffersManager.getInstance(getActivity());
                    offersManager.showOffersWall();
                }
                //万普
                if (i == 2) {
                    AppConnect.getInstance("8d157eb814348762051b123f0eacafa7", "", getActivity());
                    AppConnect.getInstance(getActivity()).showOffers(getActivity());
                }
                //芒果
                if (i == 3) {
                    MogoOffer.init(getActivity(), "e36a63b3067d4264865f86ffab74de75");
                    MogoOffer.showOffer(getActivity());
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
