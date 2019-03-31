package com.example.navigationactivity.Fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.navigationactivity.Fragment.MainActivity;
import com.example.navigationactivity.R;
import com.google.firebase.auth.FirebaseAuth;


public class Logout extends Fragment {

    private FirebaseAuth firebaseAuth;
    public Logout() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        firebaseAuth = FirebaseAuth.getInstance();
        View v= inflater.inflate(R.layout.fragment_logout, container, false);
        firebaseAuth.signOut();
        getActivity().finish();
        startActivity(new Intent(getActivity(), LoginActivity.class));
        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Logout");
    }
}
