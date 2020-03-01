package com.example.jobappmotitracker.view;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
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

    private DBManager dbManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.job_app_list);

        recyclerView = findViewById(R.id.job_app_recycler_list);

        dbManager = new DBManager(this);
        dbManager.open();

        JobApplication var1 = new JobApplication("Costco","cashier","10/19/1996",10,"notes");
        JobApplication var2 = new JobApplication("Walmart","cashier","10/19/1996",10,"notes");
        JobApplication var3 = new JobApplication("Target","cashier","10/19/1996",10,"notes");
        JobApplication var4 = new JobApplication("","cashier","10/19/1996",10,"notes");

        dbManager.insertJobApp(var1.getCompanyName(),var1.getJobPosition(),var1.getDateApplied(),100,var1.getNotes());
        dbManager.insertJobApp(var2.getCompanyName(),var2.getJobPosition(),var2.getDateApplied(),100,var2.getNotes());
        dbManager.insertJobApp(var3.getCompanyName(),var3.getJobPosition(),var3.getDateApplied(),100,var3.getNotes());
        dbManager.insertJobApp(var4.getCompanyName(),var4.getJobPosition(),var4.getDateApplied(),100,var4.getNotes());
        appList.addAll(dbManager.getJobApp());
        
    }
}
