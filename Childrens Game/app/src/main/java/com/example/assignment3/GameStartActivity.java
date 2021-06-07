package com.example.assignment3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class GameStartActivity extends AppCompatActivity {
    Bundle username;
    String user;
    StudentDBHelper studentDBHelper;
    int score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_start);
        getSupportActionBar().hide();
        username = getIntent().getExtras();
        studentDBHelper = new StudentDBHelper(this);
        user = username.getString("user");
        score = username.getInt("score");
    }

    public void startEasyGame(View view) {
        Intent myIntent = new Intent(this, EasyGame1.class);
        studentDBHelper.setStudentScore(user,score);
        myIntent.putExtra("user", user);
        myIntent.putExtra("score", score);
        startActivity(myIntent);
    }

    public void startHardGame(View view) {
        Intent myIntent = new Intent(this, HardGame1.class);
        studentDBHelper.setStudentScore(user,score);
        myIntent.putExtra("user", user);
        myIntent.putExtra("score", score);
        startActivity(myIntent);
    }
}