package com.akura.controller;


import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/ui")
public class UiController {



    @RequestMapping("/config")
    public ModelAndView config(){
        ModelAndView model = new ModelAndView();
        model.setViewName("config.html");
        return model;
    }

    @RequestMapping("/login")
    public ModelAndView login(){
        ModelAndView model = new ModelAndView();
        model.setViewName("login.html");
        return model;
    }


    @RequestMapping("/mainwindow")
    public ModelAndView mainwindow(@CookieValue(value="username") String username, @CookieValue(value="password") String password){
        return getModelView("mainwindow.html",username,password);
    }


    @RequestMapping("/home")
    public ModelAndView home(@CookieValue(value="username") String username, @CookieValue(value="password") String password){
        return getModelView("home.html",username,password);
    }


    @RequestMapping("/employee")
    public ModelAndView employee(@CookieValue(value="username") String username, @CookieValue(value="password") String password){
        return getModelView("employee.html",username,password);
    }


    @RequestMapping("/user")
    public ModelAndView user(@CookieValue(value="username") String username, @CookieValue(value="password") String password){
        return getModelView("user.html",username,password);
    }

    @RequestMapping("/previlage")
    public ModelAndView previlage(@CookieValue(value="username") String username, @CookieValue(value="password") String password){
        return getModelView("previlage.html",username,password);

    }


    @RequestMapping("/changepassword")
    public ModelAndView changepassword(@CookieValue(value="username") String username, @CookieValue(value="password") String password){
        return getModelView("changepassword.html",username,password);
    }


    @RequestMapping("/designation")
    public ModelAndView designation(@CookieValue(value="username") String username, @CookieValue(value="password") String password){
        return getModelView("designation.html",username,password);
    }

    @RequestMapping("/clas")
    public ModelAndView clas(@CookieValue(value="username") String username, @CookieValue(value="password") String password){
        return getModelView("clas.html",username,password);
    }

    @RequestMapping("/subject")
    public ModelAndView subject(@CookieValue(value="username") String username, @CookieValue(value="password") String password){
        return getModelView("subject.html",username,password);
    }

    @RequestMapping("/grade")
    public ModelAndView grade(@CookieValue(value="username") String username, @CookieValue(value="password") String password) {
        return getModelView("grade.html", username, password);
    }
    @RequestMapping("/student")
    public ModelAndView student(@CookieValue(value="username") String username, @CookieValue(value="password") String password) {
        return getModelView("student.html", username, password);
    }
    @RequestMapping("/studentstatus")
    public ModelAndView studentstatus(@CookieValue(value="username") String username, @CookieValue(value="password") String password) {
        return getModelView("studentstatus.html", username, password);
    }
    @RequestMapping("/guardiantype")
    public ModelAndView guardiantype(@CookieValue(value="username") String username, @CookieValue(value="password") String password) {
        return getModelView("guardiantype.html", username, password);
    }
    @RequestMapping("/semesterexam")
    public ModelAndView semesterexam(@CookieValue(value="username") String username, @CookieValue(value="password") String password) {
        return getModelView("semesterexam.html", username, password);
    }
    @RequestMapping("/placementtest")
    public ModelAndView placementtest(@CookieValue(value="username") String username, @CookieValue(value="password") String password) {
        return getModelView("placementtest.html", username, password);
    }

    @RequestMapping("/ptestpayment")
    public ModelAndView ptestpayment(@CookieValue(value="username") String username, @CookieValue(value="password") String password) {
        return getModelView("ptestpayment.html", username, password);
    }
    @RequestMapping("/studentclas")
    public ModelAndView studentclas(@CookieValue(value="username") String username, @CookieValue(value="password") String password) {
        return getModelView("studentclas.html", username, password);
    }
    @RequestMapping("/claspayment")
    public ModelAndView claspayment(@CookieValue(value="username") String username, @CookieValue(value="password") String password) {
        return getModelView("claspayment.html", username, password);
    }
    @RequestMapping("/semesterexampayment")
    public ModelAndView semesterexampayment(@CookieValue(value="username") String username, @CookieValue(value="password") String password) {
        return getModelView("semesterexampayment.html", username, password);
    }
    @RequestMapping("/result")
    public ModelAndView result(@CookieValue(value="username") String username, @CookieValue(value="password") String password) {
        return getModelView("result.html", username, password);
    }

    @RequestMapping("/payment")
    public ModelAndView payment(@CookieValue(value="username") String username, @CookieValue(value="password") String password) {
        return getModelView("payment.html", username, password);
    }
    @RequestMapping("/report")
    public ModelAndView report(@CookieValue(value="username") String username, @CookieValue(value="password") String password) {
        return getModelView("report.html", username, password);
    }



    public ModelAndView getModelView(String page,String username, String password){

        ModelAndView model = new ModelAndView();

        if(AuthProvider.isUser(username,password)) {

            model.setViewName(page);
        }
        else {
            model.setViewName("noprivilage.html");

        }
        return model;

    }



}