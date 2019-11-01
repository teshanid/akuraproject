package com.akura.controller;

import com.akura.dao.ExamstatusDao;
import com.akura.entity.Examstatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ExamstatusController {

    @Autowired
    private ExamstatusDao dao;

    @RequestMapping(value = "/examstatuses/list", method = RequestMethod.GET, produces = "application/json")
    public List<Examstatus> examstatuses() {
        return dao.list();
    }


}
