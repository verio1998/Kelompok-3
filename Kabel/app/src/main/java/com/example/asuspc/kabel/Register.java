package com.example.asuspc.kabel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.firebase.client.Firebase;

public class Register extends AppCompatActivity {
    EditText etEmail, etPass;
    Button btRegister;
    Firebase url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Firebase.setAndroidContext(this);

        etEmail = (EditText)findViewById(R.id.etRemail);
        etPass = (EditText)findViewById(R.id.etRpass);
        btRegister = (Button)findViewById(R.id.btRegister);
        url = new Firebase("https://application-demo-4030e.firebaseio.com/");
        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserRegister();
                Firebase firebase = url.child("Name");
                firebase.setValue("HTD");
            }
        });
    }

    private void UserRegister(){
        String email, pass;
        email = etEmail.getText().toString();
        pass = etPass.getText().toString();
    }
}
