package com.example.jobappmotitracker.view;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.jobappmotitracker.R;
import com.example.jobappmotitracker.database.DBManager;
import com.example.jobappmotitracker.model.JobApplication;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class AddJobAppFragment extends Fragment {
    private DBManager dbManager;
    EditText companyName;
    EditText jobPosition;
    EditText dateApplied;
    EditText pay;
    EditText city;
    EditText state;
    Spinner status;
    EditText notes;
    Button button;

    TextView InspirationalQuote;
    public AddJobAppFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_add_job_app, container, false);
        dbManager = new DBManager(getActivity());
        dbManager.open();
        InspirationalQuote =(TextView)v.findViewById(R.id.add_job_quote);
        new MyTask().execute();
        companyName = (EditText)v.findViewById(R.id.input_company_name);
        jobPosition = (EditText)v.findViewById(R.id.input_job_position);
        dateApplied = (EditText)v.findViewById(R.id.input_date_applied);
        pay = (EditText)v.findViewById(R.id.input_pay);
        city = (EditText)v.findViewById(R.id.input_city);
        notes = (EditText)v.findViewById(R.id.input_notes);
        button = (Button)v.findViewById(R.id.create_button);
        state = (EditText)v.findViewById(R.id.input_state);
        status = (Spinner)v.findViewById(R.id.input_status);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),R.array.status_items, android.R.layout.simple_spinner_dropdown_item);
        status.setAdapter(adapter);


        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                JobApplication addApp = new JobApplication();
                int val = Integer.parseInt(pay.getText().toString());
                addApp.setCompanyName(companyName.getText().toString());
                addApp.setJobPosition(jobPosition.getText().toString());
                addApp.setDateApplied(dateApplied.getText().toString());
                addApp.setPay(val);
                addApp.setCity(city.getText().toString());
                addApp.setState(state.getText().toString());
                addApp.setStatus(status.getSelectedItem().toString());
                addApp.setNotes(notes.getText().toString());
                dbManager.insertJobApp(addApp.getCompanyName(),addApp.getJobPosition(),addApp.getDateApplied(),addApp.getCity()+", "+ addApp.getState(), (int) addApp.getPay(),addApp.getNotes(),addApp.getStatus());
            }
        });

        return v;
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
