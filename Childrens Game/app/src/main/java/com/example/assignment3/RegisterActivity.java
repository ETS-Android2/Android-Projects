package com.example.assignment3;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import com.example.assignment3.StudentDBHelper.*;

public class RegisterActivity extends AppCompatActivity {
    EditText first;
    EditText last;
    EditText bDate;
    EditText email;
    EditText pass;
    Intent myIntent;
    Button reg;
    RadioGroup rbGroup;
    RadioButton studentBut, parentBut;
    String emailPattern;
    StudentDBHelper studentDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();
        first = (EditText) findViewById(R.id.fnameEditText);
        last = (EditText) findViewById(R.id.lnameEditText);
        bDate = (EditText) findViewById(R.id.bdayTextDate);
        email = (EditText) findViewById(R.id.emailEditText);
        pass = (EditText) findViewById(R.id.passwdEditText);
        rbGroup = findViewById(R.id.rbGroup);
        studentBut = findViewById(R.id.studentRadioButton);
        parentBut = findViewById(R.id.parentRadioButton);
        Button reg = (Button) findViewById(R.id.createAcctButton);
        studentDBHelper = new StudentDBHelper(this);

        myIntent= new Intent(this, MainActivity.class);


        reg.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                emailPattern = email.getText().toString();
                //Checks and make sures no fields are empty
                if(first.getText().toString().isEmpty() || last.getText().toString().isEmpty() || bDate.getText().toString().isEmpty() || email.getText().toString().isEmpty() || pass.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Field cannot be empty!",Toast.LENGTH_LONG).show();
                }
                else if(first.getText().toString().length() < 3 || first.getText().toString().length() > 30){ //verifies length of first name
                    Toast.makeText(getApplicationContext(), "Not valid first name!",Toast.LENGTH_LONG).show();
                }
                else if(last.getText().toString().length() < 3 || last.getText().toString().length() > 30){
                    Toast.makeText(getApplicationContext(), "Not valid last name!",Toast.LENGTH_LONG).show();
                }
                else if(!isEmailValid(emailPattern)){
                    Toast.makeText(getApplicationContext(), "Not valid email!",Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(getApplicationContext(),"Account Created",Toast.LENGTH_LONG).show();
                    startActivity(myIntent);
                }
                if(studentBut.getId() == rbGroup.getCheckedRadioButtonId()){
                    addToStudentTable();
                }else{
                    addToParentTable();
                }
            }
        });
    }
    public static boolean isEmailValid(CharSequence email) {//function to check text input in email field and confirm it is a valid email
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private void addToStudentTable() {
        Student s = new Student(first.getText().toString(), last.getText().toString(), Integer.parseInt(bDate.getText().toString()), email.getText().toString(), pass.getText().toString(), 0);
        studentDBHelper.addStudent(s);
    }

    private void addToParentTable(){
        Parent p = new Parent(first.getText().toString(), last.getText().toString(), Integer.parseInt(bDate.getText().toString()), email.getText().toString(), pass.getText().toString());
        studentDBHelper.addParent(p);
    }
}