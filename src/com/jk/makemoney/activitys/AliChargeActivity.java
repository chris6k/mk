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
import com.jk.makemoney.utils.ThreadPool;

/**
 * @author chris.xue
 *         支付宝支付
 */
public class AliChargeActivity extends BasicActivity {
    private EditText usernameEdit;
    private EditText chargeAlipayEdit;
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
                final String account = usernameEdit.getText().toString();
                final String amount = chargeAlipayEdit.getText().toString();
                if (TextUtils.isEmpty(account) || TextUtils.isEmpty(amount)) {
                    Toast.makeText(AliChargeActivity.this, "请输入账户和金额", Toast.LENGTH_SHORT).show();
                } else {
                    submit.setEnabled(false);
                    submit.setText(R.string.charge_submiting_text);
                    ThreadPool.getInstance().exec(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                String err = paymentService.askForSettlement(account, amount, 2);
                                if (!TextUtils.isEmpty(err)) {
                                    Toast.makeText(AliChargeActivity.this, err, Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(AliChargeActivity.this, "支付申请提交成功，我们将尽快处理", Toast.LENGTH_SHORT).show();
                                }
                            } finally {
                                getHandler().post(new Runnable() {
                                    @Override
                                    public void run() {
                                        submit.setText(R.string.charge_submit_text);
                                        submit.setEnabled(true);
                                    }
                                });
                            }
                        }
                    });
                }
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
