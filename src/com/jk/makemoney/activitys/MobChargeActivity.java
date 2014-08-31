package com.jk.makemoney.activitys;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.jk.makemoney.R;
import com.jk.makemoney.services.PaymentService;

/**
 * @author chris.xue
 */
public class MobChargeActivity extends BasicActivity {

    private EditText chargePhoneEdit;
    private EditText chargeAmountEdit;
    private Button submit;
    private PaymentService paymentService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bindComponent();
        paymentService = new PaymentService();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String account = chargePhoneEdit.getText().toString();
                String amount = chargeAmountEdit.getText().toString();
                if (TextUtils.isEmpty(account) || TextUtils.isEmpty(amount)) {
                    Toast.makeText(MobChargeActivity.this, "请输入账户和金额", Toast.LENGTH_SHORT).show();
                } else {
                    String err = paymentService.askForSettlement(account, amount, 1);
                    if (!TextUtils.isEmpty(err)) {
                        Toast.makeText(MobChargeActivity.this, err, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MobChargeActivity.this, "支付申请提交成功，我们将尽快处理", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void bindComponent() {
        chargePhoneEdit = (EditText) findViewById(R.id.chargePhoneEdit);
        chargeAmountEdit = (EditText) findViewById(R.id.chargeAmountEdit);
        submit = (Button) findViewById(R.id.chargeSubmitButton);
    }

    @Override
    protected void onNavArrowClick(View view) {
        finish();
    }

    @Override
    protected void initTitle(View title) {
        if (title instanceof TextView) {
            ((TextView) title).setText("话费充值");
        }
    }

    @Override
    protected void initBody(ViewGroup bodyContainer) {
        View view = LayoutInflater.from(this).inflate(R.layout.charge_phone, bodyContainer, false);
        bodyContainer.addView(view);
    }
}
