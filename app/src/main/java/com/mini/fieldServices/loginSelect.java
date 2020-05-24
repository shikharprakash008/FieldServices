package com.mini.fieldServices;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class loginSelect extends AppCompatActivity {

    Button user ,service;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login_select);

        user=findViewById(R.id.user);
        service=findViewById(R.id.technician);

        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent user=  new Intent(loginSelect.this,login.class);
                startActivity(user);
            }
        });

        service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent service= new Intent(loginSelect.this,techLogin.class);
                startActivity(service);
            }
        });
    }
}
