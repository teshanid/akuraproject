package com.akura.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/ui/report")
public class ReportUiController {

    @RequestMapping("/systemaccessanalysis")
    public ModelAndView login(){
        ModelAndView model = new ModelAndView();
        model.setViewName("systemaccessanalysis.html");
        return model;
    }

    @RequestMapping("/studentlist")
    public ModelAndView studentlist(){
        ModelAndView model = new ModelAndView();
        model.setViewName("studentlist.html");
        return model;
    }

    @RequestMapping("/studentresults")
    public ModelAndView studentresults(){
        ModelAndView model = new ModelAndView();
        model.setViewName("studentresults.html");
        return model;
    }

}