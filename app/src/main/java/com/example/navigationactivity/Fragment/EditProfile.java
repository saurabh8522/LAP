package com.example.navigationactivity.Fragment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.navigationactivity.Fragment.MainActivity;
import com.example.navigationactivity.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EditProfile extends Fragment {
    private EditText newUserName, newUserAge,newUserPhone,NewUserAddr;
    private Button save;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    String name,age,phone,addr,email,gen,rate,ratedValue;
    private ProgressDialog progressDialog;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_edit_profile, container, false);
        newUserName = v.findViewById(R.id.etUserName);
        newUserAge = v.findViewById(R.id.etAgeUpdate);
        newUserPhone = v.findViewById(R.id.etcontact);
        NewUserAddr = v.findViewById(R.id.etaddr);
        //newRate= v.findViewById(R.id.etName);
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setTitle("Loading...");
        progressDialog.show();
        //gen = radioButton.getText().toString();

        save = v.findViewById(R.id.btnSave);
        //((MainActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        //firebaseStorage = FirebaseStorage.getInstance();
        if(firebaseAuth.getUid()==null) {
            Intent i = new Intent(getActivity(), LoginActivity.class);
            startActivity(i);
            ((Activity) getActivity()).overridePendingTransition(0, 0);
        }
        else {
            final DatabaseReference databaseReference = firebaseDatabase.getReference("profiles");
            //String userID=FirebaseAuth.getInstance().getCurrentUser().getUid();
            databaseReference.child(firebaseAuth.getUid()).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    UserProfile userProfile = dataSnapshot.getValue(UserProfile.class);
                    newUserName.setText(userProfile.getUserName());
                    newUserAge.setText(userProfile.getUserAge());
                    newUserPhone.setText(userProfile.getUserPhone());
                    NewUserAddr.setText(userProfile.getUserAddr());
                    email = userProfile.getUserEmail();
                    gen = userProfile.getUserGen();
//                    rate= userProfile.getRate_review();
//                    ratedValue=userProfile.getRatings();
                    progressDialog.dismiss();

                    //newUserGen.setText(userProfile.getUserGen());
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Toast.makeText(getActivity(), databaseError.getCode(), Toast.LENGTH_SHORT).show();
                }
            });

            save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Toast.makeText(getActivity(),email+gen+rate+ratedValue,Toast.LENGTH_SHORT).show();
                    name = newUserName.getText().toString();
                    age = newUserAge.getText().toString();
                    phone = newUserPhone.getText().toString();
                    addr = NewUserAddr.getText().toString();
                    UserProfile userProfile = new UserProfile(phone, email, name, addr, gen, age);

                    databaseReference.child(firebaseAuth.getUid()).setValue(userProfile);
                    startActivity(new Intent(getContext().getApplicationContext(), MainActivity.class));
                }
            });
        }
        return v;
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("EditProfile");
    }
}
