package com.jk.makemoney.activitys;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.jk.makemoney.R;

/**
 * @author chris.xue
 */
public class AliChargeActivity extends BasicActivity {
    private EditText usernameEdit;
    private EditText chargeAlipayEdit;
    private Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bindComponent();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(AliChargeActivity.this, "提交,账户=" + usernameEdit.getText().toString() + ",金额=" + chargeAlipayEdit.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void bindComponent() {
        usernameEdit = (EditText) findViewById(R.id.usernameEdit);
        chargeAlipayEdit = (EditText) findViewById(R.id.chargeAlipayEdit);
        submit = (Button) findViewById(R.id.chargeSubmitButton);
    }

    @Override
    protected void onNavArrowClick(View view) {
        finish();
    }

    @Override
    protected void initTitle(View title) {
        if (title instanceof TextView) {
            ((TextView) title).setText("支付宝提现");
        }
    }

    @Override
    protected void initBody(ViewGroup bodyContainer) {
        View view = LayoutInflater.from(this).inflate(R.layout.charge_alipay, bodyContainer, false);
        bodyContainer.addView(view);
    }

}
