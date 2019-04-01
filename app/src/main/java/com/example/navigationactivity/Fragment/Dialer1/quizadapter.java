package com.example.navigationactivity.Fragment.Dialer1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.example.navigationactivity.Fragment.listToRate;
import com.example.navigationactivity.R;

import java.util.List;

public class quizadapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;

    private List<com.example.navigationactivity.Fragment.Dialer1.quizItems> quizItems;
    private Context context;
    //ImageLoader imageLoader  = AppController.getInstance().getImageLoader();
    public quizadapter(Activity activity, List<com.example.navigationactivity.Fragment.Dialer1.quizItems> quizItems, Context context) {

        this.activity = activity;
        this.quizItems = quizItems;
        this.context = context;
    }

    @Override
    public int getCount() {
        return quizItems.size();
    }

    @Override
    public Object getItem(int position){
        return quizItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(inflater == null)
            inflater = (LayoutInflater)activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(convertView == null)
            convertView = inflater.inflate(R.layout.quizlist,null);

//        if(imageLoader == null)
//            imageLoader = AppController.getInstance().getImageLoader();

        TextView quiz_name = (TextView)convertView.findViewById(R.id.user_name);
        TextView user_rating = (TextView) convertView.findViewById(R.id.user_ratings);
        LinearLayout linearLayout_quiz = (LinearLayout)convertView.findViewById(R.id.quiz_layout);
        final com.example.navigationactivity.Fragment.Dialer1.quizItems item = quizItems.get(position);
        quiz_name.setText(item.getName());
        user_rating.setText(item.getRating());

        //quiz_name.setText("abc");
        //quiz_image.setImageUrl(item.getQuiz_image(),imageLoader);
        linearLayout_quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
         final Intent intent   = new Intent(context, listToRate.class);
                intent.putExtra("username",item.getName());
                intent.putExtra("rating",item.getRating());
                context.startActivity(intent);

            }
        });
        return convertView;
    }
}
