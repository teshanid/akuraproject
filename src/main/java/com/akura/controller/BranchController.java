package com.akura.controller;

import com.akura.dao.BranchDao;
import com.akura.entity.Branch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BranchController {

    @Autowired
    private BranchDao dao;

    @RequestMapping(value = "/branches/list", method = RequestMethod.GET, produces = "application/json")
    public List<Branch> branch() { return dao.list();
    }


}
