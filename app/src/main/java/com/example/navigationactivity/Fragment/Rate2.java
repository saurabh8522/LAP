package com.example.navigationactivity.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.navigationactivity.R;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Rate2 extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference ref;
    RateAdapter adapter;

    EditText rate_review;
    public RatingBar ratingbar_default;
    private TextView ratingValue_default;
    String name,age,phone,addr,email,gen,ratedVal,ratedValue,uname;
    String rate,userNamesent;
    User user;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private Button btnSubmitRate;
    Fragment fragment = new Fragment();
    public Rate2() {
        // Required empty public constructor
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate2);


        user = new User();
        database = FirebaseDatabase.getInstance();
        ref = database.getReference("profiles/");
        //adapter = new RateAdapter(this, list);

        rate_review = (EditText) findViewById(R.id.rate_review);
        btnSubmitRate = findViewById(R.id.btnSubmitRate);
        ratingbar_default = (RatingBar) findViewById(R.id.ratingbar_default);
        ratingValue_default = (TextView)findViewById(R.id.ratingbar_default_number);
        FirebaseApp.initializeApp(this);
        FirebaseUser firebaseUser = firebaseAuth.getInstance().getCurrentUser();
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();


        Intent intent = getIntent();
        userNamesent = intent.getStringExtra("KEY_USER_NAME");

        ratingbar_default
                .setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                    @Override
                    public void onRatingChanged(RatingBar ratingBar,
                                                float rating, boolean fromUser) {


                        ratedValue = String.valueOf(rating);


                        int numStars = ratingBar.getNumStars();


                        ratingValue_default.setText("Rating value is : "
                                + ratedValue + "/" + numStars);
                    }
                });

//        ref.addValueEventListener(new ValueEventListener() {
//            @RequiresApi(api = Build.VERSION_CODES.N)
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren())
//                {
//                    //user = ds.getValue(User.class);
//                    UserProfile userProfile = dataSnapshot1.getValue(UserProfile.class);
//
//                    if(userProfile.getUserName().equals("REclone"))
//                    {
//                        name = userProfile.getUserName();
//                        age=userProfile.getUserAge();
//                        addr=userProfile.getUserAddr();
//                        email=userProfile.getUserEmail();
//                        phone= userProfile.getUserPhone();
//                        gen=userProfile.getUserGen();
//                        rate = rate_review.getText().toString();
//
//
//                        userProfile = new UserProfile(phone,email,name,addr,gen,age,rate,ratedValue);
//                        //databaseReference.child(firebaseAuth.getUid()).setValue(userProfile1);
//                        ref.child(firebaseAuth.getUid()).setValue(userProfile);
//
//                    }
//                    //user = ds.getValue(User.class);
//
//
//                }
//
//                //String name = dataSnapshot.getValue().toString();
//                //  Toast.makeText(dialerpge.this,""+list.size(),Toast.LENGTH_SHORT ).show();
//                ref.removeEventListener(this);
//                adapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });


        btnSubmitRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(firebaseAuth.getUid()==null) {
                    Intent i = new Intent(Rate2.this, LoginActivity.class);
                    startActivity(i);
                    ((Activity) Rate2.this).overridePendingTransition(0, 0);
                }
                else {
                    //String userID=FirebaseAuth.getInstance().getCurrentUser().getUid();
                    databaseReference = firebaseDatabase.getReference("profiles");
                    databaseReference.child(firebaseAuth.getUid()).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                            for(DataSnapshot ds:dataSnapshot.getChildren())
                            {


                                UserProfile userProfile = dataSnapshot.getValue(UserProfile.class);
                                if(userProfile.getUserName().equals(userNamesent))
                                {
                                    //userProfile.setRatings(ratedValue);
                                    name = userProfile.getUserName();
                                    age=userProfile.getUserAge();
                                    addr=userProfile.getUserAddr();
                                    email=userProfile.getUserEmail();
                                    phone= userProfile.getUserPhone();
                                    gen=userProfile.getUserGen();
                                }

                                Log.d("ADebugTag", "Value: " + userNamesent);




                                //list.add(user);



                            }


                            rate = rate_review.getText().toString();
                            UserProfile userProfile1 = new UserProfile(phone,email,name,addr,gen,age);


                           // databaseReference.child(firebaseAuth.getUid()).setValue(userProfile1);
                            Toast.makeText(Rate2.this,"Thank you for rating!", Toast.LENGTH_SHORT).show();

                            startActivity(new Intent(Rate2.this, MainActivity.class));


                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            Toast.makeText(Rate2.this, databaseError.getCode(), Toast.LENGTH_SHORT).show();
                        }
                    });


                }


            }
        });


    }
}
