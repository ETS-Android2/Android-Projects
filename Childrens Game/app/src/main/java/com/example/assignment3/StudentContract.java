package com.example.assignment3;

import android.provider.BaseColumns;

public class StudentContract {
    public static final class StudentEntry implements BaseColumns{
        public static final String TABLE_NAME = "studentList";
        public static final String COLUMN_FNAME = "firstName";
        public static final String COLUMN_LNAME = "lastName";
        public static final String COLUMN_BDAY = "birthDate";
        public static final String COLUMN_EMAIL = "email";
        public static final String COLUMN_PASSWORD = "password";
        public static final String COLUMN_SCORE = "score";
    }

    public static final class ParentEntry implements BaseColumns{
        public static final String TABLE_NAME = "parentList";
        public static final String COLUMN_FNAME = "firstName";
        public static final String COLUMN_LNAME = "lastName";
        public static final String COLUMN_BDAY = "birthDate";
        public static final String COLUMN_EMAIL = "email";
        public static final String COLUMN_PASSWORD = "password";
        public static final String COLUMN_CHILD = "child";
    }
}
