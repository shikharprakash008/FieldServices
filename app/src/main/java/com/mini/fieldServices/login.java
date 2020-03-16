package com.mini.fieldServices;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import javax.crypto.Cipher;

public class login extends AppCompatActivity {
    TextView signin;
    TextView signup;
    Button register;
    private int[] layouts;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        signin=findViewById(R.id.sign_in);
        signup=findViewById(R.id.sign_up);


        register=findViewById(R.id.register);


       signup.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               setContentView(R.layout.layout_login);
           }
       });






    }



}
