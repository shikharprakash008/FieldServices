package com.mini.fieldServices;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ThrowOnExtraProperties;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Home extends AppCompatActivity {

    TextView temp;
    CardView cardView;
    LinearLayout linearLayout ;
     TextView errortext;
     String email;

    int count =0;
    String sEmail,sPassword,subject,details;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        BottomNavigationView bottomNavigationView = findViewById(R.id.botton_navigation);
        //set home selected
        bottomNavigationView.setSelectedItemId(R.id.home);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.home:
                        return true;
                    case R.id.stock:
                        startActivity(new Intent(getApplicationContext(),stock.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.contact:
                        startActivity(new Intent(getApplicationContext(),contactUs.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.profile:
                        startActivity(new Intent(getApplicationContext(),profile.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.feed:
                        startActivity(new Intent(getApplicationContext(),feed.class));
                        overridePendingTransition(0,0);
                        return true;


                }
                return false;
            }
        });

        temp=findViewById(R.id.temp);
        cardView=findViewById(R.id.slotOne);
        linearLayout=findViewById(R.id.layout_main);
        errortext=findViewById(R.id.errorText);
        //sender email credentials
        subject="Malfunction in cooling Device";
        email="shikharprakash005@gmail.com";
        details="Anurag gadha hai lola sala bail insan";

        DatabaseReference refTemp;
        refTemp= FirebaseDatabase.getInstance().getReference("temp").child("value");
        refTemp.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String s = dataSnapshot.getValue().toString();

                temp.setText(s);
                float temps=Float.parseFloat(s);

                if(temps>30.0 && temps<=100)
                {

                     DatabaseReference rEmail;
                     rEmail =FirebaseDatabase.getInstance().getReference("Technician").child("shik").child("email");
                     rEmail.addValueEventListener(new ValueEventListener() {
                         @Override
                         public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                             String email =dataSnapshot.getValue().toString();
                             JavaMailAPI javaMailAPI=new JavaMailAPI(this,email,subject,details);
                             javaMailAPI.execute();
                         }

                         @Override
                         public void onCancelled(@NonNull DatabaseError databaseError) {

                         }
                     });

                    linearLayout.setBackgroundColor(Color.parseColor("#c62828"));
                    errortext.setText("Malfunction in cooling device");
                   // Toast.makeText(Home.this, "Malfunction in cooling device", Toast.LENGTH_SHORT).show();
                }
                else {
                    errortext.setText("");
                    linearLayout.setBackgroundColor(Color.parseColor("#fece2f"));
                }




            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }



}
