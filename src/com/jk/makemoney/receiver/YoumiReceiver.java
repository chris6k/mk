package com.jk.makemoney.receiver;

import android.content.Context;
import android.util.Log;
import com.jk.makemoney.services.TaskService;
import com.jk.makemoney.utils.PlatformEnum;
import com.jk.makemoney.utils.ThreadPool;
import net.youmi.android.offers.EarnPointsOrderInfo;
import net.youmi.android.offers.EarnPointsOrderList;
import net.youmi.android.offers.PointsReceiver;

/**
 * @author kun
 */
public class YoumiReceiver extends PointsReceiver {
    private static TaskService taskService = new TaskService();

    @Override
    protected void onEarnPoints(Context context, final EarnPointsOrderList list) {
        ThreadPool.getInstance().exec(new Runnable() {
            @Override
            public void run() {
                EarnPointsOrderInfo info;
                boolean succ;
                for (int i = 0; i < list.size(); i++) {
                    info = list.get(i);
                    succ = taskService.finishTask(PlatformEnum.YOUMI.getCode(), info.getOrderID(), info.getPoints(), info.getMessage());
                    if (!succ) {
                        //TODO:日志记录，避免丢失数据。
                        Log.w("Task", "upload task info failed!, info => " + info);
                    }
                }
            }
        });
    }

    @Override
    protected void onViewPoints(Context context) {
        // 这里是个有趣的小功能，当用户赚取积分之后，积分墙 SDK 会在通知栏上显示一条获取积分的提示，如果用户点击了这个通知，该函数会被调用。
        // 这时候您可以在这里实现一个跳转，让用户跳转到您设计好的一个积分账户余额页面（如"我的账户"之类的 Activity）。
        // 该操作是可选的，如果需要关闭通知栏积分提示，请调用 PointsManager.getInstance(context).setEnableEarnPointsNotification(false)
    }
}