package com.example.assignment3;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class EasyGame2 extends AppCompatActivity {
    TextView txt1, txt2, txt3, txtQ, txtTarget;
    RelativeLayout relativeLayout;
    Bundle username;
    Button contButton;
    StudentDBHelper studentDBHelper;
    int score;
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy_game2);
        getSupportActionBar().hide();

        username = getIntent().getExtras();
        studentDBHelper = new StudentDBHelper(this);
        txt1 = findViewById(R.id.txt1);
        txt2 = findViewById(R.id.txt2);
        txt3 = findViewById(R.id.txt3);
        txtQ = findViewById(R.id.txtQuest);
        txtTarget = findViewById(R.id.txtTarget);
        relativeLayout = findViewById(R.id.easyGame2Layout);
        contButton = findViewById(R.id.continueButton2);
        contButton.setClickable(false);
        contButton.setBackgroundColor(Color.DKGRAY);

        txt1.setOnTouchListener(onTouchListener);
        txt2.setOnTouchListener(onTouchListener);
        txt3.setOnTouchListener(onTouchListener);

        txtTarget.setOnDragListener(dragListener);

        score = username.getInt("score");

    }

    View.OnTouchListener onTouchListener = new View.OnTouchListener() {
        @SuppressLint("ClickableViewAccessibility")
        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            ClipData data = ClipData.newPlainText("","");
            View.DragShadowBuilder myShadowBuilder = new View.DragShadowBuilder(view);
            view.startDragAndDrop(data, myShadowBuilder, view, 0);
            return true;
        }
    };

    View.OnDragListener dragListener = new View.OnDragListener() {
        @SuppressLint("SetTextI18n")
        @Override
        public boolean onDrag(View view, DragEvent dragEvent) {
            int dragE = dragEvent.getAction();
            final View v = (View) dragEvent.getLocalState();

            switch (dragE){
                case DragEvent.ACTION_DRAG_ENTERED:
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    if (v.getId() == R.id.txt2){
                        txtTarget.setText("Place Answer Here");
                        txtTarget.setTextColor(Color.BLACK);
                    }else if (v.getId() == R.id.txt1 || v.getId() == R.id.txt3){
                        txtTarget.setText("Place Answer Here");
                        txtTarget.setTextColor(Color.BLACK);
                    }
                    break;
                case DragEvent.ACTION_DROP:
                    if (v.getId() == R.id.txt2){
                        txtTarget.setText("Correct!");
                        txtTarget.setTextColor(Color.GREEN);
                        txtTarget.setBackgroundColor(Color.TRANSPARENT);
                        v.animate()
                                .x(txtTarget.getX())
                                .y(txtTarget.getY())
                                .setDuration(700)
                                .start();
                        MediaPlayer mediaPlayer = MediaPlayer.create(EasyGame2.this, R.raw.correct);
                        mediaPlayer.start();
                        contButton.setClickable(true);
                        contButton.setBackgroundColor(Color.LTGRAY);
                        score++;
                        studentDBHelper.setStudentScore(username.getString("user"), score);
                    }else if (v.getId() == R.id.txt1 || v.getId() == R.id.txt3){
                        txtTarget.setText("Incorrect! \n Can continue without point.");
                        txtTarget.setTextColor(Color.RED);
                        MediaPlayer mediaPlayer = MediaPlayer.create(EasyGame2.this, R.raw.wrong);
                        mediaPlayer.start();
                        contButton.setClickable(true);
                        contButton.setBackgroundColor(Color.LTGRAY);
                    }

                    break;
            }
            return true;
        }
    };

    public void moveToNextGame2(View view) {
        Intent myIntent = new Intent(this, EasyGame3.class);
        myIntent.putExtra("user", username.getString("user"));
        myIntent.putExtra("score", score);
        startActivity(myIntent);
    }
}