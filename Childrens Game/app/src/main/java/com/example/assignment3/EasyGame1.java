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
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class EasyGame1 extends AppCompatActivity {

    ImageView img;
    ViewGroup rootLayout;
    int xDelta, yDelta;
    Button contButton;
    TextView txtTarget;
    int score;
    Bundle username;
    StudentDBHelper studentDBHelper;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy_game1);
        getSupportActionBar().hide();
        username = getIntent().getExtras();

        rootLayout = findViewById(R.id.view_root);
        img = findViewById(R.id.game1ImageView);
        txtTarget = findViewById(R.id.txtTarget1);
        contButton = findViewById(R.id.continueButton);
        contButton.setClickable(false);
        contButton.setBackgroundColor(Color.DKGRAY);
        studentDBHelper = new StudentDBHelper(this);

        score = username.getInt("score");

        img.setOnTouchListener(onTouchListener);

        txtTarget.setOnDragListener(dragListener);
    }

    View.OnTouchListener onTouchListener = new View.OnTouchListener() {
        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            ClipData data = ClipData.newPlainText("","");
            View.DragShadowBuilder myShadowBuilder = new View.DragShadowBuilder(view);
            view.startDragAndDrop(data, myShadowBuilder, view, 0);
            return true;        }
    };

    View.OnDragListener dragListener = new View.OnDragListener() {
        @Override
        public boolean onDrag(View view, DragEvent dragEvent) {
            int dragE = dragEvent.getAction();
            final View v = (View) dragEvent.getLocalState();

            switch (dragE){
                case DragEvent.ACTION_DRAG_ENTERED:
                    break;
                case DragEvent.ACTION_DRAG_EXITED://Text for if the object enters the field and is dragged out

                   if (v.getId() == R.id.game1ImageView){
                        txtTarget.setText("Place Answer Here");
                        txtTarget.setTextColor(Color.BLACK);
                    }
                    break;
                case DragEvent.ACTION_DROP://When correct object is dropped in field it will enable the button and update the score
                    if (v.getId() == R.id.game1ImageView){
                        txtTarget.setText("Correct!");
                        txtTarget.setTextColor(Color.GREEN);
                        txtTarget.setBackgroundColor(Color.TRANSPARENT);
                        v.animate()
                                .x(txtTarget.getX())
                                .y(txtTarget.getY())
                                .setDuration(700)
                                .start();
                        MediaPlayer mediaPlayer = MediaPlayer.create(EasyGame1.this, R.raw.correct);
                        mediaPlayer.start();
                        contButton.setClickable(true);
                        contButton.setBackgroundColor(Color.LTGRAY);
                        score++;
                        studentDBHelper.setStudentScore(username.getString("user"), score);//passes score to database object to store it
                    }
                    break;
            }
            return true;
        }
    };
    public void moveToNextGame(View view) {//passes user name and score to next activity
        Intent myIntent = new Intent(this, EasyGame2.class);
        myIntent.putExtra("user", username.getString("user"));
        myIntent.putExtra("score", score);
        startActivity(myIntent);
    }
}