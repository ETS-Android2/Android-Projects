package com.example.assignment3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.assignment3.StudentContract.ParentEntry;
import com.example.assignment3.StudentContract.StudentEntry;

import static android.provider.BaseColumns._ID;
import static com.example.assignment3.StudentContract.ParentEntry.COLUMN_CHILD;
import static com.example.assignment3.StudentContract.StudentEntry.COLUMN_EMAIL;
import static com.example.assignment3.StudentContract.StudentEntry.COLUMN_SCORE;

public class StudentDBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "studentlist.db";
    public static final int DATABASE_VERSION = 1;

    private SQLiteDatabase db;

    public StudentDBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {//Creating Student and Parent tables
        this.db =db;

        final String SQL_CREATE_STUDENT_TABLE = "CREATE TABLE " + StudentEntry.TABLE_NAME + " ( " +
                StudentEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                StudentEntry.COLUMN_FNAME + " TEXT, " +
                StudentEntry.COLUMN_LNAME + " TEXT, " +
                StudentEntry.COLUMN_BDAY + " INTEGER, " +
                StudentEntry.COLUMN_EMAIL + " TEXT, " +
                StudentEntry.COLUMN_PASSWORD + " TEXT, " +
                COLUMN_SCORE + " INTEGER " +
                ")";

        db.execSQL(SQL_CREATE_STUDENT_TABLE);

        final String SQL_CREATE_PARENT_TABLE = "CREATE TABLE " + ParentEntry.TABLE_NAME + " ( " +
                ParentEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ParentEntry.COLUMN_FNAME + " TEXT, " +
                ParentEntry.COLUMN_LNAME + " TEXT, " +
                ParentEntry.COLUMN_BDAY + " INTEGER, " +
                ParentEntry.COLUMN_EMAIL + " TEXT, " +
                ParentEntry.COLUMN_PASSWORD + " TEXT, " +
                COLUMN_CHILD + " INTEGER, " +
                " FOREIGN KEY ("+COLUMN_CHILD+") REFERENCES "+StudentEntry.TABLE_NAME+" ("+_ID+"));";
        db.execSQL(SQL_CREATE_PARENT_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + StudentEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + ParentEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public void addStudent(Student student){
        ContentValues cv = new ContentValues();
        cv.put(StudentEntry.COLUMN_FNAME, student.getFname());
        cv.put(StudentEntry.COLUMN_LNAME, student.getLname());
        cv.put(StudentEntry.COLUMN_BDAY, student.getBday());
        cv.put(StudentEntry.COLUMN_EMAIL, student.getEmail());
        cv.put(StudentEntry.COLUMN_PASSWORD, student.getPassword());
        db = this.getReadableDatabase();
        db.insert(StudentEntry.TABLE_NAME, null, cv);
    }

    public void addParent(Parent parent){
        ContentValues cv = new ContentValues();
        cv.put(ParentEntry.COLUMN_FNAME, parent.getFname());
        cv.put(ParentEntry.COLUMN_LNAME, parent.getLname());
        cv.put(ParentEntry.COLUMN_BDAY, parent.getBday());
        cv.put(ParentEntry.COLUMN_EMAIL, parent.getEmail());
        cv.put(ParentEntry.COLUMN_PASSWORD, parent.getPassword());
        db = this.getReadableDatabase();
        db.insert(ParentEntry.TABLE_NAME, null, cv);
    }

    public boolean LogIn(String user, String pass){//Checks passed strings to see if they exist and match to data and the database and pass true or false back
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT email, password FROM " + StudentEntry.TABLE_NAME + " WHERE email = '"+ user + "' AND password ='"+ pass + "'", null);
        if (c.getCount()>0){
            return true;
        }
        else{
            c = db.rawQuery("SELECT email, password FROM " + ParentEntry.TABLE_NAME + " WHERE email = '"+ user + "' AND password ='"+ pass + "'", null);
            if(c.getCount()<=0){
                return false;
            }else{
                return true;
            }
        }

    }

    public void setStudentScore(String user, int s){
        db = getReadableDatabase();
        String query = " update " + StudentEntry.TABLE_NAME + " SET " + COLUMN_SCORE + " = '" + s + "'  WHERE " + COLUMN_EMAIL + " = '" + user+"'";
    }

    public void getStudentScore(String user){
        int s;
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT score FROM " + StudentEntry.TABLE_NAME + " WHERE email = '"+ user, null);
    }
}
