package com.example.jobappmotitracker.view;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.jobappmotitracker.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class Dashboard extends AppCompatActivity {
    TextView InspirationalQuote;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);
        InspirationalQuote =(TextView)findViewById(R.id.xml_inspirational_quote);
        new MyTask().execute();
    }
    private class MyTask extends AsyncTask<Void, Void, Void> {
    String words;
        @Override
        protected Void doInBackground(Void... params) {

            Document doc;
            try {
                doc = Jsoup.connect("https://content.wisestep.com/motivational-inspirational-quotes-job-seekers/").get();
                words = doc.outerHtml();
                System.out.print(words);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void Avoid) {
            InspirationalQuote.setText(words);
        }
    }
}
