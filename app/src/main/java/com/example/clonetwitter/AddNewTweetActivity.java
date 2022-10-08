package com.example.clonetwitter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AddNewTweetActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageView back;
    private Button tweet;
    private EditText message;

    FirebaseDatabase database;
    DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_tweet);

        initialize();
        back.setOnClickListener(this);
        //to make focus cursor in editText when activity popUp
        message.setFocusableInTouchMode(true);
        message.requestFocus();
        tweet.setOnClickListener(this);

    }

    private void initialize() {
        back = findViewById(R.id.img_backNewTweet);
        tweet = findViewById(R.id.btn_tweet);
        message = findViewById(R.id.edt_newTweet);
        database = FirebaseDatabase.getInstance("https://twitterauth-184f5-default-rtdb.asia-southeast1.firebasedatabase.app/");
        dbRef = database.getReference("Users");
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.img_backNewTweet:
                onBackPressed();
                break;
            case R.id.btn_tweet:
                
                if(!message.getText().toString().equals("")){
                    back.setEnabled(false);
                    String key = dbRef.push().getKey();
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yy", Locale.US);
                    String currentDateAndTime = simpleDateFormat.format(new Date());

                    dbRef.child(key).child("dis_name").setValue("Anonymous");
                    dbRef.child(key).child("time").setValue(currentDateAndTime);
                    dbRef.child(key).child("username").setValue("@hacker001");
                    dbRef.child(key).child("tweet").setValue(message.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                onBackPressed();
                            }
                        }
                    });
                }else{
                    Toast.makeText(AddNewTweetActivity.this, "Say Something!", Toast.LENGTH_SHORT).show();
                }
                
                break;
            default:break;
        }
    }
}