package com.example.navigationactivity.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.navigationactivity.R;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ContactActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    // private TextView profileName, profileAge, profileEmail;
    private FirebaseDatabase firebaseDatabase;
    String num1,temp1,temp2,temp3,temp4,temp5;
    String name1="";
    String name2="";
    String name3="";
    String name4="";
    String name5="";
    TextView tv1,tv2,tv3,tv4,tv5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        FirebaseApp.initializeApp(this);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference1 = firebaseDatabase.getReference("/koVHx0ZNmSc5QOsNZ0fnW5lqcoq2");
        DatabaseReference databaseReference2 = firebaseDatabase.getReference("/8T8CSWLgSSOErwfyelPJR6wQnf93");
        DatabaseReference databaseReference3 = firebaseDatabase.getReference("/Einy4Gy6u3f24huiJSNaedpJ76l1");
        DatabaseReference databaseReference4 = firebaseDatabase.getReference("/HRzjY75QWuQxzvVnGvshes1efLI2");
        DatabaseReference databaseReference5 = firebaseDatabase.getReference("/kLTSuz8x77ehaUAOxE5dSLk8Dbk2");

        databaseReference1.addValueEventListener(new ValueEventListener() {
            @Override

            public void onDataChange(DataSnapshot dataSnapshot) {
                try{
                    UserProfile userProfile = dataSnapshot.getValue(UserProfile.class);
                    name1 = userProfile.getUserName();
                    temp1 = userProfile.getUserAge();
                    tv1=(TextView)findViewById(R.id.textView7);
                    tv1.setText(name1);

                }

                catch (Exception e){
                    Toast.makeText(ContactActivity.this,"1",Toast.LENGTH_LONG).show();}

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(ContactActivity.this, databaseError.getCode(), Toast.LENGTH_SHORT).show();
            }
        });

        databaseReference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                try{
                    UserProfile userProfile = dataSnapshot.getValue(UserProfile.class);
                    name2 = userProfile.getUserName();
                    temp2 = userProfile.getUserAge();
                    tv2=(TextView)findViewById(R.id.textview6);
                    tv2.setText(name2);}

                catch (Exception e){Toast.makeText(ContactActivity.this,"2",Toast.LENGTH_LONG).show();}

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(ContactActivity.this, databaseError.getCode(), Toast.LENGTH_SHORT).show();
            }
        });

        databaseReference3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                try{
                    UserProfile userProfile = dataSnapshot.getValue(UserProfile.class);
                    name3 = userProfile.getUserName();
                    temp3 = userProfile.getUserAge();
                    tv3=(TextView)findViewById(R.id.textView88);
                    tv3.setText(name3);}

                catch(Exception e){Toast.makeText(ContactActivity.this,"3",Toast.LENGTH_LONG).show();}

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(ContactActivity.this, databaseError.getCode(), Toast.LENGTH_SHORT).show();
            }
        });

        databaseReference4.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                try{
                    UserProfile userProfile = dataSnapshot.getValue(UserProfile.class);
                    name4 = userProfile.getUserName();
                    temp4 = userProfile.getUserAge();
                    tv4=(TextView)findViewById(R.id.textView9);
                    tv4.setText(name4);}

                catch (Exception e){Toast.makeText(ContactActivity.this,"4",Toast.LENGTH_LONG).show();}

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(ContactActivity.this, databaseError.getCode(), Toast.LENGTH_SHORT).show();
            }
        });

        databaseReference5.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                try{
                    UserProfile userProfile = dataSnapshot.getValue(UserProfile.class);
                    name5 = userProfile.getUserName();
                    temp5 = userProfile.getUserAge();
                    tv5=(TextView)findViewById(R.id.textView10);
                    tv5.setText(name5);}

                catch (Exception e){Toast.makeText(ContactActivity.this,"5",Toast.LENGTH_LONG).show();}

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(ContactActivity.this, databaseError.getCode(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void call(View v)
    {
        int bid=v.getId();
        switch (bid)
        {
            case R.id.imageButton:
                num1=temp1;
                break;

            case R.id.imageButton2:
                num1=temp2;
                break;

            case R.id.imageButton3:
                num1=temp3;
                break;

            case R.id.imageButton4:
                num1=temp4;
                break;

            case R.id.imageButton5:
                num1=temp5;
                break;


        }
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:"+num1));
        startActivity(intent);
    }
}
