package com.akura.controller;


import java.math.BigDecimal;

public class ReportEntityStudentResults {
    String number;
    String fullname;
    BigDecimal result;

    public ReportEntityStudentResults(String number, String fullname,BigDecimal result ){
        this.number=number;
        this.fullname=fullname;
        this.result=result;

    }

    public String getNumber(){
        return this.number;

    }

    public String getFullname(){
        return this.fullname;

    }

    public BigDecimal getResult(){
        return this.result;

    }



}
