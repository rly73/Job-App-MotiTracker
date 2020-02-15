package com.example.jobappmotitracker;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    public static final String DATABASE_NAME = "jobApplications.db";
    public static final String TABLE_NAME = "JOBAPPLICATIONS";
    public static final String _ID = "ID";
    public static final String COMPANY_NAME = "COMPANY_NAME";
    public static final String JOB_POSITION = "JOB_POSITION";
    public static final String DATE_APPLIED = "DATE_APPLIED";
    public static final String PAY = "PAY";
    public static final String NOTES = "NOTES";

    private static final String CREATE_TABLE = "create table " + TABLE_NAME + "(" + _ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COMPANY_NAME + " TEXT NOT NULL, "
            + JOB_POSITION + " TEXT," + DATE_APPLIED + " TEXT NOT NULL, " + PAY
            + " INTEGER, " + NOTES + " TEXT);";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

}
