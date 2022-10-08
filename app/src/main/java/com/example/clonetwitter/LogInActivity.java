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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class LogInActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText email,pass;
    private Button finish;
    private ImageView back;

    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener authStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        //initialize
        back = findViewById(R.id.img_close);
        email = findViewById(R.id.edt_phEmailUsername);
        pass = findViewById(R.id.edt_password);
        finish = findViewById(R.id.btn_finish);
        auth = FirebaseAuth.getInstance();

        finish.setOnClickListener(this);
        back.setOnClickListener(this);

        authStateListener = firebaseAuth -> {
            if(firebaseAuth.getCurrentUser()!=null){
                startActivity(new Intent(LogInActivity.this,MainActivity.class));
                finish();
            }
        };


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_finish:
                if(AllDataCorrect()){
                    CompareDatatoDatabaseAuth();
                }else{
                    Toast.makeText(this, "UnSuccessfully!", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.img_close:
                startActivity(new Intent(LogInActivity.this,WelcomeActivity.class));
                finish();
                break;
            default:
                break;
        }
    }

    private void CompareDatatoDatabaseAuth() {
        String _email = email.getText().toString();
        String _pass = pass.getText().toString();

        auth.signInWithEmailAndPassword(_email,_pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
//                    Toast.makeText(LogInActivity.this, "Successfully!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LogInActivity.this,MainActivity.class));
                    finish();
                }
            }
        });
    }

    private boolean AllDataCorrect() {
        if(email.getText().toString().equals("") ||pass.getText().toString().equals("")){
            return false;
        }
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        auth.addAuthStateListener(authStateListener);
    }
}