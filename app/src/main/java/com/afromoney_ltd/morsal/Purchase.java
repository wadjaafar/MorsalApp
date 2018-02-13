package com.afromoney_ltd.morsal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Purchase extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);
    }

    public void purchaseButton(View view){

        int id = view.getId();

        switch(id) {
            case R.id.purchase_operation:
                startActivity(new Intent(Purchase.this, PurchaseOperation.class));
                break;
            case R.id.purchase_cashback:
                startActivity(new Intent(Purchase.this, PurchaseCashback.class));
                break;
            case R.id.purchase_reversal:
                startActivity(new Intent(Purchase.this, PurchaseReversal.class));
                break;

        }

    }
}
