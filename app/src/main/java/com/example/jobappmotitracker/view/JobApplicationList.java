package com.example.jobappmotitracker.view;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jobappmotitracker.R;
import com.example.jobappmotitracker.database.DBManager;
import com.example.jobappmotitracker.database.model.JobApplication;

import java.util.ArrayList;
import java.util.List;

public class JobApplicationList extends AppCompatActivity {
    private List<JobApplication> appList = new ArrayList<>();
    private RecyclerView recyclerView;
    private TextView noAppView;
    private JobAdapter adapter;
    private DBManager dbManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.job_app_list);

        recyclerView = findViewById(R.id.job_app_recycler_list);


        JobApplication var1 = new JobApplication("Facebook","Software Developer","Jan 20, 2020", 10, "", "San Francisco","CA");
        appList.add(var1);
        appList.add(var1);
        appList.add(var1);
        appList.add(var1);
        appList.add(var1);
        appList.add(var1);
        appList.add(var1);
        appList.add(var1);
        appList.add(var1);
        appList.add(var1);
        appList.add(var1);
        appList.add(var1);
        appList.add(var1);
        appList.add(var1);
        appList.add(var1);
        appList.add(var1);
        appList.add(var1);
        appList.add(var1);
        appList.add(var1);
        appList.add(var1);
        appList.add(var1);
        appList.add(var1);
        appList.add(var1);
        appList.add(var1);



        adapter = new JobAdapter(this,appList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);




    }
}
