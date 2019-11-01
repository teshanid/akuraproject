package com.akura.controller;

import com.akura.dao.SemesterDao;
import com.akura.entity.Semester;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SemesterController {

    @Autowired
    private SemesterDao dao;


    @RequestMapping(value = "/semesters/list", method = RequestMethod.GET, produces = "application/json")
    public List<Semester> semesters() {
        return dao.list();
    }



}
