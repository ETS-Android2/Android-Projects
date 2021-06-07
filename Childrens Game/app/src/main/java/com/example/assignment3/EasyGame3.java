package com.example.assignment3;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class EasyGame3 extends AppCompatActivity {
    TextView txtTarget;
    ImageView cat, dog, horse;
    Bundle username;
    StudentDBHelper studentDBHelper;
    int score;
    Button finishButton;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy_game3);
        getSupportActionBar().hide();

        username = getIntent().getExtras();
        studentDBHelper = new StudentDBHelper(this);
        txtTarget = findViewById(R.id.txtTarget3);
        cat = findViewById(R.id.catImageView);
        dog = findViewById(R.id.dogImageView);
        horse = findViewById(R.id.horseImageView);
        finishButton = findViewById(R.id.finishButton);
        finishButton.setClickable(false);
        finishButton.setBackgroundColor(Color.DKGRAY);

        score = username.getInt("score");

        cat.setOnTouchListener(onTouchListener);
        dog.setOnTouchListener(onTouchListener);
        horse.setOnTouchListener(onTouchListener);

        txtTarget.setOnDragListener(dragListener);
    }
    View.OnTouchListener onTouchListener = new View.OnTouchListener() {
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
                    if (v.getId() == R.id.catImageView){
                        txtTarget.setText("Correct!");
                        txtTarget.setTextColor(Color.GREEN);
                        txtTarget.setBackgroundColor(Color.TRANSPARENT);
                        v.animate()
                                .x(txtTarget.getX())
                                .y(txtTarget.getY())
                                .setDuration(700)
                                .start();
                        MediaPlayer mediaPlayer = MediaPlayer.create(EasyGame3.this, R.raw.correct);
                        mediaPlayer.start();
                        finishButton.setClickable(true);
                        finishButton.setBackgroundColor(Color.LTGRAY);
                    }else if (v.getId() == R.id.dogImageView || v.getId() == R.id.horseImageView){
                        txtTarget.setText("Incorrect!");
                        txtTarget.setTextColor(Color.RED);
                        MediaPlayer mediaPlayer = MediaPlayer.create(EasyGame3.this, R.raw.wrong);
                        mediaPlayer.start();
                    }

                    break;
            }
            return true;
        }
    };

    public void returnToMenu(View view) {
        if(txtTarget.getText() == "Correct!"){
            score++;
            studentDBHelper.setStudentScore(username.getString("user"), score);
        }
        Intent myIntent = new Intent(this, LoginSuccessful.class);
        myIntent.putExtra("user", username.getString("user"));
        myIntent.putExtra("score", score);
        startActivity(myIntent);
    }
}