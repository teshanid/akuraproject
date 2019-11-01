package com.akura.controller;



public class ReportEntityStudentList {
    String number;
    String fullname;

    public ReportEntityStudentList(String number, String fullname ){
        this.number=number; this.fullname=fullname;

    }

    public String getNumber(){
        return this.number;

    }

    public String getFullname(){
        return this.fullname;

    }



}
