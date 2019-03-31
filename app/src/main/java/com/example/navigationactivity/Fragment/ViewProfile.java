package com.example.navigationactivity.Fragment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.navigationactivity.Fragment.MainActivity;
import com.example.navigationactivity.R;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class ViewProfile extends Fragment {
    private TextView profileName, profileAge, profileEmail,profilePhone,profileAddr,profileGen,profileratings,profileUserName;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private ProgressDialog progressDialog;
    public ViewProfile() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_view_profile, container, false);
        profileName = v.findViewById(R.id.tvProfileName);
        profileAge = v.findViewById(R.id.tvProfileAge);
        profileEmail = v.findViewById(R.id.tvProfileEmail);
        profilePhone = v.findViewById(R.id.tvProfilePhone);
        profileAddr = v.findViewById(R.id.tvProfileAddr);
        profileratings=v.findViewById(R.id.originalRatings);
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setTitle("Loading...");
        progressDialog.show();

        //((MainActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FirebaseApp.initializeApp(getActivity());
        FirebaseUser firebaseUser = firebaseAuth.getInstance().getCurrentUser();
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        //firebaseStorage = FirebaseStorage.getInstance();
        if(firebaseAuth.getUid()==null) {
            Intent i = new Intent(getActivity(), LoginActivity.class);
            startActivity(i);
            ((Activity) getActivity()).overridePendingTransition(0, 0);
        }
        else {
            //Toast.makeText(getActivity(),firebaseAuth.getUid(),Toast.LENGTH_SHORT).show();
            databaseReference = firebaseDatabase.getReference("profiles");
            databaseReference.child(firebaseAuth.getUid()).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    UserProfile userProfile = dataSnapshot.getValue(UserProfile.class);
                    profileName.setText(userProfile.getUserName());
                    profileAge.setText(userProfile.getUserAge());
                    profileEmail.setText(userProfile.getUserEmail());
                    profileAddr.setText(userProfile.getUserAddr());
                    profilePhone.setText(userProfile.getUserPhone());
                    profileratings.setText(userProfile.getRatings());
                    //profileUserName.setText(userProfile.getuName());
                    progressDialog.dismiss();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Toast.makeText(getActivity(), databaseError.getCode(), Toast.LENGTH_SHORT).show();
                }
            });
        }
        return v;
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("View Profile");
    }
}
