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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.navigationactivity.Fragment.Dialer1.AppController;
import com.example.navigationactivity.Fragment.Dialer1.quizItems;
import com.example.navigationactivity.R;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;

public class Rate2 extends AppCompatActivity {


    EditText rate_review;
    public RatingBar ratingbar_default;
    private TextView ratingValue_default;
    String ratedValue,rate;
    private Button btnSubmitRate;
    Fragment fragment = new Fragment();
    String username,userRating;
    public Rate2() {
        // Required empty public constructor
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate2);
        rate_review = (EditText) findViewById(R.id.rate_review);
        btnSubmitRate = findViewById(R.id.btnSubmitRate);
        ratingbar_default = (RatingBar) findViewById(R.id.ratingbar_default);
        ratingValue_default = (TextView)findViewById(R.id.ratingbar_default_number);
        Intent intent = getIntent();
        username = intent.getStringExtra("username11");
        Toast.makeText(getApplicationContext(),username+"  -- ajdbf", Toast.LENGTH_LONG).show();



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


        btnSubmitRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                            rate = rate_review.getText().toString();
                Toast.makeText(getApplicationContext(),ratedValue+ "is rate given by the user", Toast.LENGTH_LONG).show();
                          //  Toast.makeText(Rate2.this,"Thank you for rating!", Toast.LENGTH_SHORT).show();

                            submitRating(rate);

                            startActivity(new Intent(Rate2.this, MainActivity.class));


                        }
        });


    }
    public void submitRating(String rate) {

        final String srate = rate;

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.URL_Normal_Rating_Student,
                new com.android.volley.Response.Listener<String>() {

                    @Override
                    public void onResponse(String ServerResponse) {
                        //  SharedPrefsMethods.saveprofiledata("Logined",email,mobile,spinner,name);


                        Toast.makeText(getApplicationContext(), ServerResponse, Toast.LENGTH_LONG).show();

                        // Hiding the progress dialog after all task complete.


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                        // Hiding the progress dialog after all task complete.


                        // Showing error message if something goes wrong.
                        Toast.makeText(getApplicationContext(), volleyError.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {

                // Creating Map String Params.
                Map<String, String> params = new HashMap<String, String>();

                // Adding All values to Params.
                // The first argument should be same sa your MySQL database table columns.

                params.put("username",username);
                params.put("rating",ratedValue);
                //params.put(Config.wallet,wallet);
                //  params.put("User_Password", PasswordHolder);

                return params;
            }

        };

        // Creating RequestQueue.
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        // Adding the StringRequest object into requestQueue.
        requestQueue.add(stringRequest);


    }




}
