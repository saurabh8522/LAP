package com.example.navigationactivity.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
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

public class rate_review extends Fragment {
    EditText rate_review;
    public  RatingBar ratingbar_default;
    private  TextView ratingValue_default;
    String name,age,phone,addr,email,gen,ratedVal,ratedValue,uname;
    String rate;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private Button btnSubmitRate;
    Fragment fragment = new Fragment();
    public rate_review() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_rate_review, container, false);
        rate_review = (EditText) view.findViewById(R.id.rate_review);
        btnSubmitRate = view.findViewById(R.id.btnSubmitRate);
        ratingbar_default = (RatingBar) view.findViewById(R.id.ratingbar_default);
        ratingValue_default = (TextView)view. findViewById(R.id.ratingbar_default_number);
        FirebaseApp.initializeApp(getActivity());
        FirebaseUser firebaseUser = firebaseAuth.getInstance().getCurrentUser();
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        ratingbar_default
                .setOnRatingBarChangeListener(new OnRatingBarChangeListener() {
                    @Override
                    public void onRatingChanged(RatingBar ratingBar,
                                                float rating, boolean fromUser) {


                        ratedValue = String.valueOf(rating);


                        int numStars = ratingBar.getNumStars();


                        ratingValue_default.setText("Rating value is : "
                                + ratedValue + "/" + numStars);
                    }
                });
        btnSubmitRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(firebaseAuth.getUid()==null) {
                    Intent i = new Intent(getActivity(), LoginActivity.class);
                    startActivity(i);
                    ((Activity) getActivity()).overridePendingTransition(0, 0);
                }
                else {
                    //String userID=FirebaseAuth.getInstance().getCurrentUser().getUid();
                    databaseReference = firebaseDatabase.getReference("profiles");
                    databaseReference.child(firebaseAuth.getUid()).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            UserProfile userProfile = dataSnapshot.getValue(UserProfile.class);
                            name = userProfile.getUserName();
                            age=userProfile.getUserAge();
                            addr=userProfile.getUserAddr();
                            email=userProfile.getUserEmail();
                            phone= userProfile.getUserPhone();
                            gen=userProfile.getUserGen();
//                            ratedVal=userProfile.getRatings();
//                            rate=userProfile.getRate_review();

                            //ratedValue=userProfile.getRatings();
                            //ratings=userProfile.getRatings();
                            rate = rate_review.getText().toString();
                            UserProfile userProfile1 = new UserProfile(phone,email,name,addr,gen,age,rate,ratedValue);


                            databaseReference.child(firebaseAuth.getUid()).setValue(userProfile1);
                            Toast.makeText(getActivity(),"Thank you for rating!", Toast.LENGTH_SHORT).show();

                            startActivity(new Intent(getContext().getApplicationContext(), MainActivity.class));


                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            Toast.makeText(getActivity(), databaseError.getCode(), Toast.LENGTH_SHORT).show();
                        }
                    });


                }
            }
        });

        return  view;
    }

//    private void loadwindow(Fragment fragment){
//        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
//        transaction.replace(R.id.frameLayout,fragment);
//        transaction.addToBackStack(null);
//        transaction.commit();
//
//    }

}
