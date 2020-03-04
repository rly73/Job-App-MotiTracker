package com.example.jobappmotitracker.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;

import com.example.jobappmotitracker.database.model.JobApplication;

import java.util.ArrayList;
import java.util.List;


public class DBManager {
    private DatabaseHelper dbHelper;
    private Context context;
    private SQLiteDatabase database;

    public DBManager(Context c){
        this.context = c;
    }

    public DBManager open() {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public void insertJobApp(String company_name, String job_position, String date_applied, String location, Integer pay, String notes){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.COMPANY_NAME, company_name);
        contentValues.put(DatabaseHelper.JOB_POSITION, job_position);
        contentValues.put(DatabaseHelper.DATE_APPLIED, date_applied);
        contentValues.put(DatabaseHelper.LOCATION, location);
        contentValues.put(DatabaseHelper.PAY, pay);
        contentValues.put(DatabaseHelper.NOTES, notes);

        database.insert(DatabaseHelper.TABLE_NAME, null, contentValues);
    }

    public List<JobApplication> getAllJobApp(){

        List<JobApplication> apps = new ArrayList<>();
        String[] columns = new String[] { DatabaseHelper._ID, DatabaseHelper.COMPANY_NAME, DatabaseHelper.DATE_APPLIED, DatabaseHelper.LOCATION,
                        DatabaseHelper.PAY, DatabaseHelper.NOTES};
        Cursor cursor = database.query(DatabaseHelper.TABLE_NAME, columns, null, null, null, null, null);
        if(cursor.moveToFirst()){
            do{
                JobApplication app = new JobApplication();
                app.setCompanyName(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COMPANY_NAME)));
                app.setDateApplied(cursor.getString(cursor.getColumnIndex(DatabaseHelper.JOB_POSITION)));
                app.setJobPosition(cursor.getString(cursor.getColumnIndex(DatabaseHelper.DATE_APPLIED)));
                app.setCity(cursor.getString(cursor.getColumnIndex(DatabaseHelper.LOCATION)));
                app.setPay(cursor.getDouble(cursor.getColumnIndex(DatabaseHelper.PAY)));
                app.setNotes(cursor.getString(cursor.getColumnIndex(DatabaseHelper.NOTES)));
            }while(cursor.moveToNext());
        }

        return apps;
    }

    public int update(long _id,String company_name, String job_position, String date_applied, Integer pay, String location, String notes){

        ContentValues contentValues = new ContentValues();

        contentValues.put(DatabaseHelper.COMPANY_NAME, company_name);
        contentValues.put(DatabaseHelper.JOB_POSITION, job_position);
        contentValues.put(DatabaseHelper.DATE_APPLIED, date_applied);
        contentValues.put(DatabaseHelper.LOCATION, location);
        contentValues.put(DatabaseHelper.PAY, pay);
        contentValues.put(DatabaseHelper.NOTES, notes);

        int i = database.update(DatabaseHelper.TABLE_NAME, contentValues, DatabaseHelper._ID + " = " + _id, null);
        return i;
    }

    public void delete(long _id){
        database.delete(DatabaseHelper.TABLE_NAME, DatabaseHelper._ID + " = " + _id,null);
    }



}
