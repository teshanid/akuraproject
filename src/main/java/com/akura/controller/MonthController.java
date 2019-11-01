package com.akura.controller;


import com.akura.dao.MonthDao;

import com.akura.entity.Month;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MonthController {

    @Autowired
    private MonthDao dao;


    @RequestMapping(value = "/months/list", method = RequestMethod.GET, produces = "application/json")
    public List<Month> day() { return dao.list();
    }
}
