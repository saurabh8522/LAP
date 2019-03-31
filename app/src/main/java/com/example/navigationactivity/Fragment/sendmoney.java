package com.example.navigationactivity.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.example.navigationactivity.R;

public class sendmoney extends AppCompatActivity {
    private EditText username;
    private EditText Password;
    private Button sendmoney;
    private int counter = 5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sendmoney);

        username = (EditText)findViewById(R.id.etusername);
        Password = (EditText)findViewById(R.id.etpassword);

        sendmoney = (Button)findViewById(R.id.btnsendmoney);


        sendmoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate(username.getText().toString(), Password.getText().toString());
            }
        });
    }

    private void validate(String username, String userPassword){
        if((username .equals("Admin")) && (userPassword.equals("1234"))){
            Intent intent = new Intent(sendmoney.this, moneysent.class);
            startActivity(intent);
        }
        else
            sendmoney.requestFocus();
        sendmoney.setError("Wrong credentials");


    }
}
