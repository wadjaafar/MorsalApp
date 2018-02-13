package com.afromoney_ltd.morsal;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

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

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class PurchaseReversal extends AppCompatActivity {

    Pojo pojo = new Pojo();
    EditText amount;
    EditText amountCashback;
    Spinner spinner;

    HashMap<Integer,String> spinnerMap = new HashMap<Integer, String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_reversal);

        amount = findViewById(R.id.amount);
        amountCashback = findViewById(R.id.cashback_amount);

        spinnerMap.put(1, "Purchase");
        spinnerMap.put(2, "Purchase With Cash Back");
        spinnerMap.put(3, "Balance Inquiry");
        spinnerMap.put(4, "Mini Statement");
        spinnerMap.put(5, "Bill Payment");
        spinnerMap.put(5, "Bill Prepayment");
        spinnerMap.put(7, "Account Transfer");
        spinnerMap.put(8, "Card Transfer");
        spinnerMap.put(9, "Cash-in");
        spinnerMap.put(9, "Refund");
        spinnerMap.put(10, "Generate Voucher");
        spinnerMap.put(11, "Cash-out Voucher");
        spinnerMap.put(12, "Bill Inquiry");
        spinnerMap.put(13, "Cash out");
        spinnerMap.put(14, "Request eGovernment service");
        spinnerMap.put(15, "Purchase Mobile");

        String[] spinnerArray = spinnerMap.values().toArray(new String[0]);
        Log.i("Spinner Array", String.valueOf(Arrays.toString(spinnerArray)));
        spinner = findViewById(R.id.spinner);

        ArrayAdapter<String> adapter =new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, spinnerArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


    }

    public void purchaseReversal(View view){

        pinDialog();

    }

    public void pinDialog(){

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Title");
        alert.setMessage("Are you sure you want to reverse the payment?");
        // Create TextView
        final TextView input = new TextView (this);
        alert.setView(input);

        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // Do something with value!
                requestWithSomeHttpHeaders();
            }
        });

        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // Canceled.
            }
        });
        alert.show();
    }




    public void requestWithSomeHttpHeaders() {

        HashMap<String, String> params = new HashMap<String, String>();
        params.put("expDate", pojo.getExpDate());
        params.put("PAN", pojo.getPAN());
        params.put("serviceId", String.valueOf(spinner.getId()));
        params.put("terminalId", pojo.getTerminalId());
        params.put("tranAmount", amount.getText().toString());
        params.put("tranCurrencyCode", "SDG");
        params.put("tranDateTime", pojo.getTranDateTime());
        params.put("originalTranSystemTraceAuditNumber", pojo.getOriginalSystemTraceNum());

        Log.i("MY REQUEST ", String.valueOf(params));

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = Constants.URL + "transactions/reverse/";
        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(params),
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response) {
                        // response
                        Log.d("Response", String.valueOf(response));
                        try {
                            String responseStatus = response.getString("responseStatus");
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
