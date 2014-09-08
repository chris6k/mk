package com.jk.makemoney.activitys;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.jk.makemoney.R;
import com.jk.makemoney.beans.FriendsInfo;
import com.jk.makemoney.services.AccountService;
import com.jk.makemoney.services.FriendsService;
import com.jk.makemoney.utils.TextViewUtils;
import com.jk.makemoney.utils.ThreadPool;
import com.jk.makemoney.utils.ToastUtils;
import com.jk.makemoney.utils.UserProfile;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.controller.UMServiceFactory;
import com.umeng.socialize.controller.UMSocialService;
import com.umeng.socialize.media.UMImage;

import java.util.List;

/**
 * @author chris.xue
 */
public class InviteFriendActivity extends BasicActivity {
    private View inviteCount;
    private View totalPaymentText;
    private View userIdText;
    private View inviteFriendSubmit;
    private View copyLink;
    private View inviteCodeText;
    private View inviteChampion;
    private View inviteSecondPlace;
    private View inviteThirdPlace;
    private View rewardChampion;
    private View rewardSecondPlace;
    private View rewardThirdPlace;
    private UMSocialService mController;
    private FriendsService friendsService;
    private AccountService accountService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        friendsService = new FriendsService();
        accountService = new AccountService();
        initComponent();
        initData();
    }

    private void initData() {
        ThreadPool.getInstance().exec(new Runnable() {
            @Override
            public void run() {

                try {
                    final FriendsInfo friendsInfo = friendsService.readInfoById(UserProfile.getInstance().getUserId());
                    final List<FriendsInfo> rankingList = friendsService.readRankingList();
                    getHandler().post(new Runnable() {
                        @Override
                        public void run() {

                            TextViewUtils.setText(inviteCount, String.valueOf(friendsInfo.getFriendCount()));
                            TextViewUtils.setText(totalPaymentText, friendsInfo.getLastMonthCommissionYuan());
                            TextViewUtils.setText(userIdText, "用户ID:" + UserProfile.getInstance().getUserId());
                            TextViewUtils.setText(inviteCodeText, FriendsService.INVITE_ENDPOINT + "?" + UserProfile.getInstance().getUserId());
                            View[] rankTextView = new View[]{inviteChampion, inviteSecondPlace, inviteThirdPlace};
                            View[] rewardTextView = new View[]{rewardChampion, rewardSecondPlace, rewardThirdPlace};
                            if (rankingList != null) {
                                for (int i = 0; i < rankingList.size() && i < rankTextView.length; i++) {
                                    TextViewUtils.setText(rankTextView[i], String.valueOf(rankingList.get(i).getFriendCount()));
                                    TextViewUtils.setText(rewardTextView[i], rankingList.get(i).getLastMonthCommissionYuan());
                                }
                            }
                        }
                    });
                } catch (Exception e) {
                    Log.e("InviteFriendActivity", "get user info err", e);
                }
            }
        });
        TextViewUtils.setText(inviteCount, "0");
        TextViewUtils.setText(totalPaymentText, "0");
        TextViewUtils.setText(userIdText, "用户ID:" + UserProfile.getInstance().getUserId());
        TextViewUtils.setText(inviteCodeText, FriendsService.INVITE_ENDPOINT + "?" + UserProfile.getInstance().getUserId());

        TextViewUtils.setText(inviteChampion, "0");
        TextViewUtils.setText(inviteSecondPlace, "0");
        TextViewUtils.setText(inviteThirdPlace, "0");
        TextViewUtils.setText(rewardChampion, "0");
        TextViewUtils.setText(rewardSecondPlace, "0");
        TextViewUtils.setText(rewardThirdPlace, "0");
        //
        mController = UMServiceFactory.getUMSocialService("com.umeng.share");
        //
        mController.setShareContent("邀请好友");
        //
        mController.getConfig().setShareSms(true);
        mController.getConfig().setShareMail(true);
        mController.setShareMedia(new UMImage(InviteFriendActivity.this,
                "http://www.umeng.com/images/pic/banner_module_social.png"));
        mController.getConfig().setPlatforms(SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE,
                SHARE_MEDIA.QQ, SHARE_MEDIA.SINA, SHARE_MEDIA.TENCENT);
        mController.getConfig().setPlatformOrder(SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE,
                SHARE_MEDIA.QQ, SHARE_MEDIA.SINA, SHARE_MEDIA.TENCENT);
        inviteFriendSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mController.openShare(InviteFriendActivity.this, false);
            }

        });
        copyLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager cmb = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                cmb.setPrimaryClip(ClipData.newPlainText("inviteCode", ((TextView) inviteCodeText).getText().toString()));
                ToastUtils.show("已经复制到剪贴板");
            }
        });
    }

    private void initComponent() {
        inviteCount = findViewById(R.id.inviteCount);
        totalPaymentText = findViewById(R.id.totalPaymentText);
        userIdText = findViewById(R.id.userIdText);
        inviteFriendSubmit = findViewById(R.id.inviteFriendSubmit);
        copyLink = findViewById(R.id.copyLink);
        inviteCodeText = findViewById(R.id.inviteCodeText);
        inviteChampion = findViewById(R.id.inviteChampion);
        inviteSecondPlace = findViewById(R.id.inviteSecondPlace);
        inviteThirdPlace = findViewById(R.id.inviteThirdPlace);
        rewardChampion = findViewById(R.id.inviteRewardChampion);
        rewardSecondPlace = findViewById(R.id.inviteRewardSecondPlace);
        rewardThirdPlace = findViewById(R.id.inviteRewardThirdPlace);
    }

    @Override
    protected void onNavArrowClick(View view) {
        finish();
    }

    @Override
    protected void initTitle(View title) {
        setTitleBar("朋友奖励");
    }

    @Override
    protected void initBody(ViewGroup bodyContainer) {
        View view = LayoutInflater.from(this).inflate(R.layout.invitefriend, null, false);
        bodyContainer.addView(view);
    }
}
