package com.akura.controller;

import com.akura.dao.DayDao;
import com.akura.entity.Day;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DayController {

    @Autowired
    private DayDao dao;


    @RequestMapping(value = "/days/list", method = RequestMethod.GET, produces = "application/json")
    public List<Day> day() { return dao.list();
    }
}
