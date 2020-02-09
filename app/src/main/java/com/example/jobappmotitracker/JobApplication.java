package com.example.jobappmotitracker;

import java.util.Date;

public class JobApplication {
    String companyName;
    String jobPosition;
    String notes;
    Date dateApplied;
    double pay;

    public String companyName(){
        return this.companyName;
    }

    public void setCompanyName(String name){
        this.companyName = name;
    }

    public String getJobPosition(){
        return this.jobPosition;
    }

    public void setJobPosition(String position){
        this.jobPosition = position;
    }

    public Date getDateApplied(){
        return this.dateApplied;
    }

    public void setDateApplied(Date appliedDate){
        this.dateApplied = appliedDate;
    }

    public String notes(){
        return this.notes;
    }

    public void setNotes(String text){
        this.notes = text;
    }

    public double getPay(){
        return this.pay;
    }

    public void setPay(double payGrade){
        this.pay = payGrade;
    }
}
