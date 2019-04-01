package com.example.navigationactivity.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.navigationactivity.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class listToRate extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference ref;
    User user;
    Button rate, pay, call;
    String name, phoneNumber,userNamesent;
    String username, userRating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_to_rate);

        call = findViewById(R.id.btnCall);
        rate = findViewById(R.id.btnRate);
        user = new User();
        database = FirebaseDatabase.getInstance();
        ref = database.getReference("profiles/");


        /* Get values from Intent */
        //Intent intent = getIntent();
       // phoneNumber  = intent.getStringExtra("KEY_PHONE_NUMBER");
       // userNamesent = intent.getStringExtra("KEY_USER_NAME");
        Intent intent1 = getIntent();
        username = intent1.getStringExtra("username");
        userRating = intent1.getStringExtra("rating");



        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+phoneNumber)));
                Toast.makeText(listToRate.this,phoneNumber, Toast.LENGTH_SHORT).show();
            }
        });


        rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),userRating +"is rating",Toast.LENGTH_LONG).show();
                Intent intent  = new Intent(listToRate.this, Rate2.class);
                intent.putExtra("username11",username);
                intent.putExtra("rating11",userRating);

                //intent.putExtra("KEY_PHONE_NUMBER", list.get(position).getUserPhone());
                startActivity(intent);

                //startActivity(new Intent(listToRate.this, Rate2.class));
               // Toast.makeText(listToRate.this,"RATED", Toast.LENGTH_SHORT).show();
            }
        });





    }



}
