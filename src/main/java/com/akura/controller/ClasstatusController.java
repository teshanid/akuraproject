package com.akura.controller;

import com.akura.dao.ClasstatusDao;
import com.akura.entity.Classtatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ClasstatusController {
    @Autowired
    private ClasstatusDao dao;

    @RequestMapping(value = "/classtatuses/list", method = RequestMethod.GET, produces = "application/json")
    public List<Classtatus> classtatus() { return dao.list();
    }

}
