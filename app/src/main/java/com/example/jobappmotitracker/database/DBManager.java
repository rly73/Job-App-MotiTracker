package com.example.jobappmotitracker.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


public class DBManager {
    private DatabaseHelper dbHelper;
    private Context context;
    private SQLiteDatabase database;

    public DBManager(Context c){
        context = c;
    }

    public DBManager open() {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public void insertJobApp(String company_name, String job_position, String date_applied, Integer pay, String notes){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.COMPANY_NAME, company_name);
        contentValues.put(DatabaseHelper.JOB_POSITION, job_position);
        contentValues.put(DatabaseHelper.DATE_APPLIED, date_applied);
        contentValues.put(DatabaseHelper.PAY, pay);
        contentValues.put(DatabaseHelper.NOTES, notes);

        database.insert(DatabaseHelper.TABLE_NAME, null, contentValues);
    }

    public Cursor getJobApp(){
        String[] columns = new String[] { DatabaseHelper._ID, DatabaseHelper.COMPANY_NAME, DatabaseHelper.DATE_APPLIED,
                        DatabaseHelper.PAY, DatabaseHelper.NOTES};
        Cursor cursor = database.query(DatabaseHelper.TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public int update(long _id,String company_name, String job_position, String date_applied, Integer pay, String notes){

        ContentValues contentValues = new ContentValues();

        contentValues.put(DatabaseHelper.COMPANY_NAME, company_name);
        contentValues.put(DatabaseHelper.JOB_POSITION, job_position);
        contentValues.put(DatabaseHelper.DATE_APPLIED, date_applied);
        contentValues.put(DatabaseHelper.PAY, pay);
        contentValues.put(DatabaseHelper.NOTES, notes);

        int i = database.update(DatabaseHelper.TABLE_NAME, contentValues, DatabaseHelper._ID + " = " + _id, null);
        return i;
    }

    public void delete(long _id){
        database.delete(DatabaseHelper.TABLE_NAME, DatabaseHelper._ID + " = " + _id,null);
    }



}
