package com.jk.makemoney.activitys;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.jk.makemoney.R;
import com.jk.makemoney.com.jk.makemoney.utils.TextViewUtils;

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
        TextViewUtils.setText(userIdText, "用户ID:1");
        TextViewUtils.setText(inviteCodeText, "http://test.com");
        TextViewUtils.setText(inviteChampion, "100");
        TextViewUtils.setText(inviteSecondPlace, "100");
        TextViewUtils.setText(inviteThirdPlace, "100");
        TextViewUtils.setText(rewardChampion, "100");
        TextViewUtils.setText(rewardSecondPlace, "100");
        TextViewUtils.setText(rewardThirdPlace, "100");
        inviteFriendSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getBaseContext(), "提交", Toast.LENGTH_SHORT).show();
            }
        });
        copyLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager cmb = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                cmb.setPrimaryClip(ClipData.newPlainText("inviteCode", ((TextView) inviteCodeText).getText().toString()));
                Toast.makeText(getBaseContext(), "已复制到剪贴板", Toast.LENGTH_SHORT).show();
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
