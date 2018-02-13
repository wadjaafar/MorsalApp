package com.afromoney_ltd.morsal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Voucher extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voucher);
    }

    public void voucher(View view){

        int id = view.getId();

        switch (id){

            case R.id.generate_voucher:
                startActivity(new Intent(Voucher.this, GenerateVoucher.class));
                break;
            case R.id.cashout_voucher:
                startActivity(new Intent(Voucher.this, CashoutVoucher.class));
                break;


        }

    }
}
