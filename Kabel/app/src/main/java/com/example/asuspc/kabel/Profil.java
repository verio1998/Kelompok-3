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

import java.util.HashMap;
import java.util.Map;

import static com.example.asuspc.kabel.MainActivity.TAG_TELEPON;
import static com.example.asuspc.kabel.MainActivity.my_shared_preferences;

public class Profil extends AppCompatActivity {

    ProgressDialog pDialog;
    Intent intent;
    Button btn_edit;
    String id, username, alamat, nama, telepon, password, confirm_password;
    SharedPreferences sharedpreferences;
    EditText txt_username, txt_password, txt_confirm_password, txt_alamat, txt_nama, txt_telepon;

    int success;
    ConnectivityManager conMgr;

    private String url = Server.URL + "edit.php";

    private static final String TAG = Profil.class.getSimpleName();

    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";

    String tag_json_obj = "json_obj_req";

    public static final String TAG_ID = "id";
    public static final String TAG_NAMA = "nama";
    public static final String TAG_ALAMAT = "alamat";
    public static final String TAG_USERNAME = "username";
    public static final String TAG_PASSWORD = "password";
    public static final String TAG_CONFIRM_PASSWORD = "confirm_password";
    public static final String TAG_TELEPON = "telepon";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

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

        btn_edit = (Button)findViewById(R.id.btn_edit);
        txt_username =findViewById(R.id.txt_username);
        txt_alamat =findViewById(R.id.txt_alamat);
        txt_nama =findViewById(R.id.txt_nama);
        txt_telepon =findViewById(R.id.txt_telepon);
        txt_password =findViewById(R.id.txt_password);
        txt_confirm_password =findViewById(R.id.txt_confirm_password);


        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        sharedpreferences = getSharedPreferences(my_shared_preferences, Context.MODE_PRIVATE);
        id = sharedpreferences.getString(TAG_ID, null);
        nama = sharedpreferences.getString(TAG_NAMA,null);
        alamat = sharedpreferences.getString(TAG_ALAMAT,null);
        username = sharedpreferences.getString(TAG_USERNAME,null);
        password = sharedpreferences.getString(TAG_PASSWORD,null);
        //confirm_password = sharedpreferences.getString(TAG_CONFIRM_PASSWORD,null);
        telepon = sharedpreferences.getString(TAG_TELEPON,null);


        txt_nama.setText(nama);
        txt_alamat.setText(alamat);
        txt_username.setText(username);
        txt_password.setText(password);
        txt_confirm_password.setText(password);
        txt_telepon.setText(telepon);

        btn_edit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                String username = txt_username.getText().toString();
                String password = txt_password.getText().toString();
                String confirm_password = txt_confirm_password.getText().toString();
                String alamat = txt_alamat.getText().toString();
                String nama = txt_nama.getText().toString();
                String telepon = txt_telepon.getText().toString();

                if (conMgr.getActiveNetworkInfo() != null
                        && conMgr.getActiveNetworkInfo().isAvailable()
                        && conMgr.getActiveNetworkInfo().isConnected()) {
                    checkRegister(id,username, password, confirm_password, alamat, nama, telepon);
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
            final String password,
            final String confirm_password,
            final String alamat,
            final String nama,
            final String telepon
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

                        Log.e("Successfully Register!", jObj.toString());

                        Toast.makeText(getApplicationContext(),
                                jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();


                        txt_username.setText(username);
                        txt_password.setText(password);
                        txt_confirm_password.setText(password);
                        txt_alamat.setText(alamat);
                        txt_nama.setText(nama);
                        txt_telepon.setText(telepon);

                        SharedPreferences.Editor editor = sharedpreferences.edit();
                        editor.putString(TAG_USERNAME, username);
                        editor.putString(TAG_PASSWORD, password);
                        editor.putString(TAG_ALAMAT, alamat);
                        editor.putString(TAG_NAMA, nama);
                        editor.putString(TAG_TELEPON, telepon);
                        editor.commit();

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
                params.put("password", password);
                params.put("confirm_password", confirm_password);
                params.put("alamat", alamat);
                params.put("nama", nama);
                params.put("telepon", telepon);

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
        finish();
    }

}
