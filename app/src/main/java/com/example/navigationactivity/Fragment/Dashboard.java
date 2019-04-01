package com.example.navigationactivity.Fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.navigationactivity.Fragment.Dialer1.student_quiz_list;
import com.example.navigationactivity.R;
import com.google.firebase.auth.FirebaseAuth;

public class Dashboard extends Fragment {
    private FirebaseAuth firebaseAuth;
    private Button conbtn , profile , helpdesk , wallet ;
    public Dashboard() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_dashboard, container, false);

        firebaseAuth = FirebaseAuth.getInstance();
        wallet = (Button) v.findViewById(R.id.Wallet);
        helpdesk = (Button) v.findViewById(R.id.helpdesk);
        profile = (Button) v.findViewById(R.id.profile);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openprofile();
            }
        });

        helpdesk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openhelpdesk();
            }
        });

        wallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openwallet();
            }
        });

        return v;
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("DashBoard");
    }
    public void openhelpdesk(){
        Intent i = new Intent(getActivity(),Helpdesk.class);
        startActivity(i);
        ((Activity) getActivity()).overridePendingTransition(0,0);
    }

    public void openprofile(){
        Intent i = new Intent(getActivity(),student_quiz_list.class );
        startActivity(i);
        //Fragment fragment = new student_quiz_list();
        //loadwindow(fragment);
        ((Activity) getActivity()).overridePendingTransition(0,0);
    }

    public void openwallet(){
        Intent i = new Intent(getActivity(), Wallet_main.class);
        startActivity(i);
        ((Activity) getActivity()).overridePendingTransition(0,0);
    }
//    private void loadwindow(Fragment fragment){
//        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
//        transaction.replace(R.id.frameLayout,fragment);
//        transaction.addToBackStack(null);
//        transaction.commit();
//
//    }
}
