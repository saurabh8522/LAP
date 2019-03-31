package com.example.navigationactivity.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.navigationactivity.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Comparator;

public class dialerpge extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference ref;
    ListView listView;
    ArrayList<User> list;
    RateAdapter adapter;
    User user;
    Button bestest,nearest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialerpge);

        listView = (ListView)findViewById(R.id.listView);

        user = new User();
        database = FirebaseDatabase.getInstance();
        ref = database.getReference("profiles/");
        list = new ArrayList<>();
        adapter = new RateAdapter(this, list);
        listView.setAdapter(adapter);
        bestest = (Button)findViewById(R.id.button5);
        nearest = (Button)findViewById(R.id.button4) ;
        ref.addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds:dataSnapshot.getChildren())
                {
                    user = ds.getValue(User.class);
                    list.add(user);
                    //ref.getDatabase().getUid().getcutrre;
                }
                list.sort(new Comparator<User>() {
                    @Override
                    public int compare(User o1, User o2) {
                        if(o1.getRatings().compareTo(o2.getRatings()) == 0)
                            return 0;
                        else if(o1.getRatings().compareTo(o2.getRatings()) > 0)
                            return -1;
                        else if(o1.getRatings().compareTo(o2.getRatings()) > 0)
                            return 1;
                        return  0;
                    }
                });
                //String name = dataSnapshot.getValue().toString();
              //  Toast.makeText(dialerpge.this,""+list.size(),Toast.LENGTH_SHORT ).show();
                ref.removeEventListener(this);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(getApplicationContext(),"tel:"+list.get(position).getUserPhone(),Toast.LENGTH_SHORT).show();
                

                //   startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+list.get(position).getUserPhone())));
                try {
                    list.get(position).setRatings("50");

                    Toast.makeText(getApplicationContext(),"tel:"+list.get(position).getRatings(),Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        nearest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(dialerpge.this,nearest.class));
            }
        });
    }
}
