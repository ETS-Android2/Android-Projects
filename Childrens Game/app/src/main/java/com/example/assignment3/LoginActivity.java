package com.example.assignment3;

import android.content.Intent;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    Button btn1;
    EditText user;
    EditText pass;
    Intent myIntent;
    String username;
    StudentDBHelper studentDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        user = (EditText) findViewById(R.id.nameEditText);
        pass = (EditText) findViewById(R.id.passEditText);
        btn1 = (Button) findViewById(R.id.log_in_button);
        studentDBHelper = new StudentDBHelper(this);

    }

    public void LogIn(View view) {
        myIntent= new Intent(this, LoginSuccessful.class);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(user.getText().toString().isEmpty() || pass.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Field cannot be empty!",Toast.LENGTH_LONG).show();
                }
                if(studentDBHelper.LogIn(user.getText().toString(), pass.getText().toString())) {
                    Toast.makeText(getApplicationContext(),"Login Successful",Toast.LENGTH_LONG).show();
                    username = user.getText().toString();
                    myIntent.putExtra("user", username);
                    startActivity(myIntent);
                }else{
                    Toast.makeText(getApplicationContext(), "Invalid Credentials!",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
