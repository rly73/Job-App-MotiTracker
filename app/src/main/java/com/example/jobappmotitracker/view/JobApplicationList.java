package com.example.jobappmotitracker.view;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jobappmotitracker.R;
import com.example.jobappmotitracker.database.DBManager;
import com.example.jobappmotitracker.model.JobApplication;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class JobApplicationList extends AppCompatActivity {
    private List<JobApplication> appList = new ArrayList<>();
    private RecyclerView recyclerView;
    private TextView noAppView;
    TextView InspirationalQuote;
    private JobAdapter adapter;
    private DBManager dbManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.job_app_list);

        recyclerView = findViewById(R.id.job_app_recycler_list);
        InspirationalQuote =(TextView)findViewById(R.id.list_quote);
        new MyTask().execute();
        dbManager = new DBManager(this);
        dbManager.open();

        appList.addAll(dbManager.getAllJobApp());


        adapter = new JobAdapter(this,appList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
    private class MyTask extends AsyncTask<Void, Void, Void> {
        Elements words;
        String quote;
        @Override
        protected Void doInBackground(Void... params) {

            Document doc;
            try {
                Random number = new Random();
                doc = Jsoup.connect("https://content.wisestep.com/motivational-inspirational-quotes-job-seekers/").get();
                words = doc.select("p:nth-of-type("+ number.nextInt(11) +")");
                System.out.println("Text== " + words);
                quote = words.eq(number.nextInt(11)).text();
                String arr[] = quote.split(" ",2);
                quote = arr[1];
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void Avoid) {
            InspirationalQuote.setText(quote);
        }
    }

}
