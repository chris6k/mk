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
public class MobChargeActivity extends BasicActivity {

    private EditText chargePhoneEdit;
    private EditText chargeAmountEdit;
    private Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bindComponent();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MobChargeActivity.this, "提交,号码=" + chargePhoneEdit.getText().toString() + ",金额=" + chargeAmountEdit.getText().toString(), Toast.LENGTH_SHORT).show();
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
