package com.example.assignment3;

import android.os.Parcel;
import android.os.Parcelable;

public class Parent implements Parcelable {
    private String fname;
    private String lname;
    private int bday;
    private String email;
    private String password;

    public Parent(){};

    public Parent(String fname, String lname, int bday, String email, String passwd){
        this.fname = fname;
        this.lname = lname;
        this.bday = bday;
        this.email = email;
        this.password = passwd;

    }

    protected Parent(Parcel in) {
        fname = in.readString();
        lname = in.readString();
        bday = in.readInt();
        email = in.readString();
        password = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(fname);
        dest.writeString(lname);
        dest.writeInt(bday);
        dest.writeString(email);
        dest.writeString(password);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Parent> CREATOR = new Creator<Parent>() {
        @Override
        public Parent createFromParcel(Parcel in) {
            return new Parent(in);
        }

        @Override
        public Parent[] newArray(int size) {
            return new Parent[size];
        }
    };

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
}
