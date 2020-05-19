package com.example.jobappmotitracker.view;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.jobappmotitracker.R;
import com.example.jobappmotitracker.database.DBManager;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class Dashboard extends AppCompatActivity {
    float x1, x2, y1, y2;
    TextView InspirationalQuote;
    TextView TotalApplicatons;
    TextView TotalResponses;
    TextView TotalInterviews;
    TextView TotalRejections;
    TextView TotalOffers;
    TextView TotalNoAnswers;
    private LineChart dashChart;
    private DBManager db;
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
        TotalApplicatons = (TextView)findViewById(R.id.xml_total_applications);
        TotalResponses = (TextView)findViewById(R.id.xml_total_responses);
        TotalInterviews = (TextView)findViewById(R.id.xml_total_interviews);
        TotalRejections = (TextView)findViewById(R.id.xml_total_offers);
        TotalOffers = (TextView)findViewById(R.id.xml_total_offers);
        TotalNoAnswers = (TextView)findViewById(R.id.xml_no_answers);

        db = new DBManager(this);
        db.open();

        //Get count of Job Applications
        System.out.println("Number of existing applications = " + db.getAllJobApp().size());
        TotalApplicatons.setText(( Integer.toString(db.getAllJobApp().size())));
        //Get count of Responded
        System.out.println("Responded = " + db.getApplicationStatusCount("Responded"));
        TotalResponses.setText(db.getApplicationStatusCount("Responded"));
        //Get count of Interviewed
        System.out.println("Interviewed = " + db.getApplicationStatusCount("Interview"));
        TotalInterviews.setText(db.getApplicationStatusCount("Interview"));
        //Get count of Rejected
        System.out.println("Rejected = " + db.getApplicationStatusCount("Rejected"));
        TotalRejections.setText(db.getApplicationStatusCount("Rejected"));
        //Get count of No response
        System.out.println("No Response = " + db.getApplicationStatusCount("No Response"));
        TotalOffers.setText(db.getApplicationStatusCount("No Response"));
        //Get count of Offered
        System.out.println("Offered = " + db.getApplicationStatusCount("Offered"));
        TotalNoAnswers.setText(db.getApplicationStatusCount("Offered"));

        new MyTask().execute();
        dashChart = (LineChart)findViewById(R.id.xml_line_graph);


        dashChart.setDragEnabled(false);
        dashChart.setScaleEnabled(false);
        dashChart.getXAxis().setDrawGridLines(false);
        dashChart.getAxisLeft().setDrawGridLines(false);
        dashChart.getAxisRight().setDrawGridLines(false);

        dashChart.getAxisRight().setEnabled(false);
        ArrayList<Entry>yValues = new ArrayList<>();

        Calendar currentTime = Calendar.getInstance();
        System.out.println("Current time is "+ currentTime.getTime() );

        final ArrayList<String> xAxisLabel = new ArrayList<>();
        xAxisLabel.add("Sun");
        xAxisLabel.add("Mon");
        xAxisLabel.add("Tue");
        xAxisLabel.add("Wed");
        xAxisLabel.add("Thu");
        xAxisLabel.add("Fri");
        xAxisLabel.add("Sat");

        XAxis xAxis = dashChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        ArrayList<ILineDataSet>dataSets = new ArrayList<>();
        List<String> xAxisValues = new ArrayList<>(Arrays.asList("MON","TUE","WED","THU","FRI","SAT","SUN"));



        yValues.add(new Entry(0,20f));
        yValues.add(new Entry(1,40f));
        yValues.add(new Entry(2,60f));
        yValues.add(new Entry(3,65f));
        yValues.add(new Entry(4,60f));
        yValues.add(new Entry(5,20f));
        yValues.add(new Entry(6,40f));



        LineDataSet set1 = new LineDataSet(yValues,"Data Set 1");

        set1.setFillAlpha(110);


        dataSets.add(set1);
        xAxis.setValueFormatter(new com.github.mikephil.charting.formatter.IndexAxisValueFormatter(xAxisValues));
        LineData data = new LineData(dataSets);

        dashChart.setData(data);
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

    public boolean onTouchEvent(MotionEvent touchevent){
        switch(touchevent.getAction()){
            case MotionEvent.ACTION_DOWN:
                x1 = touchevent.getX();
                y1 = touchevent.getY();
                break;
            case MotionEvent.ACTION_UP:
                x2 = touchevent.getX();
                y2 = touchevent.getY();
                if(x1 > x2){
                    Intent i = new Intent(Dashboard.this,JobApplicationList.class);
                    startActivity(i);
                }
                if(x1 < x2){
                    Intent i = new Intent(Dashboard.this,FragmentActivityHolder.class);
                    startActivity(i);
                }

                break;
        }
        return false;
    }
}
