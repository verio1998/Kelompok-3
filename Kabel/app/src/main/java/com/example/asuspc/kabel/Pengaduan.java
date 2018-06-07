package com.example.asuspc.kabel;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import static com.example.asuspc.kabel.MainActivity.my_shared_preferences;

public class Pengaduan extends AppCompatActivity {

    ProgressDialog pDialog;
    TextView txt_id, txt_username, txt_tanggal, txt_alamat, txt_status;
    EditText txt_isi;
    String id, username, alamat;
    SharedPreferences sharedpreferences;
    int tanggal, bulan, tahun;
    Button btn_kirim;
    Intent intent;

    int success;
    ConnectivityManager conMgr;

    private String url = Server.URL + "pengaduan.php";

    private static final String TAG = Pengaduan.class.getSimpleName();

    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";

    public static final String TAG_ID = "id";
    public static final String TAG_USERNAME = "username";
    public static final String TAG_ALAMAT = "alamat";
    public static final String TAG_NAMA = "nama";

    String tag_json_obj = "json_obj_req";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengaduan);

        conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        {
            if (conMgr.getActiveNetworkInfo() != null
                    && conMgr.getActiveNetworkInfo().isAvailable()
                    && conMgr.getActiveNetworkInfo().isConnected()) {
            } else {
                Toast.makeText(getApplicationContext(), "No Internet Connection",
                        Toast.LENGTH_LONG).show();
            }
        }

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btn_kirim = (Button) findViewById(R.id.btn_kirim);
        txt_id = (TextView) findViewById(R.id.txt_id);
        txt_username = (TextView) findViewById(R.id.txt_username);
        txt_alamat = (TextView) findViewById(R.id.txt_alamat);

        Calendar calendar = Calendar.getInstance();
        tanggal = calendar.get(Calendar.DAY_OF_MONTH);
        bulan = calendar.get(Calendar.MONTH);
        tahun = calendar.get(Calendar.YEAR);
        String finalTanggal = tahun+"-"+bulan+"-"+tanggal;


        txt_tanggal = (TextView)findViewById(R.id.txt_tanggal);
        txt_tanggal.setText(finalTanggal);
        btn_kirim = (Button) findViewById(R.id.btn_kirim);
        txt_isi = (EditText) findViewById(R.id.txt_isi);
        txt_status = (TextView) findViewById(R.id.txt_status);


        sharedpreferences = getSharedPreferences(my_shared_preferences, Context.MODE_PRIVATE);
        id = sharedpreferences.getString(TAG_ID, null);
        username = sharedpreferences.getString(TAG_USERNAME,null);
        alamat = sharedpreferences.getString(TAG_ALAMAT,null);

        txt_id.setText(id);
        txt_username.setText(username);
        txt_alamat.setText(alamat);

        btn_kirim.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                String id = txt_id.getText().toString();
                String username = txt_username.getText().toString();
                String tanggal = txt_tanggal.getText().toString();
                String alamat = txt_alamat.getText().toString();
                String isi = txt_isi.getText().toString();
                String status = txt_status.getText().toString();

                if (conMgr.getActiveNetworkInfo() != null
                        && conMgr.getActiveNetworkInfo().isAvailable()
                        && conMgr.getActiveNetworkInfo().isConnected()) {
                    checkRegister(id, username, tanggal, alamat, isi, status);
                } else {
                    Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home){
            this.finish();
        }

        return super.onOptionsItemSelected(item);
    }


    private void checkRegister(
            final String id,
            final String username,
            final String tanggal,
            final String alamat,
            final String isi,
            final String status
    ) {
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        pDialog.setMessage("Register ...");
        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.e(TAG, "Register Response: " + response.toString());
                hideDialog();

                try {
                    JSONObject jObj = new JSONObject(response);
                    success = jObj.getInt(TAG_SUCCESS);

                    // Check for error node in json
                    if (success == 1) {

                        Log.e("Successfully Pengaduan!", jObj.toString());

                        Toast.makeText(getApplicationContext(),
                                jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();

                        txt_id.setText("");
                        txt_username.setText("");
                        txt_tanggal.setText("");
                        txt_alamat.setText("");
                        txt_isi.setText("");
                        txt_status.setText("");

                    } else {
                        Toast.makeText(getApplicationContext(),
                                jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();

                    }
                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Login Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();

                hideDialog();

            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("id", id);
                params.put("username", username);
                params.put("tanggal", tanggal);
                params.put("alamat", alamat);
                params.put("isi", isi);
                params.put("status", status);

                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

    @Override
    public void onBackPressed() {
        intent = new Intent(Pengaduan.this, Home.class);
        finish();
        startActivity(intent);
    }

}