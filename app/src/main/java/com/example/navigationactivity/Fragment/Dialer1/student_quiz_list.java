

package com.example.navigationactivity.Fragment.Dialer1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.example.navigationactivity.R;
import com.example.navigationactivity.Fragment.Config;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;


public class student_quiz_list extends AppCompatActivity {

    private  ListView listView_quiz;
    private List<com.example.navigationactivity.Fragment.Dialer1.quizItems>quizItems;
    private com.example.navigationactivity.Fragment.Dialer1.quizadapter quizadapter;


    private String mParam1;
    private String mParam2;

    public student_quiz_list() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_student_quiz_list);
        setContentView(R.layout.activity_dialerpge);
        listView_quiz = (ListView)findViewById(R.id.quiz_list);
        quizItems = new ArrayList<quizItems>();
        quizadapter = new quizadapter(student_quiz_list.this,quizItems,this);
        listView_quiz.setAdapter(quizadapter);



            StringRequest jsonReq = new StringRequest(Request.Method.GET,
                    Config.URL_FETCH_RATING,  new Response.Listener<String>() {

                @Override
                public void onResponse(String response) {
                    Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();
                    VolleyLog.d(TAG, "Response: " + response.toString());
                    if (response != null) {
                        parseJsonFeed(response);
                    }
                }
            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    VolleyLog.d(TAG, "Error: " + error.getMessage());
                }
            });

            // Adding request to volley request queue
            AppController.getInstance().addToRequestQueue(jsonReq);
        }




    private void parseJsonFeed(String response) {
        try {
            //  Toast.makeText(getContext(),response,Toast.LENGTH_LONG).show();
            JSONArray quizArray = new JSONArray(response);

            for (int i = 0; i < quizArray.length(); i++) {
                JSONObject feedObj = (JSONObject) quizArray.get(i);


                quizItems item= new quizItems();

                item.setName(feedObj.getString("name"));




                item.setRating(feedObj.getString("rating"));




                quizItems.add(item);
            }

            // notify data changes to list adapater
            quizadapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }



}
