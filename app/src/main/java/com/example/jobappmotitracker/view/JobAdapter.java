package com.example.jobappmotitracker.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jobappmotitracker.R;
import com.example.jobappmotitracker.database.model.JobApplication;

import java.util.List;

public class JobAdapter extends RecyclerView.Adapter<JobAdapter.MyViewHolder> {

    private Context context;
    private List<JobApplication> appList;

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public final TextView company_name;
        public final TextView job_position;
        public final TextView date_applied;
        public final TextView pay;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            company_name = itemView.findViewById(R.id.text_company_name);
            job_position = itemView.findViewById(R.id.text_job_position);
            date_applied = itemView.findViewById(R.id.text_date_applied);
            pay = itemView.findViewById(R.id.text_pay);
        }
    }

    public JobAdapter(Context context, List<JobApplication> appList){
        this.context = context;
        this.appList = appList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.job_application,parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        JobApplication app = appList.get(position);
        holder.company_name.setText(app.getCompanyName());
        holder.job_position.setText(app.getJobPosition());
        holder.date_applied.setText(app.getDateApplied());
        holder.pay.setText((int) app.getPay());
    }

    @Override
    public int getItemCount() {

        return appList.size();
    }

}
