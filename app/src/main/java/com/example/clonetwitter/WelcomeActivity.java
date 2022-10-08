package com.example.clonetwitter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;

public class WelcomeActivity extends AppCompatActivity implements View.OnClickListener{
    private MaterialButton createAcc;
    private Button logIn, next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);



        createAcc = findViewById(R.id.btn_creAcc);
        logIn = findViewById(R.id.btn_login);
        next = findViewById(R.id.btn_next);

        createAcc.setOnClickListener(this);
        logIn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btn_creAcc:
                startActivity(new Intent(this,CreateAccountActivity.class));
                finish();
                break;
            case R.id.btn_login:
                startActivity(new Intent(this,LogInActivity.class));
                finish();
                break;
            case R.id.btn_next:
                break;
            default:
                break;
        }
    }

}