package com.example.clonetwitter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.dynamicanimation.animation.SpringAnimation;

import java.util.ArrayList;
import java.util.List;

public class TwitterAdapter extends ArrayAdapter<Twitter> {

    private List<Twitter> twitters = new ArrayList<>();
    private Context context;
    public TwitterAdapter(Context context,List<Twitter> twitters){
        super(context,R.layout.item_tweet,twitters);
        this.context = context;
        this.twitters.addAll(twitters);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        LayoutInflater layoutInflater = LayoutInflater.from(context);
//        View view = layoutInflater.inflate(R.layout.item_tweet,null);
        @SuppressLint("ViewHolder") View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tweet,parent,false);

        TextView username = view.findViewById(R.id.txt_username);
        TextView disName = view.findViewById(R.id.txt_displayName);
        TextView twitterMsg = view.findViewById(R.id.txt_tweet);
        TextView time = view.findViewById(R.id.txt_time);

        Twitter twitter = getItem(position);
        //TODO:Error here
        username.setText(twitter.getUsername());
        disName.setText(twitter.getDisName());
        twitterMsg.setText(twitter.getTwitterMessage());
        time.setText(twitter.getTime());

        return view;
    }
}
