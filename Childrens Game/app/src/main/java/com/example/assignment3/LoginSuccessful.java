package com.example.assignment3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LoginSuccessful extends AppCompatActivity {

    Bundle username;
    String user;
    int score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_successful);
        getSupportActionBar().hide();
        username = getIntent().getExtras();
        if(username != null) {
            user = username.getString("user");
            try {
                score = username.getInt("score");
            } catch (NumberFormatException e) {
                score = 0;
            }
        }
    }

    public void startGame(View view) {
        Intent myIntent = new Intent(this, GameStartActivity.class);
        myIntent.putExtra("user", user);
        myIntent.putExtra("score", score);
        startActivity(myIntent);
    }

    public void exitGame(View view) {
       this.finishAffinity();
    }

    public void showScore(View view) {
        Intent myIntent = new Intent(this, DisplayScore.class);
        myIntent.putExtra("user", user);
        myIntent.putExtra("score", score);
        startActivity(myIntent);
    }
}