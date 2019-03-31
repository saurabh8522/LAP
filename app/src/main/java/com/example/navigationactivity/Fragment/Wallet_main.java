package com.example.navigationactivity.Fragment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.navigationactivity.R;

public class Wallet_main extends AppCompatActivity {
    private Button wall,addselfmoney;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet_main);
        addselfmoney = (Button)findViewById(R.id.button3);
        wall = (Button)findViewById(R.id.button);

        wall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opensendmoney();
            }
        });

        addselfmoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addmoneytoself();
            }
        });
    }
    public void opensendmoney() {
        Intent intent = new Intent(Wallet_main.this, sendmoney.class);
        startActivity(intent);
    }

    public void addmoneytoself(){
        Intent next = new Intent(Wallet_main.this, selfadd.class);
        startActivity(next);
    }
}
