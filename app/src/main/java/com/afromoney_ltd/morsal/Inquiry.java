package com.afromoney_ltd.morsal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Inquiry extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inquiry);

    }

    public void inquiryButton(View view){

        int id = view.getId();

        switch (id){
            case R.id.bill_inquiry:
                startActivity(new Intent(Inquiry.this, BillInquiry.class));
                break;
            case R.id.balance_inquiry:
                startActivity(new Intent(Inquiry.this, BalanceInquiry.class));
                break;
            case R.id.mini_statement:
                startActivity(new Intent(Inquiry.this, MiniStatement.class));
                break;
        }

    }

}
