package com.example.navigationactivity.Fragment;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.navigationactivity.R;

import java.util.ArrayList;

public class RateAdapter extends ArrayAdapter<User> {

    private Context mContext;
    private ArrayList<User> moviesList;

    public RateAdapter(@NonNull Context context, ArrayList<User> list) {
        super(context,0,list);
        mContext = context;
        moviesList = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.item_layout,parent,false);

        User user = moviesList.get(position);
        TextView tx1 = listItem.findViewById(R.id.name);
        tx1.setText(user.getUserName());

        TextView tx2 = listItem.findViewById(R.id.rating);
        String rate="Rating:"+user.getRatings();
       // Toast.makeText(getContext(),rate,Toast.LENGTH_SHORT).show();
        tx2.setText(rate);

        return listItem;
    }
}