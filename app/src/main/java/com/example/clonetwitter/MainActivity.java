package com.example.clonetwitter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity{

    private List<Twitter> twitters;
    private ListView listView;
    private FloatingActionButton floatingActionButton;
    private ImageView menu;

    private DrawerLayout drawerLayout;
    private NavigationView navView;

    FirebaseDatabase database;
    DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //initialize
        drawerLayout = findViewById(R.id.drawerLayout);
        navView = findViewById(R.id.navigation_view);
        menu = findViewById(R.id.img_menu);
        floatingActionButton = findViewById(R.id.flbtn_add_tweet);
        listView = findViewById(R.id.twitterListView);
	   int a = 10;



        database = FirebaseDatabase.getInstance("https://twitterauth-184f5-default-rtdb.asia-southeast1.firebasedatabase.app/");
        dbRef = database.getReference("Users");

        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                twitters = new ArrayList<>();
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    twitters.add(new Twitter(dataSnapshot.child("username").getValue(String.class),
                            dataSnapshot.child("dis_name").getValue(String.class),
                            dataSnapshot.child("tweet").getValue(String.class),
                            dataSnapshot.child("time").getValue(String.class)));
                }
                Collections.reverse(twitters);
                TwitterAdapter adapter = new TwitterAdapter(MainActivity.this,twitters);
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,AddNewTweetActivity.class));
            }
        });

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(drawerLayout.isDrawerOpen(GravityCompat.START)){
                    drawerLayout.closeDrawer(GravityCompat.START);
                }else{
                    drawerLayout.openDrawer(GravityCompat.START);
                }
            }
        });


    }


}