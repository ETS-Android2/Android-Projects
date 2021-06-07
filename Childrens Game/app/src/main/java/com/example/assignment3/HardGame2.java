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
import android.widget.RelativeLayout;
import android.widget.TextView;

public class HardGame2 extends AppCompatActivity {

    TextView txtDest, red1, red2, red3, red4, red5, red6 ,red7;
    ImageView droid;
    RelativeLayout relativeLayout;
    Bundle username;
    Button contButton;
    StudentDBHelper studentDBHelper;
    int score;
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hard_game2);
        getSupportActionBar().hide();

        username = getIntent().getExtras();
        studentDBHelper = new StudentDBHelper(this);
        score = username.getInt("score");

        droid = findViewById(R.id.droidImg);
        txtDest = findViewById(R.id.txtGoal);
        red1 = findViewById(R.id.redBar1);
        red2 = findViewById(R.id.redBar2);
        red3 = findViewById(R.id.redBar3);
        red4 = findViewById(R.id.redBar4);
        red5 = findViewById(R.id.redBar5);
        red6 = findViewById(R.id.redBar6);
        red7 = findViewById(R.id.redBar7);

        droid.setOnTouchListener(onTouchListener);

        contButton = findViewById(R.id.ContinueButton3);
        contButton.setClickable(false);
        contButton.setBackgroundColor(Color.DKGRAY);

        red1.setOnDragListener(dragListener1);
        red2.setOnDragListener(dragListener1);
        red3.setOnDragListener(dragListener1);
        red4.setOnDragListener(dragListener1);
        red5.setOnDragListener(dragListener1);
        red6.setOnDragListener(dragListener1);
        red7.setOnDragListener(dragListener1);

        txtDest.setOnDragListener(dragListener);

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

    View.OnDragListener dragListener1 = new View.OnDragListener() {
        @RequiresApi(api = Build.VERSION_CODES.N)
        @SuppressLint("SetTextI18n")
        @Override
        public boolean onDrag(View view, DragEvent dragEvent) {//Will cancel drag if img is dragged into one of the red bars and you must restart the game
            int dragE = dragEvent.getAction();
            final View v = (View) dragEvent.getLocalState();

            switch (dragE){
                case DragEvent.ACTION_DRAG_ENTERED:
                     if(v.getId() == R.id.droidImg){
                        txtDest.setText("Failed! \n You can continue but will not receive a point!");
                        txtDest.setTextColor(Color.RED);
                        droid.cancelDragAndDrop();
                        MediaPlayer mediaPlayer = MediaPlayer.create(HardGame2.this, R.raw.wrong);
                        mediaPlayer.start();
                        contButton.setClickable(true);
                        contButton.setBackgroundColor(Color.LTGRAY);
                    }
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    break;
                case DragEvent.ACTION_DROP:
                    break;
            }
            return true;
        }
    };

    View.OnDragListener dragListener = new View.OnDragListener() {
        @RequiresApi(api = Build.VERSION_CODES.N)
        @SuppressLint("SetTextI18n")
        @Override
        public boolean onDrag(View view, DragEvent dragEvent) {//game is passed and user can continue and gains a point
            int dragE = dragEvent.getAction();
            final View v = (View) dragEvent.getLocalState();

            switch (dragE){
                case DragEvent.ACTION_DRAG_ENTERED:
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    break;
                case DragEvent.ACTION_DROP:
                    if (v.getId() == R.id.droidImg){
                        txtDest.setText("Good Job!");
                        txtDest.setTextColor(Color.GREEN);
                        txtDest.setBackgroundColor(Color.TRANSPARENT);
                        v.animate()
                                .x(txtDest.getX())
                                .y(txtDest.getY())
                                .setDuration(700)
                                .start();
                        MediaPlayer mediaPlayer = MediaPlayer.create(HardGame2.this, R.raw.correct);
                        mediaPlayer.start();
                        contButton.setClickable(true);
                        contButton.setBackgroundColor(Color.LTGRAY);
                        score++;
                        studentDBHelper.setStudentScore(username.getString("user"), score);
                    }
                    break;
            }
            return true;
        }
    };

    public void moveToNextGame(View view) {
        Intent myIntent = new Intent(this, HardGame3.class);
        myIntent.putExtra("user", username.getString("user"));
        myIntent.putExtra("score", score);
        startActivity(myIntent);
    }
}