package com.afromoney_ltd.morsal;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class CashoutVoucher extends AppCompatActivity {

    Pojo pojo = new Pojo();
    EditText amount;
    EditText voucher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cashout_voucher);

        amount = findViewById(R.id.amount);
        voucher = findViewById(R.id.voucher_number);


    }

    public void cashoutVoucher(View view){

        pinDialog();

    }


    public void pinDialog(){

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(CashoutVoucher.this);
        alertDialog.setTitle("PIN");
        alertDialog.setMessage("Enter the phone number used when generating the voucher");

        final EditText input = new EditText(CashoutVoucher.this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        input.setLayoutParams(lp);
        alertDialog.setView(input);

        alertDialog.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        String password = input.getText().toString();
                        pojo.setPhoneNumber(password);
                        requestWithSomeHttpHeaders();

                    }
                });

        alertDialog.show();
    }



    public void requestWithSomeHttpHeaders() {

        HashMap<String, String> params = new HashMap<String, String>();
        params.put("voucherNumber", voucher.getText().toString());
        params.put("systemTraceAuditNumber", String.valueOf(pojo.getSystemTraceNum()));
        params.put("terminalId", pojo.getTerminalId());
        params.put("tranAmount", amount.getText().toString());
        params.put("tranCurrencyCode", "SDG");
        params.put("tranDateTime", pojo.getTranDateTime());
        params.put("phoneNumber", pojo.getPhoneNumber());

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = Constants.URL + "transactions/cashOutVoucher/";
        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(params),
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response) {
                        // response
                        Log.d("Response", String.valueOf(response));
                        try {
                            String responseStatus = response.getString("responseStatus");
                            String voucher = response.getString("voucherNumber");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub
                        Log.d("ERROR","error => "+error.toString());
                        NetworkResponse networkResponse = error.networkResponse;
                        if (networkResponse != null && networkResponse.data != null) {
                            // Print Error!
                            String jsonError = new String(networkResponse.data);
                            Log.d("ERROR MESSAGE", jsonError);
                        }
                    }
                }
        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("Content-Type", "application/json");
                params.put("API-KEY", Constants.API_KEY);

                return params;
            }
        };
        queue.add(jsonRequest);

    }
}