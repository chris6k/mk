package com.jk.makemoney.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.jk.makemoney.R;

/**
 * @author chris.xue
 */
public class ChargeBoardActivity extends BasicActivity {
    private View chargeAlipayButton;
    private View chargePhoneButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bindComponent();
        chargeAlipayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), AliChargeActivity.class);
                startActivity(intent);
            }
        });
        chargePhoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), MobChargeActivity.class);
                startActivity(intent);
            }
        });
    }

    private void bindComponent() {
        chargeAlipayButton = findViewById(R.id.chargeAlipay);
        chargePhoneButton = findViewById(R.id.chargePhone);
    }

    @Override
    protected void onNavArrowClick(View view) {
        finish();
    }

    @Override
    protected void initTitle(View title) {
        if (title instanceof TextView) {
            ((TextView) title).setText("赏金提现");
        }
    }

    @Override
    protected void initBody(ViewGroup bodyContainer) {
        View view = LayoutInflater.from(this).inflate(R.layout.charge_list, bodyContainer, false);
        bodyContainer.addView(view);
    }
}
