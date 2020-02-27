package com.example.jobappmotitracker.database.model;

public class JobApplication {
    private String company_name,job_position,date_applied,notes;
    private double pay;

    public JobApplication(String company_name, String job_position, String date_applied, double pay, String notes) {
        this.company_name = company_name;
        this.job_position = job_position;
        this.date_applied = date_applied;
        this.pay = pay;
        this.notes = notes;
    }

    public void setCompanyName(String company_name){
        this.company_name = company_name;
    }
    public void setJobPosition(String job_position){
        this.job_position = job_position;
    }
    public  void setDateApplied(String date_applied){
        this.date_applied = date_applied;
    }
    public void setPay(double pay){
        this.pay = pay;
    }
    public void setNotes(String notes){
        this.notes = notes;
    }
    public String getCompanyName(){
        return this.company_name;
    }
    public String getJobPosition(){
        return this.job_position;
    }
    public String getDateApplied(){
        return this.date_applied;
    }
    public double getPay(){
        return this.pay;
    }
    public String getNotes(){
        return this.notes;
    }
}
