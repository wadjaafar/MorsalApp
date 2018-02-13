package com.afromoney_ltd.morsal;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public class GetKey extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... strings) {
            return null;
        }
    }

    public void buttonTapped(View view){

        int id = view.getId();

        switch(id) {
            case R.id.bill_payment:
                startActivity(new Intent(MainActivity.this, BillPayment.class));
                break;
            case R.id.purchase:
                startActivity(new Intent(MainActivity.this, Purchase.class));
                break;
            case R.id.voucher:
                startActivity(new Intent(MainActivity.this, Voucher.class));
                break;
            case R.id.inquiry:
                startActivity(new Intent(MainActivity.this, Inquiry.class));
                break;
            case R.id.transaction:
                startActivity(new Intent(MainActivity.this, Transaction.class));
                break;
        }

    }

}
