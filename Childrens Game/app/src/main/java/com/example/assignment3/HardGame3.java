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
import android.widget.Switch;
import android.widget.TextView;

public class HardGame3 extends AppCompatActivity {

    TextView txtDest, txtDest2, txtFinalDest, red1, red2, red3, red4, red5, red6, red7, red8, red9, redDoor;
    ImageView droid;
    RelativeLayout relativeLayout;
    Bundle username;
    Button finishButton, openButton;
    StudentDBHelper studentDBHelper;
    int score;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hard_game3);
        getSupportActionBar().hide();

        username = getIntent().getExtras();
        studentDBHelper = new StudentDBHelper(this);

        score = username.getInt("score");

        relativeLayout = findViewById(R.id.hardGame3);

        droid = findViewById(R.id.droidImage);
        txtDest = findViewById(R.id.txtGoal);
        red1 = findViewById(R.id.red1);
        red2 = findViewById(R.id.red2);
        red3 = findViewById(R.id.red3);
        red4 = findViewById(R.id.red4);
        red5 = findViewById(R.id.red5);
        red6 = findViewById(R.id.red6);
        red7 = findViewById(R.id.red7);
        red8 = findViewById(R.id.red8);
        red9 = findViewById(R.id.red9);
        redDoor = findViewById(R.id.redDoor);


        txtDest = findViewById(R.id.txtGoal1);
        txtDest2 = findViewById(R.id.txtGoal2);
        txtFinalDest = findViewById(R.id.txtFinalGoal);

        droid.setOnTouchListener(onTouchListener);

        finishButton = findViewById(R.id.finishGameButton);
        finishButton.setClickable(false);
        finishButton.setBackgroundColor(Color.DKGRAY);
        openButton = findViewById(R.id.openVaultBut);
        openButton.setClickable(false);
        openButton.setBackgroundColor(Color.DKGRAY);

        red1.setOnDragListener(dragListener);
        red2.setOnDragListener(dragListener);
        red3.setOnDragListener(dragListener);
        red4.setOnDragListener(dragListener);
        red5.setOnDragListener(dragListener);
        red6.setOnDragListener(dragListener);
        red7.setOnDragListener(dragListener);
        red8.setOnDragListener(dragListener);
        red9.setOnDragListener(dragListener);
        redDoor.setOnDragListener(dragListener);

        txtDest.setOnDragListener(dragListenerGoal1);
        txtDest2.setOnDragListener(dragListenerGoal2);
        txtFinalDest.setOnDragListener(dragListenerFinish);
    }

    View.OnTouchListener onTouchListener = new View.OnTouchListener() {
        @SuppressLint("ClickableViewAccessibility")
        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            ClipData data = ClipData.newPlainText("", "");
            View.DragShadowBuilder myShadowBuilder = new View.DragShadowBuilder(view);
            view.startDragAndDrop(data, myShadowBuilder, view, 0);
            return true;
        }
    };

    View.OnDragListener dragListener = new View.OnDragListener() {
        @RequiresApi(api = Build.VERSION_CODES.N)
        @SuppressLint("SetTextI18n")
        @Override
        public boolean onDrag(View view, DragEvent dragEvent) {//Sets condition for red bars to react when they come in contact with droid
            int dragE = dragEvent.getAction();
            final View v = (View) dragEvent.getLocalState();

            switch (dragE) {
                case DragEvent.ACTION_DRAG_ENTERED:
                    if (v.getId() == R.id.droidImage) {
                        droid.cancelDragAndDrop();
                        MediaPlayer mediaPlayer = MediaPlayer.create(HardGame3.this, R.raw.wrong);
                        mediaPlayer.start();
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

    View.OnDragListener dragListenerGoal1 = new View.OnDragListener() {
        @RequiresApi(api = Build.VERSION_CODES.N)
        @SuppressLint("SetTextI18n")
        @Override
        public boolean onDrag(View view, DragEvent dragEvent) {//indicates successful first drop
            int dragE = dragEvent.getAction();
            final View v = (View) dragEvent.getLocalState();

            switch (dragE) {
                case DragEvent.ACTION_DRAG_ENTERED:
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    break;
                case DragEvent.ACTION_DROP:
                    if (v.getId() == R.id.droidImage) {
                        txtDest.setTextColor(Color.GREEN);
                        txtDest.setText("Dropped!");
                        txtDest.setBackgroundColor(Color.TRANSPARENT);
                        v.animate()
                                .x(txtDest.getX())
                                .y(txtDest.getY())
                                .setDuration(700)
                                .start();
                        MediaPlayer mediaPlayer = MediaPlayer.create(HardGame3.this, R.raw.correct);
                        mediaPlayer.start();
                    }
                    break;
            }
            return true;
        }
    };

    View.OnDragListener dragListenerGoal2 = new View.OnDragListener() {
        @RequiresApi(api = Build.VERSION_CODES.N)
        @SuppressLint("SetTextI18n")
        @Override
        public boolean onDrag(View view, DragEvent dragEvent) {//successful 2nd drop and enables the open button
            int dragE = dragEvent.getAction();
            final View v = (View) dragEvent.getLocalState();

            switch (dragE) {
                case DragEvent.ACTION_DRAG_ENTERED:
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    break;
                case DragEvent.ACTION_DROP:
                    if (v.getId() == R.id.droidImage && txtDest.getText() == "Dropped!") {
                        txtDest2.setText("Dropped!");
                        txtDest2.setTextColor(Color.GREEN);
                        txtDest2.setBackgroundColor(Color.TRANSPARENT);
                        v.animate()
                                .x(txtDest2.getX())
                                .y(txtDest2.getY())
                                .setDuration(700)
                                .start();
                        MediaPlayer mediaPlayer = MediaPlayer.create(HardGame3.this, R.raw.correct);
                        mediaPlayer.start();
                        openButton.setClickable(true);
                        openButton.setBackgroundColor(Color.GREEN);
                    }
                    break;
            }
            return true;
        }
    };

    View.OnDragListener dragListenerFinish = new View.OnDragListener() {
        @RequiresApi(api = Build.VERSION_CODES.N)
        @SuppressLint("SetTextI18n")
        @Override
        public boolean onDrag(View view, DragEvent dragEvent) {//game completed and score updated and you're returned to the main menu
            int dragE = dragEvent.getAction();
            final View v = (View) dragEvent.getLocalState();

            switch (dragE) {
                case DragEvent.ACTION_DRAG_ENTERED:
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    break;
                case DragEvent.ACTION_DROP:
                    if (v.getId() == R.id.droidImage) {
                        txtFinalDest.setText("Good Job!");
                        txtFinalDest.setTextColor(Color.GREEN);
                        txtFinalDest.setBackgroundColor(Color.TRANSPARENT);
                        v.animate()
                                .x(txtFinalDest.getX())
                                .y(txtFinalDest.getY())
                                .setDuration(700)
                                .start();
                        MediaPlayer mediaPlayer = MediaPlayer.create(HardGame3.this, R.raw.correct);
                        mediaPlayer.start();
                        finishButton.setClickable(true);
                        finishButton.setBackgroundColor(Color.LTGRAY);
                        score++;
                        studentDBHelper.setStudentScore(username.getString("user"), score);
                    }
                    break;
            }
            return true;
        }
    };

    public void openVault(View view) {
        relativeLayout.removeView(redDoor);
    }

    public void returnToMenu(View view) {
        Intent myIntent = new Intent(this, LoginSuccessful.class);
        myIntent.putExtra("user", username.getString("user"));
        myIntent.putExtra("score", score);
        startActivity(myIntent);
    }
}