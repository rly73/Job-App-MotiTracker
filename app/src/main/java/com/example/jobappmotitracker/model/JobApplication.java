package com.example.jobappmotitracker.model;

public class JobApplication {
    private String company_name,job_position,date_applied,notes,city,state,location,status;
    private double pay;

    public JobApplication(){

    }
    public JobApplication(String company_name, String job_position, String date_applied, double pay, String notes,String city, String state,String status) {
        this.company_name = company_name;
        this.job_position = job_position;
        this.date_applied = date_applied;
        this.pay = pay;
        this.notes = notes;
        this.city = city;
        this.state = state;
        this.location = this.city + ", " + this.state;
        this.status = status;
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
    public void setCity(String city){ this.city = city; }
    public void setState(String state){ this.state = state;}
    public void setLocation(String location){this.location = location;}
    public void setNotes(String notes){
        this.notes = notes;
    }
    public void setStatus(String status){ this.status = status;}
    public String getCompanyName(){
        return this.company_name;
    }
    public String getJobPosition(){
        return this.job_position;
    }
    public String getDateApplied(){ return this.date_applied; }
    public double getPay(){ return this.pay; }
    public String getNotes(){ return this.notes; }
    public String getCity(){ return this.city;}
    public String getState(){ return this.state;}
    public String getLocation(){return this.location;}
    public String getStatus(){return this.status;}
}
