package com.example.navigationactivity.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.navigationactivity.R;

public class nearest extends AppCompatActivity {

    Button dialerpage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nearest);

        dialerpage = (Button)findViewById(R.id.button5);
        dialerpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(nearest.this, dialerpge.class));
            }
        });
    }
}
