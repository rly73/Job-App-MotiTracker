package com.example.jobappmotitracker.view;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.jobappmotitracker.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Dashboard extends AppCompatActivity {
    TextView InspirationalQuote;
    private LineChart dashChart;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        //Hiding the title bar
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.dashboard);
        InspirationalQuote =(TextView)findViewById(R.id.xml_inspirational_quote);
        new MyTask().execute();
        dashChart = (LineChart)findViewById(R.id.xml_line_graph);


        dashChart.setDragEnabled(false);
        dashChart.setScaleEnabled(false);
        dashChart.getXAxis().setDrawGridLines(false);
        dashChart.getAxisLeft().setDrawGridLines(false);
        dashChart.getAxisRight().setDrawGridLines(false);

        ArrayList<Entry>yValues = new ArrayList<>();

        yValues.add(new Entry(0,60f));
        yValues.add(new Entry(1,20f));
        yValues.add(new Entry(2,40f));
        yValues.add(new Entry(3,60f));
        yValues.add(new Entry(4,65f));

        LineDataSet set1 = new LineDataSet(yValues,"Data Set 1");

        set1.setFillAlpha(110);

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1);

        LineData data = new LineData(dataSets);

        dashChart.setData(data);
    }
    private class MyTask extends AsyncTask<Void, Void, Void> {
    Elements words;
    ArrayList<String> quotes = new ArrayList<>();
    String quote;
        @Override
        protected Void doInBackground(Void... params) {

            Document doc;
            try {
                doc = Jsoup.connect("https://content.wisestep.com/motivational-inspirational-quotes-job-seekers/").get();
                words = doc.select("p:nth-of-type(2)");
                quote = words.eq(1).text();
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
