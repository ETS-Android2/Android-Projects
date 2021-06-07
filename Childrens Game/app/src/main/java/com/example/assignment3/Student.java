package com.example.assignment3;

import android.os.Parcel;
import android.os.Parcelable;

public class Student implements Parcelable {
    private String fname;
    private String lname;
    private int bday;
    private String email;
    private String password;
    private int score;

    public Student(){};

    public Student(String fname, String lname, int bday, String email, String passwd, int score){
        this.fname = fname;
        this.lname = lname;
        this.bday = bday;
        this.email = email;
        this.password = passwd;
        this.score = score;
    }

    protected Student(Parcel in) {
        fname = in.readString();
        lname = in.readString();
        bday = in.readInt();
        email = in.readString();
        password = in.readString();
    }

    public static final Creator<Student> CREATOR = new Creator<Student>() {
        @Override
        public Student createFromParcel(Parcel in) {
            return new Student(in);
        }

        @Override
        public Student[] newArray(int size) {
            return new Student[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(fname);
        parcel.writeString(lname);
        parcel.writeInt(bday);
        parcel.writeString(email);
        parcel.writeString(password);
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public int getBday() {
        return bday;
    }

    public void setBday(int bday) {
        this.bday = bday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getScore() { return score; }

    public void setScore(int score) { this.score = score; }
}
