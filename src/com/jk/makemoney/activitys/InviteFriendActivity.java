package com.jk.makemoney.activitys;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.jk.makemoney.R;
import com.jk.makemoney.com.jk.makemoney.utils.TextViewUtils;
import com.umeng.socialize.controller.UMSocialService;
import com.umeng.socialize.controller.UMServiceFactory;   
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.sso.QZoneSsoHandler;  
import com.umeng.socialize.sso.SinaSsoHandler;  
import com.umeng.socialize.sso.TencentWBSsoHandler;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initComponent();
        initData();
    }

    private void initData() {
        TextViewUtils.setText(inviteCount, "100");
        TextViewUtils.setText(totalPaymentText, "1000.0");
        TextViewUtils.setText(userIdText, "鐢ㄦ埛ID:1");
        TextViewUtils.setText(inviteCodeText, "http://test.com");
        TextViewUtils.setText(inviteChampion, "100");
        TextViewUtils.setText(inviteSecondPlace, "100");
        TextViewUtils.setText(inviteThirdPlace, "100");
        TextViewUtils.setText(rewardChampion, "100");
        TextViewUtils.setText(rewardSecondPlace, "100");
        TextViewUtils.setText(rewardThirdPlace, "100");
        // 棣栧厛鍦ㄦ偍鐨凙ctivity涓坊鍔犲涓嬫垚鍛樺彉閲�
        final UMSocialService mController = UMServiceFactory.getUMSocialService("com.umeng.share");
        // 璁剧疆鍒嗕韩鍐呭
        mController.setShareContent("鎴戠敤璧氶挶杈句汉缁欎綘鍙戠孩鍖呭暒锛屽彲浠ュ厬鎹㈡垚鐜伴噾锛岃刀蹇潵棰嗗惂锛�");
        // 璁剧疆鍒嗕韩鍥剧墖, 鍙傛暟2涓哄浘鐗囩殑url鍦板潃
        mController.setShareMedia(new UMImage(getActivity(), 
                                              "http://www.umeng.com/images/pic/banner_module_social.png"));
        inviteFriendSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getBaseContext(), "鎻愪氦", Toast.LENGTH_SHORT).show();
                mController.openShare(getActivity(), false);
            }

        });
        copyLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager cmb = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                cmb.setPrimaryClip(ClipData.newPlainText("inviteCode", ((TextView) inviteCodeText).getText().toString()));
                Toast.makeText(getBaseContext(), "宸插鍒跺埌鍓创鏉�", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private Activity getActivity() {
		// TODO Auto-generated method stub
		return null;
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
        setTitleBar("鏈嬪弸濂栧姳");
    }

    @Override
    protected void initBody(ViewGroup bodyContainer) {
        View view = LayoutInflater.from(this).inflate(R.layout.invitefriend, null, false);
        bodyContainer.addView(view);
    }
}
